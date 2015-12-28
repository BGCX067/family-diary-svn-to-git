package com.lebsh.diary.shared;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DiaryEventDTO implements Serializable, Comparable<DiaryEventDTO> {

	private long key ;
	private Date date;
	private String name;
	private String content;
	private List<VideoItemDTO> videoItems;
	private List<ImageItemDTO> imageItems;
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<VideoItemDTO> getVideoItems() {
		return videoItems;
	}
	public void setVideoItems(List<VideoItemDTO> videoItens) {
		this.videoItems = videoItens;
	}
	public List<ImageItemDTO> getImageItems() {
		return imageItems;
	}
	public void setImageItems(List<ImageItemDTO> imageItens) {
		this.imageItems = imageItens;
	}
	@Override
	public int compareTo(DiaryEventDTO otherEvent) {
		return date.compareTo(otherEvent.getDate());
	}
	
	
	
}
