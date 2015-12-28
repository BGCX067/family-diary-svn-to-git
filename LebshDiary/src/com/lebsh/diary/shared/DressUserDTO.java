package com.lebsh.diary.shared;

import java.io.Serializable;

public class DressUserDTO implements Serializable{

	private String usernameName;
	private String password;
	public DressUserDTO(){
		
	}
	public DressUserDTO(String usernameName, String password) {
		super();
		this.usernameName = usernameName;
		this.password = password;
	}
	public String getUsernameName() {
		return usernameName;
	}
	public String getPassword() {
		return password;
	}
	
	
}
