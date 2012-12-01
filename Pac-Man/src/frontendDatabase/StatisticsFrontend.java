package frontendDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import backendDatabase.StatisticsBackend;

/**
 * The class communicates with statistics backend class in order to perform
 * tasks for each player
 */
public class StatisticsFrontend {

	/**
	 * The method returns the current top 10 HighScores of a given player
	 * 
	 * @param username
	 *            the username which we need to pull the top 10 highscores for
	 * @return the array with the top 10 highscores
	 */
	public static int[] getHighScores(String username) {
		StatisticsBackend database = new StatisticsBackend();
		int[] topHighScores = new int[10];
		// populating the array with the top 10 scores
		for (int i = 0; i < topHighScores.length; i++) {
			topHighScores[i] = database.getScore(username, i);
		}
		return topHighScores;
	}

	/**
	 * The method returns the current top 10 global HighScores currently in the
	 * game
	 * 
	 * @return a string array list with the top 10 players and their respective
	 *         scores in the database
	 */
	public static ArrayList<String> getGlobalHighScores() {
		StatisticsBackend database = new StatisticsBackend();
		ArrayList<String> globalHighScores = new ArrayList<String>();
		globalHighScores = database.getGlobalHighScore();
		globalHighScores.add("");
		int i = 0, j = 0;

		// converting from an array list to make easier the compatibility with
		// the rest of the program
		ArrayList<String> finalGlobalHighScores = new ArrayList<String>();
		while (i < globalHighScores.size() - 2) {
			finalGlobalHighScores.add(j, (globalHighScores.get(i)
					+ "                      " + globalHighScores.get(i + 1)));
			i += 2;
			j++;
		}
		return finalGlobalHighScores;

	}

	/**
	 * Sets the new score in the top 10 highscores of a given player, and
	 * returns the new top 10 high scores
	 * 
	 * @param username
	 *            the username for which we need to set the new score into the
	 *            database
	 * @param newScore
	 *            the new score of a specific user
	 */
	public static boolean setHighScores(String username, int newScore) {
		StatisticsBackend database = new StatisticsBackend();

		boolean set = false;
		int[] topHighScores = new int[11];
		int i = 0;
		// getting the current top 10 high scores from the database
		for (i = 0; i < topHighScores.length - 1; i++) {
			topHighScores[i] = database.getScore(username, i);
		}
		// adding new score
		topHighScores[10] = newScore;
		// sort in ascending order
		Arrays.sort(topHighScores);
		//write the last ten to database
		for (int j = topHighScores.length - 1; j > 0; j--){
			
			database.setScore(username, "personalHighscore" + (11-j),topHighScores[j]);
		}

		return set;

	}

	/**
	 * Initializes a field in the database for the statistics of a new player
	 * 
	 * @param newUsername
	 *            the new username that needs to be added into the database
	 * @return true if the player's statistics was initialized, false otherwise
	 */
	public static boolean initializePlayerStats(String newUsername) {
		StatisticsBackend database = new StatisticsBackend();
		boolean result = false;
		// creating the player
		result = database.createPlayerStats(newUsername);
		return result;
	}

	/**
	 * The method removes all the statistics corresponding to a specific player
	 * 
	 * @param username
	 *            the username whose statistics needs to be deleted permantly
	 * @return true if the player was removed, false otherwise
	 */
	public static boolean removePlayerStats(String username) {
		StatisticsBackend database = new StatisticsBackend();
		boolean result = false;
		// trying to remove the player
		result = database.removePlayerStats(username);
		return result;
	}
}