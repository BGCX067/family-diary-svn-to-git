package com.lebsh.diary.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.lebsh.diary.client.html.AppHTMLResource;
import com.lebsh.diary.client.i18n.Labels;
import com.lebsh.diary.client.images.AppImageResource;
import com.lebsh.diary.client.mvp.AppActivityMapper;
import com.lebsh.diary.client.profile.ClientProfile;
import com.lebsh.diary.shared.DressUserDTO;

public class AppClientFactoryImpl implements AppClientFactory{

	private GreetingServiceAsync greetingService;
	private static final SimpleEventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private ActivityManager activityManager;
	private AppSecurityManager securityManager = new AppSecurityManager();
	private ViewFactory viewFactory;
	private AppActivityMapper activityMapper;
	private SimplePanel appWidget ;
	private Labels labelResource;
	private AsyncCallbackManager asyncCallbackManager;
	private AppImageResource imageResource;
	private AppHTMLResource htmlResource;
	private DressUserDTO manager;
	private ClientProfile clientProfile;
	
	@Override
	public AppImageResource getImageResource() {
		if(imageResource == null){
			imageResource = GWT.create(AppImageResource.class);
		}
		return imageResource;
	}
	
	@Override
	public AppHTMLResource getHTMLResource() {
		if(htmlResource == null){
			htmlResource = GWT.create(AppHTMLResource.class);
		}
		return htmlResource;
	}
	
	public AppClientFactoryImpl(){
		activityManager = new ActivityManager(getAppActivityMapper(), eventBus);
	}
	@Override
	public GreetingServiceAsync getService() {
		if(greetingService == null){
			greetingService = GWT.create(GreetingService.class);
			
		}
		return greetingService;
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public ActivityManager getActivityManager() {
		return activityManager;
	}

	@Override
	public AppSecurityManager getSecurityManager() {
		return securityManager;
	}

	@Override
	public ViewFactory getViewFactory() {
		if(viewFactory == null)
			viewFactory = GWT.create(ViewFactory.class);
		return viewFactory;
	}

	@Override
	public AppActivityMapper getAppActivityMapper() {
		if(activityMapper == null){
			activityMapper = new AppActivityMapper();
		}
		return activityMapper;
	}

	@Override
	public SimplePanel getBodyConteiner() {
		if(appWidget == null){
			appWidget = new SimplePanel();
		}
		return appWidget;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Labels getLabelResource() {
		if(labelResource == null)
			labelResource = GWT.create(Labels.class);
		return labelResource;
	}

	@Override
	public DressUserDTO getManager() {
		if(manager == null)
			manager = new DressUserDTO("efratl", "efratl");
		return manager;
	}

	@Override
	public AsyncCallbackManager getAsyncCallbackManager() {
		if(asyncCallbackManager == null){
			asyncCallbackManager = new AsyncCallbackManager();
		}
		return asyncCallbackManager;
	}

	@Override
	public ClientProfile getClientProfile() {
		if(clientProfile == null){
			clientProfile = ClientProfile.generateProfile();
		}
		return clientProfile;
	}

	

}
