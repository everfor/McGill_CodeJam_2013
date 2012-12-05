import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import frontendDatabase.PlayerFrontend;

import backendDatabase.PlayerBackend;
import backendDatabase.StatisticsBackend;

/**
 * This class tests all of the methods of PlayerBackend. Since it is the one
 * communicating to the backendDatabase, any type of string you can input into
 * the backendDatabase will be checked
 * 
 * 
 */
public class TestBackendDatabase {

	PlayerBackend backendDatabase = new PlayerBackend();

	JTextField usernameTextFields[] = new JTextField[] {
			new JTextField("teamBlaBlA"), new JTextField("Team33333"),
			new JTextField("My Team"), new JTextField("One More3") };

	JPasswordField passwordsTextField[] = new JPasswordField[] {
			new JPasswordField("12345678"), new JPasswordField("myTeam333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("TeamArjun3") };

	JTextField securityQuestionTextField[] = new JTextField[] {
			new JTextField("Is 2+2=4?"),
			new JTextField("what is this project"),
			new JTextField("did you scream pacman 3 times?"),
			new JTextField("in what month were you born") };
	JTextField securityAnswerTextField[] = new JTextField[] {
			new JTextField("yes it isss"),
			new JTextField("the best project in the world"),
			new JTextField("i did once, maybe twice"),
			new JTextField("1st one lets say january") };
	JTextField usernameTextFieldsfail[] = new JTextField[] {
			new JTextField("invalid"), new JTextField("invalid1"),
			new JTextField("invalid2"), new JTextField("invalid3") };
	// initializations of string objects

	String[] username = { "teamBlaBLA", "Team33333", "My Team", "One More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	String[] databaseField = { "username", "password", "securityQuestion",
			"securityAnswer" };

	String[] newUsername = { "Username1", "Group-12131", "Go Slow",
			"permert 72" };
	String[] newPassword = { "password1", "23423134134", "krist bojani3",
			"KrispyKreme23" };
	String[] newSecurityQuestions = { "WHy are you here?",
			"who goes to mcgill", "who is our prof", "Who is your father" };
	String[] newSecurityAnswers = { "Work", "Everyone relevant",
			"Professor Mahajan", "Jack" };

	String[] failUsername = {
			null,
			"",
			"permert71sdfasdfdfiukjhkhkjhhkjhkhfdfasfadsfdfasdfsfadffasdfsfasdfasdfas",
			"username.length353" };

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] rank = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] level = { 1, 2, 3, 4 };

	StatisticsBackend backStats = new StatisticsBackend();

	PlayerFrontend frontendDatabase = new PlayerFrontend();

	/**
	 * This method tests the creation of a player in the backend backendDatabase
	 * 
	 */

	@Test
	public void testCreatePlayer() {

		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			assertEquals(backendDatabase.createPlayer(username[i],
					passwords[i], securityQuestions[i], securityAnswers[i]),
					true);

		}

	}

	/**
	 * This method tests whether the login checking mechanism is in order
	 * 
	 */
	@Test
	public void testCheckLogin() {
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			assertEquals(backendDatabase.checkLogin(username[i], passwords[i]),
					true);
			assertEquals(
					backendDatabase.checkLogin(username[i], passwords[3 - i]),
					false);
		}
	}

	/**
	 * This method tests whether the findPlayer method suceeds in finding a
	 * player that has been added
	 */
	@Test
	public void testFindPlayer() {
		for (int i = 0; i < username.length; i++) {
			assertEquals(backendDatabase.findPlayer(username[i]), true);
			assertEquals(backendDatabase.findPlayer("invalid"), false);
		}
	}

	/**
	 * This method tests whether only the correct Answer to the Security
	 * Question is accepted
	 */
	@Test
	public void testCheckSecurityQuestion() {

		for (int i = 0; i < username.length; i++) {
			assertEquals(backendDatabase.checkSecurityQuestion(username[i],
					securityAnswers[i]), true);
			assertEquals(backendDatabase.checkSecurityQuestion(username[i],
					securityAnswers[3 - i]), false);
		}
	}

	/**
	 * This method tests whether the changeProfileDetails method successfully
	 * changes the requisite fields
	 */
	@Test
	public void testChangeProfileDetails() {
		for (int i = 0; i < username.length; i++) {
			assertEquals(backendDatabase.changeProfileDetails(username[i],
					databaseField[0], newUsername[i]), true);
			assertEquals(backendDatabase.changeProfileDetails(username[i],
					databaseField[1], newPassword[i]), true);
			assertEquals(backendDatabase.changeProfileDetails(username[i],
					databaseField[2], newSecurityQuestions[i]), true);
			assertEquals(backendDatabase.changeProfileDetails(username[i],
					databaseField[3], newSecurityAnswers[i]), true);

		}

	}

	/**
	 * This method tests whether the player is removed successfully from the
	 * backendDatabase by the removePlayer method
	 */
	@Test
	public void testRemovePlayer() {
		for (int i = 0; i < username.length; i++) {
			assertEquals(backendDatabase.removePlayer(newUsername[i]), true);
			assertEquals(backendDatabase.removePlayer(passwords[i]), false);

		}
	}

	/**
	 * Tests whether the method createPlayerStats successfully creates default
	 * statistics for the profile object
	 * 
	 */
	@Test
	public void testCreatePlayerStats() {

		for (int i = 0; i < username.length; i++) {
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
		for (int i = 0; i < username.length; i++) {
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
	 * This method tests whether the changeProfileDetails method successfully
	 * changes the level achieved of a player
	 */
	@Test
	public void testChangeLevelDetails() {
		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}

		for (int i = 0; i < username.length; i++) {

			assertEquals(backendDatabase.changeProfileDetails(username[i],
					"levelachieved", level[i]), true);

		}
		for (int i = 0; i < usernameTextFields.length; i++) {
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
