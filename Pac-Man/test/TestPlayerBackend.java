import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import backendDatabase.PlayerBackend;
import frontendDatabase.PlayerFrontend;

/**
 * This class tests all of the methods of PlayerBackend. Since it is the one
 * communicating to the backendDatabase, any type of string you can input into
 * the backendDatabase will be checked
 * 
 * 
 */
public class TestPlayerBackend {

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
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
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

		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
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
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array

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
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			assertEquals(backendDatabase.removePlayer(username[i]), true);
			assertEquals(backendDatabase.removePlayer(passwords[i]), false);

		}
	}

}