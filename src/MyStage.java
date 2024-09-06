// Areen Fetyani 1212673

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
//MyStage class extends Stage class and has the common nodes and panes in other stages
public class MyStage extends Stage {
	static Inventory store = new Inventory("groceries"); //creating an static object of inventory 
	//declare the panes and nodes that will be used
	public GridPane screen;
	public HBox buttons;
	public Label type, quantity, price, msg, exp;
	public TextField txt1, txt2, txt3, txtexp;
	public Button cancel;
	public Font labelsfont,msgfont;
	public VBox screenmsg;
	public MyStage() {
		super();
		screenmsg = new VBox(20); //create a VBox
		screen = new GridPane(); //create a Gridpane
		buttons = new HBox(10); //create HBox
		
		//creating labels
		type = new Label("Type");
		quantity = new Label("Quantity");
		price = new Label("Price");
		msg = new Label();
		exp = new Label("Expiration date");
		//creating textfields
		txt1 = new TextField();
		txt2 = new TextField();
		txt3 = new TextField();
		txtexp = new TextField();

		
		cancel = new Button("Cancel"); //create cancel button
		CancelHandler cancelhandler = new CancelHandler(); //create an object of the handler
		cancel.setOnAction(cancelhandler); //set the cancel button on action
		//add the labels and textfields to the gridpane
		screen.add(type, 0, 1);
		screen.add(quantity, 0, 2);
		screen.add(price, 0, 3);
		screen.add(txt1, 1, 1);
		screen.add(txt2, 1, 2);
		screen.add(txt3, 1, 3);
		screen.add(exp, 0, 4);
		screen.add(txtexp, 1, 4);

		screen.setVgap(10); //set the vertical gap
		screen.setHgap(10); //set the horizontal
		buttons.setAlignment(Pos.BOTTOM_RIGHT); //make the buttons on the right side of the hbox
		screenmsg.setStyle("-fx-background-color:LIGHTGRAY;"); //set the background color to light gray
		labelsfont = Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 12); //create a bold font with the size 12 
		msgfont = Font.font("Courier New", FontWeight.NORMAL, FontPosture.REGULAR, 16); //create a font with the size 16
		//setting fonts to labels
		type.setFont(labelsfont);
		quantity.setFont(labelsfont);
		price.setFont(labelsfont);
		exp.setFont(labelsfont);
		msg.setFont(msgfont);
		msg.setVisible(false);
		screenmsg.setPadding(new Insets (10,10,10,10)); 
		screenmsg.getChildren().addAll(screen,msg); //setting the gridpane and message label to the main screen that will appear
		Scene scene = new Scene(screenmsg, 470, 320); //creating a scene
		this.setScene(scene);//setting the scene to the stage
	}
	//creating a handler class for the cancel button, clicking on the cancel button will close the stage
	class CancelHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			msg.setText(null);
			MyStage.this.close();
			
		}
		
	}
}
