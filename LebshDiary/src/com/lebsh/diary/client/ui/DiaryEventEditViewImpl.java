package com.lebsh.diary.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.ui.components.AuthenticationDialog;
import com.lebsh.diary.client.ui.components.AuthenticationDialog.AuthenticationResultHandler;
import com.lebsh.diary.client.ui.components.ImageLabelButton;
import com.lebsh.diary.client.ui.components.ZoomableImage;
import com.lebsh.diary.shared.DiaryEventDTO;
import com.lebsh.diary.shared.ImageItemDTO;
import com.lebsh.diary.shared.VideoItemDTO;

public class DiaryEventEditViewImpl extends Composite implements DiaryEventEditView{

	private static DiaryEventEditViewImplUiBinder uiBinder = GWT.create(DiaryEventEditViewImplUiBinder.class);
	@UiField 
	ScrollPanel root;
	private DiaryEventDTO editedEvent;
	private FlexTable rootLayout = new FlexTable();
	private FlexTable loaderLayout = new FlexTable();
	private EventDetails eventDetails;
	private ImageLoader imageLoader;
	private MovieLoader movieLoader;
	DiaryEventEditView.Presenter presenter;
	
	private ImageLabelButton startUploadBtn = new ImageLabelButton(AppController.getClientFactory().getLabelResource().startUploadItems(), 
			AppController.getClientFactory().getImageResource().getConfirm());
	interface DiaryEventEditViewImplUiBinder extends UiBinder<Widget, DiaryEventEditViewImpl> {
	}

