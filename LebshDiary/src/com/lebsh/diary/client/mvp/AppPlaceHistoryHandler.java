package com.lebsh.diary.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class AppPlaceHistoryHandler extends PlaceHistoryHandler {

	public AppPlaceHistoryHandler(PlaceHistoryMapper aMapper) {
		super(aMapper);
	}
	
	@Override
	public void handleCurrentHistory() {
		super.handleCurrentHistory();
	}

}
