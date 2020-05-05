package gwt.com.client.controller;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import gwt.com.client.model.Model;
import gwt.com.client.view.IntroView;
import gwt.com.client.view.SortScreen;
import gwt.com.shared.FieldVerifier;

public class MainController {

	private IntroView intro;
	private SortScreen sortScreen;
	private Model model;
	final SimpleEventBus bus = new SimpleEventBus();
	
	public MainController() {
		this.model = new Model();
		

	}

	public void initIntroListeners() {
		intro.addButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {
				checkClick();
			}
		});
		intro.addKeyHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					checkClick();
				}
			}

		});

	}

	private void checkClick() {
		String value = intro.getNumberTextBox().getValue();
		if (FieldVerifier.isValidNumber(value)) {
			model.setNumbers(Integer.parseInt(value));
			getSortScreen().buildContent(model.getNumbers());
			repaintRoot(getSortScreen().asWidget());
		} else {
			Window.alert("Please, enter a positive integer number from 1 to 1000.");
		}
	}

	public void initSortListeners() {
		sortScreen.addUpToThirtyButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String value = ((Button) event.getSource()).getHTML();
				model.setNumbers(Integer.parseInt(value));
				getSortScreen().buildContent(model.getNumbers());
				repaintRoot(getSortScreen().asWidget());
			}

		});
		sortScreen.addSortButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (model.getNumbersSize() > 1) {
					model.sortArray(bus);
				}
			}
		});
		sortScreen.addResetButtonListener(new ClickHandler() {
			public void onClick(ClickEvent event) {

				intro.getNumberTextBox().setText("");
				repaintRoot(intro.asWidget());
			}
		});

	}

	private void repaintRoot(Widget composite) {
		RootPanel.get().clear();
		RootPanel.get().add(composite.asWidget());
	}

	public IntroView getIntro() {
		if (intro == null) {
			intro = new IntroView();
			initIntroListeners();
		}
		return intro;
	}

	public SortScreen getSortScreen() {
		if (sortScreen == null) {
			sortScreen = new SortScreen(model);
			bus.addHandler(GWTEvent.TYPE, new GWTEventHandler() {
				@Override
				public void onEvent(GWTEvent event) {
					Timer refreshTimer = new Timer() {
						@Override
						public void run() {
							
							refreshWatchList(event.getCutPoint());
						}
					};
					refreshTimer.schedule(0);
				}
			});
			initSortListeners();
		}
		return sortScreen;
	}

	private  void refreshWatchList(int [] cutPoint) {
			getSortScreen().updateGrid(cutPoint);
	}

}
