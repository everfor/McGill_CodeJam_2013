package frontendDatabase;

import java.util.ArrayList;

import backendDatabase.*;

public class StatisticsFrontend {

	/* Returns the current top 10 HighScores of a given player */
	public static int[] getHighScores(String username) {
		StatisticsBackend database = new StatisticsBackend();
		int[] topHighScores = new int[10];

		for (int i = 0; i < topHighScores.length; i++) {
			topHighScores[i] = database.getScore(username, i);
		}
		return topHighScores;
	}
	
	/* Returns the current top 10 HighScores in the game */
	public static ArrayList getGlobalHighScores() {
		StatisticsBackend database = new StatisticsBackend();
		ArrayList globalHighScores = new ArrayList();
		globalHighScores = database.getGlobalHighScore();
		return globalHighScores;

	}
	/* Displays the current top 10 HighScores in the game */
	public static void displayGlobalHighScores() {
		ArrayList globalHighScores = new ArrayList();
		globalHighScores = getGlobalHighScores();

		for (int i = 0; i < globalHighScores.size(); i++) {
			System.out.println(globalHighScores.get(i));
		}

	}

	/*
	 * Sets the new score in the top 10 highscores of a given player, and
	 * returns the new top 10 high scores
	 */
	public static void setHighScores(String username, int newScore) {
		StatisticsBackend database = new StatisticsBackend();

		int[] topHighScores = new int[10];
		int i = 0;

		for (i = 0; i < topHighScores.length; i++) {
			topHighScores[i] = database.getScore(username, i);
		}

		i = topHighScores.length - 1;

		// checking the top 10 highscores, and updating it if newScore should be
		// placed in the top10
		while (i >= 0) {
			if (topHighScores[i] < newScore) {
				topHighScores[i] = newScore;
				break;
			} else {
				i--;
			}
		}

		// saving the updated scores into the database
		for (i = 0; i < topHighScores.length; i++) {
			database.setScore(username, i, topHighScores[i]);
		}

	}

	//Initializes a field in the database for the statistics of a given player
	public static boolean initializePlayerStats(String newUsername) {
		StatisticsBackend database = new StatisticsBackend();
		boolean result = false;
		result = database.createPlayerStats(newUsername);
		return result;
	}
	
	public boolean removePlayerStats(String username) {
		StatisticsBackend database = new StatisticsBackend();
		boolean result = false;
		result = database.removePlayerStats(username);
		return result;
	}
}