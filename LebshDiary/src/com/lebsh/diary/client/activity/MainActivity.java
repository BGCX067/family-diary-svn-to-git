package com.lebsh.diary.client.activity;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.place.DiaryEventsPlace;
import com.lebsh.diary.client.place.MainPlace;
import com.lebsh.diary.client.ui.MainView;

public class MainActivity extends AppActivityContainer implements MainView.Presenter{

	private MainPlace place;
	
	public MainActivity(MainPlace place){
		this.place = place;
	}
	@Override
	public void innerSave() {
		// TODO Auto-generated method stub

	}
//	
//	@Override
//	public String mayStop() {
//		return AppController.getClientFactory().getLabelResource().aboutToExit();
//	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void load(AcceptsOneWidget aPanel) {
		getView().setPresenter(this);
		AppController.getClientFactory().getBodyConteiner().setWidget(getView());
		AppController.getClientFactory().getActivityManager().setDisplay(getView().getCenterContainer());
		

	}

	@Override
	public void attachHandlers() {/*

		AppController.getClientFactory().getEventBus().addHandler(ItemToBasketEvent.TYPE, new ItemToBasketHandler() {

			@Override
			public void onItemAdded(ItemToBasketEvent evt) {
				AppController.getClientFactory().getMyBasket().addToBasket(evt.getDressItem(), evt.getGroup(), evt.getDressCollection());

			}
		});

	
	*/}

	@Override
	public void refresh(Object userData) {
		goToDiary();

	}

	@Override
	public MainPlace getPlace() {
		return place;
	}

	@Override
	public void goTo(Place aPlace) {
		AppController.getClientFactory().getPlaceController().goTo(aPlace);
		
	}
	
	@Override
	public void goToDiary() {
//		goTo(new DiaryEventEditPlace(""));
		goTo(new DiaryEventsPlace(""));
		
	}
	
	
	private MainView getView(){
		return AppController.getClientFactory().getViewFactory().getMainView();
	}
	@Override
	public boolean isRestricted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void gotoPhotos() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void gotoVideos() {
		// TODO Auto-generated method stub
		
	}
	

}
