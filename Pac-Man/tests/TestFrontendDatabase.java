import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import backendDatabase.PlayerBackend;
import backendDatabase.StatisticsBackend;
import frontendDatabase.PlayerFrontend;
import frontendDatabase.StatisticsFrontend;

/**
 * This class tests all of the methods found in all of the classes of the
 * frontendDatabase package
 * 
 * 
 */
public class TestFrontendDatabase {
	// JTextField and JPasswordField initializations

	JTextField usernameTextFields[] = new JTextField[] {
			new JTextField("KristiB21"), new JTextField("Crew214433"),
			new JTextField("My Crew214542"), new JTextField("3 More245") };

	JPasswordField passwordsTextField[] = new JPasswordField[] {
			new JPasswordField("12345678"), new JPasswordField("myCrew2144"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("Crew21Arjun3") };

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

	String[] username = { "KristiB21", "Crew214433", "My Crew214542",
			"3 More245" };
	String[] passwords = { "12345678", "myCrew2144", "ohis isthis3",
			"Crew21Arjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	String[] databaseField = { "username", "password", "securityQuestion",
			"securityAnswer" };

	String[] newUsername = { "Username12", "Crew21-121312", "Go Slow2",
			"permert 722" };
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

	PlayerFrontend frontendDatabase = new PlayerFrontend();

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] zerohighscores = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	int[] highscores2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	int[] zerohighscores2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	PlayerBackend backendDatabase = new PlayerBackend();
	StatisticsFrontend frontStats = new StatisticsFrontend();

	StatisticsBackend backStats = new StatisticsBackend();

	/**
	 * This method tests the addition of a player via the frontend
	 * frontendDatabase
	 * 
	 */

	@Test
	public void testAddNewPlayer() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextField[i], securityQuestionTextField[i],
					securityAnswerTextField[i]), true);

		}
		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.removePlayer(username[i]);

		}
	}

	/**
	 * utilizes the username to check if the added player exists in the
	 * frontendDatabase
	 */
	@Test
	public void testPlayerExists() {

		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(frontendDatabase.playerExists(usernameTextFields[i]),
					true);
			assertEquals(
					frontendDatabase.playerExists(usernameTextFieldsfail[i]),
					false);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.removePlayer(username[i]);

		}

	}

	/**
	 * checks whether the given security answer for a given username matches the
	 * security answer for the player in the frontendDatabase
	 */
	@Test
	public void testChecksecurityAnswer() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(frontendDatabase.checkSecurityAnswer(username[i],
					securityAnswerTextField[i]), true);
			assertEquals(frontendDatabase.checkSecurityAnswer(username[i],
					securityAnswerTextField[3 - i]), false);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {

			backendDatabase.removePlayer(username[i]);

		}
	}

	/**
	 * checks whether the specific profile detail has been changed after using
	 * usingChangeProfileDetails
	 */

	@Test
	public void testChangeProfileDetails() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {

			assertEquals(frontendDatabase.changeProfileDetails(username[i],
					databaseField[1], newPassword[i]), true);
			assertEquals(frontendDatabase.changeProfileDetails(username[i],
					databaseField[2], newSecurityQuestions[i]), true);

			assertEquals(frontendDatabase.changeProfileDetails(username[i],
					databaseField[3], newSecurityAnswers[i]), true);

		}

		for (int i = 0; i < username.length; i++) {

			backendDatabase.removePlayer(username[i]);

		}
	}

	/**
	 * checks whether the deleteProfile method successfully deletes the profile
	 */
	@Test
	public void testDeleteProfile() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextField[i], securityQuestionTextField[i],
					securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(frontendDatabase.deleteProfile(usernameTextFields[i]),
					true);
			assertEquals(
					frontendDatabase.deleteProfile(usernameTextFieldsfail[i]),
					false);
		}
		for (int i = 0; i < usernameTextFields.length; i++) {

			backendDatabase.removePlayer(username[i]);

		}

	}

	/**
	 * Tests whether the method intializePlayerStats successfully creates
	 * default statistics for the profile object
	 * 
	 */
	@Test
	public void testInitializePlayerStats() {

		for (int i = 0; i < username.length; i++) {
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
	 * Tests whether the method removePlayerStats successfully removes the
	 * HighScores
	 * 
	 */
	@Test
	public void testRemovePlayerStats() {
		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			frontStats.initializePlayerStats(username[i]);
			assertEquals(frontStats.removePlayerStats(username[i]), true);

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
	public void testSetGetHighScores() {

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

}