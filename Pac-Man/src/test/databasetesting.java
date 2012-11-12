package test;
import static org.junit.Assert.*;

import javax.swing.*;
import FrontendDatabase.*;
import BackendDatabase.*;
import org.junit.Assert.*;
import org.junit.Test;

public class databasetesting{
//JTextField and JPasswordField initializations 
	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("myUsernameii"),  
			new JTextField("myUsernameii1"),  
			new JTextField("myUsernameii2"),  
			new JTextField("myUsernameii3")  
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
	String[] username = {"myUsernameii", "myUsernameii1", "myUsernameii2", "myUsernameii3"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	String[] databaseField = {"username", "password", "securityQuestion", "securityAnswer"};
	String[] newinfo = {"newUsernamess", "newUsernamess", "newUsernamess", "newUsernamess"};

	//adds a new player to the database
	@Test
	public void testAddNewPlayer(){ 
		for(int i = 0; i<4; i++){ //four fields in each of the string or JTextField arrays
			assertEquals(playerFrontend.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]),true);
		}
	}
	// checks the success of the login operation for a given username and password
	@Test
	public void testLoginOperator() {
		for(int i = 0; i<4; i++){
			assertEquals(playerFrontend.loginOperator(passwordsTextField[i], usernameTextFields[i]),true);
		}
	}
	//utilizes the username to check if the added player exists in the database
	@Test
	public void testPlayerExists() { 
		for(int i = 0; i<4; i++){
			assertEquals(playerFrontend.playerExists(usernameTextFields[i]),true);
		}
	}
	//checks whether the given security answer for a given username matches the security answer for the player in the database
	@Test
	public void testChecksecurityAnswer() { 
		for(int i = 0; i<4; i++){
			assertEquals(playerFrontend.checkSecurityAnswer(username[i], securityAnswerTextField[i]),true);
		}
	}
	//checks whether the specific profile detail has been changed, returns true if so
	@Test
	public void testChangeProfileDetails() {
		for(int i = 0; i<4; i++){
			assertEquals(playerFrontend.changeProfileDetails(username[0], databaseField[i], newinfo[i]),true);
		}
	}
	//in order for this to test positively, first the above code needs to run in order for the four players to be added
	//then the info can be pulled from their objects.
/*
	String[] existingUsername = {"myUsernameii", "myUsernameii1", "myUsernameii2", "myUsernameii3"};
	String[] existingPasswords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] esecurityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] esecurityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	
	@Test
	public void testGetInfo() {
		for(int i = 0; i<4; i++){
			assertEquals(playerFrontend.getInfo(existingUsername[i], databaseField[1]),existingPasswords[i]);
			assertEquals(playerFrontend.getInfo(existingUsername[i], databaseField[2]),esecurityQuestions[i]);
			assertEquals(playerFrontend.getInfo(existingUsername[i], databaseField[3]),esecurityAnswers[i]);
		}
	}*/
}