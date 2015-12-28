package com.lebsh.diary.client;

import com.lebsh.diary.shared.SessionInfoDTO;


public class AppSecurityManager {

	private SessionInfoDTO sessionKey;
	
	public void setUserSession(SessionInfoDTO sessionKey){
		this.sessionKey = sessionKey;
	}
	
	public void invalidateUserSession(){
		sessionKey = null;
	}
	
	public SessionInfoDTO getSessionKey(){
		return sessionKey;
	}
	
	public boolean isUserLoggedIn(){
		return sessionKey != null;
	}
}
