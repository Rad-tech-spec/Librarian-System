package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private static Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setTitle("Library App | Log In");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeScene(String fxmlFile) {
		try {
			Parent newRoot = FXMLLoader.load(getClass().getResource(fxmlFile));
			currentStage.getScene().setRoot(newRoot);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
