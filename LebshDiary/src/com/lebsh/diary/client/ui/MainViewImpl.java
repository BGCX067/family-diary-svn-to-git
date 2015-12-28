package com.lebsh.diary.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.events.DiaryEventEditEvent;
import com.lebsh.diary.client.place.DiaryEventEditPlace;
import com.lebsh.diary.client.ui.components.EnvLinkLabel;
import com.lebsh.diary.client.ui.components.TitledLabel;

public class MainViewImpl extends Composite implements MainView {
	private static MainViewImplUiBinder uiBinder = GWT.create(MainViewImplUiBinder.class);
	@UiField 
	ScrollPanel root;
	private FlexTable layout = new FlexTable();
	private SimplePanel header = new SimplePanel();
	private SimplePanel center = new SimplePanel();
	private Label footer = new Label(AppController.getClientFactory().getClientProfile().copyrights());
	private MainPageChooser pageChooser;
	
	private MainView.Presenter presenter;
	interface MainViewImplUiBinder extends UiBinder<Widget, MainViewImpl> {
	}

	public MainViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		initHeader();
		layout.setWidget(0, 0, header);
		layout.setWidget(1, 0, center);
		layout.setWidget(2, 0, footer);
		footer.getElement().getStyle().setColor("#777777");
		layout.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		layout.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		layout.getFlexCellFormatter().setAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		center.setSize("100%", "100%");
		root.setWidget(layout);
		header.setWidth("100%");
		layout.setWidth("100%");
	}
	


private void initHeader() {
	FlexTable headerLayout = new FlexTable();
	EnvLinkLabel     newEvent = new EnvLinkLabel(AppController.getClientFactory().getLabelResource().newEvent());
	EnvLinkLabel     editEvent = new EnvLinkLabel(AppController.getClientFactory().getLabelResource().editEvent());
	header.setWidget(headerLayout);
	 pageChooser = new MainPageChooser();
	headerLayout.setWidget(0, 0, pageChooser);
	headerLayout.setWidget(0, 1, createHeaderSeperator());
	headerLayout.setWidget(0, 2, new TitledLabel(AppController.getClientFactory().getClientProfile().mainTitle()));
	headerLayout.setWidget(0, 3, createHeaderSeperator());
	headerLayout.setWidget(0, 4, newEvent);
	headerLayout.setWidget(0, 5, createHeaderSeperator());
	headerLayout.setWidget(0, 6, editEvent);

	newEvent.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			presenter.goTo(new DiaryEventEditPlace(""));
			
		}
	});
	
	editEvent.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			AppController.getClientFactory().getEventBus().fireEvent(new DiaryEventEditEvent());
			
		}
	});
	}


private TitledLabel createHeaderSeperator(){
	return new TitledLabel("  |  ");
}



	@Override
	public void setPresenter(Presenter p) {
		presenter = p;
		pageChooser.setPresenter(presenter);
		
	}

	@Override
	public AcceptsOneWidget getCenterContainer() {
		return center;
	}

	@Override
	public AcceptsOneWidget getTopContainer() {
		return header;
	}

	@Override
	public void resetView() {
		
	}

}
