package com.lebsh.diary.server.jdo;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DiaryEvent {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key ;
	@Persistent
	private Date date;
	@Persistent
	private String name;
	@Persistent
	private String content;
	@Persistent(mappedBy = "parentEvent")
	private List<VideoItem> videoItems;
	@Persistent(mappedBy = "parentEvent")
	private List<ImageItem> imageItems;
	
	
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
	public List<VideoItem> getVideoItems() {
		return videoItems;
	}
	public void setVideoItems(List<VideoItem> videoItens) {
		this.videoItems = videoItens;
	}
	public List<ImageItem> getImageItems() {
		return imageItems;
	}
	public void setImageItems(List<ImageItem> imageItens) {
		this.imageItems = imageItens;
	}
	public Key getKey() {
		return key;
	}
	
	
}
