import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import backendDatabase.PlayerBackend;
import frontendDatabase.PlayerFrontend;

/**
 * This class tests the method GetInfo from playerFrontend
 * 
 */

public class TestGetInfo {

	// variables needed for testing
	String[] existingUsername = { "teamBlaBLA", "Team33333", "My Team",
			"One More3" };
	String[] existingPasswords = { "12345678", "myTeam333", "ohis isthis3",
			"TeamArjun3" };
	String[] esecurityQuestions = { "Is 2+2=4?", "what is this project",
			"did you scream pacman 3 times?", "in what month were you born" };
	String[] esecurityAnswers = { "yes it isss",
			"the best project in the world", "i did once, maybe twice",
			"1st one lets say january" };

	String[] databaseField = { "username", "password", "securityQuestion",
			"securityAnswer" };
	PlayerFrontend database = new PlayerFrontend();
	PlayerBackend backdatabase = new PlayerBackend();

	/**
	 * Tests whether the getInfo method successfully pulls the requisite fields
	 * from the database
	 * 
	 */

	@Test
	public void testGetInfo() {
		for (int i = 0; i < existingUsername.length; i++) {
			backdatabase.createPlayer(existingUsername[i],
					existingPasswords[i], esecurityQuestions[i],
					esecurityAnswers[i]);
			assertEquals(
					database.getInfo(existingUsername[i], databaseField[1]),
					existingPasswords[i]);
			assertEquals(
					database.getInfo(existingUsername[i], databaseField[2]),
					esecurityQuestions[i]);
			assertEquals(
					database.getInfo(existingUsername[i], databaseField[3]),
					esecurityAnswers[i]);
			assertFalse(database.getInfo(existingUsername[i], databaseField[2]) == esecurityAnswers[i]);
			backdatabase.removePlayer(existingUsername[i]);
		}
	}

}