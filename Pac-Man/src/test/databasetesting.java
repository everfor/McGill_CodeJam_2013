package test;

import javax.swing.*;
import FrontendDatabase.*;
import BackendDatabase.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class databasetesting{
//JTextField and JPasswordField initializations 
	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("puyolsux"),  
			new JTextField("puyolsux2"),  
			new JTextField("puyolsux3"),  
			new JTextField("puyolsux4")  
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
//initializations of string objects
	String[] username = {"puyolsux", "puyolsux2", "puyolsux3", "puyolsux4"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};
	String[] newUsername = {"permert69", "permert70", "permert71", "permert72"};
	String[] newPassword = {"password1", "password2", "password3", "password4"};
	String[] newSecurityQuestions = {"SecurityQ", "SecurityQ2", "SecurityQ3", "SecurityQ4"};
	String[] newSecurityAnswers = {"SecurityA", "SecurityA2", "SecurityA3", "SecurityA4"};
	
	String[] failUsername = {null , "", "permert71sdfasdfdfiukjhkhkjhhkjhkhfdfasfadsfdfasdfsfadffasdfsfasdfasdfas", "4353"};
	
	playerFrontend database = new playerFrontend();
	

	//adds a new player to the database
	@Test
	public void testAddNewPlayer(){ 
		for(int i = 0; i<4; i++){ //four fields in each of the string or JTextField arrays
			assertEquals(database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]),true);
		}
	}
	// checks the success of the login operation for a given username and password
	@Test
	public void testLoginOperator() {
		for(int i = 0; i<4; i++){
			assertEquals(database.loginOperator(passwordsTextField[i], usernameTextFields[i]),true);
		}
	}
	//utilizes the username to check if the added player exists in the database
	@Test
	public void testPlayerExists() { 
		for(int i = 0; i<4; i++){
			assertEquals(database.playerExists(usernameTextFields[i]),true);
		}
	}
	//checks whether the given security answer for a given username matches the security answer for the player in the database
	@Test
	public void testChecksecurityAnswer() { 
		for(int i = 0; i<4; i++){
			assertEquals(database.checkSecurityAnswer(username[i], securityAnswerTextField[i]),true);
		}
	}
	//checks whether the specific profile detail has been changed, returns true if so
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
	public void testDeleteProfile(){
			for(int i = 0; i<4; i++){
				assertEquals(database.deleteProfile(new JTextField(newUsername[i])) ,true);
//				assertEquals(database.deleteProfile(new JTextField(username[i])) ,true);
//				assertEquals(database.deleteProfile(new JTextField(failUsername[i])) ,true);
				
					
	}
		}
		
		
		
	//in order for this to test positively, first the above code needs to run in oFrder for the four players to be added
	//then the info can be pulled from their objects.

	
}