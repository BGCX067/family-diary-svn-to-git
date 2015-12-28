package com.lebsh.diary.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.lebsh.diary.shared.DiaryEventDTO;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	void createDiaryEvent(DiaryEventDTO newEvent) throws IllegalArgumentException;
	List<DiaryEventDTO> getThinEvents(Date from , Date to);
	List<DiaryEventDTO> getThinEvents(String filter);
	DiaryEventDTO getEventDetail(long eventKey) throws IllegalArgumentException;
	boolean authenticateManager(String password);
	void updateDiaryEvent(DiaryEventDTO newEvent);
}
