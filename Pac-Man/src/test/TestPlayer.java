package test;
import playerManipulation.*;
import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import frontendDatabase.*;




public class TestPlayer {

	String[] username = {"PlayerName", "PlayerName2", "PlayerName3", "PlayerName4"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};

	
	JTextField usernameTextFields[] = new JTextField[]{ 
			new JTextField("PlayerName"),  
			new JTextField("PlayerName2"),  
			new JTextField("PlayerNameusernameTextFields.length"),  
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
	
	

	
	PlayerFrontend database = new PlayerFrontend();
	
	
	
	
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

	@Test
	public void testGetSecuirtyAnswer() {
		
		
		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
			Player profile = new Player(username[0]);	
			assertEquals(profile.getSecurityAnswer(), securityAnswers[0]);
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}

	

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
	
	@Test
	public void testGetHighScores(){
		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
		 	
			Player profile = new Player(username[0]);
			profile.setNewHighScore(10);
			assertEquals(profile.getHighScores()[0], 5);
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}
	
	

}
