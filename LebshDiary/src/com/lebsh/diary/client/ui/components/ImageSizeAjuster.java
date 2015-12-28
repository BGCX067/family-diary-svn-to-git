package com.lebsh.diary.client.ui.components;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;
import com.lebsh.diary.client.AppController;


public class ImageSizeAjuster extends Composite {

	private FlexTable layout = new FlexTable();
	private Slider slider;
	private Label caption = new Label(AppController.getClientFactory().getLabelResource().imageResizer());
	 
	public ImageSizeAjuster(BarValueChangedHandler action){
		slider = new Slider(action);
		layout.setWidget(0, 0, slider);
		layout.setWidget(1, 0, caption);
		layout.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_BOTTOM);
		layout.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		initWidget(layout);
		
	}
	
	
	private class Slider extends SliderBarHorizontal{
		
		
		public Slider(BarValueChangedHandler action){
			initSlider();
			addBarValueChangedHandler(action);
		}
		
		private void initSlider(){
			this.setLessWidget(new Image(AppController.getClientFactory().getImageResource().less()));
			this.setScaleWidget(new Image(AppController.getClientFactory().getImageResource().scale().getUrl()), 4);
			this.setMoreWidget(new Image(AppController.getClientFactory().getImageResource().more()));		
			this.setDragWidget(new Image(AppController.getClientFactory().getImageResource().drag()));
			this.setMaxValue(4);
			setValue(3);
			this.setWidth("148px");
			
		}

//		private void initActions() {
//			addBarValueChangedHandler(new BarValueChangedHandler() {
//				
//				@Override
//				public void onBarValueChanged(BarValueChangedEvent event) {
//					AppController.getClientFactory().getEventBus().fireEvent(new ImageResizeEvent(	event.getValue()+1));
//				
//					
//				}
//			});
//			
//		}
	}
}
