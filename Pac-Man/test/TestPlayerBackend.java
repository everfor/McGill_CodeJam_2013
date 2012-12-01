import static org.junit.Assert.*;

import org.junit.Test;

import backendDatabase.PlayerBackend;

/**
 * This class tests all of the methods of PlayerBackend. Since it is the one
 * communicating to the database, any type of string you can input into the
 * database will be checked
 * 
 * 
 */
public class TestPlayerBackend {

	PlayerBackend database = new PlayerBackend();

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

	/**
	 * This method tests the creation of a player in the backend database
	 * 
	 */

	@Test
	public void testCreatePlayer() {

		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			assertEquals(database.createPlayer(username[i], passwords[i],
					securityQuestions[i], securityAnswers[i]), true);

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
			assertEquals(database.checkLogin(username[i], passwords[i]), true);
			assertEquals(database.checkLogin(username[i], passwords[3 - i]),
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
			assertEquals(database.findPlayer(username[i]), true);
			assertEquals(database.findPlayer("invalid"), false);
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
			assertEquals(database.checkSecurityQuestion(username[i],
					securityAnswers[i]), true);
			assertEquals(database.checkSecurityQuestion(username[i],
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
	 * This method tests whether the player is removed successfully from the
	 * database by the removePlayer method
	 */
	@Test
	public void testRemovePlayer() {
		for (int i = 0; i < username.length; i++) { // four fields in each of
													// the string array
			assertEquals(database.removePlayer(newUsername[i]), true);
			assertEquals(database.removePlayer(passwords[i]), false);

		}
	}

}