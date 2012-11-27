package test;
import playerManipulation.*;

import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import frontendDatabase.PlayerFrontend;

import playerManipulation.ChangeProfileDetails;

public class TestVerifyPlayer {
	String[] username = {"PlayerName", "PlayerName2", "PlayerName3", "PlayerName5"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("PlayerName"),  
			new JTextField("PlayerName2"),  
			new JTextField("PlayerName3"),  
			new JTextField("PlayerName5")  
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
	JTextField usernameTextFieldsfail[] = new JTextField[]{ 
			new JTextField("invalid"),  
			new JTextField("invalid2"),  
			new JTextField("invalid3"),  
			new JTextField("invalid4")  
	}; 
	

	PlayerFrontend database = new PlayerFrontend();
	VerifyPlayer profile = new VerifyPlayer();

	@Test
	public void testLoginCheck() {
		
		for (int i = 0; i < usernameTextFields.length; i++) {	
		database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		}
		
		for (int i = 0; i < usernameTextFields.length; i++) {	
	assertEquals(profile.loginCheck(passwordsTextField[i],usernameTextFields[i]), true);
	
	}
	
	for (int i = 0; i < usernameTextFields.length; i++) {	
	database.deleteProfile(new JTextField(username[i]));
	database.deleteProfile(usernameTextFields[i]);
	
	
	}
		
	}

	@Test
	public void testUsernameExists() {
	
		for (int i = 0; i < usernameTextFields.length; i++) {	
		database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		}
		
		for (int i = 0; i < usernameTextFields.length; i++) {	
		assertEquals(profile.usernameExists(usernameTextFields[i]), true);
		assertEquals(profile.usernameExists(usernameTextFieldsfail[i]), false);
		
		}
		
		for (int i = 0; i < usernameTextFields.length; i++) {	
		database.deleteProfile(new JTextField(username[i]));
		database.deleteProfile(usernameTextFields[i]);
		
		
		}
		
	}

}
