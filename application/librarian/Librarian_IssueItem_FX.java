package application.librarian;

import dao.Data;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Info;

/**
 * <h2>Issue Item - JavaFx Method Control </h2>
 * 
 * @author Rad Eshghi
 *
 */
public class Librarian_IssueItem_FX extends Info{

	
	private static int Avalibility_V;
	private static String StudentName_V;
	private static int StudentID_V; 
	private static String StudentPhone_V;
	
	
	/**
	 * This is the default constructor that will set the variables into empty state.
	 */
	public Librarian_IssueItem_FX()
	{
		Avalibility_V = 0;
		StudentName_V = "";
		StudentID_V = 0;
		StudentPhone_V = "";
	}

	
	/**
	 * This method is responsible for issuing a item by collecting data from the user and by using<br>
	 * abstract base class Info stores some data and extract to pass into the<br>
	 * data base by calling the Issue() method from the data.java file.<br>
	 * Also Created the interface of the scene using the javaFx libraries.<br>
	 * 1. Item Name <br>
	 * 2. Availability (search) <br>
	 * 3. Item ID <br>
	 * 4. Student Name <br>
	 * 5. Student ID <br>
	 * 6. Student phoneNumber <br>
	 * 7. Issue (Button)<br>
	 * 8. Menu (Button)
	 * @return Parent(VBox): Back to the main scene.
	 * @throws Exception
	 * This also uses try catch method where is needed to handle and reduce exceptions.
	 */
	public static Parent IssueItem() throws Exception {
	
		Text scenetitle = new Text("Issuing Item's");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		Label BookName = new Label("Item Name:");
		TextField textField = new TextField ();
		textField.setPromptText("Book, Movie, Magazine");
		hbox.getChildren().addAll(BookName, textField);
		

		
		// Show availability.
		HBox hbox3 = new HBox(10);
		hbox3.setAlignment(Pos.CENTER);
		
		Label Avalibility = new Label("Availability:    " + Avalibility_V);
		
		
		
		Button search = new Button("Search");
		search.setMaxWidth(100);
		search.setMaxHeight(40);
		search.setOnAction(e -> {
			
			try
			{
				setCOL_Name(textField.getText()); 
				
				Data.SearchQuantity(getCOL_Name());
				Avalibility.setText("Availability:    " + Avalibility_V);
				
			}
			catch(Exception exe)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Something went wrong on search!");
				alert.setHeaderText(null);
				alert.showAndWait();
				System.out.println("Error: On search!");
				exe.printStackTrace();
			}
			
			
		});
		
	
		
		
		hbox3.getChildren().addAll(Avalibility, search);
		
		// Prompting book ID.
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.CENTER);
		Label BookID = new Label("Item ID:      ");
		TextField textField2 = new TextField ();
		textField2.setPromptText("1234,3214..");
		hbox2.getChildren().addAll(BookID, textField2);
		
		
		// Prompt Student name.
		HBox hbox4 = new HBox(10);
		hbox4.setAlignment(Pos.CENTER);
		Label StudentName = new Label("Name:          ");
		TextField textField4 = new TextField();
		textField4.setPromptText("Student Name...");
		hbox4.getChildren().addAll(StudentName, textField4);
		
		// Prompt Student ID. 
		HBox hbox5 = new HBox(10);
		hbox5.setAlignment(Pos.CENTER); 
		Label StudentID = new Label("ID:                ");
		TextField textField5 = new TextField();
		textField5.setPromptText("Student ID...");
		hbox5.getChildren().addAll(StudentID, textField5);
		
		// Prompt Student Phone.
		HBox hbox6 = new HBox(10);
		hbox6.setAlignment(Pos.CENTER); 
		Label Phone = new Label("Phone:         ");
		TextField textField6 = new TextField();
		textField6.setPromptText("###-###-####");
		hbox6.getChildren().addAll(Phone, textField6);
		
		
		// Issue a book.
		Button Issue = new Button("Issue");
		Issue.setMaxWidth(100);
		Issue.setMaxHeight(20);
		Issue.setOnAction(e -> {
			
			try
			{
				setCOL_Name(textField.getText()); 
				setCOL_ID(Integer.parseInt(textField2.getText().trim())); 
				StudentName_V = textField4.getText();
				StudentID_V =  Integer.parseInt(textField5.getText().trim());
				StudentPhone_V = textField6.getText();
				
				Data.Issue(getCOL_Name(), getCOL_ID(), Avalibility_V,StudentName_V,StudentID_V,StudentPhone_V);
				

			}
			catch(Exception exe)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Something went wrong while filling in the form!");
				alert.setHeaderText(null);
				alert.showAndWait();
				System.out.println("Error: On Issue!");
				exe.printStackTrace();
			}
			
		});
		
		
		
		// Add all HBox to VBox as a children.
		final VBox vbox = new VBox(20);
		vbox.setAlignment(Pos.CENTER);
		
		
		Button Menu = new Button("Menu");
		Menu.setMaxWidth(100);
		Menu.setMaxHeight(20);
		Menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					Avalibility_V = 0;
					Menu.getScene().setRoot(Librarian_Main_FX.LibrarianMenu());
				} catch (Exception e) {
						
					e.printStackTrace();
				}
			}
		});
		
		vbox.getChildren().addAll(scenetitle, hbox,hbox3,hbox2,hbox4, hbox5, hbox6 ,Issue, Menu);
		
		return vbox;
	}
	
	/**
	 * This method adds the integer value in the parameter into the availability.
	 * @param num
	 * Integer value
	 */
	public static void setAvalibility_V_Increment(int num)
	{
		Avalibility_V += num;
	}
	
	public static void setAvalibility_V(int num2)
	{
		Avalibility_V = num2;
	}
}
