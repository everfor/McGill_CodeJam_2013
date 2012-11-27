package playerManipulation;

import javax.swing.JPasswordField;

import logging.SignUp;
import frontendDatabase.PlayerFrontend;

public class ChangeProfileDetails {

	public static boolean changePassword(String username,
			JPasswordField Inputtedpassword, JPasswordField reenterPassword) {
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

	public static boolean changeUsername(String username, String newUsername) {
		return PlayerFrontend.changeProfileDetails(username, "username",
				newUsername);

	}

	public static boolean changeSecurity(String securityQuestion,
			String securityAnswer) {
		boolean updated = false;
		if ((PlayerFrontend.changeProfileDetails(Player.getUsername(),
				"securityQuestion", securityQuestion))
				&& (PlayerFrontend.changeProfileDetails(Player.getUsername(),
						"securityAnswer", securityAnswer))) {
			updated = true;
		}
		return updated;
	}
}