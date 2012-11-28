package test;
import playerManipulation.*;
import static org.junit.Assert.*;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

import frontendDatabase.*;




public class TestPlayerGetHighScores {

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
	

	@Test
	public void testGetHighScores(){
		for (int i = 0; i<usernameTextFields.length; i++){
			database.addNewPlayer(usernameTextFields[i], passwordsTextField[i], securityQuestionTextField[i], securityAnswerTextField[i]);
			
			}
		 	
			Player profile = new Player(username[0]);
			for (int j = 0; j <10 ; j++){
			profile.setNewHighScore(highscores[j]);
			}
			
			
			assertEquals(profile.getHighScores(), highscores) ;
					
				
			for (int i = 0; i<usernameTextFields.length; i++){		
					database.deleteProfile(usernameTextFields[i]);
			}	
	}
	

}
