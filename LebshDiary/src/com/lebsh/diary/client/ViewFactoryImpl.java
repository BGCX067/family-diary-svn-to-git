package com.lebsh.diary.client;

import com.lebsh.diary.client.ui.DiaryEventEditView;
import com.lebsh.diary.client.ui.DiaryEventEditViewImpl;
import com.lebsh.diary.client.ui.DiaryEventsView;
import com.lebsh.diary.client.ui.DiaryEventsViewImpl;
import com.lebsh.diary.client.ui.MainView;
import com.lebsh.diary.client.ui.MainViewImpl;


public class ViewFactoryImpl implements ViewFactory{

	private MainView mainView;
	private DiaryEventEditView diaryEventEditView;
	private DiaryEventsView diaryEventsView;

	@Override
	public MainView getMainView() {
		if(mainView == null)
			mainView = new MainViewImpl();
			return mainView;
	}
	
	@Override
	public DiaryEventEditView getDiaryEventEditView() {
		if(diaryEventEditView == null)
			diaryEventEditView = new DiaryEventEditViewImpl();
		return diaryEventEditView;
	}

	@Override
	public DiaryEventsView getDiaryEventsView() {
		if(diaryEventsView == null)
			diaryEventsView = new DiaryEventsViewImpl();
		return diaryEventsView;
	}


}
