package test;

import static org.junit.Assert.assertEquals;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.ChangeProfileDetails;
import frontendDatabase.PlayerFrontend;

/**
 * This class tests all of the methods of ChangeProfileDetails
 * 
 */

public class TestChangeProfileDetails {

	// variables needed for testing
	String[] username = { "teamBlABLA", "Team33333", "My Team", "One More3" };
	String[] passwords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
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

	String[] newusername = { "group232302", "Two More4", "teamRaRa",
			"Your Team" };

	JTextField usernameTextField[] = new JTextField[] {
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

	JPasswordField ReenterPass[] = new JPasswordField[] {
			new JPasswordField("12345678"), new JPasswordField("myTeam333"),
			new JPasswordField("ohis isthis3"),
			new JPasswordField("TeamArjun3") };
	

	JPasswordField PassFail[] = new JPasswordField[] {
			new JPasswordField("123"), new JPasswordField("my333"),
			new JPasswordField("ohis s3"),
			new JPasswordField("Tejun3") };

	PlayerFrontend database = new PlayerFrontend();
	ChangeProfileDetails profile = new ChangeProfileDetails();

	/**
	 * Tests whether the method changePassword successfully changes the old
	 * password attributed to a username to a new password
	 * 
	 */
	@Test
	public void testChangePassword() {

		for (int i = 0; i < usernameTextField.length; i++) {
			database.addNewPlayer(usernameTextField[i], passwordsTextField[i],
					securityQuestionTextField[i], securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextField.length; i++) {
			assertEquals(profile.changePassword(username[i],
					passwordsTextField[i], ReenterPass[i]), true);
			assertEquals(profile.changePassword(username[i],
					passwordsTextField[i], PassFail[i]), false);


		}

		for (int i = 0; i < usernameTextField.length; i++) {
			database.deleteProfile(new JTextField(username[i]));
			database.deleteProfile(usernameTextField[i]);

		}

	}

	/**
	 * Tests whether the method changeUsername successfully changes the old
	 * username to a new username
	 * 
	 */
	@Test
	public void testChangeUsername() {
		for (int i = 0; i < usernameTextField.length; i++) {
			database.addNewPlayer(usernameTextField[i], passwordsTextField[i],
					securityQuestionTextField[i], securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextField.length; i++) {
			assertEquals(profile.changeUsername(username[i], newusername[i]),
					true);

		}

		for (int i = 0; i < usernameTextField.length; i++) {
			database.deleteProfile(new JTextField(newusername[i]));
			database.deleteProfile(usernameTextField[i]);

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
		for (int i = 0; i < usernameTextField.length; i++) {
			database.addNewPlayer(usernameTextField[i], passwordsTextField[i],
					securityQuestionTextField[i], securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextField.length; i++) {
			assertEquals(profile.changeSecurity(securityQuestions2[i],
					securityAnswers2[i]), true);
			
		}

		for (int i = 0; i < usernameTextField.length; i++) {
			database.deleteProfile(new JTextField(username[i]));
			database.deleteProfile(usernameTextField[i]);

		}

	}
}
