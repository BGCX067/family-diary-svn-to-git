package com.lebsh.diary.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.lebsh.diary.persist.PMF;
import com.lebsh.diary.server.jdo.DiaryEvent;
import com.lebsh.diary.server.jdo.ImageItem;

public class FileUploadService extends HttpServlet implements ServletConstants {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	private ImagesService imagesService = ImagesServiceFactory.getImagesService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long key = Long.parseLong( req.getParameter(DIARY_EVENT_KEY));
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(req);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		String query = "select from " + DiaryEvent.class.getName();
		List<DiaryEvent> events = (List<DiaryEvent>) pm.newQuery(query).execute();
		for (DiaryEvent diaryEvent : events) {
			if(diaryEvent.getKey().getId() == key){
				diaryEvent.setImageItems( getEventImages(blobs , diaryEvent));
				pm.makePersistent(diaryEvent);
			}
		}
		}finally{
			pm.close();
		}
	}
	private List<ImageItem> getEventImages(Map<String, BlobKey> blobs,DiaryEvent diaryEvent) {
		List<ImageItem> imageItems = new ArrayList<ImageItem>();
		for(String imageTitle: blobs.keySet()){
			BlobKey fileKey = blobs.get(imageTitle);
			ImageItem image = new ImageItem();
			image.setName(imageTitle);
			image.setFileKey(fileKey);
			image.setDefaultServingUrl(imagesService.getServingUrl(fileKey,100,false));
			imageItems.add(image);
		}
		return imageItems;
	}
}
