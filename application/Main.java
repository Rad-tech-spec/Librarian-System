package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import config.DBController;

public class Main extends Application {
	
	private static Stage currentStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			
			StudentView panel = new StudentView();
			Scene scene = new Scene(panel.getStudentView(),1080,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			currentStage.setResizable(false);
			currentStage.setTitle("Library Application | Student View");
			currentStage.setScene(scene);
			currentStage.show();
			
			DBController db = new DBController("jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl", "efcagywl", "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci");
			db.connect();
			db.getItemsByTitle("Business");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
