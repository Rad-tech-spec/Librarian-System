package application;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;

import config.DBController;
import model.Item;
import model.ItemSearchAttribute;

/**
 * This class is responsible for loading Student View (studentView.fxml) and handling its events.
 * 
 * @author Nikita Mezhenskyi
 */
public class StudentView {
	@FXML
	private TextField searchField;
	@FXML
	private ComboBox<String> parameterSelector;
	@FXML
	private TableView<String> dataTable;
	
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
	
	/**
	 * Handles Search Item event. Displays <i>Items</i> from the database based on search parameters.
	 * @param event
	 */
	public void handleSearch(ActionEvent event) {
		String selectedParameter = parameterSelector.getSelectionModel().getSelectedItem();
		
		if (selectedParameter != null) {
			switch(selectedParameter) {
				case "by Title":
					displayItems(ItemSearchAttribute.TITLE, searchField.getText());
					break;
				case "by Author":
					displayItems(ItemSearchAttribute.AUTHOR, searchField.getText());
					break;
				case "by ID":
					displayItems(ItemSearchAttribute.ID, searchField.getText());
					break;
			}
		}
		else {
			System.out.print("Please specify parameter\n");
		}
	}
	
	/**
	 * Displays <i>Items</i> from the database in the dataTable.
	 * @param value
	 */
	private void displayItems(ItemSearchAttribute attributeName, String value) {
		DBController db = new DBController("jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl", "efcagywl", "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci");
		db.connect();
		
		List<Item> items = db.getItemsByAttribute(attributeName, value);
		if (items != null && !items.isEmpty()) {
			for (Item item : items) {
				System.out.print(item.getId() + " " + " " + item.getTitle() + " " + item.getAuthor() + "\n");
			}
		}
		else {
			System.out.print("Nothing found.\n");
		}
		
		db.disconnect();
	}
}
