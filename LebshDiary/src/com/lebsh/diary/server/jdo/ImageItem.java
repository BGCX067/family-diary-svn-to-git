package com.lebsh.diary.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ImageItem {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key ;
	@Persistent
	private DiaryEvent parentEvent;
	
	@Persistent
	private String name;
	@Persistent
	private String description;
	@Persistent
	private BlobKey fileKey;
	@Persistent
	private String defaultServingUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BlobKey getFileKey() {
		return fileKey;
	}
	public void setFileKey(BlobKey fileKey) {
		this.fileKey = fileKey;
	}
	public String getDefaultServingUrl() {
		return defaultServingUrl;
	}
	public void setDefaultServingUrl(String defaultServingUrl) {
		this.defaultServingUrl = defaultServingUrl;
	}
	public Key getKey() {
		return key;
	}
	public DiaryEvent getParentEvent() {
		return parentEvent;
	}
	
	
}
