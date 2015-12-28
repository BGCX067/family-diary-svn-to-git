package com.lebsh.diary.client.images;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.ImageResource;

public interface AppImageResource extends ClientBundle {

	@Source("waitAnimated.gif")
	public ImageResource getWait();
	@Source("help.png")
	public ImageResource getHelp();
	@Source("mainBanner.png")
	public ImageResource diary();
	@Source("mainBanner.png")
	public ImageResource photos();
	@Source("mainBanner.png")
	public ImageResource videos();
	@Source("confirm.png")
	public ImageResource getConfirm();
	@Source("diary3.jpg")
	public ImageResource lebshLogo();
	@Source("diaryBanner.png")
	public ImageResource diaryBanner();
	@Source("arrowLeft.png")
	public ImageResource arrowLeft();
	@Source("arrowRight.png")
	public ImageResource arrowRight();
	@Source("cancel.png")
	public ImageResource cancel();
	@Source("find.png")
	public ImageResource find();
	@Source("amitLee.jpg")
	public ImageResource lebshWelcom();
	@Source("pointer.png")
	public ImageResource pointer();
	
//	resizer slider images:
	@Source("portraitless.png")
	public ImageResource less();
	@Source("portraitmore.png")
	public ImageResource more();
	@Source("drag.png")
	public ImageResource drag();
	@Source("scale.png")
	public DataResource scale();
	@Source("saritWelcom.png")
	public ImageResource saritWelcom();
	@Source("saritLogo.png")
	public ImageResource saritLogo();
}
