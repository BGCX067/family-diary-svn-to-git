package com.lebsh.diary.client.ui.components;

import com.google.gwt.user.client.ui.Label;

public class TitledLabel extends Label {
//	
	public TitledLabel(){
		setStyleName("titleStyle");
	}
	
	public TitledLabel(String text){
		super(text);
		setStyleName("titleStyle");
	}
}
