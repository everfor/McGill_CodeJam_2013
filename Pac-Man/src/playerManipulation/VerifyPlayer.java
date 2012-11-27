package playerManipulation;
import javax.swing.*;

import frontendDatabase.PlayerFrontend;

public class VerifyPlayer {
	/**
	 * Checks whether the entered username and password match the information in the database
	 * and returns a boolean indicating whether it does or not
	 * @param passwordInput the password inputted by the player
	 * @param usernameInput the username inputted by the player
	 * @return true if username and password combination
	 */
	public static boolean loginCheck(JPasswordField passwordInput, JTextField usernameInput){
		boolean Verification = (PlayerFrontend.loginOperator(passwordInput, usernameInput));
		return Verification;
	}
	/**
	 * Checks to see if the username already exists in the database and returns a 
	 * boolean indicating wether a username was found matching the given username
	 * parameter.
	 * @param usernameInput the username inputted by the player
	 * @return true if username already exists in the database, false otherwise
	 */
	public static boolean usernameExists (JTextField usernameInput){
		boolean exists = PlayerFrontend.playerExists(usernameInput);
		return exists;
	}
}
