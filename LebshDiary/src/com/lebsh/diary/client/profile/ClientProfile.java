package com.lebsh.diary.client.profile;

import com.google.gwt.resources.client.ImageResource;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.shared.UserProfileEnum;

public abstract class ClientProfile {

	public abstract ImageResource welcomImage();
	public abstract ImageResource logoImage();
	public abstract String welcomeMessage();
	public abstract String mainTitle();
	public  String copyrights(){
		return AppController.getClientFactory().getLabelResource().generalCopyrights();
	}
	
	
	public static ClientProfile generateProfile() {
		if(UserProfileEnum.getProfile() == UserProfileEnum.LEBSHORS){
			return new LebshClientProfile();
		}
		else if(UserProfileEnum.getProfile() == UserProfileEnum.SARIT){
			return new SaritClientProfile();
		}
		else if(UserProfileEnum.getProfile() == UserProfileEnum.GOLAN){
			return new GolanClientProfile();
		}
		throw new UnsupportedOperationException("User Profile not exists");
	}
	
	
	
}
