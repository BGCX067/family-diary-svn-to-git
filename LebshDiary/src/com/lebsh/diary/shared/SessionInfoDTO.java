package com.lebsh.diary.shared;

import java.io.Serializable;

public class SessionInfoDTO implements  Serializable {

	private String sessionKey;
	private String userName;
	
	public SessionInfoDTO(){
		
	}
	public SessionInfoDTO(String sessionKey, String userName) {
		super();
		this.sessionKey = sessionKey;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String aUserName) {
		userName = aUserName;
	}
	
	public String getSessionKey() {
		return sessionKey;
	}
	
	public void setSessionKey(String aSessionKey) {
		sessionKey = aSessionKey;
	}
}

