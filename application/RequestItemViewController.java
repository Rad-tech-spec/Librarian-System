package application;

import java.util.regex.Pattern;

import config.DBController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import model.Item;
import model.Student;

/**
 * This class is responsible for handling Request Item View events.
 * It is important to call <code>setStudentView()</code> and <code>setRequestData()</code> methods on this controller before using.
 * 
 * @author Nikita Mezhenskyi
 * @version 1.0.0, 2021-03-08
 */
public class RequestItemViewController {
	@FXML
	private TextField studentIdField;
	@FXML
	private TextField studentNameField;
	@FXML
	private TextField studentPhoneField;
	@FXML
	private ComboBox<String> standingSelection;
	@FXML
	private ComboBox<String> degreeLevelSelection;
	@FXML
	private Label feedback;
	/**
	 * Holds a reference to the parent Student View Controller.
	 */
	private StudentViewController studentViewController;
	
	private Item requestedItem = null;
	@FXML
	private Label parentFeedback = null;
	/**
	 * DBController instance is used for requesting an <i>Item</i>.
	 */
	private DBController db = new DBController();
	
	/**
	 * Creates an instance of RequestItemViewController class.
	 */
	public RequestItemViewController() { }
	
	/**
	 * Sets the reference to the parent Student View Contoller.
	 * 
	 * @param studentView Reference to the Student View Controller
	 */
	public void setStudentView(StudentViewController studentViewController) {
		this.studentViewController = studentViewController;
	}
	
	/**
	 * Sets the data <i>Item</i> for request and a reference to parent's feedback label.
	 * 
	 * @param requestedItem <i>Item</i> to be requested
	 * @param parentFeedback Reference to the parent feedback label
	 */
	public void setRequestData(Item requestedItem, Label parentFeedback) {
		this.requestedItem = requestedItem;
		this.parentFeedback = parentFeedback;
	}
	
	/**
	 * Handles requesting of <i>Item</i> after user fills text fields and clicks confirm button.
	 * 
	 * @param event
	 */
	public void handleConfirmStudentData(ActionEvent event) {
		boolean valid = true;
		
		String studentStanding = standingSelection.getSelectionModel().getSelectedItem();
		String degreeLevel = degreeLevelSelection.getSelectionModel().getSelectedItem();
		 
		// Input validation:
		if (studentIdField.getText().isBlank()) {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Please enter your Student ID");
			valid = false;
		}
		else if (!Pattern.matches("^[0-9]{1,}$", studentIdField.getText())) {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Student ID should contain only digits");
			valid = false;
		}
		else if (studentNameField.getText().isBlank()) {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Please enter your full name");
			valid = false;
		}
		else if (studentPhoneField.getText().isBlank()) {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Please enter your phone");
			valid = false;
		}
		else if (studentStanding == null) {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Please select academic standing");
			valid = false;
		}
		else if (degreeLevel == null) {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Please select degree level");
			valid = false;
		}
		
		if (valid) {
			feedback.setText("");
			
			Student student = new Student();
			student.setStudentId(studentIdField.getText());
			student.setStudentName(studentNameField.getText());
			student.setStudentPhone(studentPhoneField.getText());
			student.setAcademicStanding(studentStanding);
			student.setDegreeLevel(degreeLevel);
			
			if (requestedItem != null && parentFeedback != null && studentViewController != null) {
				String requestNum = db.requestItem(requestedItem, student);
				
				if (requestNum != null) {
					parentFeedback.setTextFill(Color.web("#006400"));
					parentFeedback.setText("Item requested, your request #" + requestNum);
				}
				else {
					parentFeedback.setTextFill(Color.web("#FF0000"));
					parentFeedback.setText("Cannot make a request: database error");
				}
				
				// Close the Request Item View
				studentViewController.closeAuxiliaryStage();
			}
			else {
				feedback.setText("Unexpected error occured");
			}
		}
	}
}
