import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import backendDatabase.PlayerBackend;
import backendDatabase.StatisticsBackend;

/**
 * This class tests the methods pertaining to Statistics in the Backend Database
 * 
 * 
 */

public class TestStatisticsBackend {

	// variables needed for testing
	String[] username = { "teamBlaGla", "Team34443", "Your Team", "Two More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] rank = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	PlayerBackend database = new PlayerBackend();
	StatisticsBackend profile = new StatisticsBackend();

	/**
	 * Tests whether the method createPlayerStats successfully creates default
	 * statistics for the profile object
	 * 
	 */
	@Test
	public void testCreatePlayerStats() {

		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			assertEquals(profile.createPlayerStats(username[i]), true);
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the method removesPlayerStats successfully removes
	 * statistics for the profile object
	 * 
	 */
	@Test
	public void testRemovePlayerStats() {
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			profile.createPlayerStats(username[i]);
			assertEquals(profile.removePlayerStats(username[i]), true);
			assertFalse(profile.removePlayerStats(passwords[i]) == true);
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the method getScore successfully returns the correct score
	 * after it is set by the setScore method
	 * 
	 */
	@Test
	public void testGetScore() {

		for (int i = 0; i < username.length; i++) {
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			profile.createPlayerStats(username[i]);
		}

		for (int j = 0; j < username.length; j++) {
			for (int l = 0; l < highscores.length; l++) {
				assertEquals(profile.getScore(username[j], rank[l] - 1), 0);
				assertFalse(profile.getScore(username[j], rank[l] - 1) == -1);

			}
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
			profile.removePlayerStats(username[i]);
		}
	}

	/**
	 * Tests whether the method setScore successfully sets the correct score
	 * which is then returned by getScore
	 * 
	 */
	@Test
	public void testSetScore() {
		for (int i = 0; i < username.length; i++) {
			database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			profile.createPlayerStats(username[i]);
		}

		for (int j = 0; j < username.length; j++) {
			for (int k = 0; k < highscores.length; k++) {
				profile.setScore(username[j], "personalHighScore" + rank[k],
						highscores[k]);
			}
		}

		for (int j = 0; j < username.length; j++) {
			for (int l = 0; l < highscores.length; l++) {
				assertEquals(profile.getScore(username[j], rank[l] - 1),
						highscores[l]);
				assertFalse(profile.getScore(username[j], rank[l] - 1) == (highscores[9 - l]));
			}
		}

		for (int i = 0; i < username.length; i++) {
			database.removePlayer(username[i]);
			profile.removePlayerStats(username[i]);
		}
	}

}
