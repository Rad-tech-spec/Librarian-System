package application;

import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
//import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class LogIn {
	
	public LogIn() {}
	
	@FXML
	private Button logInButton;
	@FXML
	private Label feedback;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	public void handleLogIn(ActionEvent event) {
		verifyCredentials();
	}
	
	private void verifyCredentials() {
        try {
        	Main m = new Main();
            
            if (username.getText().toString().equals("Nikita") && password.getText().toString().equals("123456")) {
            	feedback.setText("Success.");
                m.changeScene("studentView.fxml");
            }
            else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            	feedback.setText("Please enter username and password.");
            }
            else {
            	feedback.setText("Invalid Credentials.");
            }
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
}
