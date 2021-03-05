package application;

import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * This class is responsible for loading Student View (studentView.fxml) and handling its events.
 */
public class StudentView {
	/**
	 * Creates an instance of StudentView class
	 */
	public StudentView() { }
	
	/**
	 * Returns Student View fxml layout
	 * @return Parent containing fxml layout
	 * @throws IOException
	 */
	public Parent getStudentView() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("studentView.fxml"));
		return root;
	}
}
