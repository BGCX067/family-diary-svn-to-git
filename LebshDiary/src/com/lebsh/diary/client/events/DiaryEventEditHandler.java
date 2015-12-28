package com.lebsh.diary.client.events;

import com.google.gwt.event.shared.EventHandler;


public interface DiaryEventEditHandler extends EventHandler {
	public void onEditRequest(DiaryEventEditEvent evt);
}
