package com.lebsh.diary.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.lebsh.diary.client.html.AppHTMLResource;
import com.lebsh.diary.client.i18n.Labels;
import com.lebsh.diary.client.images.AppImageResource;
import com.lebsh.diary.client.mvp.AppActivityMapper;
import com.lebsh.diary.client.profile.ClientProfile;
import com.lebsh.diary.shared.DressUserDTO;

public interface AppClientFactory {

	public AppImageResource getImageResource();
	public AppHTMLResource getHTMLResource();
	public  GreetingServiceAsync getService();
	public EventBus getEventBus();
	public PlaceController getPlaceController();
	public ActivityManager getActivityManager();
	public AppSecurityManager getSecurityManager();
	public ViewFactory getViewFactory();
	public AppActivityMapper getAppActivityMapper();
	public SimplePanel getBodyConteiner();
	public Labels getLabelResource();
	public AsyncCallbackManager getAsyncCallbackManager();
	public DressUserDTO getManager();
	public void logout();
	public ClientProfile getClientProfile();
}
