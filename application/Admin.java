package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import config.Database;
import config.Helper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Librarian;

/**
 * @author Alexander Samaniego
 */
public class Admin {

	/**
	 * Scene for the Admin main menu.
	 * 
	 * @return GridPane containing the menu components
	 */
	public static GridPane adminMenu() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Administrator Menu");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0);

		Button registerLibrarian = new Button("Register Librarian");
		Button viewLibrarian = new Button("View Librarian");
		Button deleteLibrarian = new Button("Delete Librarian");

		HBox registerBtn = new HBox();
		registerBtn.setAlignment(Pos.CENTER);
		registerBtn.getChildren().add(registerLibrarian);
		grid.add(registerBtn, 0, 1);
		registerLibrarian.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				registerLibrarian.getScene().setRoot(registerLibrarian());
			}
		});

		HBox viewBtn = new HBox();
		viewBtn.setAlignment(Pos.CENTER);
		viewBtn.getChildren().add(viewLibrarian);
		grid.add(viewBtn, 0, 2);
		viewLibrarian.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				viewLibrarian.getScene().setRoot(viewLibrarian());
			}
		});

		HBox deleteBtn = new HBox();
		deleteBtn.setAlignment(Pos.CENTER);
		deleteBtn.getChildren().add(deleteLibrarian);
		grid.add(deleteBtn, 0, 3);
		deleteLibrarian.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				viewLibrarian.getScene().setRoot(deleteLibrarian());
			}
		});

		return grid;
	}

	/**
	 * Scene for the registration of a librarian
	 * 
	 * @return GridPane containing the input forms for librarian information.
	 */
	private static GridPane registerLibrarian() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Button goBack = new Button("Back to main menu");
		HBox goBackBtn = new HBox();
		goBackBtn.setAlignment(Pos.TOP_CENTER);
		goBackBtn.getChildren().add(goBack);
		grid.add(goBackBtn, 0, 6);
		goBack.setOnAction(e -> goBack(goBack));

		Text scenetitle = new Text("Register Librarian");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label librarianID = new Label("Librarian ID:");
		grid.add(librarianID, 0, 1);

		TextField idTextField = new TextField();
		grid.add(idTextField, 1, 1);

		Label firstName = new Label("First Name:");
		grid.add(firstName, 0, 2);

		TextField fNameTextField = new TextField();
		grid.add(fNameTextField, 1, 2);

		Label lastName = new Label("Last Name:");
		grid.add(lastName, 0, 3);

		TextField lNameTextField = new TextField();
		grid.add(lNameTextField, 1, 3);

		Label pw = new Label("Password:");
		grid.add(pw, 0, 4);

		PasswordField pwBox = new PasswordField();
		grid.add(pwBox, 1, 4);
		// grid.setGridLinesVisible(true);

		Button registerButton = new Button("Register");
		HBox hbBtn = new HBox();
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(registerButton);
		grid.add(hbBtn, 0, 5);

		registerButton.setOnAction(e -> registerButton(idTextField.getText(), fNameTextField.getText(),
				lNameTextField.getText(), pwBox.getText(), grid));

		return grid;
	}

	/**
	 * Scene for viewing existing librarians in table format
	 * 
	 * @return GridPane containing the table showing all existing librarians
	 */
	private static GridPane viewLibrarian() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		// grid.setGridLinesVisible(true);

		Text scenetitle = new Text("Registered Librarians");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Button goBack = new Button("Back to main menu");
		HBox goBackBtn = new HBox();
		goBackBtn.setAlignment(Pos.TOP_CENTER);
		goBackBtn.getChildren().add(goBack);
		grid.add(goBackBtn, 1, 1);
		goBack.setOnAction(e -> goBack(goBack));

		TableView<Librarian> tableView = new TableView<>();
		ResultSet resultSet = null;
		Connection conn = Database.connect();
		ObservableList<Librarian> dbData = null;

		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM librarian");
			resultSet = preparedStatement.executeQuery();
			dbData = FXCollections.observableArrayList(dataBaseArrayList(resultSet));

			for (int i = 0; i < resultSet.getMetaData().getColumnCount() - 1; i++) {
				TableColumn column = new TableColumn<>();
				switch (resultSet.getMetaData().getColumnName(i + 1)) {
				case "id":
					column.setText("ID #");
					break;
				case "firstname":
					column.setText("First Name");
					break;
				case "lastname":
					column.setText("Last Name");
					break;
				default:
					column.setText(resultSet.getMetaData().getColumnName(i + 1));
					break;
				}
				column.setCellValueFactory(new PropertyValueFactory<>(resultSet.getMetaData().getColumnName(i + 1)));
				tableView.getColumns().add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		tableView.setItems(dbData);
		grid.add(tableView, 0, 1);

		return grid;

	}

	/**
	 * Scene to delete a specific librarian by ID number.
	 * 
	 * @return GridPane containing the input form to delete a librarian.
	 */
	private static GridPane deleteLibrarian() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Delete a Librarian");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Text subTitle = new Text("Please enter the ID of the Librarian you want to delete");
		subTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
		grid.add(subTitle, 0, 1, 2, 1);

		Label librarianID = new Label("Librarian ID:");
		grid.add(librarianID, 0, 2);

		TextField idTextField = new TextField();
		grid.add(idTextField, 1, 2);

		Button goBack = new Button("Back to main menu");
		HBox goBackBtn = new HBox();
		goBackBtn.setAlignment(Pos.TOP_CENTER);
		goBackBtn.getChildren().add(goBack);
		grid.add(goBackBtn, 0, 4);
		goBack.setOnAction(e -> goBack(goBack));

		Button deleteButton = new Button("Delete Librarian");
		HBox hbBtn = new HBox();
		hbBtn.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn.getChildren().add(deleteButton);
		grid.add(hbBtn, 0, 3);

		deleteButton.setOnAction(e -> deleteButton(idTextField.getText(), grid));

		return grid;
	}

	/**
	 * Event handler method that registers a librarian by connecting to the database
	 * and inserting the values into the librarian table.
	 * 
	 * @param id       the ID number of a librarian to register.
	 * @param fname    the first name of the librarian.
	 * @param lname    the last name of the librarian.
	 * @param password the password to be used to sign into the system.
	 * @param grid     GridPane used to display information in a new window.
	 */
	private static void registerButton(String id, String fname, String lname, String password, GridPane grid) {

		try {
			int idNum = Integer.parseInt(id);
			Connection conn = Database.connect();
			try {
				Statement statement = conn.createStatement();
				statement.execute("CREATE TABLE IF NOT EXISTS librarian "
						+ " (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, password TEXT)");
				statement.execute("INSERT INTO librarian (id, firstName, lastName, password) VALUES (" + idNum + ", '"
						+ fname + "', '" + lname + "', '" + password + "')");
				Helper.showAlert(Alert.AlertType.INFORMATION, grid.getScene().getWindow(), "Librarian Added!",
						"A new Librarian has been added.");

			} catch (SQLException e) {
				e.printStackTrace();
				Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Registration Error!",
						"Librarian ID already exists.");
			}

		} catch (NumberFormatException e) {
			Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Registration Error!",
					"Please enter a Number for ID.");
		}
	}

	/**
	 * Event handler method that is used to delete a librarian from the system.
	 * 
	 * @param id   the ID number of the librarian to be deleted.
	 * @param grid GridPane used to display information in a different window.
	 */
	private static void deleteButton(String id, GridPane grid) {
		try {
			int idNum = Integer.parseInt(id);
			Connection conn = Database.connect();
			int numDeleted = 0;
			try {
				Statement statement = conn.createStatement();
				numDeleted = statement.executeUpdate("DELETE FROM librarian WHERE id=" + idNum);
				Text actiontarget = new Text();

				if (numDeleted > 0) {
					actiontarget.setText("Librarian Deleted");
					grid.add(actiontarget, 1, 3);
				} else {
					Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Deletion Error!",
							"No librarians with this ID was found.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			Helper.showAlert(Alert.AlertType.ERROR, grid.getScene().getWindow(), "Deletion Error!",
					"Please enter a Number for ID.");
		}
	}

	/**
	 * Method used to go back to the main Admin menu.
	 * 
	 * @param goBack Button parameter used to set the scene.
	 */
	private static void goBack(Button goBack) {
		goBack.getScene().setRoot(adminMenu());
	}

	/**
	 * Used to write librarian data from an ArrayList onto a text file called
	 * "viewAllLibrarians.txt".
	 * 
	 * @param data ArrayList of librarian data.
	 */
	private static void writeTable(ArrayList<Librarian> data) {
		BufferedWriter file;

		try {
			file = new BufferedWriter(new FileWriter("viewAllLibrarians.txt"));
			file.write(String.format("Registered Librarians"));
			file.newLine();
			file.newLine();
			file.write(String.format("%-8s ", "ID"));
			file.write(String.format("%-15s ", "First Name"));
			file.write(String.format("%-15s ", "Last Name"));
			file.newLine();

			for (int i = 0; i < data.size(); i++) {

				file.write(String.format("%-8s ", data.get(i).getId()));
				file.write(String.format("%-15s ", data.get(i).getFirstname()));
				file.write(String.format("%-15s ", data.get(i).getLastname()));
				file.newLine();
			}

			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method used to extract librarian data from ResultSet and insert it into an
	 * ArrayList.
	 * 
	 * @param resultSet LIbrarian data extracted from the database.
	 * @return ArrayList of librarian data.
	 */
	private static ArrayList<Librarian> dataBaseArrayList(ResultSet resultSet) {
		ArrayList<Librarian> data = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Librarian person = new Librarian();
				person.setId(resultSet.getInt("id"));
				person.setFirstname(resultSet.getString("firstname"));
				person.setLastname(resultSet.getString("lastname"));
				data.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		writeTable(data);
		return data;
	}
}
