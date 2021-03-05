package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	private static Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			
			StudentView panel = new StudentView();
			Scene scene = new Scene(panel.getStudentView(), 1080, 720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			currentStage.setResizable(false);
			currentStage.setTitle("Library Application | Student View");
			currentStage.setScene(scene);
			currentStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// TODO: Request a book Scene
	
	public void changeScene(Parent newRoot) {
		try {
			currentStage.getScene().setRoot(newRoot);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Entry point of the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
