package test;

import org.junit.Test;

import backendDatabase.*;

import frontendDatabase.*;
import static org.junit.Assert.*;

import javax.swing.*;


/**
 * This class tests all of the methods of PlayerFrontend
 * 
 * @author Arjun
 * 
 */
public class FrontendTesting{
//JTextField and JPasswordField initializations 
	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("PlayerName"),  
			new JTextField("PlayerName2"),  
			new JTextField("PlayerName3"),  
			new JTextField("PlayerName4")  
	}; 

	JPasswordField passwordsTextField[] =new JPasswordField[]{
			new JPasswordField ("A myUsernameii"),
			new JPasswordField ("B myUsernameii"),
			new JPasswordField ("C myUsernameii"),
			new JPasswordField ("D myUsernameii")
	};

	JTextField securityQuestionTextField[] = new JTextField[]{
			new JTextField("A myUsernameii"),  
			new JTextField("B myUsernameii"),  
			new JTextField("C myUsernameii"),  
			new JTextField("D myUsernameii")  
	};  
	JTextField securityAnswerTextField[] = new JTextField[]{ 
			new JTextField("A myUsernameii"),  
			new JTextField("B myUsernameii"),  
			new JTextField("C myUsernameii"),  
			new JTextField("D myUsernameii")   
	};  
	JTextField usernameTextFieldsfail[] = new JTextField[]{ 
			new JTextField("invalid"),  
			new JTextField("invalid1"),  
			new JTextField("invalid2"),  
			new JTextField("invalid3")  
	}; 
//initializations of string objects
	String[] username = {"PlayerName", "PlayerName2", "PlayerName3", "PlayerName4"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};
	String[] newUsername = {"Username69", "Username70", "Username71", "Username72"};
	String[] newPassword = {"password1", "password2", "password3", "password4"};
	String[] newSecurityQuestions = {"SecurityQ", "SecurityQ2", "SecurityQ3", "SecurityQ4"};
	String[] newSecurityAnswers = {"SecurityA", "SecurityA2", "SecurityA3", "SecurityAu4"};
	
	String[] failUsername = {null , "", "Username71sdfasdfdfiukjhkhkjhhkjhkhfdfasfadsfdfasdfsfadffasdfsfasdfasdfas", "4353"};
	
	PlayerFrontend database = new PlayerFrontend();
	

	/**
	 * This method tests the addition of a player via the frontend database
	 * 
	 */

	@Test
	public void testAddNewPlayer(){ 
		for(int i = 0; i<usernameTextFields.length; i++){ //four fields in each of the string or JTextField arrays
			assertEquals(database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]),true);
		}
	}
	/**
	 * This method checks the success of the login operation for a given username and password
	 */
	@Test
	public void testLoginOperator() {
		for(int i = 0; i<usernameTextFields.length; i++){
			assertEquals(database.loginOperator(passwordsTextField[i], usernameTextFields[i]),true);
			assertEquals(database.loginOperator(passwordsTextField[i], usernameTextFields[3-i]), false);
		}
	}
	/**
	 *utilizes the username to check if the added player exists in the database
	 */
	@Test
	public void testPlayerExists() { 
		for(int i = 0; i<usernameTextFields.length; i++){
			assertEquals(database.playerExists(usernameTextFields[i]),true);
			assertEquals(database.playerExists(usernameTextFieldsfail[i]),false);
			
		}
	}
	/**
	 *checks whether the given security answer for a given username matches the security answer for the player in the database
	 */
	@Test
	public void testChecksecurityAnswer() { 
		for(int i = 0; i<usernameTextFields.length; i++){
			assertEquals(database.checkSecurityAnswer(username[i], securityAnswerTextField[i]),true);
			assertEquals(database.checkSecurityAnswer(username[i], securityAnswerTextField[3-i]),false);
		}
	}
	/**
	checks whether the specific profile detail has been changed after using usingChangeProfileDetails
	 */

	@Test
	public void testChangeProfileDetails() {
		for(int i = 0; i<usernameTextFields.length; i++){ //four fields in each of the string array
			
			assertEquals(database.changeProfileDetails(username[i], databaseField[0], newUsername[i]) , true);
			assertEquals(database.changeProfileDetails(username[i], databaseField[1], newPassword[i]) , true);
			assertEquals(database.changeProfileDetails(username[i], databaseField[2], newSecurityQuestions[i]) , true);
			assertEquals(database.changeProfileDetails(username[i], databaseField[3], newSecurityAnswers[i]) , true);
			
//			assertEquals(database.changeProfileDetails(username[i], databaseField[0], failUsername[i]) , false);
		
		}
	}
	/**
	 * checks whether the deleteProfile method successfully deletes the profile 
	 */
		@Test
	public void testDeleteProfile(){
			for(int i = 0; i<usernameTextFields.length; i++){
				assertEquals(database.deleteProfile(new JTextField(newUsername[i])) ,true);
//				assertEquals(database.deleteProfile(new JTextField(username[i])) ,true);
//				assertEquals(database.deleteProfile(new JTextField(failUsername[i])) ,true);
				
					
	}
		}
		
		
		
	//in order for this to test positively, first the above code needs to run in oFrder for the four players to be added
	//then the info can be pulled from their objects.

	
}