package playerManipulation;
import javax.swing.*;
import FrontendDatabase.playerFrontend;

public class ChangeProfileDetails {

	public static boolean changePassword(String username,JPasswordField Inputtedpassword, JPasswordField reenterPassword){
		String pass = "password";
		if(logging.SignUp.passwordMatch(Inputtedpassword, reenterPassword)){
			char[] temporaryPassword1 = Inputtedpassword.getPassword();
			String password1 = new String(temporaryPassword1);
			if (playerFrontend.changeProfileDetails(username, pass, password1)){
				return true;
			}
		}
		else{
			return false;
		}
		return false;
	}
}