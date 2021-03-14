package application.librarian;

import dao.Data;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//import javafx.stage.Stage;
import javafx.scene.text.Text;
import model.Info;

/**
 * <h2>Return Item - JavaFx Method Control </h2>
 * 
 * @author Rad Eshghi
 *
 */
public class Librarian_ReturnItem_FX extends Info{
	
	static String BookName_V;
	static int Book_ID; 
	public static int Avalibility_V;
	static String StudentName_V;
	static int StudentID_V; 
	static String StudentPhone_V;
	

	/**
	 * This is the default constructor that will set the variables into empty state.
	 */
	public Librarian_ReturnItem_FX()
	{

		StudentName_V = "";
		StudentID_V = 0;
		StudentPhone_V = "";
	}


	/**
	 * This method is responsible for returning a item back by collecting data from the user and by using<br>
	 * abstract base class Info stores some data and extract to pass into the<br>
	 * data base by calling the Return() method from the data.java file.<br>
	 * Also Created the interface of the scene using the javaFx libraries.<br>
	 * 1. Item Name <br>
	 * 2. Item ID <br>
	 * 3. Student Name <br>
	 * 4. Student ID <br>
	 * 5. Student phoneNumber <br>
	 * 6. Return (Button)<br>
	 * 7. Menu (Button)
	 * @return Parent(VBox): Back to the main scene.
	 * @throws Exception
	 * This also uses try catch method where is needed to handle and reduce exceptions.
	 */
	public static Parent ReturnItem() throws Exception {
		
		Text scenetitle = new Text("Returning Item's");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		Label BookName = new Label("Item Title:");
		TextField textField = new TextField ();
		textField.setPromptText("Robinhood, Miracle, The Irishman");
		hbox.getChildren().addAll(BookName, textField);
		
	
		
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		Label BookID = new Label("Item ID:      ");
		TextField textField2 = new TextField ();
		textField2.setPromptText("1234,3214..");
		hbox2.getChildren().addAll(BookID, textField2);
		
	
		
		HBox hbox3 = new HBox(10);
		hbox3.setAlignment(Pos.CENTER);
		Label StudentName = new Label("Name:          ");
		TextField textFiled3 = new TextField();
		textFiled3.setPromptText("Student Name...");
		hbox3.getChildren().addAll(StudentName, textFiled3);
		
		
		HBox hbox4 = new HBox(10);
		hbox4.setAlignment(Pos.CENTER); 
		Label StudentID = new Label("ID:                ");
		TextField textFiled4 = new TextField();
		textFiled4.setPromptText("Student ID...");
		hbox4.getChildren().addAll(StudentID, textFiled4);
		
		
		HBox hbox5 = new HBox(10);
		hbox5.setAlignment(Pos.CENTER); 
		Label Phone = new Label("Phone:         ");
		TextField textFiled5 = new TextField();
		textFiled5.setPromptText("###-###-####");
		hbox5.getChildren().addAll(Phone, textFiled5);
		
		
		final VBox vbox = new VBox(20);
		vbox.setAlignment(Pos.CENTER);
		
		
		Button Return = new Button("Return");
		Return.setMaxWidth(100);
		Return.setMaxHeight(20);
		Return.setOnAction(e -> {
			
			try
			{
				
				setCOL_Name(textField.getText()); 
				setCOL_ID(Integer.parseInt(textField2.getText().trim())); 
				StudentName_V = textFiled3.getText();
				StudentID_V =  Integer.parseInt(textFiled4.getText().trim());
				StudentPhone_V = textFiled5.getText();
				
				
				Data.Return(getCOL_Name(), getCOL_ID(),StudentName_V,StudentID_V,StudentPhone_V);
			}
			catch(Exception exe)
			{
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Something went wrong while filling in the form!");
				alert.setHeaderText(null);
				alert.showAndWait();
				System.out.println("Error: On Retrun!");
				exe.printStackTrace();
			}
		
		});
		
		
		
		Button Menu = new Button("Menu");
		Menu.setMaxWidth(100);
		Menu.setMaxHeight(20);
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
		
		vbox.getChildren().addAll(scenetitle, hbox, hbox2, hbox3, hbox4, hbox5, Return, Menu);
		
		
		return vbox;		
	}
}
