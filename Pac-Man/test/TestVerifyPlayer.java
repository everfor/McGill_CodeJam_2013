import static org.junit.Assert.assertEquals;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.VerifyPlayer;
import frontendDatabase.PlayerFrontend;

/**
 * This class tests the methods required to verify a player
 * 
 */
public class TestVerifyPlayer {

	// variables needed for testing
	String[] username = { "teamBlABLA", "Team33333", "My Team", "One More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] securityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] securityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	JTextField usernameTextFields[] = new JTextField[] {
			new JTextField("teamBlABlA"), new JTextField("Team33333"),
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

	JPasswordField NewPass[] = new JPasswordField[] {
			new JPasswordField("12345678910"),
			new JPasswordField("myTeam333444"),
			new JPasswordField("ohis isthis3456"),
			new JPasswordField("TeamArjun3669") };
	JTextField usernameTextFieldsfail[] = new JTextField[] {
			new JTextField("invalid"), new JTextField("invalid2"),
			new JTextField("invalid3"), new JTextField("invalid4") };

	PlayerFrontend database = new PlayerFrontend();
	VerifyPlayer profile = new VerifyPlayer();

	/**
	 * Tests whether the login is checked correctly
	 * 
	 */

	@Test
	public void testLoginCheck() {

		for (int i = 0; i < usernameTextFields.length; i++) {
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i],
					securityQuestionTextField[i], securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(profile.loginCheck(passwordsTextField[i],
					usernameTextFields[i]), true);
			assertEquals(profile.loginCheck(passwordsTextField[3-i],
					usernameTextFields[i]), false);
			

		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			database.deleteProfile(new JTextField(username[i]));
			database.deleteProfile(usernameTextFields[i]);

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
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i],
					securityQuestionTextField[i], securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(profile.usernameExists(usernameTextFields[i]), true);
			assertEquals(profile.usernameExists(usernameTextFieldsfail[i]),
					false);

		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			database.deleteProfile(new JTextField(username[i]));
			database.deleteProfile(usernameTextFields[i]);

		}

	}

}
