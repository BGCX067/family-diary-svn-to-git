package com.lebsh.diary.client;

import com.lebsh.diary.client.ui.DiaryEventEditView;
import com.lebsh.diary.client.ui.DiaryEventsView;
import com.lebsh.diary.client.ui.MainView;


public interface ViewFactory {
	public MainView getMainView();
	public DiaryEventEditView getDiaryEventEditView();
	public DiaryEventsView getDiaryEventsView();

}
