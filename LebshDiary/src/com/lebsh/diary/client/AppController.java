package com.lebsh.diary.client;

import com.google.gwt.core.client.GWT;

public class AppController {


	private static AppController instance;
	private AppClientFactory clientFactory;

	private AppController(){
		  clientFactory = GWT.create(AppClientFactory.class);
	}
	private static AppController getInstance(){
		if(instance == null){
			synchronized(AppController.class){
				if(instance == null){
					instance = new AppController();
				}
			}
		}
		return instance;
	}
	
	public static AppClientFactory getClientFactory(){
		return getInstance().clientFactory;
	}
}
