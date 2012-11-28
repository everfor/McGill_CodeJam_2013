package test;


import javax.swing.*;
import org.junit.Test;

import backendDatabase.*;

import frontendDatabase.*;
import static org.junit.Assert.*;

/**
 * This class tests the method GetInfo from playerBackend
 * 
 * @author Arjun
 * 
 */

public class TestGetInfoBackend{



String[] existingUsername = {"theoi", "theoi1", "theoi2", "theoi3"};
String[] existingPasswords = {"A theoi", "B theoi", "C theoi", "D theoi"};
String[] esecurityQuestions = {"A theoi", "B theoi", "C theoi", "D theoi"};
String[] esecurityAnswers = {"A theoi", "B theoi", "C theoi", "D theoi"};

String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};

PlayerBackend database = new PlayerBackend();
/**
 * Tests whether the getInfo method successfully pulls the requisite fields from the database
 * 
 */

@Test
	public void testGetInfo() {
	for(int i = 0; i<existingUsername.length; i++){ //four fields in each of the string array
		database.createPlayer(existingUsername[i], existingPasswords[i],esecurityQuestions[i], esecurityAnswers[i]);
			assertEquals((database.getInfo(existingUsername[i], databaseField[1])) , existingPasswords[i] );
			assertFalse((database.getInfo(existingUsername[3-i], databaseField[1])) == existingPasswords[i] );
			
		}
	for(int i = 0; i <existingUsername.length; i++){
		database.removePlayer(existingUsername[i]);
	}
	}

}