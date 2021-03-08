package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Load Student View
			FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlViews/StudentView.fxml"));
			Parent root = loader.load();
			StudentViewController viewCtrl = loader.getController();
			Scene studentScene = new Scene(root, 1080, 720);
			
			// Pass a reference to the created student scene to the Student View Controller
			viewCtrl.setCurrentScene(studentScene);
			
			primaryStage.setResizable(false);
			primaryStage.setTitle("Library Application | Student View");
			primaryStage.setScene(studentScene);
			primaryStage.show();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Entry point of the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) { launch(args); }
}
