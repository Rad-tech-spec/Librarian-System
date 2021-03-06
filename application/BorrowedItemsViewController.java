package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * This class is responsible for handling Borrowed Items View events.
 * 
 * @author Nikita Mezhenskyi
 */
public class BorrowedItemsViewController {
	@FXML
	private TextField studentIdField;
	@FXML
	private Label feedback;
	/**
	 * Holds reference to the parent Student View Controller.
	 */
	private StudentViewController studentViewController;
	
	/**
	 * Creates an instance of BorrowedItemsView class.
	 */
	public BorrowedItemsViewController() { }
	
	/**
	 * Handles confirm button click.
	 * Sends student id back to the Student View Controller to display borrowed <i>Items</i>.
	 * @param event
	 */
	public void handleConfirmStudentId(ActionEvent event) {
		if (!studentIdField.getText().isBlank()) {
			studentViewController.displayBorrowedItems(studentIdField.getText());
		}
		else {
			feedback.setTextFill(Color.web("#FF0000"));
		}
	}
	
	/**
	 * Sets the reference to the Student View Contoller.
	 * @param studentView Reference to the Student View Controller
	 */
	public void setStudentView(StudentViewController studentViewController) {
		this.studentViewController = studentViewController;
	}
}
