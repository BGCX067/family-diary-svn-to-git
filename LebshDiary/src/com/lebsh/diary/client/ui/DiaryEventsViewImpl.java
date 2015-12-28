package com.lebsh.diary.client.ui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.kiouri.sliderbar.client.event.BarValueChangedEvent;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.model.DiaryEventRecord;
import com.lebsh.diary.client.oracle.EventSuggestOracle;
import com.lebsh.diary.client.oracle.EventSuggestOracle.EventSelectionHandler;
import com.lebsh.diary.client.ui.EnvCellTable.SelectionListener;
import com.lebsh.diary.client.ui.components.EventPanel;
import com.lebsh.diary.client.ui.components.WelcomePanel;
import com.lebsh.diary.shared.DiaryEventDTO;

public class DiaryEventsViewImpl extends Composite implements DiaryEventsView{

	private static DiaryEventsViewImplUiBinder uiBinder = GWT.create(DiaryEventsViewImplUiBinder.class);
	@UiField 
	ScrollPanel root;
	private Presenter presenter;
	private FlexTable rootLayout = new FlexTable();
	private PageHeader pageHeader;
	private EventPanel eventPanel = new EventPanel();
	private WelcomePanel welcomPanel = new WelcomePanel();
	private EventsTable eventsTable = new EventsTable();
	interface DiaryEventsViewImplUiBinder extends UiBinder<Widget, DiaryEventsViewImpl> {
	}

	public DiaryEventsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		initHeader();
		rootLayout.setWidget(0, 0, pageHeader);
		rootLayout.setWidget(1, 0, eventsTable);
		rootLayout.setWidget(1, 1, welcomPanel);
		rootLayout.getFlexCellFormatter().setColSpan(0, 0, 2);
		rootLayout.getFlexCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		rootLayout.setSize("100%", "100%");
		root.setWidget(rootLayout);
	}

	private void initHeader() {
		pageHeader = new PageHeader(AppController.getClientFactory().getLabelResource().diary(), new BarValueChangedHandler() {
			
			@Override
			public void onBarValueChanged(BarValueChangedEvent event) {
				eventPanel.resizeFrames(event.getValue()); 
			}
		});
	}
	
	

	@Override
	public void resetView() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public DiaryEventDTO getSelectedEvent() {
		return eventPanel.getEventDTO();
	}

	@Override
	public void populateEvents(List<DiaryEventDTO> events) {
		eventsTable.setRowData(0,events);
		welcomPanel.ready();
	}
	
	@Override
	public void displaySelectedEvent(DiaryEventDTO event) {
		rootLayout.setWidget(1, 1, eventPanel);
		eventPanel.setEvent(event);
		
	}

	@Override
	public void setPresenter(Presenter p) {
		presenter = p;
		
	}
	
	private class EventsTable extends Composite{
		private ScrollPanel tableScroller = new ScrollPanel();
		private FlexTable eventsTableLayout = new FlexTable();
		private EnvCellTable<DiaryEventDTO> table = new EnvCellTable<DiaryEventDTO>();
		private EventSuggestOracle suggestOracle = new EventSuggestOracle();
		private Label title;
		public EventsTable() {
			initTableHeader();
			initTable();
			initSelectionHandlers();
			eventsTableLayout.getElement().getStyle().setBackgroundColor("#FFDAB9");
			eventsTableLayout.setWidget(0, 0, title);
			eventsTableLayout.setWidget(1, 0, new Label(AppController.getClientFactory().getLabelResource().advanceFind()));
			eventsTableLayout.setWidget(1, 1, new SuggestBox(suggestOracle));
			eventsTableLayout.setWidget(2, 0, table);
			eventsTableLayout.getFlexCellFormatter().setColSpan(0, 0, 2);
			eventsTableLayout.getFlexCellFormatter().setColSpan(2, 0, 2);
			tableScroller.setWidget(eventsTableLayout);
			initWidget(tableScroller);
			setStyleName("nowrap");
			
		}
		
		
		
		private void initSelectionHandlers() {
			table.setSelectionListener(new SelectionListener<DiaryEventDTO>() {

				@Override
				public void rowSelected(DiaryEventDTO rowData) {
					presenter.getEvent(rowData);
					
				}
			});
			suggestOracle.setEventSelectionHandler(new EventSelectionHandler() {
				
				@Override
				public void eventSelected(DiaryEventDTO event) {
					presenter.getEvent(event);
					
				}
			});
			
		}

		private void initTableHeader(){
			title = new Label(AppController.getClientFactory().getLabelResource().events());
			title.getElement().getStyle().setFontSize(36, Unit.PX);
			title.getElement().getStyle().setColor("#878787");
		}
		
		private void initTable() {
			table.addColumn(DiaryEventRecord.createDateColumn(), AppController.getClientFactory().getLabelResource().eventDate());
			table.addColumn(DiaryEventRecord.createTitleColumn(), AppController.getClientFactory().getLabelResource().eventTitle());
		
			
		}

		public void setRowData(int start, List<? extends DiaryEventDTO> values){
			table.clear();
			table.setRowData(start, values);
		}
	}



	

}
