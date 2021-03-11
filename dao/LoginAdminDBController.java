package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Alexander Samaniego
 */
public class LoginAdminDBController {
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

	public static ResultSet adminLogin(int idNum, String password) {
		Connection conn = connect();
		ResultSet resultSet = null;

		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM admin WHERE id=" + idNum + " AND password='" + password + "'");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;
	}

	public static ResultSet librarianLogin(int idNum, String password) {
		Connection conn = connect();
		ResultSet resultSet = null;

		try {
			PreparedStatement preparedStatement = conn
					.prepareStatement("SELECT * FROM librarian WHERE id=" + idNum + " AND password='" + password + "'");
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;
	}

	public static int deleteLibrarian(int idNum) {
		Connection conn = LoginAdminDBController.connect();
		int numDeleted = 0;
		try {
			Statement statement = conn.createStatement();
			numDeleted = statement.executeUpdate("DELETE FROM librarian WHERE id=" + idNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return numDeleted;
	}

	public static boolean registerLibrarian(int idNum, String fname, String lname, String password) {
		Connection conn = LoginAdminDBController.connect();
		boolean success = false;
		try {
			Statement statement = conn.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS librarian "
					+ " (id INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, password TEXT)");
			statement.execute("INSERT INTO librarian (id, firstName, lastName, password) VALUES (" + idNum + ", '"
					+ fname + "', '" + lname + "', '" + password + "')");
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}
}
