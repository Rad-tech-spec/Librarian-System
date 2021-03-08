package assignment1package;

import application.Login;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Alexander Samaniego
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane login = Login.loginMenu();

		Scene scene = new Scene(login, 600, 600);
		primaryStage.setTitle("Library Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}