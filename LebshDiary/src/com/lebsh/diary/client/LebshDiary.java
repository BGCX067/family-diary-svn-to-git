package com.lebsh.diary.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;
import com.lebsh.diary.client.mvp.AppPlaceHistoryMapper;
import com.lebsh.diary.client.place.MainPlace;
import com.lebsh.diary.client.ui.components.BounchingPointerPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LebshDiary implements EntryPoint {
	private Place defaultPlace = new MainPlace("main");
	private PlaceHistoryHandler historyHandler;
	public void onModuleLoad() {
//		test();
		initAppFramework();
		initApp();
	}
	
	private void initAppFramework(){
		SimplePanel appcontainer = AppController.getClientFactory().getBodyConteiner();
		EventBus eventBus = AppController.getClientFactory().getEventBus();
		PlaceController placeController = AppController.getClientFactory().getPlaceController();

		// Start ActivityManager for the main widget with our ActivityMapper
		AppController.getClientFactory().getActivityManager().setDisplay(appcontainer);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);
		RootLayoutPanel.get().add(appcontainer);
	}
	
	private void initApp(){
		historyHandler.handleCurrentHistory();
	}
	
	
	
	
	
	private void test(){
		
//		RootPanel.get("nameFieldContainer").add(new EnvFileUpload());
		RootPanel.get().add(new BounchingPointerPanel());
//		RootPanel.get("nameFieldContainer").add(new EnvFileDisplay());
	}
}
