package com.lebsh.diary.client.ui;

import java.util.List;

import com.lebsh.diary.client.mvp.AppPresenter;
import com.lebsh.diary.client.mvp.View;
import com.lebsh.diary.client.place.DiaryEventsPlace;
import com.lebsh.diary.shared.DiaryEventDTO;

public interface DiaryEventsView extends View{

	public void populateEvents(List<DiaryEventDTO> events);
	public void displaySelectedEvent(DiaryEventDTO event);
	public void setPresenter(Presenter p);
	public DiaryEventDTO getSelectedEvent();
	public interface Presenter extends AppPresenter<DiaryEventsPlace> {
		public void getEvent(DiaryEventDTO event);
		public void newEvent();
		public void editEvent(DiaryEventDTO event);
	}
}
