package gwt.com.client.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.com.client.model.Model;

public class SortScreen extends Composite {
	private static final int LIMIT_NUMBER_VALUE = 30;
	private static final int ROWS_IN_RESULT = 10;
	static int counter = 0;
	HorizontalPanel container;

	ClickHandler specialHandler;
	ClickHandler sortHandler;
	ClickHandler resetHandler;
	private Model model;
	private Grid grid;
	private ScrollPanel sp;
	private Button sort;
	private Button reset;

	public SortScreen(Model model) {
		this.model = model;

	}

	public void buildContent(int[] numbers) {
		container = new HorizontalPanel();
		container.setStyleName("fixed-table");
		grid = new Grid(ROWS_IN_RESULT, getColumns(numbers.length));
		grid.setStyleName("grid-style");
		updateGrid(numbers);
//		for (int idx = 0; idx < numbers.length; idx++) {
//			int caption = numbers[idx];
//			int column = (int) Math.ceil(idx / 10);
//			int row = idx - 10 * column;
//			Button itemButton = new Button(String.valueOf(caption));
//			if (caption <= LIMIT_NUMBER_VALUE) {
//				itemButton.addClickHandler(specialHandler);
//			} else {
//				itemButton.addClickHandler(new ClickHandler() {
//					public void onClick(ClickEvent event) {
//						Window.alert("Please select a value smaller or equal to 30.");
//						new PopupPanel().show();
//					}
//				});
//			}
//			itemButton.setWidth("60px");
//			itemButton.setStyleName("number-button");
//			grid.setWidget(row, column, itemButton);
//		}

		VerticalPanel controlPanel = new VerticalPanel();
		sort = new Button("Sort", sortHandler);
		reset = new Button("Reset", resetHandler);
		sort.setStyleName("control-button");
		reset.setStyleName("control-button");

		controlPanel.add(sort);
		controlPanel.add(reset);
		controlPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		controlPanel.setSpacing(10);

		grid.setBorderWidth(3);
		controlPanel.setBorderWidth(10);
		sp = new ScrollPanel(grid);
		container.add(sp);
		container.add(controlPanel);
		container.setCellWidth(controlPanel, "20%");
		container.setCellWidth(grid, "70%");
		container.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		container.setWidth("100%");
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	public int getColumns(int size) {
		double column = Math.floorDiv(size - 1, 10) + 1;
		return (int) Math.ceil(column);
	}

	public void addUpToThirtyButtonListener(ClickHandler clickHandler) {
		specialHandler = clickHandler;

	}

	public void addSortButtonListener(ClickHandler clickHandler) {
		sortHandler = clickHandler;

	}

	public void addResetButtonListener(ClickHandler clickHandler) {
		resetHandler = clickHandler;

	}

	public Grid getGrid() {
		return grid;
	}

	public void updateGrid(int[] numbers) {
		grid.clear();
		for (int idx = 0; idx < numbers.length; idx++) {
			int caption = numbers[idx];
			int column = (int) Math.ceil(idx / 10);
			int row = idx - 10 * column;
			Button itemButton = new Button(String.valueOf(caption));
			if (caption <= LIMIT_NUMBER_VALUE) {
				itemButton.addClickHandler(specialHandler);
			} else {
				itemButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.alert("Please select a value smaller or equal to 30.");
						new PopupPanel().show();
					}
				});
			}
			itemButton.setWidth("60px");
			itemButton.setStyleName("number-button");
			grid.setWidget(row, column, itemButton);
		}
	}


}
