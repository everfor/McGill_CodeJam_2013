import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.Player;
import frontendDatabase.*;
import backendDatabase.*;

/**
 * This class tests the methods in the Player class
 * 
 * 
 */

public class TestPlayer {

	// variables needed for testing
	String[] username = { "teamBlaGla", "Team34443", "Your Team", "Two More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	JTextField usernameTextFields[] = new JTextField[] {
			new JTextField("teamBlaGla"), new JTextField("Team34443"),
			new JTextField("Your Team"), new JTextField("Two More3") };

	JPasswordField passwordsTextFields[] = new JPasswordField[] {
			new JPasswordField("12345678"), new JPasswordField("myTeam333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("TeamArjun3") };

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

	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	int[] zerohighscores = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	int[] highscores2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
	int[] zerohighscores2 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	int[] verifyHighScores;
	PlayerFrontend frontendDatabase = new PlayerFrontend();
	PlayerBackend backendDatabase = new PlayerBackend();
	StatisticsFrontend frontStats = new StatisticsFrontend();
	StatisticsBackend backStats = new StatisticsBackend();

	/**
	 * Tests whether the getUsername method successfully pulls the appropriate
	 * username from the database
	 * 
	 */
	@Test
	public void testGetUsername() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextFields[i], securityQuestionTextFields[i],
					securityAnswerTextFields[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getUsername(), username[0]);

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.deleteProfile(usernameTextFields[i]);
		}

	}

	/**
	 * Tests whether the getSecurityQuestion method successfully pulls the
	 * appropriate SecurityQuestion from the database
	 * 
	 */
	@Test
	public void testGetSecurityQuestion() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextFields[i], securityQuestionTextFields[i],
					securityAnswerTextFields[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getSecurityQuestion(), securityQuestions[0]);

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.deleteProfile(usernameTextFields[i]);
		}
	}

	/**
	 * Tests whether the getSecurityAnswer method successfully pulls the
	 * appropriate SecurityAnswer from the database
	 * 
	 */
	@Test
	public void testGetSecurityAnswer() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextFields[i], securityQuestionTextFields[i],
					securityAnswerTextFields[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.getSecurityAnswer(), securityAnswers[0]);

		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.deleteProfile(usernameTextFields[i]);
		}
	}

	/**
	 * Tests whether the getLevelAchieved method successfully pulls the
	 * appropriate LevelAchieved from the database
	 * 
	 */

	@Test
	public void testGetLevelAchieved() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			// frontendDatabase.addNewPlayer(usernameTextFields[i],
			// passwordsTextFields[i],
			// securityQuestionTextFields[i], securityAnswerTextFields[i]);
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
	 * Tests whether the clearPlayer method empties the profile details
	 * corresponding to the specified object
	 * 
	 */
	@Test
	public void testClearPlayer() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			frontendDatabase.addNewPlayer(usernameTextFields[i],
					passwordsTextFields[i], securityQuestionTextFields[i],
					securityAnswerTextFields[i]);

		}
		Player profile = new Player(username[0]);
		assertEquals(profile.clearPlayer(), true);

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

		for (int i = 0; i < username.length; i++) {
			backendDatabase.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]);
			backStats.createPlayerStats(username[i]);

		}

		Player Myprofile = new Player(username[0]);

		for (int j = 0; j < highscores2.length; j++) {
			Myprofile.setNewHighScore(highscores2[j]);
		}

		verifyHighScores = Myprofile.getHighScores();

		for (int i = 0; i < highscores.length; i++) {
			assertEquals(verifyHighScores[i], highscores2[10 - i]);
			assertFalse(verifyHighScores[i] == zerohighscores[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			backStats.removePlayerStats(username[i]);
			backendDatabase.removePlayer(username[i]);
			//
		}

	}

}