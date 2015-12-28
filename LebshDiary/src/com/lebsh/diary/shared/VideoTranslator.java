package com.lebsh.diary.shared;

import java.util.ArrayList;
import java.util.List;

import com.lebsh.diary.server.jdo.VideoItem;

public class VideoTranslator {

	public static List<VideoItem> dto2entity(List<VideoItemDTO> videoItems) {
		
		List<VideoItem> ret = new ArrayList<VideoItem>();
		for(VideoItemDTO videoDTO : videoItems){
			VideoItem video = new VideoItem();
			video.setDescription(videoDTO.getDescription());
			video.setName(videoDTO.getName());
			video.setVideoURL(videoDTO.getVideoURL());
			ret.add(video);
		}
		return ret;
	}
	public static List<VideoItemDTO> entity2dto(List<VideoItem> videoItems) {
		List<VideoItemDTO> ret = new ArrayList<VideoItemDTO>();
		for(VideoItem video : videoItems){
			VideoItemDTO dto = new VideoItemDTO();
			dto.setDescription(video.getDescription());
			dto.setKey(video.getKey().getId());
			dto.setName(video.getName());
			dto.setVideoURL(video.getVideoURL());
			ret.add(dto);
		}
		return ret;
	}

}
