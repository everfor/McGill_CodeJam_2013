package test;

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

	PlayerBackend database = new PlayerBackend();
	StatisticsFrontend profile = new StatisticsFrontend();
	StatisticsBackend profileBackend = new StatisticsBackend();

	/**
	 * Tests whether the method intializePlayerStats successfully creates
	 * default statistics for the profile object
	 * 
	 */
	@Test
	public void testInitializePlayerStats() {

		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}

		for (int i = 0; i < username.length; i++) {
			assertEquals(profile.initializePlayerStats(username[i]), true);
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
			profile.removePlayerStats(username[i]);
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
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			profile.initializePlayerStats(username[i]);

		}

		int x[] = new int[10];

		for (int j = 0; j < username.length; j++) {
			for (int k = 0; k < highscores.length; k++) {
				x = profile.getHighScores(username[j]);

				assertEquals(x[k], zerohighscores[k]);
				assertFalse(x[k] == highscores[k]);
			}
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
			profile.removePlayerStats(username[i]);
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
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			profile.initializePlayerStats(username[i]);

		}

		for (int j = 0; j < username.length; j++) {
			for (int k = 0; k < highscores2.length; k++) {
				profile.setHighScores(username[j], highscores2[k]);
			}
		}

		int x[] = new int[10];

		for (int j = 0; j < username.length; j++) {
			for (int k = 0; k < highscores.length; k++) {
				x = profile.getHighScores(username[j]);
				assertEquals(x[k], highscores2[10 - k]);
				assertFalse(x[k] == zerohighscores2[k]);
			}
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
			profile.removePlayerStats(username[i]);
		}
	}

	@Test
	public void testRemovePlayerStats() {
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			profile.initializePlayerStats(username[i]);

			assertEquals(profile.removePlayerStats(username[i]), true);
			assertFalse(profile.removePlayerStats(passwords[i]) == true);
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
		}
	}

}
