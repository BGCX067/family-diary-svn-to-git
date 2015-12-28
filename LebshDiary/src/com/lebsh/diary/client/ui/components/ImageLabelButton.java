package com.lebsh.diary.client.ui.components;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class ImageLabelButton extends Composite{

	private FlexTable layout = new FlexTable();
	private TitledLabel caption;
	private Image image;
	private SimplePanel rootPanel;
	private static String HOVER_BG_COLOR = "#FFA54F";
	private static String BG_COLOR = "#FF7F24";
	
	private MouseOutHandler imgMouseOut = new MouseOutHandler() {
		@Override
		public void onMouseOut(MouseOutEvent aEvent) {
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "default");
			layout.getElement().getStyle().setBackgroundColor(BG_COLOR);
		}
	};

	private MouseOverHandler imgMouseOver = new MouseOverHandler() {
		@Override
		public void onMouseOver(MouseOverEvent aEvent) {
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "pointer");
			layout.getElement().getStyle().setBackgroundColor(HOVER_BG_COLOR);
		}
	};
	
	private ClickHandler imgClick = new ClickHandler() {
		@Override
		public void onClick(ClickEvent aEvent) {
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "default");
			layout.getElement().getStyle().setBackgroundColor(BG_COLOR);
		}
	};

	public ImageLabelButton(ImageResource imageUrl){
		caption = new TitledLabel();
		image = new  Image(imageUrl);
		 rootPanel = new SimplePanel(image);
		initWidget(rootPanel);
		initHandlers();
		
	}
	
	
	public ImageLabelButton(String title , Object imageUrl){
		if(imageUrl == null){
			layout.setWidget(0, 0, caption = new TitledLabel(title));
		}
		else{
			layout.setWidget(0, 0, caption = new TitledLabel(title));
			layout.setWidget(1, 0, image = getImage(imageUrl));
			layout.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		}
		layout.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
	
		init();
		initHandlers();
	}
	
	public void setHorizontalAlignment(){
		if(image == null){
			return;
		}
		layout.clear();
		layout.setWidget(0, 0, caption);
		layout.setWidget(0, 1, image);
	}
	public void setVerticalAlignment(){
		if(image == null){
			return;
		}
		layout.clear();
		layout.setWidget(0, 0, caption);
		layout.setWidget(1, 0, image);
	}
	
	public void setUrl(String url){
		image.setUrl(url);
	}
	
	public void setResource(ImageResource resource){
		image.setResource(resource);
	}
	
	private Image getImage(Object imageData){
		if(imageData instanceof String)
			return new Image((String)imageData);
		return new Image((ImageResource)imageData);
	}
	
	private void init(){
		rootPanel = new SimplePanel();
		layout.getElement().getStyle().setBackgroundColor(BG_COLOR);
		layout.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		rootPanel.setWidget(layout);
		initWidget(rootPanel);
	}
	
	private void initHandlers(){
	
		caption.addMouseOverHandler(imgMouseOver);
		caption.addMouseOutHandler(imgMouseOut);
		caption.addClickHandler(imgClick);
		if(image == null)
			return;
		image.addMouseOverHandler(imgMouseOver);
		image.addMouseOutHandler(imgMouseOut);
		image.addClickHandler(imgClick);
	}
	
	public void setEnabled(boolean ena){
		
	}
	
	public void addClickHandler(ClickHandler h){
		caption.addClickHandler(h);
		if(image != null)
			image.addClickHandler(h);
	}
}
