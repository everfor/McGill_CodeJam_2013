package test;
import playerManipulation.*;
import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import BackendDatabase.*;



public class TestPlayer {

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
	
	

	playerBackend database = new playerBackend();
	Player profile = new Player(username[0]);
	
	
	@Test
	public void testGetUsername() {
//		for (int i = 0; i < 4; i++) {	
//			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
//					}
	
		assertEquals(profile.getUsername(), "adebayorsux");
				
//				for (int i = 0; i < 4; i++) {	
//				
//				database.removePlayer(username[i]);
//				
//				
//				}
	}
//
//	@Test
//	public void testGetSecurityQuestion() {
//		
//	}
//
//	@Test
//	public void testGetSecuirtyAnswer() {
//		
//	}
//
//	@Test
//	public void testGetLevelAchieved() {
//		
//	}

}
