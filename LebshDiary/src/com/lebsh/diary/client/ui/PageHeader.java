package com.lebsh.diary.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.lebsh.diary.client.ui.components.ImageSizeAjuster;

public class PageHeader extends Composite {

	private FlexTable rootLayout = new FlexTable();
	public PageHeader(String pageTitle , BarValueChangedHandler action){
		Label title = new Label(pageTitle);
		title.getElement().getStyle().setFontSize(36, Unit.PX);
		title.getElement().getStyle().setColor("#878787");
		
		rootLayout.setWidget(0, 0, title);
		rootLayout.setWidget(0, 1,  new ImageSizeAjuster(action));
		rootLayout.setWidth("100%");
		rootLayout.getElement().getStyle().setBackgroundColor("#FFDAB9");
		initWidget(rootLayout);
		
	}
}
