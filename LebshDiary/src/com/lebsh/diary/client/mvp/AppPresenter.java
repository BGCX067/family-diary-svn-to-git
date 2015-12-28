package com.lebsh.diary.client.mvp;

import com.google.gwt.place.shared.Place;

public interface AppPresenter<P extends Place> {
	/**
	 * @return The place that lead this presenter to be active.
	 */
	public P getPlace();
	
	/**
	 * @param aPlace The next place to go.
	 */
	public void goTo(Place aPlace);
}
