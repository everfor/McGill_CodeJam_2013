package test;

import org.junit.Test;

import backendDatabase.*;

import frontendDatabase.*;
import playerManipulation.*;
import static org.junit.Assert.*;

import javax.swing.*;



/**
 * This class tests all of the methods of ChangeProfileDetails
 * 
 * @author Arjun
 * 
 */


public class TestChangeProfileDetails {
	
	String[] username = {"PlayerName", "PlayerName2", "PlayerName3", "PlayerName5"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	
	String[] securityQuestions2 = {"A myUsernameiii", "B myUsernameiii", "C myUsernameiii", "D myUsernameiii"};
	String[] securityAnswers2 = {"A myUsernameiii", "B myUsernameiii", "C myUsernameiii", "D myUsernameiii"};
	

	String[] newusername = {"rooneysux", "rooneysux2", "rooneysux3", "rooneysux5"};

	
	JTextField usernameTextField[] = new JTextField[]{ 
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
			new JPasswordField ("A myUsernameii"),
			new JPasswordField ("B myUsernameii"),
			new JPasswordField ("C myUsernameii"),
			new JPasswordField ("D myUsernameii")
	};

	

	PlayerFrontend database = new PlayerFrontend();
	ChangeProfileDetails profile = new ChangeProfileDetails();
	
	/**
	 * Tests whether the method changePassword successfully changes the old password attributed to a username to a new password
	 * 
	 */
	@Test
	public void testChangePassword() {
	
		for (int i = 0; i < usernameTextField.length; i++) {	
	database.addNewPlayer(usernameTextField[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		}
			
		for (int i = 0; i < usernameTextField.length; i++) {	
		assertEquals(profile.changePassword(username[i], passwordsTextField[i], NewPass[i]), true);
		
		}
		
		for (int i = 0; i < usernameTextField.length; i++) {	
		database.deleteProfile(new JTextField(username[i]));
		database.deleteProfile(usernameTextField[i]);
		
		
		}
		
	}
	/**
	 * Tests whether the method changeUsername successfully changes the old username to a new username
	 * 
	 */
	@Test
	public void testChangeUsername() {
		for (int i = 0; i < usernameTextField.length; i++) {	
			database.addNewPlayer(usernameTextField[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
				}
					
		for (int i = 0; i < usernameTextField.length; i++) {
				assertEquals(profile.changeUsername(username[i], newusername[i]), true);
		}
				
				for (int i = 0; i < usernameTextField.length; i++) {	
				database.deleteProfile(new JTextField(newusername[i]));
				database.deleteProfile(usernameTextField[i]);
				
				
				}
				
			}
	
	/**
	 * Tests whether the method changePassword successfully changes the old security questions and security answers
	 * attributed to a username to new security questions and answers
	 * 
	 */
	@Test
	public void testChangeSecurity() {
		for (int i = 0; i < usernameTextField.length; i++) {	
			database.addNewPlayer(usernameTextField[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
				}
					
			
		
		for (int i = 0; i < usernameTextField.length; i++) {
				assertEquals(profile.changeSecurity(securityQuestions2[i], securityAnswers2[i]), true);
		}
				
				for (int i = 0; i < usernameTextField.length; i++) {	
				database.deleteProfile(new JTextField(username[i]));
				database.deleteProfile(usernameTextField[i]);
				
				
				}
				
			}
	}
	


