package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Class that handles connection to and manipulation of the database.
 *
 */
class DBConnector {
	
	private String jdbcURL = "jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl";
	private String username = "efcagywl";
	private String password = "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci";
	private Connection connection = null;
	private Statement statement = null;
	
	/**
	 * Creates an instance of DBConnector and connects to the database.
	 */
	public DBConnector() {
		connect();
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
	public String findItemByTitle(String title) {
		return "";
	}
	
	/**
	 * 
	 * @param author String with author name
	 * @return
	 */
	public String findItemByAuthor(String author) {
		return "";
	}
}