	public DiaryEventEditViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		initLoaderLayout();
		initRootView();
		initActions();
	}

	private void initRootView() {
		 eventDetails = new EventDetails();
		rootLayout.setWidget(0, 0, eventDetails);
		rootLayout.setWidget(0, 1, startUploadBtn);
		rootLayout.setWidget(1, 0, loaderLayout);
		rootLayout.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.getFlexCellFormatter().setColSpan(1, 0, 2);
		root.setWidget(rootLayout);
	}
	
	
	private void initActions() {
		startUploadBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(imageLoader.isEmpty() && movieLoader.isEmpty()){
					Window.alert(AppController.getClientFactory().getLabelResource().mustAddImageOrMovie());
					return ;
				}
				if( ! imageLoader.isValid() || ! movieLoader.isValid() || ! eventDetails.isValid()){
					return;
				}
				new AuthenticationDialog(new AuthenticationResultHandler() {
					
					@Override
					public void onResult(boolean success) {
						if(success){
							if(editedEvent == null){
								presenter.createEvent(asDTO());
							}
							else{
								DiaryEventDTO updatedEvent = asDTO();
								updatedEvent.setKey(editedEvent.getKey());
								presenter.updateEvent(updatedEvent);
								
							}
						}
						
					}
				}).center();
				
			}
		});
		
	}


	protected DiaryEventDTO asDTO() {
		DiaryEventDTO dto = new DiaryEventDTO();
		dto.setDate(eventDetails.getEventDate());
		dto.setContent(eventDetails.getEventContent());
		dto.setName(eventDetails.getEventTitle());
		dto.setVideoItems(movieLoader.getMovies());
		dto.setImageItems(imageLoader.getImages());
		return dto;
	}
	
	

	private void initLoaderLayout() {
		 imageLoader = new ImageLoader();
		 movieLoader = new MovieLoader();
		loaderLayout.setWidget(0, 0, imageLoader);
		loaderLayout.setWidget(0, 1, movieLoader);
		loaderLayout.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		loaderLayout.getFlexCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
	}
	
	
	

	@Override
	public void resetView() {
		 eventDetails.resetView();
		 imageLoader.resetView();
		 movieLoader.resetView();
		
	}
	
	private void populateEvent() {
		 eventDetails.populateEvent();
		 imageLoader.populateEvent();
		 movieLoader.populateEvent();
		
	}

	@Override
	public void setPresenter(Presenter p) {
		presenter = p;
	}
	
	@Override
	public void setEvent(DiaryEventDTO event) {
		editedEvent = event;
		populateEvent();
		
	}
	

	private class EventDetails extends Composite{
		private FlexTable eventLayout = new FlexTable();
		private Label eventTitlelLabel = new Label(AppController.getClientFactory().getLabelResource().eventTitle());
		private TextBox eventTitle = new TextBox();
		private Label eventContentLabel = new Label(AppController.getClientFactory().getLabelResource().eventContent());
		private TextArea eventContent = new TextArea();
		private Label dateLabel = new Label(AppController.getClientFactory().getLabelResource().eventDate());
		private DateBox dateBox = new DateBox();
		private EventDetails(){
			initWidget(eventLayout);
			eventLayout.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
			eventLayout.getElement().getStyle().setBorderWidth(1, Unit.PX);
			eventLayout.setWidget(0, 0, eventTitlelLabel);
			eventLayout.setWidget(0, 1, eventTitle);
			eventLayout.setWidget(0, 2, dateLabel);
			eventLayout.setWidget(0, 3, dateBox);
			eventLayout.setWidget(1, 0, eventContentLabel);
			eventLayout.setWidget(1, 1, eventContent);
			eventContent.setSize("80%", "80%");
			eventLayout.getFlexCellFormatter().setColSpan(1, 1, 3);
		}
		
		public void populateEvent() {
			dateBox.setValue(editedEvent.getDate());
			eventTitle.setText(editedEvent.getName());
			eventContent.setText(editedEvent.getContent());
			
		}

		public boolean isValid() {
			if(dateBox.getValue() == null || eventTitle.getText() == null || eventContent.getText() == null ||
					eventTitle.getText().isEmpty() || eventContent.getText().isEmpty() ){
				Window.alert(AppController.getClientFactory().getLabelResource().invalidEventCreation());
				return false;
				
			}
			return true;
		}

		public void resetView() {
			eventTitle.setText("");
			eventContent.setText("");
		}

		public String getEventTitle(){
			return eventTitle.getText();
		}
		
		public String getEventContent(){
			return eventContent.getText();
		}
		
		public Date getEventDate(){
			return dateBox.getValue();
		}
	}
	

	
	private class ImageLoader extends Composite{
		private Button add = new Button(AppController.getClientFactory().getLabelResource().add());
		private FlexTable rootImageLoaderLayout = new FlexTable();
		private Label header = new Label();
		private FlexTable imagesLayout = new FlexTable();
		private FlexTable bottomLayout = new FlexTable();
		
		
		public ImageLoader(){
			initHeader();
			initLoaderLayout();
			initBottomLayout();
			initActions();
			initWidget(rootImageLoaderLayout);
			rootImageLoaderLayout.setWidget(0, 0, header);
			rootImageLoaderLayout.setWidget(1, 0, imagesLayout);
			rootImageLoaderLayout.setWidget(2, 0, bottomLayout);
		}
		
		public void populateEvent() {
			int row = 0;
			for (ImageItemDTO imageItem  : editedEvent.getImageItems()) {
				imagesLayout.setWidget(	row++, 0, new ImageChooser(imageItem));
			}
			
		}

		public void resetView() {
			imagesLayout.clear();
			
		}

		public boolean isValid() {
			for(int i=0;i<imagesLayout.getRowCount();i++){
				ImageChooser chooser = (ImageChooser)imagesLayout.getWidget(i, 0);
				if(chooser!=null && ! chooser.isValid()){
					return false;
				}
			}
			return true;
		}
		
		public boolean isEmpty(){
			return imagesLayout.getRowCount() == 0;
		}
		
		public List<ImageItemDTO> getImages() {
			List<ImageItemDTO> movies = new ArrayList<ImageItemDTO>();
			for(int i=0;i<imagesLayout.getRowCount();i++){
				ImageChooser chooser = (ImageChooser)imagesLayout.getWidget(i, 0);
				if(chooser != null){
					movies.add(chooser.asDTO());
				}
			}
			return movies;
		}
		
		public void initLoaderLayout() {
			addRow();
			
		}
		
		public void initHeader() {
			header.setText(AppController.getClientFactory().getLabelResource().photos());
			header.getElement().getStyle().setFontSize(36, Unit.PX);
			header.getElement().getStyle().setColor("#878787");
			header.getElement().getStyle().setBackgroundColor("#FFDAB9");
			
		}
		
		public void initBottomLayout() {
			bottomLayout.setWidget(0, 0, add);
			
		}
		
		private void initActions() {
			add.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					addRow();
					
				}
			});
			
			
		}
		
		private void addRow(){
			imagesLayout.setWidget(	imagesLayout.getRowCount(), 0, new ImageChooser());
			
		}
		
		private void remove(ImageChooser imageChooser){
			int rowToReove = -1;
			imagesLayout.remove(imageChooser);
			for (int i = 0; i < imagesLayout.getRowCount(); i++) {
				if(imagesLayout.getWidget(i, 0) == null){
					rowToReove = i;
				}
			}
			if(rowToReove>=0){
				imagesLayout.removeRow(rowToReove);
			}
		}
	}
	
	private class MovieLoader extends Composite{
		private Button add = new Button(AppController.getClientFactory().getLabelResource().add());
		private FlexTable rootImageLoaderLayout = new FlexTable();
		private Label header = new Label();
		private FlexTable moviesLayout = new FlexTable();
		private FlexTable bottomLayout = new FlexTable();
		
		
		public MovieLoader(){
			initHeader();
			initLoaderLayout();
			initBottomLayout();
			initActions();
			initWidget(rootImageLoaderLayout);
			rootImageLoaderLayout.setWidget(0, 0, header);
			rootImageLoaderLayout.setWidget(1, 0, moviesLayout);
			rootImageLoaderLayout.setWidget(2, 0, bottomLayout);
		}
		
		public void populateEvent() {
			int row = 0;
			for (VideoItemDTO movieItem  : editedEvent.getVideoItems()) {
				moviesLayout.setWidget(	row++, 0, new MovieChooser(movieItem));
			}
			
		}

		public void resetView() {
			moviesLayout.clear();
			
		}

		public boolean isValid() {
			for(int i=0;i<moviesLayout.getRowCount();i++){
				MovieChooser chooser = (MovieChooser)moviesLayout.getWidget(i, 0);
				if(chooser!=null && ! chooser.isValid()){
					return false;
				}
			}
			return true;
		}
		
		public boolean isEmpty(){
			return moviesLayout.getRowCount() == 0;
		}

		public List<VideoItemDTO> getMovies() {
			List<VideoItemDTO> movies = new ArrayList<VideoItemDTO>();
			for(int i=0;i<moviesLayout.getRowCount();i++){
				MovieChooser chooser = (MovieChooser)moviesLayout.getWidget(i, 0);
				if(chooser != null){
					movies.add(chooser.asDTO());
				}
			}
			return movies;
		}

		public void initLoaderLayout() {
			addRow();
			
		}
		
		public void initHeader() {
			header.setText(AppController.getClientFactory().getLabelResource().movies());
			header.getElement().getStyle().setFontSize(36, Unit.PX);
			header.getElement().getStyle().setColor("#878787");
			header.getElement().getStyle().setBackgroundColor("#FFDAB9");
			
		}

		public void initBottomLayout() {
			bottomLayout.setWidget(0, 0, add);
			
		}
		
		private void initActions() {
			add.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					addRow();

				}
			});

			
		}

		private void addRow(){
			moviesLayout.setWidget(	moviesLayout.getRowCount(), 0, new MovieChooser());

		}
		
		

		public void remove(MovieChooser movieChooser) {
			int rowToReove = -1;
			moviesLayout.remove(movieChooser);
			for (int i = 0; i < moviesLayout.getRowCount(); i++) {
				if(moviesLayout.getWidget(i, 0) == null){
					rowToReove = i;
				}
			}
			if(rowToReove>=0){
				moviesLayout.removeRow(rowToReove);
			}
			
		}
	}
	

	
	
	private class ImageChooser extends Composite{
		private FlexTable movieLayout = new FlexTable();
		private Label imageURLLabel = new Label(AppController.getClientFactory().getLabelResource().imageURL());
		private TextBox imageUTL = new TextBox();
		private Image previewImage = new Image();
		private ImageLabelButton removeButton = new ImageLabelButton(AppController.getClientFactory().getImageResource().cancel());
		
		public ImageChooser(){
			removeButton.setTitle(AppController.getClientFactory().getLabelResource().removeItem());
			initWidget(movieLayout);
			initImageChooserActions();
			movieLayout.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
			movieLayout.getElement().getStyle().setBorderWidth(1, Unit.PX);
			movieLayout.setWidget(0, 0, removeButton);
			movieLayout.setWidget(0, 1, imageURLLabel);
			movieLayout.setWidget(0, 2, imageUTL);
			movieLayout.setWidget(0, 3, previewImage);
			initImageChooserActions();
		}
		
		public ImageChooser(ImageItemDTO imageItem) {
			this();
			previewImage.setUrl(ZoomableImage.resize(.7f, imageItem.getDefaultServingUrl()));
			imageUTL.setText(imageItem.getDefaultServingUrl());
		}

		private void initImageChooserActions() {
			imageUTL.addValueChangeHandler(new ValueChangeHandler<String>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					previewImage.setUrl(ZoomableImage.resize(.7f, event.getValue()));
					
				}
			});
			
			removeButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					imageLoader.remove(ImageChooser.this);
					
				}
			});
		}

		public boolean isValid() {
			if( imageUTL.getText().trim().isEmpty() ){
				Window.alert(AppController.getClientFactory().getLabelResource().invalidImageCreation());
				return false;
			}
			return true;
		}
		
		public ImageItemDTO asDTO(){
			ImageItemDTO dto = new ImageItemDTO();
			dto.setDefaultServingUrl(imageUTL.getText());
			return dto;
		}
	}
	private class MovieChooser extends Composite{
		private FlexTable movieLayout = new FlexTable();
		private Label movieTitlelLabel = new Label(AppController.getClientFactory().getLabelResource().movieTitle());
		private TextBox movieTitle = new TextBox();
		private Label movieURLLabel = new Label(AppController.getClientFactory().getLabelResource().movieURL());
		private TextBox movieUTL = new TextBox();
		private HTML moviePreview = new HTML();
		private ImageLabelButton removeButton = new ImageLabelButton(AppController.getClientFactory().getImageResource().cancel());
		
		public MovieChooser(){
			initWidget(movieLayout);
			removeButton.setTitle(AppController.getClientFactory().getLabelResource().removeItem());
			initMovieChooserActions();
			movieLayout.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
			movieLayout.getElement().getStyle().setBorderWidth(1, Unit.PX);
			movieLayout.setWidget(0, 0, removeButton);
			movieLayout.setWidget(0, 1, movieTitlelLabel);
			movieLayout.setWidget(0, 2, movieTitle);
			movieLayout.setWidget(1, 1, movieURLLabel);
			movieLayout.setWidget(1, 2, movieUTL);
			movieLayout.setWidget(0, 3, moviePreview);
			movieLayout.getFlexCellFormatter().setRowSpan(0, 3, 2);
			initMovieChooserActions();
		}
		
		public MovieChooser(VideoItemDTO movieItem) {
			this();
			loadMoviePreview(movieItem.getVideoURL());
			movieTitle.setText(movieItem.getName());
			movieUTL.setText(movieItem.getVideoURL());
			
		}

		private void initMovieChooserActions() {
			movieUTL.addValueChangeHandler(new ValueChangeHandler<String>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<String> event) {
					loadMoviePreview(event.getValue());
				}
			});
			removeButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					movieLoader.remove(MovieChooser.this);
					
				}
			});
			
			
		}
		
		private void loadMoviePreview(String url){
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append("<iframe width=168 height=94 src=");
			htmlBuilder.append(url);
			htmlBuilder.append(" frameborder=0 allowfullscreen></iframe>");
			moviePreview.setHTML(htmlBuilder.toString());
		}

		public boolean isValid() {
			if(movieTitle.getText().trim().isEmpty() || movieUTL.getText().trim().isEmpty() ){
				Window.alert(AppController.getClientFactory().getLabelResource().invalidMovieCreation());
				return false;
			}
			return true;
		}

		public VideoItemDTO asDTO(){
			VideoItemDTO dto = new VideoItemDTO();
			dto.setName(movieTitle.getText());
			dto.setVideoURL(movieUTL.getText());
			return dto;
		}
	}

	

}
