package test;


import javax.swing.*;
import FrontendDatabase.*;
import BackendDatabase.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class testGetInfoBackend{



String[] existingUsername = {"theoi", "theoi1", "theoi2", "theoi3"};
String[] existingPasswords = {"A theoi", "B theoi", "C theoi", "D theoi"};
String[] esecurityQuestions = {"A theoi", "B theoi", "C theoi", "D theoi"};
String[] esecurityAnswers = {"A theoi", "B theoi", "C theoi", "D theoi"};

String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};

playerBackend database = new playerBackend();


@Test
	public void testGetInfo() {
	for(int i = 0; i<4; i++){ //four fields in each of the string array
		database.createPlayer(existingUsername[i], existingPasswords[i],esecurityQuestions[i], esecurityAnswers[i]);
			assertEquals((database.getInfo(existingUsername[0], databaseField[1])) , existingPasswords[0] );
			
		}
	for(int i = 0; i <4; i++){
		database.removePlayer(existingUsername[i]);
	}
	}

}