package test;
import playerManipulation.*;

import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import playerManipulation.ChangeProfileDetails;
import FrontendDatabase.playerFrontend;

public class TestVerifyPlayer {
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
			new JPasswordField ("A Password"),
			new JPasswordField ("B Password"),
			new JPasswordField ("C Password"),
			new JPasswordField ("D Password")
	};
	

	playerFrontend database = new playerFrontend();
	VerifyPlayer profile = new VerifyPlayer();

	@Test
	public void testLoginCheck() {
		
		for (int i = 0; i < 4; i++) {	
		database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		}
		
		for (int i = 0; i < 4; i++) {	
	assertEquals(profile.loginCheck(passwordsTextField[i],usernameTextFields[i]), true);
	
	}
	
	for (int i = 0; i < 4; i++) {	
	database.deleteProfile(new JTextField(username[i]));
	database.deleteProfile(usernameTextFields[i]);
	
	
	}
		
	}

	@Test
	public void testUsernameExists() {
	
		for (int i = 0; i < 4; i++) {	
		database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		}
		
		for (int i = 0; i < 4; i++) {	
		assertEquals(profile.usernameExists(usernameTextFields[i]), true);
		
		}
		
		for (int i = 0; i < 4; i++) {	
		database.deleteProfile(new JTextField(username[i]));
		database.deleteProfile(usernameTextFields[i]);
		
		
		}
		
	}

}
