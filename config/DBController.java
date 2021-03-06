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
 * Custom database controller.
 * This class handles connection to and manipulation of the Library database.
 */
public class DBController {
	
	private String jdbcURL = "jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl";
	private String username = "efcagywl";
	private String password = "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci";
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resSet = null;
	
	/**
	 * Creates an instace of DBController with the default connection string.
	 */
	public DBController() { }
	
	/**
	 * Creates an instance of DBController with the custom connection string.
	 */
	public DBController(String jdbcURL, String username, String password) {
		this.jdbcURL = jdbcURL;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Connects to the database.
	 * @return Operation success or failure (true / false)
	 */
	public boolean connect() {
		try {
			connection = DriverManager.getConnection(jdbcURL, username, password);
			return true;
		}
		catch (SQLException e) { return false; }
	}
	
	/**
	 * Closes the connection with the database.
	 * @return Operation success or failure (true / false)
	 */
	public boolean disconnect() {
		boolean success = true;
		
		if (resSet != null) {
			try  { resSet.close(); }
			catch (SQLException ex) { System.err.println("Unable to close result set."); success = false; }
		}
		if (statement != null) {
			try  { statement.close(); }
			catch (SQLException ex) { System.err.println("Unable to close statement."); success = false; }
		}
		if (connection != null) {
			try  { connection.close(); }
			catch (SQLException ex) { System.err.println("Unable to disconnect from PostgreSQL server."); success = false; }
		}
		
		return success;
	}
	
	/**
	 * Searches for items in the database by specified attribute name and its value.
	 * @param title String with item title
	 * @return
	 */
	public List<Item> getItemsByAttribute(ItemSearchAttribute attributeName, String itemTitle) {
		List<Item> result = null;
		
		try {
			connect();
			statement = connection.createStatement();
			String sql = (attributeName == ItemSearchAttribute.ID) ? 
				("SELECT * FROM item WHERE " + attributeName.getAttributeName() + "=" + itemTitle + ";") :
				("SELECT * FROM item WHERE " + attributeName.getAttributeName() + "='" + itemTitle + "';");
			resSet = statement.executeQuery(sql);
			result = new ArrayList<Item>();
			
			while (resSet.next()) {
				Item entry = new Item();
				
				entry.setId(resSet.getString("id"));
				entry.setTitle(resSet.getString("title"));
				entry.setAuthor(resSet.getString("author"));
				entry.setCategory(resSet.getString("category"));
				entry.setStatus(resSet.getString("status"));
				entry.setType(resSet.getString("type"));
				
				result.add(entry);
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		finally {
			disconnect();
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Item> getBorrowedItems(String studentId, boolean report) {
		List<Item> borrowedItems = getItemsByAttribute(ItemSearchAttribute.STATUS, "borrowed");
		if (report) generateReport(borrowedItems, "borrowed-items.txt");
		
		return borrowedItems;
	}
	
	/**
	 * Generates a report file based 
	 */
	private void generateReport(List<Item> items, String filename) {
		
	}
}
