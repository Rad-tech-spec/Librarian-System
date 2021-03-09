package application;
	
import application.student.StudentViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * <h2>Main - Primary Stage Control class</h2>
 * 
 * @author Nikita Mezhenskyi
 * @author Rad Eshghi
 * @author Alexander Samaniego
 * @version 2.0.0, 2021-03-09
 */
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
