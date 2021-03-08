/**********************************************
Assignment #1
Course:BTP 400 - Semester 4
Last Name: Eshghi
First Name: Rad
ID: 123348195
Section: NAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature R.E
Date: March-8-2021
Team: 10
**********************************************/
package librarian;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * <h2>Main - Primary Stage Control class</h2>
 * @author Rad Eshghi
 * @since March-7-2021
 */
public class Main extends Application{

	/**
	 * This is the main method that will me executed first and will lunch the start method<br>
	 * and pass the args to the parameter. Also handles exceptions by using try catch method.
	 * @param args
	 * @return Void
	 */
	public static void main(String[] args) {
		
		try
		{
			launch(args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * This is the main method that will call and handle the javaFx file and create the scene.<br>
	 * By calling the LibraryMenu() method which is implemented and defined in Librarian_Main_FX<br>
	 * class we are able to control the buttons and actions.
	 * @param ps
	 * The parameter of the method holds all the needed libraries to be used to set scene title and<br>
 	 * measures of the scene also to show the scene. 

	 */
	@Override
	public void start(Stage ps) throws Exception {
		
		VBox LibrarianMenu = Librarian_Main_FX.LibrarianMenu();
		
		Scene scene = new Scene(LibrarianMenu, 400, 400);
		ps.setTitle("Librarian Main Menu");
		ps.setScene(scene);
		ps.show();
	}
}