package com.lebsh.diary.client.ui.components;

import java.util.List;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.lebsh.diary.client.AppController;
import com.lebsh.diary.client.model.DiaryEventRecord;
import com.lebsh.diary.client.ui.DiaryEventsView;
import com.lebsh.diary.client.ui.EnvCellTable;
import com.lebsh.diary.client.ui.EnvCellTable.SelectionListener;
import com.lebsh.diary.shared.DiaryEventDTO;

public class EventsTable extends Composite {
	private DiaryEventsView.Presenter presenter;
	private ScrollPanel tableScroller = new ScrollPanel();
	private FlexTable eventsTableLayout = new FlexTable();
	private EnvCellTable<DiaryEventDTO> table = new EnvCellTable<DiaryEventDTO>();
	private Label title;
	public EventsTable() {
		initTableHeader();
		initTable();
		eventsTableLayout.getElement().getStyle().setBackgroundColor("#FFDAB9");
		eventsTableLayout.setWidget(0, 0, title);
		eventsTableLayout.setWidget(1, 0, table);
		tableScroller.setWidget(eventsTableLayout);
		initWidget(tableScroller);
		setStyleName("nowrap");
//		setWidth("100%");
		
	}
	
	private void initTableHeader(){
		title = new Label(AppController.getClientFactory().getLabelResource().events());
		title.getElement().getStyle().setFontSize(36, Unit.PX);
		title.getElement().getStyle().setColor("#878787");
		
	
		
	}
	
	private void initTable() {
		table.addColumn(DiaryEventRecord.createDateColumn(), AppController.getClientFactory().getLabelResource().eventDate());
		table.addColumn(DiaryEventRecord.createTitleColumn(), AppController.getClientFactory().getLabelResource().eventTitle());
		
		table.setSelectionListener(new SelectionListener<DiaryEventDTO>() {

			@Override
			public void rowSelected(DiaryEventDTO rowData) {
				presenter.getEvent(rowData);
				
			}
		});
		
	}

	public void setRowData(int start, List<? extends DiaryEventDTO> values){
		table.setRowData(start, values);
	}

	public void setPresenter(DiaryEventsView.Presenter p) {
		presenter = p;
		
	}
}
