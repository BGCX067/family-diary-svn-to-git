package com.lebsh.diary.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.lebsh.diary.client.place.DiaryEventEditPlace;
import com.lebsh.diary.client.place.MainPlace;

@WithTokenizers( { 
	MainPlace.Tokenizer.class,
	DiaryEventEditPlace.Tokenizer.class	
	})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
