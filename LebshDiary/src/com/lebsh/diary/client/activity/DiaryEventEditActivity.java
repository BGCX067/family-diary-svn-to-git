package com.lebsh.diary.client.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.place.DiaryEventEditPlace;
import com.lebsh.diary.client.place.DiaryEventsPlace;
import com.lebsh.diary.client.rpc.AppAsyncCallback;
import com.lebsh.diary.client.ui.DiaryEventEditView;
import com.lebsh.diary.shared.DiaryEventDTO;

public class DiaryEventEditActivity extends AppActivityContainer implements DiaryEventEditView.Presenter {
	public DiaryEventEditActivity(DiaryEventEditPlace place) {
		myPlace = place;
	}

	@Override
	public DiaryEventEditPlace getPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void goTo(Place aPlace) {
		AppController.getClientFactory().getPlaceController().goTo(aPlace);

	}

	@Override
	public void innerSave() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void load(AcceptsOneWidget aPanel) {
		getView().setPresenter(this);
		getView().resetView();
		aPanel.setWidget(getView().asWidget());
		DiaryEventEditPlace diaryEventEditPlace = (DiaryEventEditPlace)myPlace;
		if(diaryEventEditPlace.getEventToEdit() != null){
			getView().setEvent(diaryEventEditPlace.getEventToEdit());
		}

	}

	@Override
	public void attachHandlers() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Object userData) {
		// TODO Auto-generated method stub

	}
	
	private DiaryEventEditView getView(){
		return AppController.getClientFactory().getViewFactory().getDiaryEventEditView();
	}

	@Override
	public boolean isRestricted() {
		return true;
	}

	@Override
	public void createEvent(DiaryEventDTO event) {
		AppController.getClientFactory().getService().createDiaryEvent(event, new AppAsyncCallback<Void>() {

			@Override
			public void onInnerSuccess(Void v) {
				upLoadCompleted();
			}
		});
		
	}
	
	@Override
	public void updateEvent(DiaryEventDTO event) {
		AppController.getClientFactory().getService().updateDiaryEvent(event, new AppAsyncCallback<Void>() {

			@Override
			public void onInnerSuccess(Void aResult) {
				upLoadCompleted();
				
			}
		});
		
	}

	@Override
	public void upLoadCompleted() {
		goTo(new DiaryEventsPlace(""));
		
	}

	

}
