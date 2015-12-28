package com.lebsh.diary.client.ui;

import com.lebsh.diary.client.mvp.AppPresenter;
import com.lebsh.diary.client.mvp.View;
import com.lebsh.diary.client.place.DiaryEventEditPlace;
import com.lebsh.diary.shared.DiaryEventDTO;

public interface DiaryEventEditView extends View{

	public void setPresenter(Presenter p);
	public void setEvent(DiaryEventDTO event);
	public interface Presenter extends AppPresenter<DiaryEventEditPlace> {
		public void createEvent(DiaryEventDTO event);
		public void upLoadCompleted();
		public void updateEvent(DiaryEventDTO asDTO);

	}
}
