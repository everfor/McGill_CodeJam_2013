package backendDatabase;

import java.sql.*;

public class PlayerBackend {
	Connection myConnection;
	PreparedStatement myStatement;
	ResultSet result;
	String databaseName = "playerdatabase";

	public PlayerBackend() {
		try {
			// Here the connection with database will occur
			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydatabase", "root", "123456");
		} catch (Exception e) {
			System.out.println("Connection could not be established");
		}
	}

	/**
	 * This method checks the database for possible matching username and
	 * password combinations and returns true if it finds false, otherwise
	 * 
	 * @param username
	 *            the username inputed into the text field
	 * @param password
	 *            the password inputed into password field
	 * @return whether or not the username and password combination was found
	 */
	public boolean checkLogin(String username, String password) {
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select * from "
					+ databaseName + " where username=? and password=?");
			myStatement.setString(1, username); // Username and password
			// inserted in query
			myStatement.setString(2, password);
			// executes the prepared statement
			result = myStatement.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error while searching database");
			return false;
		}
	}

	/**
	 * findPlayer searches the database for a username matching the one entered
	 * by the player.
	 * 
	 * @param username
	 *            the username inputted by the player
	 * @return true if it finds a matching entry in the database, false
	 *         otherwise
	 */
	public boolean findPlayer(String username) {
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select * from "
					+ databaseName + " where username=?");
			myStatement.setString(1, username); // Username and password
			// inserted in query
			// executes the prepared statement
			result = myStatement.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error while finding player in the database");
			return false;
		}
	}

	/**
	 * createPlayer creates a new entry in the database with the information
	 * passed through its parameters. It will also autoset the level achieved
	 * field to 1.
	 * 
	 * @param newUsername
	 *            player inputted Username
	 * @param newPassword
	 *            player inputted Password
	 * @param securityQuestion
	 *            player inputted Security Question
	 * @param securityAnswer
	 *            player inputted Answer to
	 * @return true if an entry has been created, false otherwise
	 */
	public boolean createPlayer(String newUsername, String newPassword,
			String securityQuestion, String securityAnswer) {
		try {
			String query = "insert into "
					+ databaseName
					+ "(username, password, securityQuestion, securityAnswer, levelAchieved) values(?, ?, ?, ?, ?)";
			myStatement = myConnection.prepareStatement(query);
			myStatement.setString(1, newUsername);
			myStatement.setString(2, newPassword);
			myStatement.setString(3, securityQuestion);
			myStatement.setString(4, securityAnswer);
			myStatement.setInt(5, 1);
			myStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("Error while finding player in the database");

			e.printStackTrace();
			return false;
		}
	}

	public boolean checkSecurityQuestion(String username, String securityAnswer) {
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select * from "
					+ databaseName + " where username=? and securityAnswer=?");
			myStatement.setString(1, username); // Username and securityAnswer
			// inserted in query
			myStatement.setString(2, securityAnswer);
			// executes the prepared statement
			result = myStatement.executeQuery();
			if (result.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error while searching database");
			return false;
		}
	}

	public boolean changeProfileDetails(String username, String databaseField,
			String newInfo) {
		try {

			String query = "update " + databaseName + " set " + databaseField
					+ " = ? where username = ? ";
			myStatement = myConnection.prepareStatement(query); // create a
			// statement
			myStatement.setString(1, newInfo); // set input parameter 1
			myStatement.setString(2, username); // set input parameter 2
			myStatement.executeUpdate(); // execute update statement
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getInfo(String username, String databaseField) {
		String info = "";
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select "
					+ databaseField + " from " + databaseName
					+ " where username=?");
			myStatement.setString(1, username); // Username inserted in query
			// executes the prepared statement
			result = myStatement.executeQuery();
			if (result.next()) {
				info = result.getString(1);
			}
		} catch (Exception e) {
			System.out
			.println("Error while searching username in the database");
			return info;
		}
		return info;
	}

	public boolean removePlayer(String username) {
		boolean deleted=false;
		try {
			// Checks username and password in database
			// myStatement = myConnection.prepareStatement("delete from "
			// + databaseName + " where username=?");

			String query = "delete from playerdatabase where username=?";

			myStatement = myConnection.prepareStatement(query); // create a
			// statement
			myStatement.setString(1, username); // Username inserted in query
			// executes the prepared statement

			myStatement.executeUpdate();
			deleted = true;
		} catch (Exception e) {
			System.out
			.println("Error while searching username in the database");
			deleted = false;
		}
		return deleted;
	}

}