import static org.junit.Assert.assertEquals;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import frontendDatabase.PlayerFrontend;

/**
 * This class tests all of the methods of PlayerFrontend
 * 
 * 
 */
public class TestPlayerFrontend {
	// JTextField and JPasswordField initializations

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

	PlayerFrontend database = new PlayerFrontend();

	/**
	 * This method tests the addition of a player via the frontend database
	 * 
	 */

	@Test
	public void testAddNewPlayer() {
		for (int i = 0; i < usernameTextFields.length; i++) { // four fields in
																// each of the
																// string or
																// JTextField
																// arrays
			assertEquals(database.addNewPlayer(usernameTextFields[i],
					passwordsTextField[i], securityQuestionTextField[i],
					securityAnswerTextField[i]), true);
			
			
		}
	}

	/**
	 * This method checks the success of the login operation for a given
	 * username and password
	 */
	@Test
	public void testLoginOperator() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(database.loginOperator(passwordsTextField[i],
					usernameTextFields[i]), true);
			assertEquals(database.loginOperator(passwordsTextField[i],
					usernameTextFields[3 - i]), false);
		}
	}

	/**
	 * utilizes the username to check if the added player exists in the database
	 */
	@Test
	public void testPlayerExists() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(database.playerExists(usernameTextFields[i]), true);
			assertEquals(database.playerExists(usernameTextFieldsfail[i]),
					false);

		}
	}

	/**
	 * checks whether the given security answer for a given username matches the
	 * security answer for the player in the database
	 */
	@Test
	public void testChecksecurityAnswer() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(database.checkSecurityAnswer(username[i],
					securityAnswerTextField[i]), true);
			assertEquals(database.checkSecurityAnswer(username[i],
					securityAnswerTextField[3 - i]), false);
		}
	}

	/**
	 * checks whether the specific profile detail has been changed after using
	 * usingChangeProfileDetails
	 */

	@Test
	public void testChangeProfileDetails() {
		for (int i = 0; i < usernameTextFields.length; i++) { // four fields in
																// each of the
																// string array

			assertEquals(database.changeProfileDetails(username[i],
					databaseField[0], newUsername[i]), true);
			assertEquals(database.changeProfileDetails(username[i],
					databaseField[1], newPassword[i]), true);
			assertEquals(database.changeProfileDetails(username[i],
					databaseField[2], newSecurityQuestions[i]), true);
			assertEquals(database.changeProfileDetails(username[i],
					databaseField[3], newSecurityAnswers[i]), true);
		
			
		}
	}

	/**
	 * checks whether the deleteProfile method successfully deletes the profile
	 */
	@Test
	public void testDeleteProfile() {
		for (int i = 0; i < usernameTextFields.length; i++) {
			assertEquals(
					database.deleteProfile(new JTextField(newUsername[i])),
					true);
			assertEquals(
					database.deleteProfile(new JTextField(newPassword[i])),
					false);
		}
	}
}