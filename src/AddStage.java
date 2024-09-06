// Areen Fetyani 1212673

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

//making the add stage class that extends MyStage
public class AddStage extends MyStage {
	// declaring the nodes and panes that will be used
	RadioButton yes, no;
	HBox options;
	Button save;
	Label brand, brandname, message;
	TextField txt4;

	// constructor that calls the super class constructor and add the missing panes
	// and nodes
	AddStage() {
		super();
		RadioButtonsHandler yesnohandler = new RadioButtonsHandler(); // making an object of the handler that will tell
		                                                              // the radiobuttons what to do
		options = new HBox(30);
		brand = new Label("Brand");
		brand.setFont(labelsfont);
		brandname = new Label("Brand Name");
		brandname.setFont(labelsfont);
		brandname.setVisible(false); // making the brandname label invisible until an action handler is used to view
		                             // it
		txt4 = new TextField();
		txt4.setVisible(false); // making the brandname textfield invisible until an action handler is used to
		                        // view it
		// creating the radiobuttons and setting them on action
		yes = new RadioButton("Yes");
		yes.setOnAction(yesnohandler);
		no = new RadioButton("No");
		no.setOnAction(yesnohandler);
		ToggleGroup group = new ToggleGroup();// togglegroup is used so that only one radiobutton can be chosen
		// setting the togglegroup to the radiobuttons
		yes.setToggleGroup(group);
		no.setToggleGroup(group);
		save = new Button("Save");
		options.getChildren().addAll(yes, no);// putting the radio buttons on an hbox names options
		// adding the new nodes to the gridpane
		screen.add(brand, 0, 0);
		screen.add(options, 1, 0);
		screen.add(brandname, 0, 5);
		screen.add(txt4, 1, 5);
		buttons.getChildren().addAll(save, cancel);
		screen.add(buttons, 1, 6);
		SaveButton saveHandler = new SaveButton();
		save.setOnAction(saveHandler);
	}

	// making a handler class to specify what the radiobuttons do
	class RadioButtonsHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {

			if (yes.isSelected()) {// if the radiobutton yes is selected make the brandname label and textfield
			                       // visible
				brandname.setVisible(true);
				txt4.setVisible(true);
			} else if (no.isSelected()) {// if the radiobutton no is selected make the brandname label and textfield
			                             // invisible
				brandname.setVisible(false);
				txt4.setVisible(false);
			}

		}
	}

	// making a handler class for the save button
	class SaveButton implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			msg.setVisible(true);
			try {
				if (yes.isSelected()) { // if the radiobutton yes is selected, create a new item that has a brand and
				                        // display a message that the item is successfully added
					if (txt1.getText() != null && txt2.getText() != null && txt3.getText() != null
					        && txt4.getText() != null)
						store.newItem(txt4.getText(), txt1.getText(), Integer.parseInt(txt2.getText()),
						        Double.parseDouble(txt3.getText()) , txtexp.getText());
					msg.setText(store.getOutput());
				} else if (no.isSelected()) {// if the radiobutton no is selected, create a new item that doesn't have a
				                             // brand and display a message that the item is successfully added
					store.newItem(txt1.getText(), Integer.parseInt(txt2.getText()), Double.parseDouble(txt3.getText()), txtexp.getText());
					msg.setText(store.getOutput());

				} else
					msg.setText("You must choose an option (brand,not brand) .");
			} catch (NumberFormatException ex) {
				System.out.println("Error: The TextFields Are Empty.");
				msg.setText("Error: The TextFields Are Empty.\r\n");

			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt4.clear();
			txtexp.clear();

		}

	}

}
