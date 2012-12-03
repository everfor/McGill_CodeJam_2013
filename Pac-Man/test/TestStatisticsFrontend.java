import static org.junit.Assert.*;

import org.junit.Test;
import frontendDatabase.*;
import backendDatabase.*;

/**
 * This class tests the methods pertaining to Statistics in the Frontend
 * Database
 * 
 * 
 */
public class TestStatisticsFrontend {
	String[] username = { "teamBlaGla", "Team34443", "Your Team", "Two More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] zerohighscores = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	int[] highscores2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	int[] zerohighscores2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	PlayerBackend backendDatabase = new PlayerBackend();
	StatisticsFrontend frontStats = new StatisticsFrontend();
	StatisticsBackend backStats = new StatisticsBackend();

	/**
	 * Tests whether the method intializePlayerStats successfully creates
	 * default statistics for the profile object
	 * 
	 */
	@Test
	public void testInitializePlayerStats() {

		for (int i = 0; i < username.length; i++) { // four fields in each of
			// the string array
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}

		for (int i = 0; i < username.length; i++) {
			assertEquals(frontStats.initializePlayerStats(username[i]), true);
		}

		for (int i = 0; i < username.length; i++) {
			frontStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);

		}
	}

	/**
	 * Tests whether the method getHighScores successfully returns the correct
	 * highscores afte they are set by the setHighScores method
	 * 
	 */
	@Test
	public void testGetHighScores() {

		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			frontStats.initializePlayerStats(username[i]);

		}

		int x[] = new int[10];

		for (int k = 0; k < highscores.length; k++) {
			x = frontStats.getHighScores(username[0]);

			assertEquals(x[k], zerohighscores[k]);
			assertFalse(x[k] == highscores[k]);
		}

		for (int i = 0; i < username.length; i++) {
			frontStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);

		}
	}

	/**
	 * Tests whether the method setHighScores successfully sets the correct
	 * highscores and are returned successfully by getHighScores
	 * 
	 */
	@Test
	public void testSetHighScores() {

		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			frontStats.initializePlayerStats(username[i]);

		}

		for (int k = 0; k < highscores2.length; k++) {
			frontStats.setHighScores(username[0], highscores2[k]);

		}

		int x[] = new int[10];

		x = frontStats.getHighScores(username[0]);

		for (int m = 0; m < highscores.length; m++) {
			assertEquals(x[m], highscores2[10 - m]);
			assertFalse(x[m] == zerohighscores2[m]);
		}

		for (int i = 0; i < username.length; i++) {
			frontStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the method removePlayerStats successfully removes the
	 * HighScores
	 * 
	 */
	@Test
	public void testRemovePlayerStats() {
		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

			backStats.createPlayerStats(username[i]);
		}

		for (int i = 0; i < username.length; i++) {
			assertEquals(frontStats.removePlayerStats(username[i]), true);
			assertFalse(frontStats.removePlayerStats(passwords[i]) == true);
		}

		for (int i = 0; i < username.length; i++) {
			frontStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);

		}
	}

}