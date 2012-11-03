package logging;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import playerManipulation.VerifyPlayer;
import FrontendDatabase.playerFrontend;
public class SignUp {
	static JLabel usernameLabel,passwordLabel,reenterPasswordLabel,securityQuestionLabel,securityAnswerLabel;
	static JTextField newUsername;
	static JTextField securityQuestion;
	static JTextField securityAnswer;
	static JPasswordField newPassword;
	static JPasswordField reenterPassword;     
	//A personalized error message for signup problems
	static String errorMessage;
	//determines which errors occured during signup
	static boolean	usernameTaken, badPassLength, badUsernameLength, differentPasswords, usernameCharacters;

	/**
	 * Checks if two passwords are equal
	 * @param password1 First password entered
	 * @param password2 Second password entered (re-enter password field)
	 * @return true if equals, false otherwise
	 */
	public static boolean passwordMatch(JPasswordField password1, JPasswordField password2 ){
		boolean match= false;
		char[] tempPassword1 = password1.getPassword();
		String pass1 = new String(tempPassword1);
		char[] tempPassword2 = password2.getPassword();
		String pass2 = new String(tempPassword2);
		if (pass1.equals(pass2)){
			match = true;
		}
		return match;
	}
	/**
	 * Checks if password length is within correct range
	 * @param password inputed password
	 * @return true if correct range, false otherwise
	 */
	public static boolean passwordLength(JPasswordField password){
		boolean goodLength = false;
		char[] tempPassword = password.getPassword();
		String pass = new String(tempPassword);
		if (pass.length()<=45 && pass.length()>=8){
			goodLength = true;
		}
		return goodLength;

	}
	/**
	 * Checks if Username length is within correct range
	 * @param usernameInput inputed username
	 * @return true if correct range, false otherwise
	 */
	public static boolean usernameLength(JTextField usernameInput){
		boolean goodLength = false;
		String username = usernameInput.getText();
		if (username.length()<=16 && username.length()>=6){
			goodLength = true;
		}
		return goodLength;

	}
	/**
	 * Checks if Username contains invalid characters.
	 * Allowed characters are: lowercase letters, uppercase letters,
	 * numbers, underscores, dashes and fullstops
	 * @param usernameInput
	 * @return true if invalid character used, false otherwise
	 */
	public static boolean invalidCharacters(JTextField usernameInput){
		String username = usernameInput.getText();
		Pattern myPattern = Pattern.compile("[^.-._-_---A-Za-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Matcher myMatcher = myPattern.matcher(username);
		return myMatcher.find();
	}
	/**
	 * signupErrors checks for errors in data input during the signing up 
	 * process and flags which errors occur. This is done through the use 
	 * of global boolean variables, one for every error. The errors that it checks 
	 * for are the following:
	 * 1)If the Username already exists
	 * 2)If the Password and re-enter password fields are equal
	 * 3)If the Password length is not between the given 8-45 characters
	 * 4)If the Username length is not between the given 6-16 characters
	 * 5)If the Username contains special characters
	 * The method then calls giveErrorMessage() which uses these booleans
	 * to generate an appropriate error message for the player.
	 */
	public static void signupErrors(JTextField newUsername, JPasswordField newPassword,JTextField securityQuestion,
			JTextField securityAnswer, JPasswordField reenterPassword){
		//true meanse there is an error false means there isnt
		usernameTaken = badPassLength = badUsernameLength = 
				differentPasswords = usernameCharacters = false;
		errorMessage = "";
		if (VerifyPlayer.usernameExists(newUsername)){
			usernameTaken = true;
		}
		if (!passwordMatch (newPassword, reenterPassword)){
			differentPasswords = true;
		}
		if (!passwordLength(newPassword)){
			badPassLength =true;
		}
		if (!usernameLength(newUsername)){
			badUsernameLength = true;
		}
		if (invalidCharacters(newUsername)){
			usernameCharacters = true;
		}
		giveErrorMessage();
	}
	/**
	 * This method uses the error booleans in order to generate a string
	 * that includes all the errors encountered during the signUp Process
	 * this string is then used to tell the player what needs to be changed 
	 * in order to create his/her account. 
	 */
	public static void giveErrorMessage(){
		if (usernameTaken){ 
			errorMessage+="Username already taken, ";
		}
		if (differentPasswords){ 
			errorMessage+="Password mismatch, ";
		}
		if (badPassLength){ 
			errorMessage+="Password is not between 8-45 characters, ";
		}
		if (badUsernameLength){ 
			errorMessage+="Username is not between 6-16 characters, ";
		}
		if (usernameCharacters){ 
			errorMessage+="Username can't contains special character, ";
		}
	}
/**
 * This method checks the player inputted data for errors using signupError() and
 * creates a personalized error message if errors do exist. These errors are shown in a pop-up
 * window. If no errors are found this method attempts to add a new entry into the database. If
 * it does so successfully the player is notified by a pop-up window that he has been added to the database.
 * Otherwise if an error occurs while inputting the data into the database the player is also notified 
 * via a pop-up window that the process was unsuccessful
 * @param newUsername the username inputted by the player
 * @param newPassword the password inputted by the player
 * @param securityQuestion the security question inputted by the player
 * @param securityAnswer the answer to the security question inputted by the player
 * @param reenterPassword the password re-entered by the player
 */
	public static void register(JTextField newUsername, JPasswordField newPassword,JTextField securityQuestion,
			JTextField securityAnswer, JPasswordField reenterPassword){
		
		signupErrors(newUsername, newPassword, securityQuestion, securityAnswer,  reenterPassword);
		if (errorMessage.equals("")){
			if (playerFrontend.addNewPlayer(newUsername, newPassword, securityQuestion, securityAnswer)){
				JOptionPane.showMessageDialog(null, "SignUp Successful","Welcome",JOptionPane.INFORMATION_MESSAGE);	
			}
			else{
				JOptionPane.showMessageDialog(null, "SignUp UnSuccessful, try again","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, errorMessage,"Error",JOptionPane.ERROR_MESSAGE);
		}
	}


}
