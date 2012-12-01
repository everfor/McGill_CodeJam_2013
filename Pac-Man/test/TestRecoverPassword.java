import static org.junit.Assert.assertEquals;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.RecoverPassword;
import frontendDatabase.PlayerFrontend;

/**
 * This class tests the methods required in order to Recover a Password
 * 
 */
public class TestRecoverPassword {

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

	PlayerFrontend database = new PlayerFrontend();
	RecoverPassword profile = new RecoverPassword();

	/**
	 * Tests whether the checkSecurityQuestion method successfully matches the
	 * correct answer with the correct security question
	 * 
	 */
	@Test
	public void testCheckSecurityQuestion() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i],
					securityQuestionTextField[i], securityAnswerTextField[i]);
		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(profile.checkSecurityQuestion(username[i],
					securityAnswerTextField[i]), true);
			// false cases
			assertEquals(profile.checkSecurityQuestion(username[i],
					securityAnswerTextField[3 - i]), false);

		}

		for (int i = 0; i < usernameTextFields.length; i++) {
			database.deleteProfile(new JTextField(username[i]));
			database.deleteProfile(usernameTextFields[i]);

		}
	}

}
