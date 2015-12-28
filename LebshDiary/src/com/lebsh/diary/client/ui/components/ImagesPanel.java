package com.lebsh.diary.client.ui.components;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.lebsh.diary.shared.ImageItemDTO;

public class ImagesPanel extends Composite {
	private ScrollPanel root = new ScrollPanel();
	FlexTable rootLayout = new FlexTable();
	private Collection<ZoomableImage> images = new ArrayList<ZoomableImage>();
	public ImagesPanel(Collection<ImageItemDTO> imagesDTO){
		this();
		setImages(imagesDTO);
		
	}
	public ImagesPanel(){
		root.setWidget(rootLayout);
		initWidget(root);
	}
	
	public void setImages(Collection<ImageItemDTO> imagesDTO){
		images.clear();
		rootLayout.clear();
		for(ImageItemDTO imageDTO : imagesDTO){
			images.add(new ZoomableImage(imageDTO,1));
		}
		int row = 0;
		for(ZoomableImage imageData : images){
			rootLayout.setWidget(row++, 0, imageData);
		}
	}

}
