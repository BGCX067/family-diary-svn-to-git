package com.lebsh.diary.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.lebsh.diary.client.GreetingService;
import com.lebsh.diary.persist.PMF;
import com.lebsh.diary.server.jdo.DiaryEvent;
import com.lebsh.diary.server.profile.ServerProfile;
import com.lebsh.diary.shared.DiaryEventDTO;
import com.lebsh.diary.shared.EventTranslator;
import com.lebsh.diary.shared.ImageTranslator;
import com.lebsh.diary.shared.VideoTranslator;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

	@Override
	public void createDiaryEvent(DiaryEventDTO newEvent) throws IllegalArgumentException {
		DiaryEvent event = new DiaryEvent();
		event.setContent(newEvent.getContent());
		event.setDate(newEvent.getDate());
		event.setName(newEvent.getName());
		event.setVideoItems(VideoTranslator.dto2entity(newEvent.getVideoItems()));
		event.setImageItems(ImageTranslator.dto2entity(newEvent.getImageItems()));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(event);
		} finally {
			pm.close();
		}
	}
	
	@Override
	public void updateDiaryEvent(DiaryEventDTO updatedEvent) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String query = "select from " + DiaryEvent.class.getName();
			List<DiaryEvent> events = (List<DiaryEvent>) pm.newQuery(query).execute();
		for (DiaryEvent diaryEvent : events) {
			if(diaryEvent.getKey().getId() == updatedEvent.getKey()){
				diaryEvent.setContent(updatedEvent.getContent());
				diaryEvent.setDate(updatedEvent.getDate());
				diaryEvent.setName(updatedEvent.getName());
				diaryEvent.setImageItems(ImageTranslator.dto2entity(updatedEvent.getImageItems()));
				diaryEvent.setVideoItems(VideoTranslator.dto2entity(updatedEvent.getVideoItems()));
				pm.makePersistent(diaryEvent);
				break;
			}
		}
		}finally{
			pm.close();
		}
	}

	@Override
	public List<DiaryEventDTO> getThinEvents(Date from, Date to){
		List<DiaryEventDTO> ret = new ArrayList<DiaryEventDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		String query = "select from " + DiaryEvent.class.getName();
		List<DiaryEvent> events = (List<DiaryEvent>) pm.newQuery(query).execute();
		for (DiaryEvent diaryEvent : events) {
			ret.add(EventTranslator.entity2ThinDTO(diaryEvent));
		}
		Collections.sort(ret);//sort by date
		return ret;
		}finally{
			pm.close();
		}
	}
	@Override
	public List<DiaryEventDTO> getThinEvents(String filter) {
		List<DiaryEventDTO> ret = new ArrayList<DiaryEventDTO>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			String query = "select from " + DiaryEvent.class.getName();
			List<DiaryEvent> events = (List<DiaryEvent>) pm.newQuery(query).execute();
		for (DiaryEvent diaryEvent : events) {
			if(diaryEvent.getName().contains(filter) || diaryEvent.getContent().contains(filter)){
				ret.add(EventTranslator.entity2ThinDTO(diaryEvent));
			}
		}
		Collections.sort(ret);//sort by date
		return ret;
		}finally{
			pm.close();
		}
	}

	@Override
	public DiaryEventDTO getEventDetail(long eventKey)  throws IllegalArgumentException{
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		String query = "select from " + DiaryEvent.class.getName();
		List<DiaryEvent> events = (List<DiaryEvent>) pm.newQuery(query).execute();
		for (DiaryEvent diaryEvent : events) {
			if(diaryEvent.getKey().getId() == eventKey){
				return EventTranslator.entity2ThikDTO(diaryEvent);
			}
		}
		throw new IllegalArgumentException("event not found - id="+eventKey);
		}finally{
			pm.close();
		}
	}

	@Override
	public boolean authenticateManager(String password) {
		return password.equals(ServerProfile.getPassword());
	}

	

}
