package com.lebsh.diary.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.ui.components.ImageLabelButton;

public class MainPageChooser extends Composite {

	private MainView.Presenter mainPresenter;
	private ImageLabelButton mainButton = new ImageLabelButton(AppController.getClientFactory().getClientProfile().logoImage());
	private ImageLabelButton diaryButton = new ImageLabelButton(AppController.getClientFactory().getImageResource().diaryBanner());
	private ImageLabelButton imagesButton = new ImageLabelButton(AppController.getClientFactory().getImageResource().diaryBanner());
	private ImageLabelButton videosButton = new ImageLabelButton(AppController.getClientFactory().getImageResource().diaryBanner());
	private DecoratedPopupPanel chooserPopup = new DecoratedPopupPanel(true);
	private FlexTable chooserLayout = new FlexTable();
	private ClickHandler chooserPopupHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			mainPresenter.goToDiary();
//			chooserPopup.showRelativeTo(mainButton);
			
		}
	};
	
	public MainPageChooser(){
	
		initWidget(mainButton);
		initView();
		initActions();
	}
	
	public void setPresenter(MainView.Presenter presenter) {
		this.mainPresenter = presenter;
		
	}
	

	private void initView() {
		chooserLayout.setWidget(0, 0, diaryButton);
		chooserLayout.setWidget(1, 0, imagesButton);
		chooserLayout.setWidget(2, 0, videosButton);
		chooserPopup.setWidget(chooserLayout);
		
	}

	private void initActions() {
		mainButton.addClickHandler(chooserPopupHandler );
		diaryButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				chooserPopup.hide();
				mainPresenter.goToDiary();
				
			}
		});
		imagesButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				chooserPopup.hide();
				mainPresenter.gotoPhotos();
				
			}
		});
		videosButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				chooserPopup.hide();
				mainPresenter.gotoVideos();
				
			}
		});
		
	}



}
