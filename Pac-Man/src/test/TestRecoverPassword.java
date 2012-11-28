package test;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import playerManipulation.*;
import static org.junit.Assert.*;

import org.junit.Test;

import frontendDatabase.PlayerFrontend;

/**
 * This class tests the methods required in order to Recover a Password
 * 
 * @author Arjun
 * 
 */
public class TestRecoverPassword {
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
	
	
	JPasswordField OriginalPass[] =new JPasswordField[]{
			new JPasswordField ("A myUsernameii"),
			new JPasswordField ("B myUsernameii"),
			new JPasswordField ("C myUsernameii"),
			new JPasswordField ("D myUsernameii")
	};
	
	JPasswordField NewPass[] =new JPasswordField[]{
			new JPasswordField ("A Password"),
			new JPasswordField ("B Password"),
			new JPasswordField ("C Password"),
			new JPasswordField ("D Password")
	};
	

	PlayerFrontend database = new PlayerFrontend();
	RecoverPassword profile = new RecoverPassword();
	
	/**
	 * Tests whether the checkSecurityQuestion method successfully matches the correct answer with the correct security question
	 * 
	 */
	@Test
	public void testCheckSecurityQuestion() {
		for (int i = 0; i < usernameTextFields.length; i++) {	
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
				}
					
		for (int i = 0; i < usernameTextFields.length; i++) {	
				assertEquals(profile.checkSecurityQuestion(username[i], securityAnswerTextField[i]), true);
				assertEquals(profile.checkSecurityQuestion(username[i], securityAnswerTextField[3-i]), false);
				
		}
				
				for (int i = 0; i < usernameTextFields.length; i++) {	
				database.deleteProfile(new JTextField(username[i]));
				database.deleteProfile(usernameTextFields[i]);
				
				
				}
	}

}
