package logging;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import playerManipulation.Player;

/**
 * The class deals with the graphical user interface related to resetting the
 * password after validating the security answer for a specific username
 */
public class SignUpGUI extends LoggingGUI {
	private static JPanel signUpPage;
	private static JTextField newUsernameInput;
	private static JPasswordField reenterPassword, newPasswordInput;
	private static JTextField securityQuestionInput, securityAnswerInput;
	private static JLabel newUsernameLabel, newPasswordLabel,
			reenterPasswordLabel, securityQuestion, securityAnswer;
	private static JButton signUpToLogin, registerButton;
	Player currentPlayer;
	String securityQuestionDisplayed;

	/**
	 * The method creates a panel for the graphical interface for the process of
	 * sign up and displays to the player the necessary information to be filled
	 * up
	 * 
	 * @return a JPanel for sign up
	 */
	public static JPanel signUpGUI() {

		// CardLayout for the sign Up Page
		signUpPage = new JPanel();
		signUpPage.setBackground(Color.BLACK);
		// initialize all components to be displayed
		signUpToLogin = new JButton("Back");
		signUpToLogin.setBounds(187, 361, 75, 23);
		registerButton = new JButton("Register");
		registerButton.setBounds(266, 361, 86, 23);
		newUsernameLabel = new JLabel("New Username");
		newUsernameLabel.setBounds(159, 180, 97, 14);
		newUsernameLabel.setForeground(Color.WHITE);
		newUsernameInput = new JTextField();
		newUsernameInput.setBounds(266, 177, 86, 20);
		newPasswordLabel = new JLabel("New Password");
		newPasswordLabel.setBounds(159, 211, 97, 14);
		newPasswordLabel.setForeground(Color.WHITE);
		newPasswordInput = new JPasswordField();
		newPasswordInput.setBounds(266, 208, 86, 20);
		reenterPasswordLabel = new JLabel("Re-enter Password");
		reenterPasswordLabel.setBounds(137, 242, 119, 14);
		reenterPasswordLabel.setForeground(Color.WHITE);
		reenterPassword = new JPasswordField();
		reenterPassword.setBounds(266, 239, 86, 20);
		securityQuestion = new JLabel("Security Question");
		securityQuestion.setBounds(147, 273, 110, 14);
		securityQuestion.setForeground(Color.WHITE);
		securityQuestionInput = new JTextField();
		securityQuestionInput.setBounds(266, 270, 86, 20);
		securityAnswer = new JLabel("Security Answer");
		securityAnswer.setBounds(159, 304, 97, 14);
		securityAnswer.setForeground(Color.WHITE);
		securityAnswerInput = new JTextField();
		securityAnswerInput.setBounds(266, 301, 86, 20);
		signUpPage.setLayout(null);
		// displays all components in login screen
		signUpPage.add(newUsernameLabel);
		signUpPage.add(newUsernameInput);
		signUpPage.add(newPasswordLabel);
		signUpPage.add(newPasswordInput);
		signUpPage.add(reenterPasswordLabel);
		signUpPage.add(reenterPassword);
		signUpPage.add(securityQuestion);
		signUpPage.add(securityQuestionInput);
		signUpPage.add(securityAnswer);
		signUpPage.add(securityAnswerInput);
		signUpPage.add(signUpToLogin);
		signUpPage.add(registerButton);
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\background.gif");
		JLabel signUpBackground = new JLabel();
		signUpBackground.setBounds(55, 10, 400, 150);
		signUpBackground.setIcon(background);
		signUpPage.add(signUpBackground);
		// sets display size of input textfields
		newUsernameInput.setColumns(10);
		newPasswordInput.setColumns(10);
		reenterPassword.setColumns(10);
		securityQuestionInput.setColumns(10);
		securityAnswerInput.setColumns(10);
		// sets what happens when the back button is pressed
		signUpToLogin.setActionCommand("Back");
		signUpToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				newUsernameInput.setText("");
				newPasswordInput.setText("");
				reenterPassword.setText("");
				securityQuestionInput.setText("");
				securityAnswerInput.setText("");
				pages.show(pagePanels, "login");
			}
		});
		// sets what happens when the register button is pressed
		registerButton.setActionCommand("Register");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				SignUp.register(newUsernameInput, newPasswordInput,
						securityQuestionInput, securityAnswerInput,
						reenterPassword);

				
			}
		});
		return signUpPage;
	}
	public static void clearFields(){
		newUsernameInput.setText("");
		newPasswordInput.setText("");
		reenterPassword.setText("");
		securityQuestionInput.setText("");
		securityAnswerInput.setText("");
	}
}
