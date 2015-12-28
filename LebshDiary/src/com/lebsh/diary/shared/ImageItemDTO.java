package com.lebsh.diary.shared;

import java.io.Serializable;

public class ImageItemDTO implements Serializable {

	private long key ;
	private String name;
	private String description;
	private String defaultServingUrl;
	public long getKey() {
		return key;
	}
	public void setKey(long key) {
		this.key = key;
	}
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
	public String getDefaultServingUrl() {
		return defaultServingUrl;
	}
	public void setDefaultServingUrl(String defaultServingUrl) {
		this.defaultServingUrl = defaultServingUrl;
	}
	
	public static String getBigImageURL(String baseURL){
		StringBuilder builder = new StringBuilder();
		String[] urlParts = baseURL.split("/");
		urlParts[urlParts.length-2] = "s700";
		for (int i = 0; i < urlParts.length-1; i++) {
			builder.append(urlParts[i]+"/");
		}
		builder.append(urlParts[urlParts.length-1]);
		return builder.toString();
	}
	
	
}
