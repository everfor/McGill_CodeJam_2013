import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.*;
import frontendDatabase.*;
import backendDatabase.*;

/**
 * This class tests all of the methods of ChangeProfileDetails
 * 
 */

public class TestChangeProfileDetails {

	// variables needed for testing
	String[] username = { "CollectiveBLABLA", "Collective343333",
			"Mine Collective", "Ten More5" };
	String[] passwords = { "12345678", "myCollective333", "ohis isthis3",
			"CollectiveArjun3" };
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

	String[] newusername = { "group23235202", "Two More6", "CollectiveLaLa",
			"My Collectives" };

	JTextField usernameTextFields[] = new JTextField[] {
			new JTextField("CollectiveBLABlA"),
			new JTextField("Collective34333"),
			new JTextField("Mine Collective"), new JTextField("Ten More5") };

	JPasswordField passwordsTextFields[] = new JPasswordField[] {
			new JPasswordField("12345678"),
			new JPasswordField("myCollective333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("CollectiveArjun3") };

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
			new JPasswordField("myCollective333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("CollectiveArjun3") };

	JPasswordField PassFail[] = new JPasswordField[] {
			new JPasswordField("123"), new JPasswordField("my333"),
			new JPasswordField("ohis s3"), new JPasswordField("Tejun3") };

	PlayerFrontend frontendDatabase = new PlayerFrontend();
	ChangeProfileDetails cpd = new ChangeProfileDetails();
	PlayerBackend backendDatabase = new PlayerBackend();

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

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(cpd.changeUsername(username[i], newusername[i]), true);
		}

		for (int i = 0; i < username.length; i++) {
			backendDatabase.removePlayer(newusername[i]);
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
}
