package gwt.com.client.view;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class IntroView extends Composite {
	VerticalPanel container;
	Label questionLabel;
	TextBox numberTextBox;
	Button enterButton;

	
	public IntroView(){
		container = new VerticalPanel();
		container.addStyleName("content");
		container.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		container.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		questionLabel= new Label("How many numbers to display?");
		numberTextBox = new TextBox();
		numberTextBox.setStyleName("intro-textbox");
		enterButton = new Button("Enter");
		enterButton.setStyleName("intro-button");
		
		container.setCellWidth(numberTextBox, "100");
		container.setCellWidth(enterButton, "100");
		container.add(questionLabel);
		container.add(numberTextBox);
		container.add(enterButton);
		
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	public void addButtonListener(ClickHandler clickHandler) {
		enterButton.addClickHandler(clickHandler);
		
	}
	
	public HasClickHandlers getLoginButton() {
		return enterButton;
	}
	
	public TextBox getNumberTextBox() {
		return numberTextBox;
	}

	public void addKeyHandler(KeyUpHandler keyUpHandler) {
		numberTextBox.addKeyUpHandler(keyUpHandler);
		
	}

	

}
