package config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Item;
import model.ItemSearchAttribute;

/**
 * Custom database controller.
 * This class handles connection to and manipulation of the <i>Library</i> database.
 * 
 * @author Nikita Mezhenskyi
 * @version 2.3.0, 2021-03-07
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
	private boolean connect() {
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
	private boolean disconnect() {
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
	 * Return a list with <i>Items</i> found.
	 * @param attributeName Constant of ItemSearchAttribute enum type
	 * @param itemTitle String with item title
	 * @return Item list
	 */
	public List<Item> getItemsByAttribute(ItemSearchAttribute attributeName, String itemTitle) {
		List<Item> result = null;
		
		try {
			connect();
			statement = connection.createStatement();
			String sql = (attributeName == ItemSearchAttribute.ID) ? 
				("SELECT * FROM items WHERE " + attributeName.getAttributeName() + "=" + itemTitle + ";") :
				("SELECT * FROM items WHERE " + attributeName.getAttributeName() + "='" + itemTitle + "';");
			resSet = statement.executeQuery(sql);
			result = new ArrayList<Item>();
			
			while (resSet.next()) {
				Item entry = new Item();
				
				entry.setId(resSet.getString("id"));
				entry.setTitle(resSet.getString("name"));
				entry.setAuthor(resSet.getString("author"));
				entry.setCategory(resSet.getString("category"));
				entry.setStatus(resSet.getString("status"));
				entry.setType(resSet.getString("type"));
				
				result.add(entry);
			}
		}
		catch (SQLException ex) { ex.printStackTrace(); }
		finally { disconnect(); }
		
		return result;
	}
	
	/**
	 * 
	 * @param studentId
	 * @param report
	 * @return
	 */
	public List<Item> getBorrowedItems(String studentId, boolean report) {
		List<Item> borrowedItems = null;
		
		try {
			connect();
			statement = connection.createStatement();
			String sql = "SELECT * FROM items WHERE id = (SELECT id FROM issued WHERE student_id = " + studentId + ");";
			resSet = statement.executeQuery(sql);
			borrowedItems = new ArrayList<Item>();
			
			while (resSet.next()) {
				Item entry = new Item();
				
				entry.setId(resSet.getString("id"));
				entry.setTitle(resSet.getString("name"));
				entry.setAuthor(resSet.getString("author"));
				entry.setCategory(resSet.getString("category"));
				entry.setStatus(resSet.getString("status"));
				entry.setType(resSet.getString("type"));
				
				borrowedItems.add(entry);
			}
			
			if (report) generateReport(borrowedItems, "borrowed_items_" + studentId + ".txt");
		}
		catch (SQLException ex) { ex.printStackTrace(); }
		finally { disconnect(); }
		
		return borrowedItems;
	}
	
	/**
	 * Generates a report file based on the <i>Items</i> list passed.
	 * @param items List with <i>Items</i>
	 * @param fileName String representing a file used to generate a report
	 */
	private void generateReport(List<Item> items, String fileName) {
		File outputFile = new File(fileName);
		
		try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
			outputFile.createNewFile();
			
			for (int i = 0; i < 135; ++i)
				out.write("=" + (i == 134 ? "\n" : ""));
			
			out.write(String.format("| %6s | %30s | %25s | %25s | %15s | %15s |\n",
					"Id  ", "Title            ", "Author         ", "Category        ", "Status    ", "Type     "));
			
			for (int i = 0; i < 135; ++i)
				out.write("=" + (i == 134 ? "\n" : ""));
			
			for (Item item : items)
				out.write(item.toString());
			
			for (int i = 0; i < 135; ++i)
				out.write("=" + (i == 134 ? "\n" : ""));
			
			out.write("\nNumber of items found: " + items.size() + "\n");
			out.write("Date generated: " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())) + "\n");
		}
		catch (IOException ex) { System.err.print("Error occurred while generating a report."); }
	}
	
	public void requestItem(Item item) {
		
	}
}
