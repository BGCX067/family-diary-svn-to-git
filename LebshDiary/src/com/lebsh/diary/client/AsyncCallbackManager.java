package com.lebsh.diary.client;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class AsyncCallbackManager  {

	private PopupPanel bussyPanel;
	
	public AsyncCallbackManager(){
		bussyPanel = createBussyPanel();
	}

	public void setBusy(boolean aBusy) {
		bussyPanel.setVisible(aBusy);
	}
	
	public void handleFailure(Throwable caught) {
//		if(caught instanceof ItemAlreadyExistException){
//			ItemAlreadyExistException existEx = (ItemAlreadyExistException)caught;
//			Window.alert(AppController.getClientFactory().getLabelResource().itemAlreadyExist()+":"+existEx.getItemName());
//		}
//		else{
			Window.alert(AppController.getClientFactory().getLabelResource().serverError()+"\n"+caught.getMessage());
//		}
	}
	
	private PopupPanel createBussyPanel(){
		PopupPanel panel = new PopupPanel();
		panel.getElement().getStyle().setBorderWidth(0, Unit.PX);
		panel.setGlassEnabled(true);
		panel.setWidget(new Image(AppController.getClientFactory().getImageResource().getWait()));
		panel.center();
		return panel;
	}
	
	
	
	


}
