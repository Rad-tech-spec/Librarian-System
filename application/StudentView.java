package application;

import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class StudentView {
	
	public StudentView() { }
	
	@FXML
	private Button logOutButton;
	
	public void handleLogOut(ActionEvent event) {
		try {
			Main m = new Main();
			m.changeScene("index.fxml");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
