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
	String[] username = { "teamBlaBla", "Team44443", "My Team", "ONE More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };
	String[] newUsername = { "teamBla", "Team55553", "My Teamz", "ONE More34" };

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] rank = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	PlayerBackend backendDatabase = new PlayerBackend();
	StatisticsBackend backStats = new StatisticsBackend();

	/**
	 * Tests whether the method createPlayerStats successfully creates default
	 * statistics for the profile object
	 * 
	 */
	@Test
	public void testCreatePlayerStats() {

		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			assertEquals(backStats.createPlayerStats(username[i]), true);
		}

		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(username[i]);
			backStats.removePlayerStats(username[i]);
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
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			backStats.createPlayerStats(username[i]);
			assertEquals(backStats.removePlayerStats(username[i]), true);
			assertFalse(backStats.removePlayerStats(passwords[i]) == true);
		}

		for (int i = 0; i < username.length; i++) {
			backStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);

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
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			backStats.createPlayerStats(username[i]);
		}

		for (int j = 0; j < username.length; j++) {
			for (int l = 0; l < highscores.length; l++) {
				assertEquals(backStats.getScore(username[j], rank[l] - 1), 0);
				assertFalse(backStats.getScore(username[j], rank[l] - 1) == -1);

			}
		}

		for (int i = 0; i < username.length; i++) {

			backStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);
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
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			backStats.createPlayerStats(username[i]);
		}

		for (int j = 0; j < username.length; j++) {
			for (int k = 0; k < highscores.length; k++) {
				backStats.setScore(username[j], "personalHighScore" + rank[k],
						highscores[k]);
			}
		}

		for (int j = 0; j < username.length; j++) {
			for (int l = 0; l < highscores.length; l++) {
				assertEquals(backStats.getScore(username[j], rank[l] - 1),
						highscores[l]);
				assertFalse(backStats.getScore(username[j], rank[l] - 1) == (highscores[9 - l]));
			}
		}

		for (int i = 0; i < username.length; i++) {
			backStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the method changeUsername changes the username in the
	 * statistics database
	 * 
	 */
	@Test
	public void testChangeUsername() {
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			backStats.createPlayerStats(username[i]);
			assertEquals(backStats.changeUsername(username[i], newUsername[i]),
					true);
		}

		for (int i = 0; i < username.length; i++) {
			backStats.removePlayerStats(newUsername[i]);
			backendDatabase.removePlayer(newUsername[i]);
			backStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);
		}
	}

}
