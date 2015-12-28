package com.lebsh.diary.client.model;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.cellview.client.TextColumn;
import com.lebsh.diary.shared.DiaryEventDTO;


public class DiaryEventRecord {

	public static TextColumn<DiaryEventDTO> createTitleColumn(){
		return new TextColumn<DiaryEventDTO>() {
			@Override
			public String getValue(DiaryEventDTO object) {
					return object.getName();
			}
		};
	}
	
	public static TextColumn<DiaryEventDTO> createDateColumn(){
		return new TextColumn<DiaryEventDTO>() {
			@Override
			public String getValue(DiaryEventDTO object) {
				return DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT).format(object.getDate());
			}
		};
	}
}
