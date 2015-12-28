package com.lebsh.diary.server.profile;

import com.lebsh.diary.shared.UserProfileEnum;

public abstract class ServerProfile {
	private static ServerProfile instance;
	protected abstract String innerGetPassword();
	public static String getPassword(){
		if(instance == null){
			instance = getServerProfile();
		}
		return instance.innerGetPassword();
	}
	private static ServerProfile getServerProfile(){
		if(UserProfileEnum.getProfile() == UserProfileEnum.LEBSHORS){
			return new LebshServerProfile();
		}
		if(UserProfileEnum.getProfile() == UserProfileEnum.SARIT){
			return new SaritServerProfile();
		}
		if(UserProfileEnum.getProfile() == UserProfileEnum.GOLAN){
			return new GolanServerProfile();
		}
		throw new UnsupportedOperationException("User Profile not exists");
 
	}
}
