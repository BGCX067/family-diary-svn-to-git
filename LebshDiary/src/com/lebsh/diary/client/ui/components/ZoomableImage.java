package com.lebsh.diary.client.ui.components;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.lebsh.diary.shared.ImageItemDTO;

public class ZoomableImage extends Image {
	private ImageItemDTO imageData;
	private ZoomHandler zoomHandler;
	
	
	public ZoomableImage(ImageItemDTO imageData , ZoomHandler zoomHandler, int sizeFactor){
		this(imageData,sizeFactor);
		setZoomHandler(zoomHandler);
	}
	
	
	public ZoomableImage(ImageItemDTO imageData, int sizeFactor){
		this.imageData = imageData;
		setUrl(resize(sizeFactor,imageData.getDefaultServingUrl()));
		setStyleName("selectable");
		initActions();
	}
	
	private void initActions() {
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(zoomHandler != null){
					zoomHandler.onZoom(imageData);
				}
				
			}
		});
		
	}
	
	public void setZoomHandler(ZoomHandler zoomHandler) {
		this.zoomHandler = zoomHandler;
	}

	public void resize(int value) {
		setUrl(resize(value, getUrl()));
		
	}
	/**
	 *  picasa image format support
	 * @param value
	 * @param defaultURL
	 * @return
	 */
	public static String resize(float value , String defaultURL){
		StringBuilder builder = new StringBuilder();
		String[] urlParts = defaultURL.split("/");
		urlParts[urlParts.length-2] = "s"+(int)(value*100);
		for (int i = 0; i < urlParts.length-1; i++) {
			builder.append(urlParts[i]+"/");
		}
		builder.append(urlParts[urlParts.length-1]);
		return builder.toString();
	}
/*	
 * google app engine image format support
 * 
 * public static String resize(int value , String defaultURL){
		String baseURL = defaultURL.substring(0, defaultURL.indexOf("=s"));
		return baseURL+"=s"+value*100;
	}
*/	
	public interface ZoomHandler{
		public void onZoom(ImageItemDTO imageDTO);
	}
}
