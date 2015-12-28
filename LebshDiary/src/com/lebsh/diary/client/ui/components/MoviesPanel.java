package com.lebsh.diary.client.ui.components;

import java.util.Collection;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.lebsh.diary.shared.VideoItemDTO;

public class MoviesPanel extends Composite {
	ScrollPanel root = new ScrollPanel();
	HTML htmlPanel = new HTML();
	String htmlText;
	StringBuilder htmlBuilder = new StringBuilder();
	public MoviesPanel(Collection<VideoItemDTO> movies){
		this();
		setMovies(movies);
		
	}
	public MoviesPanel(){
		root.setWidget(htmlPanel);
		initWidget(root);
	}
	
	public void setMovies(Collection<VideoItemDTO> movies){
		
	}

}
