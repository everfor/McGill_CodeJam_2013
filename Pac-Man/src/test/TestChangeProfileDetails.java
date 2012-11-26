package test;

import static org.junit.Assert.*;

import javax.swing.*;

import org.junit.Test;

import FrontendDatabase.*;
import BackendDatabase.*;
import playerManipulation.*;

public class TestChangeProfileDetails {
	
	String[] username = {"adebayorsux", "adebayorsux2", "adebayorsux3", "adebayorsux4"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("adebayorsux"),  
			new JTextField("adebayorsux2"),  
			new JTextField("adebayorsux3"),  
			new JTextField("adebayorsux4")  
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
	
	
		
	JPasswordField NewPass[] =new JPasswordField[]{
			new JPasswordField ("A myUsernameii"),
			new JPasswordField ("B myUsernameii"),
			new JPasswordField ("C myUsernameii"),
			new JPasswordField ("D myUsernameii")
	};

	

	playerFrontend database = new playerFrontend();
	ChangeProfileDetails profile = new ChangeProfileDetails();
	
	
	@Test
	public void testChangePassword() {
	
		for (int i = 0; i < 4; i++) {	
	database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		}
			
	
		assertEquals(profile.changePassword(username[0], passwordsTextField[0], NewPass[0]), true);
		
		for (int i = 0; i < 4; i++) {	
		database.deleteProfile(new JTextField(username[i]));
		database.deleteProfile(usernameTextFields[i]);
		
		
		}
		
	}

}
