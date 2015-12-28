package com.lebsh.diary.shared;

import java.util.ArrayList;
import java.util.List;

import com.lebsh.diary.server.jdo.ImageItem;

public class ImageTranslator {

	public static List<ImageItem> dto2entity(List<ImageItemDTO> imageItems) {
		List<ImageItem> ret = new ArrayList<ImageItem>();
		for (ImageItemDTO dto : imageItems) {
			ImageItem item = new ImageItem();
			item.setDefaultServingUrl(dto.getDefaultServingUrl());
			ret.add(item);
		}
		return ret;
	}
	public static List<ImageItemDTO> entity2dto(List<ImageItem> imageItems) {
		List<ImageItemDTO> ret = new ArrayList<ImageItemDTO>();
		for(ImageItem image : imageItems){
			ImageItemDTO dto = new ImageItemDTO();
			dto.setDescription(image.getDescription());
			dto.setKey(image.getKey().getId());
			dto.setName(image.getName());
			dto.setDefaultServingUrl(image.getDefaultServingUrl());
			ret.add(dto);
		}
		return ret;
	}

}
