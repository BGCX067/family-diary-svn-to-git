package com.lebsh.diary.client.activity;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.place.MainPlace;
import com.lebsh.diary.client.ui.components.AuthenticationDialog;
import com.lebsh.diary.client.ui.components.AuthenticationDialog.AuthenticationResultHandler;

public abstract class AppActivityContainer implements Activity {
	protected Place myPlace;
	private boolean firstTime = true;
	protected boolean active;
	public AppActivityContainer() {
	
	}
	
	public void setPlace(Place place){
		myPlace = place;
	}

	


	@Override
	public String mayStop() {
		return null;
	}

	@Override
	public void onCancel() {
		active = false;
	}

	@Override
	public void onStop() {
		active = false;
	}

	public final boolean save() {
		if (isValid()) {
			innerSave();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * save to server
	 */
	public abstract void innerSave();

	/**
	 * validate the presenter
	 */
	public abstract boolean isValid();

	@Override
	public final void start(AcceptsOneWidget aPanel, EventBus aEventBus) {
		if(isRestricted() ){
			authenticateAndContinue(aPanel);
			return;
		}
		else{
			activate(aPanel);
		}
	}




	private void authenticateAndContinue(final AcceptsOneWidget aPanel) {
		AuthenticationDialog authenticationDialog = new AuthenticationDialog(new AuthenticationResultHandler() {
			
			@Override
			public void onResult(boolean success) {
				if(success){
					activate(aPanel);
				}
				else{
					AppController.getClientFactory().getActivityManager().setDisplay(AppController.getClientFactory().getBodyConteiner());
					AppController.getClientFactory().getPlaceController().goTo(new MainPlace(""));
				}
				
			}
		});
		authenticationDialog.center();
		
		
	}




	private void activate(AcceptsOneWidget aPanel) {
		load(aPanel);
		active = true;
		if (firstTime) {
			attachHandlers();
			firstTime = false;
		}
		refresh(null);
	}
	
	
	
	private boolean isAdmin(){
		return AppController.getClientFactory().getSecurityManager().isUserLoggedIn();
	}
	/**
	 * load page view
	 * 
	 * @param aPanel
	 */
	public abstract void load(AcceptsOneWidget aPanel);

	/**
	 * attach desired handlers to event bus events
	 */
	public abstract void attachHandlers();

	/**
	 * refresh/load data from server
	 * 
	 * @param userData
	 */
	public abstract void refresh(Object userData);
	public abstract boolean isRestricted();
}
