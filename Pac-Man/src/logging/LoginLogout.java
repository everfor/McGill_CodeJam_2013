package logging;

import javax.swing.*;
import playerManipulation.VerifyPlayer;

public class LoginLogout{

/**
 * Checks the given username and password combination against the database.
 * If they match it outputs a pop-up notification to notify the player that he/she has 
 * successfully logged in. If they do not match it outputs a pop-up notification 
 * to notify the player that he/she was unsuccessful in logging in.
 * @param usernameInput username inputted by player
 * @param passwordInput password inputted by player
 */
	public static void login(JTextField usernameInput, JPasswordField passwordInput){
		if (VerifyPlayer.loginCheck(passwordInput, usernameInput)){
			//pop-up notification of successful login
			JOptionPane.showMessageDialog(null, "Welcome back "+ usernameInput.getText(),"Successful Login",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			//pop-up notification of unsuccessful login
			JOptionPane.showMessageDialog(null, "Invalid username or password, please try again","Error",JOptionPane.ERROR_MESSAGE);

		}

	}

}