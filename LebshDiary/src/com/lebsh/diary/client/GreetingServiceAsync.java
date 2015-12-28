package com.lebsh.diary.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lebsh.diary.shared.DiaryEventDTO;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void createDiaryEvent(DiaryEventDTO newEvent,AsyncCallback<Void> callback) throws IllegalArgumentException;
	void updateDiaryEvent(DiaryEventDTO newEvent,AsyncCallback<Void> callback) throws IllegalArgumentException;
	void getThinEvents(Date from, Date to,AsyncCallback<List<DiaryEventDTO>> callback);
	void getThinEvents(String filter,AsyncCallback<List<DiaryEventDTO>> callback);
	void getEventDetail(long eventKey, AsyncCallback<DiaryEventDTO> callback) throws IllegalArgumentException;
	void authenticateManager(String password, AsyncCallback<Boolean> callback);
}
