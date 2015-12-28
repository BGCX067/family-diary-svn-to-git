package com.lebsh.diary.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lebsh.diary.client.AppController;

public abstract class AppAsyncCallback<T> implements AsyncCallback<T> {
	
	public AppAsyncCallback(){
		super();
		AppController.getClientFactory().getAsyncCallbackManager().setBusy(true);
		
	}
	@Override
	public void onFailure(Throwable caught) {
		AppController.getClientFactory().getAsyncCallbackManager().setBusy(false);
		AppController.getClientFactory().getAsyncCallbackManager().handleFailure(caught);
	}
	
	
		
	public void onSuccess(T result) {
		AppController.getClientFactory().getAsyncCallbackManager().setBusy(false);
		onInnerSuccess(result);
	}
	
	public abstract void onInnerSuccess(T aResult);

}
