package com.lebsh.diary.client.oracle;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.SuggestOracle;

public abstract class EnvSuggestOracle<T> extends SuggestOracle {
	public interface ResultRenderer<T> {
	    /**
	     * Gets the display string associated with the result. The
	     * interpretation of the display string depends upon the value of {@link #isDisplayStringHTML()}.
	     * 
	     * @return the display string for this result
	     */
	    String getDisplayString(T aResult);

	    /**
	     * Gets the replacement string associated with this suggestion. When this
	     * suggestion is selected, the replacement string will be entered into the
	     * SuggestBox's text box.
	     * 
	     * @return the string to be entered into the SuggestBox's text box when this
	     *         suggestion is selected
	     */
	    String getReplacementString(T aResult);
	}
	
	static public class EnvSuggestion<T> implements SuggestOracle.Suggestion {
		private T myData;
		private ResultRenderer<? super T> myResultRenderer;
		
		public EnvSuggestion(T aData, ResultRenderer<? super T> aResultRenderer) {
			myData = aData;
			myResultRenderer = aResultRenderer;
		}
		
		public T getData() {
			return myData;
		}

		@Override
		public String getDisplayString() {
			return myResultRenderer.getDisplayString(getData());
		}

		@Override
		public String getReplacementString() {
			return myResultRenderer.getReplacementString(getData());
		}
	}

	public static class SuggesstionSelectionHandlerAdapter<T> implements SelectionHandler<EnvSuggestion<T>> {
		private class Event extends SelectionEvent<T> {
			protected Event(T aSelectedItem) {
				super(aSelectedItem);
			}
		}
		
		private SelectionHandler<T> myTargetHandler;
		
		public SuggesstionSelectionHandlerAdapter(SelectionHandler<T> aTargetHandler) {
			myTargetHandler = aTargetHandler;
		}
		
		@Override
		public void onSelection(SelectionEvent<EnvSuggestion<T>> aEvent) {
			SelectionEvent<T> newEvent = new Event(aEvent.getSelectedItem().getData());
			myTargetHandler.onSelection(newEvent);
		}
	}
	
	protected ResultRenderer<T> myResultRenderer;
	private boolean myIsDisplayHTML;
	
	public EnvSuggestOracle(boolean aIsDisplayHTML) {
		myIsDisplayHTML = aIsDisplayHTML;
	}
	
	public void setResultRenderer(ResultRenderer<T> aResultRenderer){
		myResultRenderer = aResultRenderer;
	}
	
	@Override
	public boolean isDisplayStringHTML() {
		return myIsDisplayHTML;
	}
	
	public ResultRenderer<T> getResultRenderer() {
		return myResultRenderer;
	}
	
	protected Response generateResponse(Collection<T> aResults) {
		Collection<EnvSuggestion<T>> retVal = new ArrayList<EnvSuggestion<T>>(aResults.size());
		for (T s : aResults) {
			retVal.add(new EnvSuggestion<T>(s, myResultRenderer));
		}
		
		return new Response(retVal);
	}
}
