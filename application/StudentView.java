package application;

import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;

import config.DBController;
import model.ItemSearchAttribute;

/**
 * This class is responsible for loading Student View (studentView.fxml) and handling its events.
 */
public class StudentView {
	@FXML
	private TextField searchField;
	
	private DBController db = null;
	
	/**
	 * Creates an instance of StudentView class
	 */
	public StudentView() { }
	
	/**
	 * Returns Student View fxml layout
	 * @return Parent containing fxml layout
	 * @throws IOException
	 */
	public Parent getStudentView() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("studentView.fxml"));
		return root;
	}
	
	public void handleSearch(ActionEvent event) {
		displayItems(searchField.getText());
	}
	
	private void displayItems(String value) {
		DBController db = new DBController("jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl", "efcagywl", "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci");
		db.connect();
		db.getItemsByAttribute(ItemSearchAttribute.TITLE, value);
		db.disconnect();
	}
}
