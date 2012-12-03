package backendDatabase;

import java.sql.*;
import java.util.ArrayList;

/**
 * The class writes and reads to the mySQL database for only the statistics
 * information of a specific player.
 */
public class StatisticsBackend {
	Connection myConnection;
	PreparedStatement myStatement;
	ResultSet result;
	String databaseName = "statisticsdatabase";

	/**
	 * The constructor of a backend statistics in order to communicate with the
	 * statistics database. It establish the connection with the database
	 */
	public StatisticsBackend() {
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
	 * The method creates a player statistics entry for each player in the
	 * database
	 * 
	 * @param newUsername
	 *            the new username that needs to have an entry initialized in
	 *            the database
	 * @return true if the player's entry was created in the database, false
	 *         otherwise
	 */
	public boolean createPlayerStats(String newUsername) {
		boolean created = false;
		try {
			// query to be sent to the database in order to prepare the
			// statement
			String query = "insert into "
					+ databaseName
					+ "(username, personalHighScore1, personalHighScore2, personalHighScore3, personalHighScore4, "
					+ "personalHighScore5, personalHighScore6, personalHighScore7, personalHighScore8, personalHighScore9, personalHighScore10) "
					+ "values(?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
			// Sending the statement through the connection
			myStatement = myConnection.prepareStatement(query);
			// inputting the username and executing the query
			myStatement.setString(1, newUsername);
			int createdEntries = myStatement.executeUpdate();
			if (createdEntries == 1) {
				created = true;
			}
		} catch (Exception e) {
			System.out
					.println("Error while creating player statistics in the database");
			e.printStackTrace();
		}
		return created;
	}

	/**
	 * The method completely removes a player's statistics entry from the
	 * database
	 * 
	 * @param username
	 *            the username that needs to be removed fromt the statistics
	 *            database
	 * @return true if the player was removed, false otherwise
	 */
	public boolean removePlayerStats(String username) {
		boolean deleted = false;
		try {
			// query to be sent through the connection with the database
			String query = "delete from " + databaseName + " where username=?";
			myStatement = myConnection.prepareStatement(query); // create a
			// statement
			myStatement.setString(1, username); // Username inserted in query
			// executes the prepared statement
			int deletedEntries = myStatement.executeUpdate();
			if (deletedEntries == 1) {
				deleted = true;
			}
		} catch (Exception e) {
			System.out
					.println("Error while removing player from the statistics database");
			deleted = false;
		}
		return deleted;
	}

	/**
	 * The method returns 1-10th score,depending on rank, of the current player
	 * or -1 if there is a problem
	 * 
	 * @param username
	 *            the username of the score that needs to be pulled
	 * @param rank
	 *            a dummy variable used to pull the 1st-10th highscore field of
	 *            a username's top highscores
	 * @return the score currently saved in the field
	 */
	public int getScore(String username, int rank) {
		String databaseField = "personalHighScore" + Integer.toString(rank + 1);
		int score = -1;
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement("select "
					+ databaseField
					+ " from statisticsdatabase where username=?");
			myStatement.setString(1, username); // Username inserted in query
			// executes the prepared statement
			result = myStatement.executeQuery();
			if (result.next()) {
				score = Integer.parseInt(result.getString(1));
			}
		} catch (Exception e) {
			System.out
					.println("Error while getting the personal highscore from the database");
		}
		return score;
	}

	/**
	 * The method sets the score of a given username in the sorted rank of the
	 * player's top 10 highscores
	 * 
	 * @param username
	 *            the username which needs to set a new score in his database
	 * @param rank
	 *            a dummy variable to change the 1st-10th personal high score
	 *            field
	 * @param score
	 *            the new score that needs to be inputted into the top personal
	 *            highscores
	 * @return true if the score was set correctly, false otherwise
	 */

	public boolean setScore(String username, String databaseField, int score) {
		boolean updatedScore = false;
		try {

			String query = "update " + databaseName + " set " + databaseField
					+ " = ? where username = ? ";
			myStatement = myConnection.prepareStatement(query); // create a
			// statement
			myStatement.setInt(1, score); // set input parameter 1
			myStatement.setString(2, username); // set input parameter 2
			int updatedEntries=myStatement.executeUpdate(); // execute update statement
			if(updatedEntries==1){
			updatedScore = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return updatedScore;
	}

	/**
	 * The method gets the top global high scores currently saved in all the
	 * database
	 * 
	 * @return the array list with the top 10 highscores and their respective
	 *         usernames
	 */
	public ArrayList<String> getGlobalHighScore() {
		ArrayList<String> resultsArray = new ArrayList<String>();
		// query to be sent to the database which finds the top 10 scores
		String query = "select * from ( select username, personalhighscore1 from statisticsdatabase "
				+ "union all select username,  personalhighscore2 from statisticsdatabase union all select username,"
				+ " personalhighscore3 from statisticsdatabase union all  "
				+ "select username, personalhighscore4 from statisticsdatabase union all select username,"
				+ " personalhighscore5 from statisticsdatabase union all select username,  personalhighscore6 from statisticsdatabase"
				+ " union all select username,  personalhighscore7 from statisticsdatabase union all select username,  "
				+ "personalhighscore8 from statisticsdatabase union all select username,  personalhighscore9 from statisticsdatabase"
				+ " union all select username,  personalhighscore10 from statisticsdatabase) a order by personalhighscore1 desc limit 10;";
		try {
			// Checks username and password in database
			myStatement = myConnection.prepareStatement(query);
			// executes the prepared statement
			result = myStatement.executeQuery();
			ResultSetMetaData rsmd = result.getMetaData();

			while (result.next()) {
				ArrayList<String> row = new ArrayList<String>();
				// getting all the top 10 highscores and the username from the
				// database result
				for (int column = 1; column <= rsmd.getColumnCount(); column++) {
					row.add(result.getString(column));
				}
				resultsArray.addAll(row);
			}

		} catch (Exception e) {
			System.out
					.println("Error while getting the global highscores from the database");
		}
		return resultsArray;
	}
	/**
	 * 
	 * @param username
	 * @param databaseField
	 * @param newInfo
	 * @return
	 */
	
	public boolean changeUsername(String username,	String newInfo) {
		try {

			String query = "update " + databaseName + " set " + "username"
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
}