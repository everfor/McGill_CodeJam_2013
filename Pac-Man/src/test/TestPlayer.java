package test;
import playerManipulation.*;
import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import frontendDatabase.*;

/**
 * This class tests the methods in the Player class
 * 
 * @author Arjun
 * 
 */



public class TestPlayer {

	String[] username = {"PlayerName11", "PlayerName112", "PlayerName113", "PlayerName114"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("PlayerName11"),  
			new JTextField("PlayerName112"),  
			new JTextField("PlayerName113"),  
			new JTextField("PlayerName114")  
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
	
	
	int[] highscores = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	PlayerFrontend database = new PlayerFrontend();
	
	/**
	 * Tests whether the getUsername method successfully pulls the appropriate username from the database
	 * 
	 */
	@Test
	public void testGetUsername() {
	
	
		for (int i = 0; i<usernameTextFields.length; i++){
		database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
		
		}
		Player profile = new Player(username[0]);	
		assertEquals(profile.getUsername(), username[0]);
				
			
		for (int i = 0; i<usernameTextFields.length; i++){		
				database.deleteProfile(usernameTextFields[i]);
		}	
				
				
	}
	
	/**
	 * Tests whether the getSecurityQuestion method successfully pulls the appropriate SecurityQuestion from the database
	 * 
	 */
	@Test
	public void testGetSecurityQuestion() {
		
		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
			Player profile = new Player(username[0]);	
			assertEquals(profile.getSecurityQuestion(), securityQuestions[0]);
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}
	/**
	 * Tests whether the getSecurityAnswer method successfully pulls the appropriate SecurityAnswer from the database
	 * 
	 */
	@Test
	public void testGetSecurityAnswer() {
		
		
		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
			Player profile = new Player(username[0]);	
			assertEquals(profile.getSecurityAnswer(), securityAnswers[0]);
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}

	/**
	 * Tests whether the getLevelAchieved method successfully pulls the appropriate LevelAchieved from the database
	 * 
	 */

	@Test
	public void testGetLevelAchieved() {

		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
			Player profile = new Player(username[0]);	
			assertEquals(profile.getLevelAchieved(), 1);
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}
	
	/**
	 * Tests whether the clearPlayer method empties the profile details corresponding to the specified object
	 * 
	 */	
	@Test
	public void testClearPlayer(){
		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
			Player profile = new Player(username[0]);	
			assertEquals(profile.clearPlayer(), true);
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}
	

	

}
