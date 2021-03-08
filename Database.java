package assignment1package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static String jdbcURL = "jdbc:postgresql://ziggy.db.elephantsql.com:5432/efcagywl";
	private static String username = "efcagywl";
	private static String password = "PMMtt1RExmvYJXt37yaT0qxi5XQI5fci";

	/**
	 * Method to connect to the database.
	 * 
	 * @return The connection to the database.
	 */
	public static Connection connect() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("Connected to PostgreSQL server.");
		} catch (SQLException e) {
			System.out.println("Error. Couldn't connect to PostgreSQL server.");
			e.printStackTrace();
		}

		return connection;
	}
}
