package com.lebsh.diary.client.ui.components;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.rpc.AppAsyncCallback;
import com.lebsh.diary.shared.SessionInfoDTO;

public class AuthenticationDialog extends DecoratedPopupPanel {

	private AuthenticationResultHandler loginResultHandler;
	private PasswordTextBox passwordField = new PasswordTextBox();
	private Label authenticateError = new Label(AppController.getClientFactory().getLabelResource().authenticationFailure());
	private AuthenticationPanel authenticationPanel = new AuthenticationPanel();
	
	public AuthenticationDialog(AuthenticationResultHandler loginResultHandler){
		this.loginResultHandler = loginResultHandler;
		setWidget(authenticationPanel);
	}
	
	private class AuthenticationPanel extends Composite{
		private FlexTable mainLayout = new FlexTable();
		private Label paswordLabel = new Label(AppController.getClientFactory().getLabelResource().password());
		private Button ok = new Button(AppController.getClientFactory().getLabelResource().ok());
		private Button cancel = new Button(AppController.getClientFactory().getLabelResource().cancel());
		private AuthenticationPanel(){
			mainLayout.getElement().getStyle().setBackgroundColor("#FFDAB9");
			mainLayout.getElement().getStyle().setBorderWidth(0, Unit.CM);
			mainLayout.setWidget(0, 0, paswordLabel);
			mainLayout.setWidget(0, 1, passwordField);
			mainLayout.setWidget(1, 0, ok);
			mainLayout.setWidget(1, 1, cancel);
			mainLayout.setWidget(2, 0, authenticateError);
			mainLayout.getFlexCellFormatter().setColSpan(2, 0, 2);
			authenticateError.setVisible(false);
			authenticateError.getElement().getStyle().setFontWeight(FontWeight.BOLD);
			authenticateError.getElement().getStyle().setColor("#FF0000");
			initWidget(mainLayout);
			initActions();
			setStyleName("dir");
		}
		private void initActions() {
			cancel.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					loginResultHandler.onResult(false);
					hide();
					
				}
			});
			ok.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					authenticateManager();
					
				}

				
			});
			
		}
	}
	
	private void authenticateManager() {
		authenticateError.setVisible(false);
		AppController.getClientFactory().getService().authenticateManager(passwordField.getText(), new AppAsyncCallback<Boolean>() {

			@Override
			public void onInnerSuccess(Boolean success) {
				if(success){
					loginResultHandler.onResult(true);
					hide();
				}
				else{
					authenticateError.setVisible(true);
				}
				
			}
		});
		
	}
	public interface AuthenticationResultHandler{
		public void onResult(boolean success);
	}
}
