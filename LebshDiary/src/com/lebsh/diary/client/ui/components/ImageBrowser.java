package com.lebsh.diary.client.ui.components;

import java.util.Collection;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.shared.ImageItemDTO;

public class ImageBrowser extends DecoratedPopupPanel {

	private FlexTable mainLayout = new FlexTable();
	private ImageLabelButton left  = new ImageLabelButton(AppController.getClientFactory().getImageResource().arrowLeft());
	private ImageLabelButton right = new ImageLabelButton(AppController.getClientFactory().getImageResource().arrowRight());
	private ImageLabelButton close = new ImageLabelButton(AppController.getClientFactory().getImageResource().cancel());
//	private Image currentDisplayedImage = new Image();
	private SimplePanel displayingImagePanel = new SimplePanel();
	private ImageItemDTO currentDisplayedImageDTO ;
	private int currentSelectedImageIndex;
	private ImageItemDTOWraper[] imagesItemArray;
	private DisposeHandler disposeHandler;
	public static String demoImg = "https://lh3.googleusercontent.com/-wXbVJNkkERI/RdK0ya23VZI/AAAAAAAABas/GiRFRq3pWF0/s600/IMG_3125.jpg";

	public ImageBrowser(DisposeHandler disposeHandler){
		super(false);
		this.disposeHandler = disposeHandler;
//		getElement().getStyle().setBorderWidth(0, Unit.PX);
		setGlassEnabled(true);
		initLayout();
		setWidget(mainLayout);
		initActions();
	}
	
	public void open(Collection<ImageItemDTO> images , ImageItemDTO selectedImage){
		this.currentDisplayedImageDTO = selectedImage;
		initHanlers();
		initBrowser(images);
		displayingImagePanel.setWidget(imagesItemArray[currentSelectedImageIndex]);
		center();
		
		
	}

	private void initHanlers() {
//		currentDisplayedImage.addLoadHandler(new LoadHandler() {
//
//			@Override
//			public void onLoad(LoadEvent event) {
//				center();
//
//			}
//		});
		
	}

	private void initLayout() {
		mainLayout.setWidget(0, 0, close);
		mainLayout.setWidget(1, 0, left);
		mainLayout.setWidget(1, 1, displayingImagePanel);
		mainLayout.setWidget(1, 2, right);
		mainLayout.getFlexCellFormatter().setColSpan(0, 0, 3);
		mainLayout.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
		mainLayout.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		mainLayout.getFlexCellFormatter().setAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		
	}

	private void initBrowser(Collection<ImageItemDTO> images) {
		right.setVisible(true);
		left.setVisible(true);
		ImageItemDTO[]  imagesArr = images.toArray(new ImageItemDTO[images.size()]);;
		this.imagesItemArray = new ImageItemDTOWraper[images.size()];
		for (int i = 0; i < imagesItemArray.length; i++) {
			imagesItemArray[i] = new ImageItemDTOWraper(imagesArr[i]);
			if(currentDisplayedImageDTO.getKey() == imagesItemArray[i].getDto().getKey()){
				currentSelectedImageIndex = i;
			}
		}
		adjustBrowser();
		
		
		
	}
	
	private void adjustBrowser(){
		if(currentSelectedImageIndex == 0){
			left.setVisible(false);
		}
		if(currentSelectedImageIndex == imagesItemArray.length-1){
			right.setVisible(false);
		}
	}

	private void initActions() {
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
				disposeHandler.onDispose();
			}
		});
		left.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				browse(-1);
			}
		});
		right.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				browse(1);
			}
		});

	}

	private void browse(int direction){
		right.setVisible(true);
		left.setVisible(true);
		currentSelectedImageIndex+=direction;
		currentDisplayedImageDTO = imagesItemArray[currentSelectedImageIndex].getDto();
		displayingImagePanel.setWidget(imagesItemArray[currentSelectedImageIndex]);
		adjustBrowser();
		
	}

	/**
	 * picasa image format support
	 * @param baseURL
	 * @return
	 */
	public static String getBigImageURL(String baseURL){
		StringBuilder builder = new StringBuilder();
		String[] urlParts = baseURL.split("/");
		urlParts[urlParts.length-2] = "s850";
		for (int i = 0; i < urlParts.length-1; i++) {
			builder.append(urlParts[i]+"/");
		}
		builder.append(urlParts[urlParts.length-1]);
		return builder.toString();
	}
/*	
 * google app engine image format support
 * 
 * public static String getBigImageURL(String baseURL){
		return baseURL.substring(0, baseURL.indexOf("=s"))+"=s700";
	}
*/	
	public interface DisposeHandler{
		public void onDispose();
	}
	
	private class ImageItemDTOWraper extends Image{
		private ImageItemDTO dto;

		private ImageItemDTOWraper(ImageItemDTO dto){
			this.dto = dto;
			setUrl(ImageBrowser.getBigImageURL(dto.getDefaultServingUrl()));
			addLoadHandler(new LoadHandler() {
			
						@Override
						public void onLoad(LoadEvent event) {
							ImageBrowser.this.center();
			
						}
					});
					
		}
		public ImageItemDTO getDto() {
			return dto;
		}
	}
}
