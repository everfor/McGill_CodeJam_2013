package playerManipulation;

import javax.swing.JTextField;

import frontendDatabase.PlayerFrontend;

/**
 * This class is used to check from the database whether a particular username
 * has a correct answer to their security question.
 */
public class RecoverPassword {
	/**
	 * This method is used to check from the database whether a particular
	 * username has a correct security answer. This is used when a player has
	 * forgotten his/her password and is trying to restore it.
	 * 
	 * @param usernameInput
	 *            the player's username
	 * @param securityAnswerInput
	 *            the answer to his security question (inputted by the Player)
	 * @return true if the given username has the given security answer, false
	 *         otherwise
	 */
	public static boolean checkSecurityQuestion(String usernameInput, JTextField securityAnswerInput) {
		boolean correctAnswer = (PlayerFrontend.checkSecurityAnswer(usernameInput,
				securityAnswerInput));
		return correctAnswer;
	}

}
