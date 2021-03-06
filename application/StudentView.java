package application;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private Label feedback;
	@FXML
	private ComboBox<String> parameterSelector;
	@FXML
	private TableView<Item> dataTable;
	@FXML
	private TableColumn<Item, String> itemId;
	@FXML
	private TableColumn<Item, String> itemTitle;
	@FXML
	private TableColumn<Item, String> itemAuthor;
	@FXML
	private TableColumn<Item, String> itemQuantity;
	@FXML
	private TableColumn<Item, String> itemCategory;
	@FXML
	private TableColumn<Item, String> itemStatus;
	@FXML
	private TableColumn<Item, String> itemLocation;
	@FXML
	private TableColumn<Item, String> itemType;
	
	private ObservableList<Item> itemList = FXCollections.observableArrayList();
	
	private DBController db = new DBController();
	
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
			if (!searchField.getText().isBlank()) {
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
				feedback.setTextFill(Color.web("#FF0000"));
				feedback.setText("Please enter a search string");
			}
		}
		else {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Please select a parameter");
		}
	}
	
	/**
	 * Clears the input field and removes all <i>Items</i> from the table.
	 * @param event
	 */
	public void handleClear(ActionEvent event) {
		clearTable();
		searchField.setText("");
	}
	
	/**
	 * 
	 * @param event
	 */
	public void handleViewBorrowed(ActionEvent event) {
		feedback.setTextFill(Color.web("#006400"));
		feedback.setText("View Borrowed fired.");
	}
	
	/**
	 * Displays <i>Items</i> from the database in the dataTable.
	 * @param value
	 */
	private void displayItems(ItemSearchAttribute attributeName, String value) {
		clearTable();
		
		List<Item> items = db.getItemsByAttribute(attributeName, value);
		if (items != null && !items.isEmpty()) {
			for (Item item : items) itemList.add(item);
			
			itemId.setCellValueFactory(new PropertyValueFactory<>("Id"));
			itemTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
			itemAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
			itemQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
			itemCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
			itemStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
			itemLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
			itemType.setCellValueFactory(new PropertyValueFactory<>("Type"));
			dataTable.setItems(itemList);
			
			feedback.setTextFill(Color.web("#006400"));
			feedback.setText("Found " + itemList.size() + (itemList.size() > 1 ? " entries" : " entry"));
		}
		else {
			feedback.setTextFill(Color.web("#FF0000"));
			feedback.setText("Nothing found");
		}
	}
	
	/**
	 * Removes all <i>Items</i> from the table.
	 */
	private void clearTable() {
		feedback.setText("");
		dataTable.getItems().clear(); 
	}
}
