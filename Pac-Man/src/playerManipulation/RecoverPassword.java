package playerManipulation;

import javax.swing.JTextField;

import frontendDatabase.PlayerFrontend;

public class RecoverPassword {

	public static boolean checkSecurityQuestion(String usernameInput, JTextField securityAnswerInput){
		boolean correctAnswer =(PlayerFrontend.checkSecurityAnswer(usernameInput, securityAnswerInput));
		return correctAnswer;
	}


}
