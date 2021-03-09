package application.librarian;


import dao.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Info;

/**
 * <h2>Add Item - JavaFX And Control</h2>
 * 
 * @author Rad Eshghi
 *
 */

public class AddItem_FX extends Info {

	
	/**
	 * This method is responsible for collecting data from the user and by using<br>
	 * abstract base class Info stores all the data and extract to pass into the<br>
	 * data base by calling the AddItem() method from the data.java file.<br>
	 * Also Created the interface of the scene using the javaFx libraries.<br>
	 * 1. Item Title <br>
	 * 2. Item ID <br>
	 * 3. Author/Director <br>
	 * 4. Status <br>
	 * 5. Category <br>
	 * 6. Type <br>
	 * 
	 * @return Parent(VBox): Back to the main scene.
	 * @throws Exception
	 * This also uses try catch method where is needed to handle and reduce exceptions.
	 */
	public static Parent AddItem() throws Exception {
		
		
		// Create a text field to get item title and store in hbox.
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		Label ItemName = new Label("Item Title:           ");
		TextField textField = new TextField ();
		textField.setPromptText("Book, Movie, Magazine");
		hbox.getChildren().addAll(ItemName, textField);
		
		
		// Create a text field to get item ID and store in hbox2.
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		Label ItemID = new Label("Item ID:               ");
		TextField textField2 = new TextField ();
		textField2.setPromptText("1234,3214..");
		hbox2.getChildren().addAll(ItemID, textField2);
		
		
		// Create a text field to get item Author/Director and store in hbox3.
		HBox hbox3 = new HBox(10);
		hbox3.setAlignment(Pos.CENTER);
		Label ItemAuthor = new Label("Author/Director: ");
		TextField textField3 = new TextField ();
		textField3.setPromptText("William Shakespeare..");
		hbox3.getChildren().addAll(ItemAuthor, textField3);
		
		
		
		// Create a drop box to get item status and store in hbox4.
		HBox hbox4 = new HBox(10);
		hbox4.setAlignment(Pos.CENTER);
		Label ItemStatus = new Label("Status :");
		ObservableList<String> Status_List = FXCollections.observableArrayList(
		    	"--Select--",	
		    	"Available",
		    	"Borrowed",
		    	"Hold"
				);
								
		ComboBox<String> combo0 = new ComboBox<String>(Status_List);
		combo0.setPromptText("Select");
		hbox4.getChildren().addAll(ItemStatus, combo0);

		
		
		// Create a drop box to get item category and store in hbox5.
		HBox hbox5 = new HBox(10);
		hbox5.setAlignment(Pos.CENTER);
		Label Category = new Label("Category:  ");
		ObservableList<String> Categories = FXCollections.observableArrayList(
    	"--Select--",	
        "Comedy",
        "History",
        "Advanture", 
        "Action", 
        "Business", 
        "Improvement"
		);
				
		ComboBox<String> combo = new ComboBox<String>(Categories);
		combo.setPromptText("Select");
		hbox5.getChildren().addAll(Category, combo);
		
		
		// Create a drop box to get item type and store in hbox6.
		HBox hbox6 = new HBox(10);
		hbox6.setAlignment(Pos.CENTER);
		Label Kind = new Label("Type:    ");
		ObservableList<String> Kind_List = FXCollections.observableArrayList(
    	"--Select--",	
        "Book",
        "Magazines",
        "Video"
		);
				
		ComboBox<String> combo2 = new ComboBox<String>(Kind_List);
		combo2.setPromptText("Select");
		hbox6.getChildren().addAll(Kind, combo2);
		
		
		
		// Create a Add Item button to create and add new data into the table by calling the AddBook method in Data.java.
		Button Add = new Button("Add Item"); 
		Add.setMaxWidth(100);
		Add.setMaxHeight(20);
		Add.setOnAction(e ->{
			
			try {
				
				setCOL_Name(textField.getText()); 
				setCOL_ID(Integer.parseInt(textField2.getText())); 
				setCOL_Author(textField3.getText()); 
				setCOL_Status(combo0.getValue());  
				setCOL_Category(combo.getValue()); 
				setCOL_Kind(combo2.getValue()); 
				
			
				Data.AddItem(getCOL_Name(), getCOL_ID(), getCOL_Author(), getCOL_Category(), getCOL_Status(), getCOL_Kind());
				
			} catch (Exception exe) {
				
				// Display a message to let the user know something is wrong.
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Something went wrong while filling in the form!");
				alert.setHeaderText(null);
				alert.showAndWait();
				exe.printStackTrace();
			}
		});

		
		
		// Created a menu button to be able to return back to the menu scene once is clicked.
		Button Menu = new Button("Menu"); 
		Add.setMaxWidth(100);
		Add.setMaxHeight(20);
		Menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					Menu.getScene().setRoot(Librarian_Main_FX.LibrarianMenu());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		// Add all the hbox's, add and menu button into vbox.
		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(hbox, hbox2, hbox3, hbox4, hbox5, hbox6, Add, Menu);
		
		
		return vbox;
	
	}
}
