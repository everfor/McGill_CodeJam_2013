package FrontendDatabase;
import javax.swing.*;
import playerManipulation.Player;
import BackendDatabase.playerBackend;

public class playerFrontend {
	/**
	 * Handles the creation of a new entry in the database. This initializes all the variables
	 * as strings for the createPlayer() method in Backend Database to use as input into the database.
	 * @param newUsername the username for the new entry
	 * @param newPassword the password for the new entry
	 * @param securityQuestion the security question for the new entry
	 * @param securityAnswer the answer to the security question for the new entry
	 * @return returns true if it the entry has been added to the database, false otherwise
	 */
	public static boolean addNewPlayer(JTextField newUsername, JPasswordField newPassword,JTextField securityQuestion,JTextField securityAnswer){
		playerBackend database;
		database= new playerBackend();
		String username, password, securityQ, securityA;
		boolean signUpStatus= false;
		username = newUsername.getText();
		char[] temporaryPassword = newPassword.getPassword();
		password = new String(temporaryPassword);
		securityQ = securityQuestion.getText();
		securityA = securityAnswer.getText();
		signUpStatus = (database.createPlayer(username, password, securityQ, securityA));
		return signUpStatus;
	}
	/**
	 * Handles the login operation for a given username and password. The method converts the 
	 * username and password from JPasswordField and JTextField into Strings for the database
	 * to match against. 
	 * @param passwordInput player inputted password
	 * @param usernameInput player inputted username
	 * @return return true if the username password combination matches, false otherwise
	 */
	public static boolean loginOperator(JPasswordField passwordInput, JTextField usernameInput){
		playerBackend database;
		database= new playerBackend();
		boolean loginStatus = false;
		char[] temporaryPassword = passwordInput.getPassword();
		String password = new String(temporaryPassword);
		loginStatus = (database.checkLogin(usernameInput.getText(), password));
		if (loginStatus){
			Player currentPlayer = new Player(usernameInput.getText());
		}
		return loginStatus;	
	}
	/**
	 * Handles searching the database for a given username. This method tales
	 * a JTextField as input and changes it to a string in order to be searched with.
	 * @param usernameInput player inputted username
	 * @return return true if found, false otherwise
	 */
	public static boolean playerExists(JTextField usernameInput){
		playerBackend database;
		database= new playerBackend();
		boolean exists = false;
		String username = usernameInput.getText();
		exists = (database.findPlayer(username));
		return exists;	
	}
	/**
	 * Handles checking the secuirty question in the database for a given username. This method
	 * takes a username and a security answer as input and checks if it is identical as the saved one 
	 * for the specific user.
	 * @param username - the players username
	 * @param securityAnswer - the player's inputted answer
	 * @return return true if the answers are the same, false otherwise
	 */
	public static boolean checkSecurityAnswer(String username, JTextField securityAnswer){
		playerBackend database;
		database= new playerBackend();
		boolean same = false;
		String securityA = securityAnswer.getText();
		same = (database.checkSecurityQuestion(username, securityA));
		return same;	
	}
	/**
	 * Handles getting a specific info from the database for a specific player.
	 * @param username - the players username
	 * @param databaseField - Some existing info for a player
	 * @return returns the string with the data the field in the database contains
	 */
	public static String getInfo(String username, String databaseField){
		playerBackend database;
		database= new playerBackend();
		String returnText=(database.getInfo(username,databaseField));
		return returnText;
	}
	/**
	 * Handles inputting a specific info to the database for a specific player.
	 * @param username - the players username
	 * @param databaseField - Some existing info for a player
	 * @param newInfo - the updated info for that specific field in the database
	 * @return returns the changes made to the players info
	 */
	public static boolean changeProfileDetails(String username, String databaseField, String newInfo){
		boolean changesMade = false;		
		playerBackend database;
		database= new playerBackend();
		changesMade = (database.changeProfileDetails (username, databaseField, newInfo));
		return changesMade;
	}
}