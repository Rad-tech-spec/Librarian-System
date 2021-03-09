package application.librarian;


import dao.Data;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * <h2>View Item - JavaFX And Control</h2>
 * @author Rad Eshghi
 */
public class Librarian_ViewBook_FX{


	private static int NumOfBook;
	private static int NumOfMagazine; 
	private static int NumOfVideo;
	private static int flag = 0;

	/**
	 * This is the default constructor that will set the variables into empty state.
	 */
	public Librarian_ViewBook_FX()
	{
	
		NumOfBook = 0; 
		NumOfMagazine = 0;
		NumOfVideo = 0;
	}

	
	/**
	 * This method is managing all the steps that needs to be taken to call the data methods<br>
	 * to connect to the data base and to create a table. Also to manage and create the interface<br>
	 * using the javaFx to display the following:<br>
	 * 1. Generate File (Button)<br>
	 * 2. Show number of Books><br>
	 * 3. Show number of Magazines<br>
	 * 4. Show number of Videos <br>
	 * 5. Return to menu (Button)
	 * 
	 * @return Parent(VBox): Back to the main scene.  
	 * @throws Exception
	 * This method also uses try catch method where is needed to reduce and handle exceptions.
	 */
	public static Parent ViewBook() throws Exception {
		
		
		// To get the quantity.
		if(flag == 0)
			Data.GetQuantityType();
		
		// Increment to avoid duplication
		flag++;
		
		// Generate a button for Generating a file and store in hbox.
		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.TOP_CENTER);
		Button View = new Button("Generate File");
		View.setMaxWidth(100);
		View.setMaxHeight(10);
		View.setOnAction(e -> {
			
			try
			{
				Data.GenerateFile();
				System.out.println(NumOfBook);
			}
			catch (Exception exe)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Something went wrong on search!");
				alert.setHeaderText(null);
				alert.showAndWait();
				exe.printStackTrace();
			}
			
		});
		
		hbox.getChildren().add(View);

		
		// Create a text field to show number of books and store in hbox2.
		HBox hbox2 = new HBox(10);
		hbox2.setAlignment(Pos.BOTTOM_CENTER);
		Label label = new Label("Books#:      ");
		TextField textField = new TextField ();
		textField.setText("" + NumOfBook);
		textField.setMaxWidth(40);
		textField.setEditable(false);
		hbox2.getChildren().addAll(label, textField);
		
		
		
		// Create a text field to show number of magazines and store in hbox3.
		HBox hbox3 = new HBox(10);
		hbox3.setAlignment(Pos.BOTTOM_CENTER);
		Label label2 = new Label("Magazines#:");
		TextField textField2 = new TextField ();
		textField2.setText("" + NumOfMagazine);
		textField2.setMaxWidth(40);
		textField2.setEditable(false);
		hbox3.getChildren().addAll(label2, textField2);
		
		
		// Create a text field to show number of video and store in hbox4.
		HBox hbox4 = new HBox(10);
		hbox4.setAlignment(Pos.BOTTOM_CENTER);
		Label label4 = new Label("Videos#:       ");
		TextField textField3 = new TextField ();
		textField3.setText("" + NumOfMagazine);
		textField3.setMaxWidth(40);
		textField3.setEditable(false);
		hbox4.getChildren().addAll(label4, textField3);
		
		
		// Create a VBox to store all the HBox's in there. 
		final VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		
		
		// Created a menu button to return to the main scene.
		Button Menu = new Button("Menu");
		Menu.setMaxWidth(100);
		Menu.setMaxHeight(20);
		Menu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					// Initializing back into empty state to avoid duplication for multiple click. 
					NumOfBook = 0; 
					NumOfMagazine = 0;
					NumOfVideo = 0;
					flag = 0;
					Menu.getScene().setRoot(Librarian_Main_FX.LibrarianMenu());
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
		});
		
		// Adding all the hbox;s and menu into the vbox.
		vbox.getChildren().addAll(hbox, hbox2, hbox3, hbox4 ,Menu);
		
		return vbox;		
	}
	

	/**
	 * This method adds the number in the parameter into NumOfBooks.
	 * @param num 
	 * An integer value.
	 */
	public static void setNumOfBook(int num)
	{
		NumOfBook += num;  
	}
	

	/**
	 * This method return the value of the NumOfBooks.
	 * @return Integer value
	 */
	public static int getNumOfBook()
	{
		return NumOfBook;  
	}
	
	
	/**
	 * This method adds the number in the parameter into NumOfMagazine.
	 * @param num 
	 * An integer value.
	 */
	public static void setNumOfMagazine(int num)
	{
		NumOfMagazine += num;  
	}
	
	
	/**
	 * This method return the value of the NumOfMagazine.
	 * @return Integer value
	 */
	public static int getNumOfMagazine()
	{
		return NumOfMagazine;  
	}
	
	
	/**
	 * This method adds the number in the parameter into NumOfVideo.
	 * @param num 
	 * An integer value.
	 */
	public static void setNumOfVideo(int num)
	{
		NumOfVideo += num;  
	}
	
	/**
	 * This method return the value of the NumOfVideo.
	 * @return Integer value
	 */
	public static int getNumOfVideo()
	{
		return NumOfVideo;  
	}
}
