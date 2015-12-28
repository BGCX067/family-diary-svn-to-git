package com.lebsh.diary.client.ui.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.lebsh.diary.client.ui.components.ImageBrowser.DisposeHandler;
import com.lebsh.diary.client.ui.components.ZoomableImage.ZoomHandler;
import com.lebsh.diary.shared.DiaryEventDTO;
import com.lebsh.diary.shared.ImageItemDTO;
import com.lebsh.diary.shared.VideoItemDTO;

public class EventPanel extends Composite {
	FlexTable rootLayout = new FlexTable();
	private DiaryEventDTO eventDTO;
	private MoviesPanel selectedEventMovies = new MoviesPanel();
	private ImagesPanel  selectedEventImages = new ImagesPanel();
	private DetailesPanel  selectedEventDetailesPanel = new DetailesPanel();
	private int framesSiseFactor = 0;
	public EventPanel(){
		rootLayout.setWidget(0, 0, selectedEventDetailesPanel);
		rootLayout.setWidget(1, 0, selectedEventImages);
		rootLayout.setWidget(1, 1, selectedEventMovies);
		rootLayout.getFlexCellFormatter().setColSpan(0, 0, 2);
		rootLayout.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.getFlexCellFormatter().setAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_TOP);
		initWidget(rootLayout);
		setSize("100%", "100%");
	}
	
	public void setEvent(DiaryEventDTO event) {
		eventDTO = event;
		if(event.getVideoItems().isEmpty()){
			selectedEventImages.setImages(event.getImageItems(),2);
		}
		else{
			selectedEventImages.setImages(event.getImageItems(),1);
		}
		selectedEventMovies.setMovies(event.getVideoItems());
		selectedEventDetailesPanel.setEvent(event);
	}
	
	public DiaryEventDTO getEventDTO() {
		return eventDTO;
	}
	
	public void resizeFrames(int value) {
		framesSiseFactor = value;
		selectedEventImages.resizeFrames(value);
		selectedEventMovies.resizeFrames(value);
		
	}
	
	private class DetailesPanel extends Composite{
		private FlexTable rootDetailsLayout = new FlexTable();
		private Label eventTitle = new Label();
		private Label eventContent = new Label();
		
		private DetailesPanel(){
			eventTitle.getElement().getStyle().setFontSize(36, Unit.PX);
			eventTitle.getElement().getStyle().setColor("#878787");
			rootDetailsLayout.setWidget(0, 0, eventTitle);
			rootDetailsLayout.setWidget(1, 0, eventContent);
			rootDetailsLayout.getFlexCellFormatter().setAlignment(0, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
			rootDetailsLayout.getFlexCellFormatter().setAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT, HasVerticalAlignment.ALIGN_TOP);
			initWidget(rootDetailsLayout);
			getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
			getElement().getStyle().setBorderWidth(1, Unit.PX);
			setWidth("100%");
		}
		
		public void setEvent(DiaryEventDTO event) {
			this.eventTitle.setText( event.getName());
			this.eventContent.setText( event.getContent());
		}
	}

	
	public class MoviesPanel extends Composite {
		private double defaultWidth = 640;
		private double defaultHeight = 480;
		Collection<VideoItemDTO> movies;
		private double factors[] = new double[]{.6,.7,.8,.9,1};
		private  String size = " width="+defaultWidth*factors[0]+" height="+defaultHeight*factors[0]+" ";
		ScrollPanel root = new ScrollPanel();
		HTML htmlPanel = new HTML();
		String htmlText;
		StringBuilder htmlBuilder;
		
		
		public MoviesPanel(){
			root.setWidget(htmlPanel);
			initWidget(root);
			setStyleName("moviesPanel");
		}
		
		public void setMovies(Collection<VideoItemDTO> movies){
			size = " width="+defaultWidth*factors[framesSiseFactor]+" height="+defaultHeight*factors[framesSiseFactor]+" ";
			this.movies = movies;
			refresh();
			
		}
		

		public void resizeFrames(int value) {
			size = " width="+defaultWidth*factors[value]+" height="+defaultHeight*factors[value]+" ";
			refresh();
		}
		
		
		private void refresh() {
			if(movies == null || movies.isEmpty()){
				htmlPanel.setHTML("");
				return;
			}
			htmlBuilder = new StringBuilder();
			for(VideoItemDTO video : movies){
				htmlBuilder.append("<p>"+video.getName()+"<p>");
				htmlBuilder.append("<iframe "+size+" src=");
				htmlBuilder.append(video.getVideoURL());
				htmlBuilder.append(" frameborder=0 allowfullscreen></iframe>");
			}
			htmlPanel.setHTML(htmlBuilder.toString());
			
		}

	}
	
	public class ImagesPanel extends Composite {
		private ScrollPanel root = new ScrollPanel();
		private FlexTable rootImagesLayout = new FlexTable();
		private int columns;
		private ImageBrowser browser = new ImageBrowser(new DisposeHandler() {
			
			@Override
			public void onDispose() {
				selectedEventMovies.setVisible(true);
				
			}
		});
		private Collection<ImageItemDTO> imagesDTO;
		private ZoomHandler zoomHandler = new ZoomHandler() {
			
			@Override
			public void onZoom(ImageItemDTO imageDTO) {
				selectedEventMovies.setVisible(false);
				 browser.open(imagesDTO, imageDTO);
			}
		};
		private Collection<ZoomableImage> images = new ArrayList<ZoomableImage>();
		
		public void resizeFrames(int value) {
			for(ZoomableImage image : images){
				image.resize(value+1);
			}
			
		}
		public ImagesPanel(){
			root.setWidget(rootImagesLayout);
			initWidget(root);
		}
		
		public void setImages(Collection<ImageItemDTO> imagesDTO,int colSpan){
			this.imagesDTO = imagesDTO;
			this.columns = colSpan;
			images.clear();
			rootImagesLayout.clear();
			for(ImageItemDTO imageDTO : imagesDTO){
				images.add(new ZoomableImage(imageDTO,zoomHandler,framesSiseFactor+1));
			}
			int row = 0;
			int col=0;
			Iterator<ZoomableImage> imagesIter = images.iterator();
			while(imagesIter.hasNext()){
				for (col = 0; col < columns; col++) {
					if(imagesIter.hasNext()){
						rootImagesLayout.setWidget(row, col, imagesIter.next());
						
					}
				}
				row++;
			}
		}

	}

	

	
}
