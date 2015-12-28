package com.lebsh.diary.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class DiaryEventEditEvent extends GwtEvent<DiaryEventEditHandler> {

	
	public DiaryEventEditEvent(){
	}
	public  static Type<DiaryEventEditHandler> TYPE = new Type<DiaryEventEditHandler>();
	@Override
	protected void dispatch(DiaryEventEditHandler aHandler) {
		aHandler.onEditRequest(this);

	}

	@Override
	public Type<DiaryEventEditHandler> getAssociatedType() {
		return TYPE;
	}

}
