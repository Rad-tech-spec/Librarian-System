package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import model.ItemSearchAttribute;

/**
 * Class that handles connection to and manipulation of the database.
 */
public class DBController {
	
	private String jdbcURL;
	private String username;
	private String password;
	private Connection connection = null;
	private Statement statement = null;
	
	/**
	 * Creates an instance of DBConnector.
	 */
	public DBController(String jdbcURL, String username, String password) {
		this.jdbcURL = jdbcURL;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Connects to the database.
	 * @return Operation status (success or failure)
	 */
	public boolean connect() {
		try {
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to PostgreSQL server.");
			return true;
		}
		catch (SQLException e) {
			System.err.println("Unable to connect to PostgreSQL server.");
			return false;
		}
	}
	
	/**
	 * Closes the connection with the database.
	 * @return Operation status (success or failure)
	 */
	public boolean disconnect() {
		try {
			connection.close();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}
	
	/**
	 * Searches for items in the database by provided attribute and its value.
	 * @param title String with item title
	 * @return
	 */
	public List<Item> getItemsByAttribute(ItemSearchAttribute attributeName, String itemTitle) {
		List<Item> result = null;
		
		try {
			statement = connection.createStatement();
			String sql = "SELECT * FROM books WHERE " + attributeName.getAttributeName() + "='" + itemTitle + "';";
			ResultSet resSet = statement.executeQuery(sql);
			
			result = new ArrayList<Item>();
			
			while (resSet.next()) {
				Item entry = new Item();
				
				entry.setId(resSet.getString("id"));
				entry.setTitle(resSet.getString("name"));
				entry.setAuthor(resSet.getString("author"));
				entry.setQuantity(resSet.getString("quantity"));
				entry.setCategory(resSet.getString("category"));
				entry.setType(resSet.getString("type"));
				
				result.add(entry);
				
				System.out.print(entry.getId() + " " + " " + entry.getTitle() + " " + entry.getAuthor() + "\n");
			}
			
			resSet.close();
			statement.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to test retrieving of items from DB.
	 */
	public void getItems() throws SQLException {
		statement = connection.createStatement();
		String sql = "SELECT * FROM books";
		ResultSet resSet = statement.executeQuery(sql);
			
		while (resSet.next()) {
			String id = resSet.getString("id");
			String title = resSet.getString("name");
			String author = resSet.getString("author");
			System.out.print(id + " " + " " + title + " " + author + "\n");
		}
		
		resSet.close();
		statement.close();
	}
}
