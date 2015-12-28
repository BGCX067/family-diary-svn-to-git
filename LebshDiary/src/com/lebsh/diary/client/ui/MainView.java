package com.lebsh.diary.client.ui;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.lebsh.diary.client.mvp.AppPresenter;
import com.lebsh.diary.client.mvp.View;
import com.lebsh.diary.client.place.MainPlace;

public interface MainView extends View{

	public void setPresenter(Presenter p);
	public AcceptsOneWidget getCenterContainer();
	public AcceptsOneWidget getTopContainer();
	
	public interface Presenter extends AppPresenter<MainPlace> {
		public void goToDiary();
		public void gotoPhotos();
		public void gotoVideos();

		
	}
}
