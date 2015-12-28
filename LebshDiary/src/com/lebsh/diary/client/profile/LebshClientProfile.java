package com.lebsh.diary.client.profile;

import com.google.gwt.resources.client.ImageResource;
import com.lebsh.diary.client.AppController;

public class LebshClientProfile extends ClientProfile {

	@Override
	public ImageResource welcomImage() {
		return AppController.getClientFactory().getImageResource().lebshWelcom();
	}

	@Override
	public ImageResource logoImage() {
		return AppController.getClientFactory().getImageResource().lebshLogo();
	}

	@Override
	public String welcomeMessage() {
		return  AppController.getClientFactory().getLabelResource().lebshWlcomeMessage();
	}

	@Override
	public String mainTitle() {
		return AppController.getClientFactory().getLabelResource().lebshMainTitle();
	}

	@Override
	public String copyrights() {
		return AppController.getClientFactory().getLabelResource().lebshCopyrights();
	}

}
