package test;

import static org.junit.Assert.*;

import org.junit.Test;
import frontendDatabase.*;
import backendDatabase.*;

/**
 * This class tests the methods pertaining to Statistics in the Frontend Database
 * 
 * @author Arjun
 * 
 */
public class TestStatisticsFrontend {
	String[] username = {"PlayerName783", "PlayerName7832", "PlayerName7833", "PlayerName7834"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	
	int[] highscores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	PlayerBackend database = new PlayerBackend();
	StatisticsFrontend profile = new StatisticsFrontend();
	StatisticsBackend profileBackend = new StatisticsBackend();
	
	
	/**
	 * Tests whether the method intializePlayerStats successfully creates default statistics for the profile object
	 * 
	 */
	@Test
	public void testInitializePlayerStats() {
		
		for (int i = 0; i < username.length; i++) { // four fields in each of the string array
			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
			
		}
		
		for (int i = 0; i < username.length; i++) {
			assertEquals(profile.initializePlayerStats(username[i]), true);
		}
		
			for (int i = 0; i < username.length; i++) {
				database.removePlayer(username[i]);
			}
		}
	/**
	 * Tests whether the method getHighScores successfully returns the correct highscores afte they are set by the setHighScores method
	 * 
	 */
	@Test
	public void testGetHighScores() {
		for (int i = 0; i < username.length; i++) { 
			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
		
		}
		for (int i = 0; i < username.length; i++) {
			profile.initializePlayerStats(username[i]);
		}
		
		
			for (int i = 0; i < highscores.length; i++) {
			
				profile.setHighScores(username[0], highscores[i] );
			}
		
			
			assertEquals(profile.getHighScores(username[0]), highscores);
		
		
			for (int i = 0; i < username.length; i++) {
				database.removePlayer(username[i]);
			}
		}
	

//	@Test
//	public void testGetGlobalHighScores() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetHighScores() {
//		
//	}



}
