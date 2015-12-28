package com.lebsh.diary.shared;

import com.lebsh.diary.server.jdo.DiaryEvent;

public class EventTranslator {

	public static DiaryEventDTO entity2ThinDTO(DiaryEvent event) {
		DiaryEventDTO dto = new DiaryEventDTO();
		dto.setName(event.getName());
		dto.setDate(event.getDate());
		dto.setKey(event.getKey().getId());
		return dto;
	}
	public static DiaryEventDTO entity2ThikDTO(DiaryEvent event) {
		DiaryEventDTO dto = new DiaryEventDTO();
		dto.setName(event.getName());
		dto.setDate(event.getDate());
		dto.setKey(event.getKey().getId());
		
		dto.setContent(event.getContent());
		dto.setImageItems(ImageTranslator.entity2dto(event.getImageItems()));
		dto.setVideoItems(VideoTranslator.entity2dto(event.getVideoItems()));
		return dto;
	}
	
//	public static DiaryEvent thikDTO2Entity(DiaryEventDTO dto){
//		
//	}

}
