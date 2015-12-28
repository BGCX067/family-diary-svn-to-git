package com.lebsh.diary.client.activity;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.events.DiaryEventEditEvent;
import com.lebsh.diary.client.events.DiaryEventEditHandler;
import com.lebsh.diary.client.place.DiaryEventEditPlace;
import com.lebsh.diary.client.place.DiaryEventsPlace;
import com.lebsh.diary.client.rpc.AppAsyncCallback;
import com.lebsh.diary.client.ui.DiaryEventsView;
import com.lebsh.diary.client.ui.DiaryEventsView.Presenter;
import com.lebsh.diary.shared.DiaryEventDTO;

public class DiaryEventsActivity extends AppActivityContainer implements Presenter {

	public DiaryEventsActivity(DiaryEventsPlace place) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DiaryEventsPlace getPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void goTo(Place aPlace) {
		AppController.getClientFactory().getPlaceController().goTo(aPlace);

	}

	

	@Override
	public void newEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void editEvent(DiaryEventDTO event) {
		// TODO Auto-generated method stub

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
		aPanel.setWidget(getView().asWidget());

	}

	private DiaryEventsView getView() {
		return AppController.getClientFactory().getViewFactory().getDiaryEventsView();
	}

	@Override
	public void attachHandlers() {
		AppController.getClientFactory().getEventBus().addHandler(DiaryEventEditEvent.TYPE, new DiaryEventEditHandler() {
			
			@Override
			public void onEditRequest(DiaryEventEditEvent evt) {
				DiaryEventEditPlace place = new DiaryEventEditPlace("");
				DiaryEventDTO selectedEvent = getView().getSelectedEvent();
				if(selectedEvent != null){
					place.setEventToEdit(selectedEvent);
					goTo(place);
				}
			
			}
		});

	}
	
	@Override
	public void getEvent(DiaryEventDTO event) {
		AppController.getClientFactory().getService().getEventDetail(event.getKey(), new AppAsyncCallback<DiaryEventDTO>() {

			@Override
			public void onInnerSuccess(DiaryEventDTO aResult) {
				getView().displaySelectedEvent(aResult);
				
			}
		});

	}

	@Override
	public void refresh(Object userData) {
		AppController.getClientFactory().getService().getThinEvents(null, null, new AppAsyncCallback<List<DiaryEventDTO>>() {

			@Override
			public void onInnerSuccess(List<DiaryEventDTO> aResult) {
			getView().populateEvents(aResult);
				
			}
		});

	}

	@Override
	public boolean isRestricted() {
		// TODO Auto-generated method stub
		return false;
	}

}
