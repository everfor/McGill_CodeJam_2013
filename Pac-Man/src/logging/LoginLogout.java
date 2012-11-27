package logging;

import javax.swing.*;

import playerManipulation.Player;
import playerManipulation.VerifyPlayer;
import profilePageGUI.ProfilePage;

/**
 * The class deals with logging in and logging out graphical user interface and
 * pulling a username from the player class for easier access to data, when
 * commands need to be performed
 * 
 */
public class LoginLogout extends LoggingGUI {
	/**
	 * Checks the given username and password combination against the database.
	 * If they match it outputs a pop-up notification to notify the player that
	 * he/she has successfully logged in. If they do not match it outputs a
	 * pop-up notification to notify the player that he/she was unsuccessful in
	 * logging in.
	 * 
	 * @param usernameInput
	 *            username inputted by player
	 * @param passwordInput
	 *            password inputted by player
	 */
	public static void login(JTextField usernameInput,
			JPasswordField passwordInput) {
		if (VerifyPlayer.loginCheck(passwordInput, usernameInput)) {
			// pop-up notification of successful login
			pagePanels.add(ProfilePage.profilePage(), "profilePage");
			pages.show(pagePanels, "profilePage");
		} else {
			// pop-up notification of unsuccessful login
			JOptionPane.showMessageDialog(null,
					"Invalid username or password, please try again", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
/**
 * The method logs a user out clearing all his info from the program
 */
	public static void logout() {
		if (Player.clearPlayer()) {
			// pop-up notification of successful login
			JOptionPane.showMessageDialog(null, "Successful Logout", "",
					JOptionPane.INFORMATION_MESSAGE);
			pages.show(pagePanels, "login");
		} else {
			// pop-up notification of unsuccessful login
			JOptionPane.showMessageDialog(null,
					"Unsuccessful Logout, please try again", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}