package backendDatabase;

import java.sql.*;

/**
 * 
 * The Backend player database reads and writes to the mySQL database in order
 * to manage the player information, excluding statistics.
 * 
 */
public class PlayerBackend {
	Connection myConnection;
	PreparedStatement myStatement;
	ResultSet result;
	String databaseName = "playerdatabase";

	/**
	 * Constructor for building a backend player database. It connects to the
	 * mySQL Server to establish the connection.
	 */
	public PlayerBackend() {
		try {
			// Here the connection with database will occur
			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase",
					"root", "123456");
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
			myStatement = myConnection.prepareStatement("select * from " + databaseName
					+ " where username=? and password=?");
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
			myStatement = myConnection.prepareStatement("select * from " + databaseName
					+ " where username=?");
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
	public boolean createPlayer(String newUsername, String newPassword, String securityQuestion,
			String securityAnswer) {
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

	/**
	 * The method checks the security question inputted by the user with the one
	 * saved in the database when the user has previously logged in
	 * 
	 * @param username
	 *            the username inputed by the player
	 * @param securityAnswer
	 *            the securityAnswers inputted by the player
	 * @return true if the security questions are the same, false otherwise
	 */
	public boolean checkSecurityQuestion(String username, String securityAnswer) {
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select * from " + databaseName
					+ " where username=? and securityAnswer=?");
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

	/**
	 * The method changes the profile details depending on the databasefield
	 * which could be a password, security question, security answer or level
	 * achieved(only called from game play since the user has no access to it).
	 * 
	 * @param username
	 *            the username inputted by the player
	 * @param databaseField
	 *            the field in the database, whose info needs to be changed.
	 * @param newInfo
	 *            the new information that needs to be stored in the database
	 *            field that needs to be changed
	 * @return true if the change of the profile details was changed
	 *         successfully, false otherwise
	 */
	public boolean changeProfileDetails(String username, String databaseField, String newInfo) {
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

	/**
	 * The method pulls the specific information from the database field
	 * specified by the player or the other classes that interact with it
	 * 
	 * @param username
	 *            the username of the specific user whose information needs to
	 *            be pulled
	 * @param databaseField
	 *            the string database field whose information needs to be pulled
	 * @return a string representing the info that is stored inside the database
	 *         field of the specific user
	 */
	public String getInfo(String username, String databaseField) {
		String info = "";
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select " + databaseField + " from "
					+ databaseName + " where username=?");
			myStatement.setString(1, username); // Username inserted in query
			// executes the prepared statement
			result = myStatement.executeQuery();
			if (result.next()) {
				info = result.getString(1);
			}
		} catch (Exception e) {
			System.out.println("Error while searching username in the database");
			return info;
		}
		return info;
	}

	/**
	 * The method pulls the specific information from the database field
	 * specified by the player or the other classes that interact with it
	 * 
	 * @param username
	 *            the username of the specific user whose information needs to
	 *            be pulled
	 * @param databaseField
	 *            the int field whose information needs to be pulled
	 * @return a string representing the info that is stored inside the database
	 *         field of the specific user
	 */
	public boolean changeProfileDetails(String username, String databaseField, int newInfo) {
		boolean changed = false;
		try {

			String query = "update " + databaseName + " set " + databaseField
					+ " = ? where username = ? ";
			myStatement = myConnection.prepareStatement(query); // create a
			// statement
			myStatement.setInt(1, newInfo); // set input parameter 1
			myStatement.setString(2, username); // set input parameter 2
			myStatement.executeUpdate(); // execute update statement

			int changedEntries = myStatement.executeUpdate();
			if (changedEntries == 1) {
				changed = true;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return changed;
	}

	/**
	 * The method deletes all the information of a player corresponding to a
	 * specific username
	 * 
	 * @param username
	 *            the username of the player that needs to be removed
	 * @return true if the player is removed, false otherwise
	 */
	public boolean removePlayer(String username) {
		boolean deleted = false;
		try {
			// Checks username and password in database
			String query = "delete from playerdatabase where username=?";
			// create a statement
			myStatement = myConnection.prepareStatement(query);
			// Username inserted in query
			myStatement.setString(1, username);
			// executes the prepared statement
			int deletedEntries = myStatement.executeUpdate();
			if (deletedEntries == 1) {
				deleted = true;
			}
		} catch (Exception e) {
			System.out.println("Error while searching username in the database");
			deleted = false;
		}
		return deleted;
	}

}