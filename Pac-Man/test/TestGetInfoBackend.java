import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import backendDatabase.PlayerBackend;

/**
 * This class tests the method GetInfo from playerBackend
 * 
 */

public class TestGetInfoBackend {

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

	PlayerBackend database = new PlayerBackend();

	/**
	 * Tests whether the getInfo method successfully pulls the requisite fields
	 * from the database
	 * 
	 */

	@Test
	public void testGetInfo() {
		for (int i = 0; i < existingUsername.length; i++) { // four fields in
															// each of the
															// string array
			database.createPlayer(existingUsername[i], existingPasswords[i],
					esecurityQuestions[i], esecurityAnswers[i]);
			assertEquals(
					(database.getInfo(existingUsername[i], databaseField[1])),
					existingPasswords[i]);
			// false cases
			assertFalse((database.getInfo(existingUsername[3 - i],
					databaseField[1])) == existingPasswords[i]);

		}
		for (int i = 0; i < existingUsername.length; i++) {
			database.removePlayer(existingUsername[i]);
		}
	}

}