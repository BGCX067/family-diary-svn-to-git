package com.lebsh.diary.client.ui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

public class EnvLinkLabel extends Composite implements ClickHandler {

	public static String HIGHLIGHT_BGCOLOR = "#96CDCD";
	public static String DISABLE_COLOR = "#878787";
	public static String ENABLE_COLOR = "#000000";
	public static String SUCCESS_COLOR = "#FFF68F";
	private SimplePanel layout = new SimplePanel();
	private Label label;
	private Label link;
	
	public EnvLinkLabel(){
		link = new Label();
		label = new Label();
		layout.setWidget(link);
		initWidget(layout);
		link.setStyleName("linkLabel");
//		setStylePrimaryName("linkLabel");
	}
	
	public EnvLinkLabel(String title){
		this();
		setTitle(title);
	}
	
	public void setTitle(String title){
		link.setText(title);
		label.setText(title);
	}
	
	@Override
	public void onClick(ClickEvent aEvent) {
		link.fireEvent(aEvent);
	}
	
	public void addClickHandler(ClickHandler handler) {
		link.addClickHandler(handler);
	}
	
	public void asLabel(boolean enableStyle){
		label.getElement().getStyle().setColor(enableStyle?ENABLE_COLOR:DISABLE_COLOR);
		layout.setWidget(label);
	}
	
	public void asLink(){
		layout.setWidget(link);
	}
}
