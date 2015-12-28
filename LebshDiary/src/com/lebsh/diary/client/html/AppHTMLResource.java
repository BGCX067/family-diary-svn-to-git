package com.lebsh.diary.client.html;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface AppHTMLResource extends ClientBundle {

	public static String INTRO_PAGE = "intro.html";
	 @Source("intro.html")
	 public TextResource getIntroHtml();
}

