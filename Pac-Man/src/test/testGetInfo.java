package test;


import javax.swing.*;
import org.junit.Test;

import backendDatabase.*;

import frontendDatabase.*;
import static org.junit.Assert.*;

/**
 * This class tests the method GetInfo from playerFrontend
 * 
 * @author Arjun
 * 
 */

public class TestGetInfo{
	
	
	String[] existingUsername = {"rosicky", "rosicky1", "rosicky2", "rosicky3"};
	String[] existingPasswords = {"A rosicky", "B rosicky", "C rosicky", "D rosicky"};
	String[] esecurityQuestions = {"A rosicky", "B rosicky", "C rosicky", "D rosicky"};
	String[] esecurityAnswers = {"A rosicky", "B rosicky", "C rosicky", "D rosicky"};
	
	String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};
	PlayerFrontend database = new PlayerFrontend();
	PlayerBackend backdatabase = new PlayerBackend();
	
	/**
	 * Tests whether the getInfo method successfully pulls the requisite fields from the database
	 * 
	 */

	@Test
	public void testGetInfo() {
		for(int i = 0; i<existingUsername.length; i++){
			backdatabase.createPlayer(existingUsername[i], existingPasswords[i], esecurityQuestions[i], esecurityAnswers[i]);
			assertEquals(database.getInfo(existingUsername[i], databaseField[1]),existingPasswords[i]);
			assertEquals(database.getInfo(existingUsername[i], databaseField[2]),esecurityQuestions[i]);
			assertEquals(database.getInfo(existingUsername[i], databaseField[3]),esecurityAnswers[i]);
			backdatabase.removePlayer(existingUsername[i]);
		}
	}
	
	
	
	
	
}