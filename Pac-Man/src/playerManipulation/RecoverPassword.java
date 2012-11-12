package playerManipulation;

import FrontendDatabase.playerFrontend;
import javax.swing.JTextField;

public class RecoverPassword {

	public static boolean checkSecurityQuestion(String usernameInput, JTextField securityAnswerInput){
		boolean correctAnswer =(playerFrontend.checkSecurityAnswer(usernameInput, securityAnswerInput));
		return correctAnswer;
	}


}
