
package test;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;

import FrontendDatabase.*;
import BackendDatabase.*;



import FrontendDatabase.playerFrontend;
import BackendDatabase.playerBackend;

public class backendtesting {
		
	playerBackend database = new playerBackend();
	
	
	String[] username = {"diaby69", "diaby70", "diaby71", "diaby72"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};
	
	String[] newUsername = {"permert69", "permert70", "permert71", "permert72"};
	String[] newPassword = {"password1", "password2", "password3", "password4"};
	String[] newSecurityQuestions = {"SecurityQ", "SecurityQ2", "SecurityQ3", "SecurityQ4"};
	String[] newSecurityAnswers = {"SecurityA", "SecurityA2", "SecurityA3", "SecurityA4"};
	
	String[] failUsername = {null , "", "permert71sdfasdfdfiukjhkhkjhhkjhkhfdfasfadsfdfasdfsfadffasdfsfasdfasdfas", "4353"};
	
	
	
	@Test
	public void testCreatePlayer() {

		for (int i = 0; i < 4; i++) { // four fields in each of the string array
			assertEquals(database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]), true);
		}

	}

	@Test
	public void testCheckLogin() {
		for (int i = 0; i < 4; i++) { // four fields in each of the string array
			assertEquals(database.checkLogin(username[i], passwords[i]), true);
		}
	}

	@Test
	public void testFindPlayer() {
		for (int i = 0; i < 4; i++) { // four fields in each of the string array
			assertEquals(database.findPlayer(username[i]), true);
		}
	}

	@Test
	public void testCheckSecurityQuestion() {
		
		for(int i = 0; i<4; i++){ //four fields in each of the string array
			assertEquals(database.checkSecurityQuestion(username[i], securityAnswers[i]) , true);
		}
	}

	@Test
	public void testChangeProfileDetails() {
		for(int i = 0; i<4; i++){ //four fields in each of the string array
			
			assertEquals(database.changeProfileDetails(username[i], databaseField[0], newUsername[i]) , true);
			assertEquals(database.changeProfileDetails(username[i], databaseField[1], newPassword[i]) , true);
			assertEquals(database.changeProfileDetails(username[i], databaseField[2], newSecurityQuestions[i]) , true);
			assertEquals(database.changeProfileDetails(username[i], databaseField[3], newSecurityAnswers[i]) , true);
			
//			assertEquals(database.changeProfileDetails(username[i], databaseField[0], failUsername[i]) , false);
			
		}
		
	}
	

	@Test
	public void testRemovePlayer() {
		for(int i = 0; i<4; i++){ //four fields in each of the string array
			assertEquals(database.removePlayer(newUsername[i]) , true);
//			assertEquals(database.removePlayer(newUsername[i]) , true);
//			assertEquals(database.removePlayer(failUsername[i]) , true);
			
		}
		
	}
	
}
