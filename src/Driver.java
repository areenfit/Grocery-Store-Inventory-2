// Areen Fetyani 1212673

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//the driver class
public class Driver extends Application {
	//declare the panes and nodes that will be used
	Button add, modify, delete, stockreport;
	BorderPane mainscreen;
	HBox bottom;
	Label title;
	Image image;
	ImageView imageview1;
	@Override
	public void start(Stage primaryStage) {
		
		mainscreen = new BorderPane();//the main screen that will show when we run the program is made in borderpane
		bottom = new HBox(10);
		mainscreen.setPadding(new Insets(10, 10, 10, 10));
		title = new Label("Inventory Management System: Comp 2311 Project, Phase 2");
		Font titlefont = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20);//the font that is used for the title
		image = new Image("inventoryManagement.jpg");//we made an image
		imageview1 = new ImageView(image);//this is to view the image
		add = new Button("Add");
		AddHandler addhandler = new AddHandler();//handler for when we press the add button
		add.setOnAction(addhandler);
		modify = new Button("Modify");
		ModifyHandler modifyhandler = new ModifyHandler();//handler for when we press the modify button
		modify.setOnAction(modifyhandler);
		delete = new Button("Delete");
		DeleteHandler deletehandler = new DeleteHandler();//handler for when we press the delete button
		delete.setOnAction(deletehandler);
		stockreport = new Button("Stock Report");
		StockReportHandler stockreporthandler = new StockReportHandler();//handler for when we press the stock report button
		stockreport.setOnAction(stockreporthandler);
		bottom.getChildren().addAll(add, modify, delete, stockreport);//we put the 4 buttons in hbox
		mainscreen.setTop(title);//we set the title to the top of the borderpane
		mainscreen.setCenter(imageview1);//we set the image to the center of the borderpane
		mainscreen.setBottom(bottom);//we set the hbox that includes the 4 buttons to the bottom of the borderpane
		mainscreen.setAlignment(title, Pos.TOP_CENTER);//we put the title in the center of the top of the borderpane
		bottom.setAlignment(Pos.BOTTOM_CENTER);//and the hbox that includes the buttons to the center of the bottom of the borderpane
		title.setStyle("-fx-text-fill:DIMGRAY;");//change the title's color
		title.setFont(titlefont);//change the title's font
		//change the height and width of the image
		imageview1.setFitHeight(180);
		imageview1.setFitWidth(200);
		imageview1.setStyle("-fx-rotate:30;");//rotate the image
		mainscreen.setStyle("-fx-background-color:LIGHTGRAY;");//set the main screen's background color
		Scene scene = new Scene(mainscreen, 550, 350);//create the scene
		primaryStage.setTitle("Inventory Management System");//set the title of the stage
		primaryStage.setScene(scene);//set the scene to the stage
		primaryStage.show();//show the stage
	}
	//handler class for add button
	class AddHandler extends AddStage implements EventHandler<ActionEvent> {
		//when add button is pressed show the add stage with it's title
		@Override
		public void handle(ActionEvent e) {
			this.setTitle("Add New Item");
			this.show();
		}
	}
	//handler class for modify button
	class ModifyHandler extends ModifyStage implements EventHandler<ActionEvent> {
		//when add button is pressed show the modify stage with it's title
		@Override
		public void handle(ActionEvent arg0) {
			this.setTitle("Update Item");
			this.show();
		}
	}
	//handler class for delete button
	class DeleteHandler extends DeleteStage implements EventHandler<ActionEvent> {
		
		//when add button is pressed show the delete stage with it's title
		@Override
		public void handle(ActionEvent arg0) {
			this.setTitle("Delete Item");
			this.show();
		}
	}
	//handler class for stock report button
	class StockReportHandler implements EventHandler<ActionEvent> {
		Stage stockStage;//we declare a new stage because it's different from the others
		Scene stockScene;//new scene is declared
		//nodes and panes will be used in this stage
		Text txt;
		CheckBox exportToFile, area;
		TextArea txtarea;
		TextField fileName;
		Button export;
		VBox exportFile, textArea;
		HBox stockScreen, checkBoxes;
		FlowPane pane;

		@Override
		public void handle(ActionEvent arg0) {
			pane = new FlowPane();
			checkBoxesHandler checkboxeshandler = new checkBoxesHandler();
			pane.setOrientation(Orientation.VERTICAL);//set the orientation of the flowpane to vertical, nodes will be organized vertically
			pane.setAlignment(Pos.TOP_LEFT);
			pane.setPadding(new Insets(10, 10, 10, 10));
			//create a new text give it a string and set it's font
			txt = new Text("The following options can be used to print a report");
			Font txtfont = Font.font(null, FontWeight.BOLD, 20);
			txt.setFont(txtfont);
			//create two checkboxes and set them on action
			exportToFile = new CheckBox("Export a copy to a file");
			exportToFile.setOnAction(checkboxeshandler);
			area = new CheckBox("TextArea");
			area.setOnAction(checkboxeshandler);
			checkBoxes = new HBox(10);
			//put the checkboxes in hbox
			checkBoxes.getChildren().addAll(exportToFile, area);
			//create a text field for the file name and make it invisisble unless the first checkbox is checked
			fileName = new TextField("myFile.txt");
			fileName.setVisible(false);
			//if we clicked on the text field it will be cleared
			MyFiletxtHandler click = new MyFiletxtHandler();
			fileName.setOnMouseClicked(click);
			//create a new button, it will be invisible unless the first checkbox is checked, and set the button on action
			export = new Button("Export");
			export.setVisible(false);
			ExportHandler exporthandler = new ExportHandler();
			export.setOnAction(exporthandler);
			//create a text area, if the area checkbox is pressed, it will make what it's asked to do...
			txtarea = new TextArea();
			TextAreaHandler txtareahandler = new TextAreaHandler();
			area.setOnMousePressed(txtareahandler);
			txtarea.setVisible(false);
			exportFile = new VBox(5);//create a vbox
			exportFile.setPadding(new Insets(5, 5, 5, 5));
			exportFile.getChildren().addAll(exportToFile, fileName, export);//put the export checkbox,filename text field and the export button in it
			textArea = new VBox(70);//create another vbox
			textArea.setPadding(new Insets(5, 5, 5, 5));
			textArea.getChildren().addAll(area, txtarea);//put the area checkbox and text area in it
			stockScreen = new HBox();//create an hbox
			stockScreen.getChildren().addAll(exportFile, textArea);//put the 2 vboxes in it
			pane.getChildren().addAll(txt, stockScreen);//the final screen is a vertical flowpane that has the text and the hbox
			stockStage = new Stage();//create a new stage
			stockStage.setTitle("Stock Report");//set it's title
			stockScene = new Scene(pane, 700, 350);//create a new scene
			stockStage.setScene(stockScene);//set the stage's scene
			stockStage.show();//show the stage
		}
		//handler class for the checkboxes
		class checkBoxesHandler implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent arg0) {
				//if we check both ckeckboxes all contents will become visible
				if (exportToFile.isSelected() && area.isSelected()) {
					fileName.setVisible(true);
					export.setVisible(true);
					txtarea.setVisible(true);
				} else if (exportToFile.isSelected()) {//if we only chose the export checkbox, the filename textfield and export button becomes visible
					fileName.setVisible(true);
					export.setVisible(true);
					txtarea.setVisible(false);
				} else if (area.isSelected()) {//if we only chose the text area checkbox, only the text area will become visible
					txtarea.setVisible(true);
					fileName.setVisible(false);
					export.setVisible(false);
				} else {//if we chose none, none of the contents will become visible
					fileName.setVisible(false);
					export.setVisible(false);
					txtarea.setVisible(false);
				}
			}
		}
		//handler class for the filename text field
		class MyFiletxtHandler implements EventHandler<MouseEvent> {

			@Override
			public void handle(MouseEvent arg0) {
				fileName.setText("");//if we press in the filename text field, it will be cleared
			}

		}
		//handler class for the text area, if we press on the text area checkbox, the stock report info will be displayed in the text area
		class TextAreaHandler extends MyStage implements EventHandler<MouseEvent> {

			@Override
			public void handle(MouseEvent arg0) {
				if (area.isPressed())
					txtarea.setText(store.stockReport());
			}

		}
		//handler class for the export button.
		class ExportHandler extends MyStage implements EventHandler<ActionEvent> {

			@Override
			public void handle(ActionEvent arg0) {
				//when we click on the export button, the stock report info will be displayed in afile
				try {
					//File file = new File(fileName.getText());//we take the file name from the text field
					FileWriter output = new FileWriter(fileName.getText());
					output.append(store.stockReport());
					output.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		}

	}

	public static void main(String[] args) {
		//this creates a bufferreader object wrapping a filereader objet for the file named inventoryDatabase.txt
		try (BufferedReader br = new BufferedReader(new FileReader("inventoryDatabase.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {//reads line by line
				String[] parts = line.split(" - ");//splits the line into 2 parts, the first has the product name and the other has the quantity and price details
				String[] details = parts[1].split(", ");//splits the quantity and price apart
				int quantity = Integer.parseInt(details[0].split(": ")[1]);//thats the quantity value
				double price = Double.parseDouble(details[1].split(": ")[1]);//take sthe price value
				String expDate = details[2].split(": ")[1];
				String[] product = parts[0].split(" ");//splits the product name into two parts using space
				String brand = "";//give an initial value to the brand
				String type = product[0];//if the product length is not more than one, then set the type value to the first index of the array of strings(the only index)
				if (product.length > 1) {// if the product name is more than one word
					brand = product[0];//the first word is the product's brand
					type = product[1];//the second word is the product's type
					MyStage.store.newItem(brand,type, quantity, price,expDate);//create a new item with the previous info (with brand)

				}
				MyStage.store.newItem(type, quantity, price,expDate);//create a new item with the previous info (without brand)

			}
		} catch (IOException e) {
			System.out.println("Error reading file: inventoryDatabase.txt");//if there's a problem with the chosen file, show an exception
			e.printStackTrace();
		}

		launch(args);
	}

}