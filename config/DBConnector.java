package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * Class that handles connection to and manipulation of the database.
 *
 */
class DBConnector {
	
	private Connection connection;
	private String jdbcURL = "jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl";
	private String username = "efcagywl";
	private String password = "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci";
	
	public DBConnector() {
		connect();
	}
	
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
	
	public boolean disconnect() {
		try {
			connection.close();
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}
}
