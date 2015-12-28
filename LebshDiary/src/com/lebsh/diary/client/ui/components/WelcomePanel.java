package com.lebsh.diary.client.ui.components;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.lebsh.diary.client.AppController;

public class WelcomePanel extends Composite {
	private FlexTable rootLayout = new FlexTable();
	private BounchingPointerPanel eventListPointerPanel = new BounchingPointerPanel();
	public WelcomePanel(){
		Label welcomLabel = new Label(AppController.getClientFactory().getClientProfile().welcomeMessage());
		Label instructionabel = new Label(AppController.getClientFactory().getLabelResource().instruction());
		welcomLabel.setStyleName("titleStyle");
		instructionabel.setStyleName("bigTitleStyle");
		rootLayout.setWidget(0, 0, welcomLabel);
		rootLayout.setWidget(1, 0, instructionabel);
		rootLayout.setWidget(2, 0,eventListPointerPanel);
		rootLayout.setWidget(3, 0, new Image(AppController.getClientFactory().getClientProfile().welcomImage()));
		rootLayout.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_BOTTOM);
		rootLayout.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
		rootLayout.getFlexCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.getFlexCellFormatter().setAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
//		rootLayout.getFlexCellFormatter().setColSpan(0, 0, 2);
//		rootLayout.getFlexCellFormatter().setColSpan(1, 0, 2);
		initWidget(rootLayout);
		setSize("100%", "100%");
	}
	public void ready() {
		eventListPointerPanel.start();
		
	}

}
