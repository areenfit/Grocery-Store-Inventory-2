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

//making the delete stage class that extends MyStage
public class DeleteStage extends MyStage {
	// declaring the nodes and panes that will be used
	ComboBox<String> cb;
	TextField txt5;
	Button search, remove;

	// constructor that calls the super class constructor and add the missing panes
	// and nodes
	DeleteStage() {
		// similar to modify stage but instead of the update button we put a delete
		// button
		CbHandler cbhndler = new CbHandler();
		cb = new ComboBox<String>();
		cb.getItems().add("Brand");
		cb.getItems().add("Not Brand");
		cb.setEditable(true);
		cb.setPrefWidth(150);
		cb.setOnAction(cbhndler);
		SearchHandler searchhandler = new SearchHandler();
		search = new Button("Search");
		search.setVisible(false);
		search.setOnAction(searchhandler);
		txt5 = new TextField();
		BrandNameTextHandler bnth = new BrandNameTextHandler();
		txt5.setOnMousePressed(bnth);
		remove = new Button("Remove");
		txt1.setText("Item Type");
		txt1.setOnMousePressed(bnth);
		txt2.setEditable(false);
		txt3.setEditable(false);
		buttons.getChildren().addAll(remove, cancel);
		screen.add(cb, 1, 0);
		screen.add(buttons, 1, 5);
		screen.add(search, 3, 1);
	}

	// combobox handler similar to the one in modify stage
	class CbHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			if (cb.getSelectionModel().getSelectedIndex() == 0) {
				screen.add(txt5, 2, 1);
				search.setVisible(true);
			} else if (cb.getSelectionModel().getSelectedIndex() == 1) {
				for (Node node : screen.getChildren()) {
					if (screen.getRowIndex(node) == 1 && screen.getColumnIndex(node) == 2) {
						screen.getChildren().remove(node);
						break;
					}
				}
				search.setVisible(true);

			}
		}
	}

	// search button handler similar to the one in the modify stage, but the
	// quantity and price text fields stay uneditable because there is no need for
	// them
	class SearchHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			RemoveHandler removehandler = new RemoveHandler();
			remove.setOnAction(removehandler);
			msg.setVisible(true);
			if (cb.getSelectionModel().getSelectedIndex() == 0) {

				if (store.findItem(txt1.getText(), false, txt5.getText()) != -1) {
					Item item = store.getInventory().get(store.findItem(txt1.getText(), false, txt5.getText()));
					// here we put the quantity and the price of the item we want to remove in the
					// text fields
					txt2.setText(Integer.toString(item.getQuantity()));
					txt3.setText(Double.toString(item.getPrice()));
					txtexp.setText(item.getExpDate());

				} else {
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

			} else if (cb.getSelectionModel().getSelectedIndex() == 1) {

				if (store.findItem(txt1.getText(), false) != -1) {
					Item item = store.getInventory().get(store.findItem(txt1.getText(), false));
					txt2.setText(Integer.toString(item.getQuantity()));
					txt3.setText(Double.toString(item.getPrice()));
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
			}
		}
	}

	// brand name text field handler class
	private class BrandNameTextHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent e) {
			// when the text field is pressed its content is cleared
			if (txt5.isPressed())
				txt5.setText(null);
			if (txt1.isPressed())
				txt1.setText(null);
		}
	}

	// handler class for the delete button
	class RemoveHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			msg.setVisible(true);
			if (cb.getSelectionModel().getSelectedIndex() == 0) {// if the brand option is selected update the item
			                                                     // that
			                                                     // has a brand and display a message that tells
			                                                     // what
			                                                     // has happened
				if (txt1.getText() != null && txt5.getText() != null) {
					store.removeItem(txt1.getText(), txt5.getText());
					msg.setText(store.getOutput());
				} else
					msg.setText("Error: TextField Are Empty");
			} else if (cb.getSelectionModel().getSelectedIndex() == 1) {// if the not brand option is selected
			                                                            // update
			                                                            // the item that doesn't have a brand
				if (txt1.getText() != null) {
					store.removeItem(txt1.getText());
					msg.setText(store.getOutput());
				} else
					msg.setText("Error: TextField Are Empty");
			}
			txt1.clear();
			txt2.clear();
			txt3.clear();
			txt5.clear();
			txtexp.clear();
		}

	}
}
