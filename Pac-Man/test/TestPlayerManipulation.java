import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.*;

import frontendDatabase.*;

import backendDatabase.*;

/**
 * This class integrates the tests for all the methods in the classes in the
 * playerManipulation package
 * 
 */
public class TestPlayerManipulation {

	// variables needed for testing
	// variables needed for testing
	String[] username = { "collectiveBlABLA", "collective33333",
			"My collectivemmm", "collective4" };
	String[] passwords = { "12345678", "mycollective333", "ohis isthis3",
			"collectiveArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	String[] securityQuestions2 = { "Who is Best Actor?",
			"Who is Worst Actor?", "Who is best Director?",
			"Who is worst Director?" };
	String[] securityAnswers2 = { "Robert Downey Jr.", "Robert Pattinson",
			"Chris Nolan", "Michael Bay" };

	String[] newUsername = { "group23233402", "collective4", "collectiveRaRa",
			"Your collective" };

	JTextField usernameTextFields[] = new JTextField[] {
			new JTextField("collectiveBlABlA"),
			new JTextField("collective33333"),
			new JTextField("My collectivemmm"), new JTextField("collective4") };

	JPasswordField passwordsTextFields[] = new JPasswordField[] {
			new JPasswordField("12345678"),
			new JPasswordField("mycollective333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("collectiveArjun3") };

	JTextField securityQuestionTextFields[] = new JTextField[] {
			new JTextField("Is 2+2=4?"),
			new JTextField("what is this project"),
			new JTextField("did you scream pacman 3 times?"),
			new JTextField("in what month were you born") };

	JTextField securityAnswerTextFields[] = new JTextField[] {
			new JTextField("yes it isss"),
			new JTextField("the best project in the world"),
			new JTextField("i did once, maybe twice"),
			new JTextField("1st one lets say january") };

	JPasswordField ReenterPass[] = new JPasswordField[] {
			new JPasswordField("12345678"),
			new JPasswordField("mycollective333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("collectiveArjun3") };

	JPasswordField PassFail[] = new JPasswordField[] {
			new JPasswordField("123"), new JPasswordField("my333"),
			new JPasswordField("ohis s3"), new JPasswordField("Tejun3") };

	JPasswordField NewPass[] = new JPasswordField[] {
			new JPasswordField("12345678910"),
			new JPasswordField("mycollective333444"),
			new JPasswordField("ohis isthis3456"),
			new JPasswordField("collectiveArjun3669") };

	JTextField usernameTextFieldsfail[] = new JTextField[] {
			new JTextField("invalid"), new JTextField("invalid2"),
			new JTextField("invalid3"), new JTextField("invalid4") };

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] zerohighscores = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	int[] highscores2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	int[] zerohighscores2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	int[] verifyHighScores;
	PlayerFrontend frontendDatabase = new PlayerFrontend();
	PlayerBackend backendDatabase = new PlayerBackend();
	ChangeProfileDetails cpd = new ChangeProfileDetails();
	RecoverPassword profile = new RecoverPassword();
	VerifyPlayer vPlayer = new VerifyPlayer();
	StatisticsFrontend frontStats = new StatisticsFrontend();
	StatisticsBackend backStats = new StatisticsBackend();

	/**
	 * Tests whether the checkSecurityQuestion method successfully matches the
	 * correct answer with the correct security question
	 * 
	 */
	@Test
	public void testCheckSecurityQuestion() {
		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(profile.checkSecurityQuestion(username[i],
					securityAnswerTextFields[i]), true);
			// false cases
			assertEquals(profile.checkSecurityQuestion(username[i],
					securityAnswerTextFields[3 - i]), false);

		}

		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(username[i]);

		}
	}

	/**
	 * Tests whether the method changePassword successfully changes the old
	 * password attributed to a username to a new password
	 * 
	 */
	@Test
	public void testChangePassword() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(cpd.changePassword(username[i],
					passwordsTextFields[i], ReenterPass[i]), true);
			assertEquals(cpd.changePassword(username[i],
					passwordsTextFields[i], PassFail[i]), false);

		}

		for (int i = 0; i < usernameTextFields.length; i++) {

			backendDatabase.removePlayer(username[i]);

		}

	}

	/**
	 * Tests whether the method changeUsername successfully changes the old
	 * username to a new username
	 * 
	 */
	@Test
	public void testChangeUsername() {
		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}
		assertEquals(cpd.changeUsername(username[0], newUsername[0]), true);
		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(newUsername[i]);
			backendDatabase.removePlayer(username[i]);
		}

	}

	/**
	 * Tests whether the method changePassword successfully changes the old
	 * security questions and security answers attributed to a username to new
	 * security questions and answers
	 * 
	 */
	@Test
	public void testChangeSecurity() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(cpd.changeSecurity(username[i], securityQuestions2[i],
					securityAnswers2[i]), true);

		}

		for (int i = 0; i < username.length; i++) {

			backendDatabase.removePlayer(username[i]);

		}

	}

	/**
	 * Tests whether the usernameExists method successfully finds a username
	 * that has been previously added in the database
	 * 
	 */
	@Test
	public void testUsernameExists() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextFields[i], securityQuestionTextFields[i],
					securityAnswerTextFields[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(vPlayer.usernameExists(usernameTextFields[i]), true);
			// assertEquals(vplayer.usernameExists(usernameTextFieldsfail[i]),
			// false);

		}

		for (int i = 0; i < usernameTextFields.length; i++) {

			frontendDatabase.deleteProfile(usernameTextFields[i]);

		}

	}

	/**
	 * Tests whether the getUsername method successfully pulls the appropriate
	 * username from the database
	 * 
	 */
	@Test
	public void testGetUsername() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getUsername(), username[0]);

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.removePlayer(username[i]);
		}

	}

	/**
	 * Tests whether the getSecurityQuestion method successfully pulls the
	 * appropriate SecurityQuestion from the database
	 * 
	 */
	@Test
	public void testGetSecurityQuestion() {

		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getSecurityQuestion(), securityQuestions[0]);

		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the getSecurityAnswer method successfully pulls the
	 * appropriate SecurityAnswer from the database
	 * 
	 */
	@Test
	public void testGetSecurityAnswer() {

		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getSecurityAnswer(), securityAnswers[0]);

		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the getLevelAchieved method successfully pulls the
	 * appropriate LevelAchieved from the database
	 * 
	 */

	@Test
	public void testGetLevelAchieved() {

		for (int i = 0; i < username.length; i++) {

			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getLevelAchieved(), 1);

		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(username[i]);
		}
	}

	/**
	 * Tests whether the method setNewHighScore successfully sets the correct
	 * score which is then returned by getHighScores
	 * 
	 */

	@Test
	public void testSetGetNewHighScore() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			backStats.createPlayerStats(username[i]);

		}

		Player myProfile = new Player(username[0]);

		for (int j = 0; j < highscores2.length; j++) {
			myProfile.setNewHighScore(highscores2[j]);
		}

		verifyHighScores = myProfile.getHighScores();

		for (int i = 0; i < highscores.length; i++) {
			assertEquals(verifyHighScores[i], highscores2[10 - i]);
			assertFalse(verifyHighScores[i] == zerohighscores[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			backStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);

		}

	}

}
