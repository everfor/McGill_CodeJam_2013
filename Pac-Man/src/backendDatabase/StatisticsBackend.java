package backendDatabase;

import java.sql.*;
import java.util.ArrayList;

public class StatisticsBackend {
	Connection myConnection;
	PreparedStatement myStatement;
	ResultSet result;
	String databaseName = "statisticsdatabase";

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

	public boolean createPlayerStats(String newUsername) {
		boolean created = false;
		try {
			String query = "insert into "
					+ databaseName
					+ "(username, personalHighScore1, personalHighScore2, personalHighScore3, personalHighScore4, "
					+ "personalHighScore5, personalHighScore6, personalHighScore7, personalHighScore8, personalHighScore9, personalHighScore10) "
					+ "values(?, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)";
			myStatement = myConnection.prepareStatement(query);
			myStatement.setString(1, newUsername);
			myStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out
					.println("Error while creating player statistics in the database");
			e.printStackTrace();
			return false;
		}
	}

	public boolean removePlayerStats(String username) {
		boolean deleted = false;
		try {
			String query = "delete from " + databaseName + " where username=?";
			myStatement = myConnection.prepareStatement(query); // create a
			// statement
			myStatement.setString(1, username); // Username inserted in query
			// executes the prepared statement
			int deletedEntries = myStatement.executeUpdate();
			deleted = true;
		} catch (Exception e) {
			System.out
					.println("Error while removing player from the statistics database");
			deleted = false;
		}
		return deleted;
	}

	/*
	 * Returns 1-10th score,depending on rank, of the current player or -1 if
	 * there is a problem
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

	/* Sets the score of a given username in the sorted rank */
	public boolean setScore(String username, int rank, int score) {
		boolean updatedScore = false;
		try {
			String databaseField = "personalHighScore" + Integer.toString(rank);
			//
			myStatement = myConnection.prepareStatement("UPDATE "
					+ databaseName + " SET " + databaseField + " = "
					+ "CASE WHEN VALUES(" + databaseField + ") < " + score
					+ " " + "THEN " + score + " " + "ELSE " + databaseField
					+ " " + "END " + "WHERE USERNAME=?");
			// Username inserted in query
			myStatement.setString(1, username);
			// executes the prepared statement
			myStatement.executeUpdate();
			updatedScore = true;
		} catch (Exception e) {
			System.out
					.println("Error while setting the personal highscore into the database");
			updatedScore = false;
		}
		return updatedScore;
	}

	public ArrayList<String> getGlobalHighScore() {
		ArrayList<String> resultsArray = new ArrayList<String>();
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

}