package test;

import org.junit.Test;

import backendDatabase.*;

import frontendDatabase.*;
import static org.junit.Assert.*;

import javax.swing.*;

/**
 * This class tests the methods pertaining to Statistics in the Backend Database
 * 
 * @author Arjun
 * 
 */

public class TestStatisticsBackend {

	String[] username = {"UserName41", "UserName95", "UserName953", "UserName14"};
	String[] passwords = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityQuestions = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	String[] securityAnswers = {"A myUsernameii", "B myUsernameii", "C myUsernameii", "D myUsernameii"};
	
	int[] highscores = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int[] rank = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	PlayerBackend database = new PlayerBackend();
	StatisticsBackend profile = new StatisticsBackend();
	
	/**
	 * Tests whether the method createPlayerStats successfully creates default statistics for the profile object
	 * 
	 */
	@Test
	public void testCreatePlayerStats() {
		
		for (int i = 0; i < username.length; i++) { // four fields in each of the string array
			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
			assertEquals(profile.createPlayerStats(username[i]), true);
		}
		
			for (int i = 0; i < username.length; i++) {
				database.removePlayer(username[i]);
			}
		}
			
	
	/**
	 * Tests whether the method removesPlayerStats successfully removes statistics for the profile object
	 * 
	 */
	@Test
	public void testRemovePlayerStats() {
		for (int i = 0; i < username.length; i++) { // four fields in each of the string array
			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
			assertEquals(profile.removePlayerStats(username[i]), true);
		}
		
			for (int i = 0; i < username.length; i++) {
				database.removePlayer(username[i]);
			}
		}
			
	
	/**
	 * Tests whether the method getScore successfully returns the correct score after it is set by the setScore method
	 * 
	 */
	@Test
	public void testGetScore() {
		
		for (int i = 0; i < username.length; i++) { 
			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
			profile.createPlayerStats(username[i]);
		}
		
		for (int j = 0; j < highscores.length; j++) { 
			
			profile.setScore(username[0], rank[j], highscores[j]);
			
			System.out.println(username[0] + " " + rank[j] + " " + highscores[j]);
			
		}
		profile.setScore(username[0], 9, 9);
		System.out.println(profile.getScore(username[0],9));
//		
//		assertEquals(profile.getScore(username[0], 1), 1);
	
		
			for (int i = 0; i < username.length; i++) {
				database.removePlayer(username[i]);
			}
		}
	

//	@Test
//	public void testSetScore() {
//		for (int i = 0; i < username.length; i++) { 
//			database.createPlayer(username[i], passwords[i], securityQuestions[i], securityAnswers[i]);
//		}
//			
//		for (int i = 0; i < username.length; i++) {
//			for(int j = 0; j < 10; j++ ){
//			profile.setScore(username[0], 2, highscores[j]);
//		}
//			assertEquals(profile.getScore(username[0], 1), true);
//		
//		
//			for (int i = 0; i < username.length; i++) {
//				database.removePlayer(username[i]);
//			}
//		
//}

//	@Test
//	public void testGetGlobalHighScoreFields() {
//		fail("Not yet implemented");
//	}

}
