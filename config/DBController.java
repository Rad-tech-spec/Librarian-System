package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	 * 
	 * @param title String with item title
	 * @return
	 */
	public String findItemsByTitle(String title) {
		return "";
	}
	
	/**
	 * 
	 * @param author String with author name
	 * @return
	 */
	public String findItemsByAuthor(String author) {
		return "";
	}
	
	/**
	 * Method to test retrieving of items from DB.
	 */
	public void getItems() {
		try {
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
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
