// Areen Fetyani 1212673

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//making the modify stage class that extends MyStage
public class ModifyStage extends MyStage {
	// declaring the nodes and panes that will be used
	ComboBox<String> cb;
	TextField txt5;
	Button search, update;

	// constructor that calls the super class constructor and add the missing panes
	// and nodes
	ModifyStage() {
		super();
		CbHandler cbhndler = new CbHandler(); // creating a handler object for the combobox
		cb = new ComboBox<String>();// creating the combobox
		cb.getItems().addAll("Brand", "Not Brand");// setting the values of the combobox
		cb.setEditable(true);// make the box editable so you can type the "brand" and "not brand" without
		                     // selecting them
		cb.setPrefWidth(150);// resetting the combobox width
		cb.setOnAction(cbhndler);// setting the combobox on action so that when you choose an option it does what
		                         // its asked to do
		SearchHandler searchhandler = new SearchHandler();// creating an object for the search button handler
		search = new Button("Search");// creating the search button
		search.setVisible(false);// making the search button invisible
		search.setOnAction(searchhandler);// setting the search button on action
		txt5 = new TextField("Brand Name");// making a new textfield for the brand name
		BrandNameTextHandler bnth = new BrandNameTextHandler();// making a handler object for the brand name text field
		txt5.setOnMousePressed(bnth);// setting handler will be executed when the mouse is pressed
		// creating the update button and make a handler object for it then set the
		// button on action
		update = new Button("Update");
		UpdateHandler updatehandler = new UpdateHandler();
		update.setOnAction(updatehandler);
		txt1.setText("Item Type");
		txt1.setOnMousePressed(bnth);
		txt2.setEditable(false);// make the quantity and price textfield uneditable unless the search button is
		                        // clicked
		txt3.setEditable(false);
		buttons.getChildren().addAll(update, cancel);
		// add the new nodes to the gridpane
		screen.add(cb, 1, 0);
		screen.add(buttons, 1, 5);
		screen.add(search, 3, 1);
	}

	// handler class for the combobox
	class CbHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (cb.getSelectionModel().getSelectedIndex() == 0) {// if the brand option is selected
				screen.add(txt5, 2, 1);// add the brand name text field on the screen
				search.setVisible(true);// make the search button visible
			} else if (cb.getSelectionModel().getSelectedIndex() == 1) {// if the not brand option is selected
				for (Node node : screen.getChildren()) {// remove the brand name text field by going through all the
				                                        // nodes in screen pane and chosing the node on the first row
				                                        // and second column to remove
					if (screen.getRowIndex(node) == 1 && screen.getColumnIndex(node) == 2) {
						screen.getChildren().remove(node);
						break;
					}
				}
				search.setVisible(true);// make the search button visible
			}
		}
	}

	// handler class for the brand name text field
	class BrandNameTextHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent e) {
			// when the text is pressed it's content is cleared
			if (txt5.isPressed())
				txt5.setText(null);
			if (txt1.isPressed())
				txt1.setText(null);
		}
	}

	// handler class for the search button
	class SearchHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			msg.setVisible(true);
			// when the search button is pressed the quantity and price text fields become
			// editable
			txt2.setEditable(true);
			txt3.setEditable(true);
			if (cb.getSelectionModel().getSelectedIndex() == 0) {// if the brand option is selected
				// search for the item that has a brand in the inventory
				if (store.findItem(txt1.getText(), false, txt5.getText()) != -1) {
					Item item = store.getInventory().get(store.findItem(txt1.getText(), false, txt5.getText()));// if it's found
					// give the update quantity and price text field an initial value 0, so you
					// don't leave it empty
					txt2.setText("0");
					txt3.setText("0");
					txtexp.setText(item.getExpDate());
				} else {// creating a new stage and warning window in a VBox that has a label and a
				        // button
					VBox warning = new VBox(10);
					Label msg = new Label("Cannot find Item type");
					Button ok = new Button("OK");
					warning.getChildren().addAll(msg, ok);
					warning.setAlignment(Pos.CENTER);

					Scene scene = new Scene(warning, 300, 100);
					Stage stage = new Stage();
					stage.setTitle("warning");
					stage.setScene(scene);
					stage.show();
				}
				msg.setText(store.getOutput());

			} else if (cb.getSelectionModel().getSelectedIndex() == 1) {// if the not brand option is selected...

				if (store.findItem(txt1.getText(), false) != -1) {
					Item item = store.getInventory().get(store.findItem(txt1.getText(), false));
					txt2.setText("0");
					txt3.setText("0");
					txtexp.setText(item.getExpDate());

				} else {
					VBox warning = new VBox(10);
					Label msg = new Label("Cannot find Item type");
					Button ok = new Button("OK");
					warning.getChildren().addAll(msg, ok);
					warning.setAlignment(Pos.CENTER);

					Scene scene = new Scene(warning, 300, 100);
					Stage stage = new Stage();
					stage.setTitle("Inventory Management System");
					stage.setScene(scene);
					stage.show();

				}
				msg.setText(store.getOutput());

			} else
				msg.setText("You must choose an option (brand,not brand) .");
		}
	}

	// handler class for update button
	class UpdateHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			msg.setVisible(true);
			try {
				if (cb.getSelectionModel().getSelectedIndex() == 0) {// if the brand option is selected update the item
				                                                     // that
				                                                     // has a brand and display a message that tells
				                                                     // what
				                                                     // has happened
					store.update(txt5.getText(), txt1.getText(), Integer.parseInt(txt2.getText()));
					store.update(txt5.getText(), txt1.getText(), Double.parseDouble(txt3.getText()));
					store.update(txt5.getText(), txt1.getText(), txtexp.getText());
					msg.setText(store.getOutput());
				} else if (cb.getSelectionModel().getSelectedIndex() == 1) {// if the not brand option is selected
				                                                            // update
				                                                            // the item that doesn't have a brand
					store.update(txt1.getText(), Integer.parseInt(txt2.getText()));
					store.update(txt1.getText(), Double.parseDouble(txt3.getText()));
					store.update(txt1.getText(), txtexp.getText());
					msg.setText(store.getOutput());

				} else
					msg.setText("You must choose an option (brand,not brand) .");
			} catch (Exception e) {
				msg.setText("Error: The TextFields Are Empty.");
				System.out.println("Error: The TextFields Are Empty.");
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt5.clear();
			txtexp.clear();
		}
	}
}
