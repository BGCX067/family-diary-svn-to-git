package com.lebsh.diary.client.oracle;

import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.shared.DiaryEventDTO;

public class EventSuggestOracle extends EnvSuggestOracle<DiaryEventDTO> {

	private EventSelectionHandler selectionHandler;
	public EventSuggestOracle() {
		super(false);
		
		setResultRenderer(new ResultRenderer<DiaryEventDTO>() {

			@Override
			public String getDisplayString(DiaryEventDTO aResult) {
				
				return DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(aResult.getDate())+","+aResult.getName();
			}

			@Override
			public String getReplacementString(DiaryEventDTO aResult) {
				if(selectionHandler != null){
					selectionHandler.eventSelected(aResult);
				}
				return DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(aResult.getDate())+","+aResult.getName();
			}
		});
	}

	@Override
	public void requestSuggestions(final Request request, final Callback callback) {
		AppController.getClientFactory().getService().getThinEvents(request.getQuery(), new AsyncCallback<List<DiaryEventDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onSuccess(List<DiaryEventDTO> result) {
				callback.onSuggestionsReady(request, generateResponse(result));
				
			}
		});
		
	}
	
	public void setEventSelectionHandler(EventSelectionHandler handler){
		selectionHandler = handler;
	}
	
	public interface EventSelectionHandler{
		public void eventSelected(DiaryEventDTO event);
	}

}
