package com.lebsh.diary.client.profile;

import com.google.gwt.resources.client.ImageResource;
import com.lebsh.diary.client.AppController;

public class SaritClientProfile extends ClientProfile {


	@Override
	public ImageResource welcomImage() {
		return AppController.getClientFactory().getImageResource().saritWelcom();
	}

	@Override
	public ImageResource logoImage() {
		return AppController.getClientFactory().getImageResource().saritLogo();
	}

	@Override
	public String welcomeMessage() {
		return  AppController.getClientFactory().getLabelResource().saritWlcomeMessage();
	}

	@Override
	public String mainTitle() {
		return AppController.getClientFactory().getLabelResource().saritMainTitle();
	}

}
