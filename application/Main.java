package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	private static Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			StudentViewController panel = new StudentViewController();
			Scene scene = new Scene(panel.getStudentView(), 1080, 720);
			currentStage.setResizable(false);
			currentStage.setTitle("Library Application | Student View");
			currentStage.setScene(scene);
			currentStage.show();
		} catch(Exception e) {
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
