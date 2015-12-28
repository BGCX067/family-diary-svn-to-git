package com.lebsh.diary.client.mvp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.lebsh.diary.client.activity.AppActivityContainer;
import com.lebsh.diary.client.activity.DiaryEventEditActivity;
import com.lebsh.diary.client.activity.DiaryEventsActivity;
import com.lebsh.diary.client.activity.MainActivity;
import com.lebsh.diary.client.place.DiaryEventEditPlace;
import com.lebsh.diary.client.place.DiaryEventsPlace;
import com.lebsh.diary.client.place.MainPlace;

public class AppActivityMapper implements ActivityMapper {

	private Map<Class<? extends Place>, AppActivityContainer> activityMap = new HashMap<Class<? extends Place>, AppActivityContainer>();

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public AppActivityMapper() {
		super();
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public AppActivityContainer getActivity(Place place) {
		// This is begging for GIN
		if( !isSecuer(place)){
			return getReusableActivity(new MainPlace("main"));
		}
		else{
			return getReusableActivity(place);
		}
	}
	
	/**
	 * Lazy create activities by demand and store theme in map for reusing 
	 * @param place
	 * @return
	 */
	private AppActivityContainer getReusableActivity(Place place){
		if(activityMap.get(place.getClass()) == null){
			activityMap.put(place.getClass(), createActivity(place));
		}
		AppActivityContainer activity =  activityMap.get(place.getClass());
		activity.setPlace(place);
		return activity;
	}
	
	private AppActivityContainer createActivity(Place place){
		if (place instanceof DiaryEventsPlace)
			return new DiaryEventsActivity((DiaryEventsPlace) place);
		if (place instanceof DiaryEventEditPlace)
			return new DiaryEventEditActivity((DiaryEventEditPlace) place);
		if (place instanceof MainPlace)
			return new MainActivity((MainPlace) place);
		return null;
	}
	
	private boolean isSecuer(Place place){
		return true;
	}

	
	/**
	 * returns only the visited activities. 
	 */
	public Collection<AppActivityContainer> getActivityList() {
		return activityMap.values();
	}

}

