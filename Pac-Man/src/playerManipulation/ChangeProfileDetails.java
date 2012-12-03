package playerManipulation;

import javax.swing.JPasswordField;

import logging.SignUp;
import frontendDatabase.PlayerFrontend;

/**
 * this class handles the changing of a player's profile details such as
 * username, password and security information. The methods in this class allow
 * a player to change his/her password, username and Security informaion.
 * 
 */
public class ChangeProfileDetails {
	/**
	 * This method checks if the two given passwords are matching and if this is
	 * true it will update their password with the one inputted.
	 * 
	 * @param username
	 *            the player's username
	 * @param Inputtedpassword
	 *            the new password for the player
	 * @param reenterPassword
	 *            the players reinputted password
	 * @return true if the password is changed, false otherwise
	 */
	public static boolean changePassword(String username, JPasswordField Inputtedpassword,
			JPasswordField reenterPassword) {
		String pass = "password";
		if (SignUp.passwordMatch(Inputtedpassword, reenterPassword)) {
			char[] temporaryPassword1 = Inputtedpassword.getPassword();
			String password1 = new String(temporaryPassword1);
			if (PlayerFrontend.changeProfileDetails(username, pass, password1)) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	/**
	 * This method handles changing a players username with a new inputted one.
	 * 
	 * @param username
	 *            old username in the database
	 * @param newUsername
	 *            new username inputted by the player
	 * @return true if the username is changed, false otherwise
	 */
	public static boolean changeUsername(String username, String newUsername) {
		return PlayerFrontend.changeProfileDetails(username, "username", newUsername);

	}

	/**
	 * This method handles changing a player's security question and security
	 * answer.
	 * 
	 * @param securityQuestion
	 *            the new security questioninputted by player
	 * @param securityAnswer
	 *            the new security answer inputted by player
	 * @return true if the changes have been made in the database, false
	 *         otherwise
	 */
	public static boolean changeSecurity(String username, String securityQuestion,
			String securityAnswer) {
		boolean updated = false;
		if ((PlayerFrontend.changeProfileDetails(username, "securityQuestion", securityQuestion))
				&& (PlayerFrontend.changeProfileDetails(username, "securityAnswer", securityAnswer))) {
			updated = true;
		}
		return updated;
	}
}