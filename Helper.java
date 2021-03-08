package assignment1package;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Helper {

	/**
	 * Method used to display information on a new window.
	 * 
	 * @param alertType The type of alert to be displayed.
	 * @param owner     A window object.
	 * @param title     Title of the new window.
	 * @param message   The message to be displayed in the center of the window.
	 */
	public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}
