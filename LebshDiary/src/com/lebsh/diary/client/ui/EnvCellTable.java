package com.lebsh.diary.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 * extending CellTable in order to support:
 * - remove selected row
 * - remove row by index
 * - add new row
 * - set a selection listener ( setSelectionListener(...) )
 * - set tool tip text / widget ( setTooltipProvider(...) )
 * @author einavl
 *
 * @param <T>
 */
public class EnvCellTable<T> extends CellTable<T> {

	private TooltipPanel toolTipPanel = new TooltipPanel();
	private TooltipTextProvider tooltipTextProvider = null;
	private TooltipWidgetProvider tooltipWidgetProvider = null;
	private SelectionListener<T> selectionListener;
	public EnvCellTable(){
		setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		initHandlers();
		setRowCount(0, true); // disable annoying loading status indicator
	}
	

	
	
	/**
	 * remove selected row
	 */
	public T removeSelectedRow(){
		T selected = ((SingleSelectionModel<T>)getSelectionModel()).getSelectedObject();
		List<T> items = getDisplayedItems();
		 items.remove(selected);
		setVisibleRangeAndClearData(new Range(0, 100), true);
		setRowData( 0 , items);
		if(items.size() == 0){
			setRowCount( 0 , true); // disable annoying loading status indicator
		}
		return selected;
		
		
	}
	/**
	 * remove row by index
	 * @param row
	 */
	
	public void removeRow(int row){
		T rowToRemove = getDisplayedItem(row);
		List<T> items = getDisplayedItems();
		 items.remove(rowToRemove);
		setVisibleRangeAndClearData(new Range(0, 100), true);
		if(items.size() == 0){
			setRowCount( 0 , true); // disable annoying loading status indicator
		}
	}
	
	/**
	 * hooking the "mouseover" and "mouseout" events in order to handle our tool tip and cursor icon
	 * 
	 */
	@Override
	protected void onBrowserEvent2(Event aEvent) {
		super.onBrowserEvent2(aEvent);
		 toolTipPanel.hide();
		 String eventType = aEvent.getType();
		 if ("mouseout".equals(eventType)){
			 DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "default");
		 }
		 if ("mouseover".equals(eventType)){
			 DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "pointer");
		 }
		
		if(tooltipTextProvider == null && tooltipWidgetProvider == null){
			return; // nothing else to do here; tooltipXXXProvider was not supplied 
		}
		 Element target = aEvent.getEventTarget().cast();
		 TableCellElement cellElem = findNearestParentCell(target);
		 TableRowElement row = TableRowElement.as(cellElem.getParentElement());
		 if ("mouseover".equals(eventType) && row.getRowIndex()-1>=0 && row.getRowIndex()-1 <getRowCount()) { // make sure we in the correct event and in safe range of rows
			
			 toolTipPanel.setTip(row.getRowIndex()-1 , aEvent);
			 toolTipPanel.show();
		 }
	}
	
	
	/**
	 * add new empty row
	 * @param rowData - row to add
	 * @param setSelected - set the new row selected
	 */
	public void addNewRow(T rowData , boolean setSelected){
		ArrayList<T> list = new ArrayList<T>();
		list.add(rowData);
		setRowData( getRowCount() , list);
		if(setSelected)
			getSelectionModel().setSelected(rowData, true);
	}
	
	/**
	 * set tool tip provider that provides a tool tip text for each row
	 * @param provider - provides text to the row tool tip
	 */
	 public void setTooltipProvider(TooltipTextProvider provider) {
		 tooltipTextProvider = provider;
		  }
	 /**
	  * set tool tip provider that provides a tool tip widget for each row
	  * @param provider - provides widget to the row tool tip
	  */
	 public void setTooltipProvider(TooltipWidgetProvider provider) {
		 tooltipWidgetProvider = provider;
	 }
	
	public void clear(){
		setVisibleRangeAndClearData(new Range(0, 100), true);
		setRowCount(0, true); // disable annoying loading status indicator
	}
	/**
	 * set selection listenr for rows selection
	 * @param l
	 */
	public void setSelectionListener(SelectionListener<T> l){
		selectionListener = l;
	}
	
	private void initHandlers(){
		final SingleSelectionModel<T> selectionModel = new SingleSelectionModel<T>(){
			@Override
			protected void fireSelectionChangeEvent() {
				if (isEventScheduled()) {
					setEventCancelled(true);
				}
				SelectionChangeEvent.fire(this);
			}
			
		};
		setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler(){
			public void onSelectionChange(SelectionChangeEvent event){
				T selected = selectionModel.getSelectedObject();
				if (selected != null && selectionListener != null) {
					selectionListener.rowSelected(selected);
				}
			}
		});
	}
	
	 private TableCellElement findNearestParentCell(Element elem) {
		    while (elem != null) {
		     
		      String tagName = elem.getTagName();
		      if ("td".equalsIgnoreCase(tagName) || "th".equalsIgnoreCase(tagName)) {
		        return elem.cast();
		      }
		      elem = elem.getParentElement();
		    }
		    return null;
		  }
	
	
	public interface SelectionListener<T>{
		public void rowSelected(T rowData);
	}
	
	private class TooltipPanel extends PopupPanel{
		Label text = new Label();
		public TooltipPanel(){
			setWidget(text);
			// if you like animated tool tip - turn it on here:
//			setAnimationEnabled(true);
		}
		public void setTip(int rowIndex , Event aEvent){
			int left = aEvent.getClientX()+10;
			 int top = aEvent.getClientY()+10;
			setPopupPosition(left, top);
			if(tooltipWidgetProvider != null){
				setWidget(tooltipWidgetProvider.getTooltipWidget(rowIndex));
			}
			else if(tooltipTextProvider != null){
				if(getWidget()!= text)
					setWidget(text);
				text.setText(tooltipTextProvider.getTooltipText(rowIndex));
			}
		}
	}
	
	/**
	 * provide tool tip text to the given row index
	 * @author einavl
	 *
	 */
	public interface TooltipTextProvider{
		public String getTooltipText(int rowIndex);
	}
	/**
	 * provide tool tip widget to the given row index
	 * @author einavl
	 *
	 */
	public interface TooltipWidgetProvider{
		public Widget getTooltipWidget(int rowIndex);
	}
	
}
