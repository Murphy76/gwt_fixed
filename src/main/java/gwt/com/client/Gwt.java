package gwt.com.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import gwt.com.client.controller.MainController;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwt implements EntryPoint {
	public void onModuleLoad() {
		MainController ctrl = new MainController();
		RootPanel.get().add(ctrl.getIntro().asWidget());
	}
}
