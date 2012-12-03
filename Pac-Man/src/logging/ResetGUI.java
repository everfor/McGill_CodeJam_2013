package logging;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import playerManipulation.RecoverPassword;

/**
 * The class deals with the graphical user interface related to resetting the
 * password after validating the security answer for a specific username
 * 
 */
public class ResetGUI extends RecoverGUI {
	private static JPanel resetPasswordPage;
	private static JTextField resetSecurityAnswerInput;
	private static JLabel resetSecurityQuestion, resetSecurityAnswer;
	private static JButton resetMyPass, resetToLogin;
	static String securityQuestionDisplayed;

	/**
	 * The method verifies the player's security answer to the security
	 * questions and draws all the graphical interface needed for the user
	 * 
	 * @param playersSQ
	 *            the player's auto generated security question
	 * @return a JPanel to be inputted when this method is called
	 */
	public static JPanel resetPasswordGUI(String playersSQ) {
		// Create Login Panel
		resetPasswordPage = new JPanel();
		resetPasswordPage.setBackground(Color.BLACK);
		// initialize all components to be displayed
		resetMyPass = new JButton("Submit");
		resetMyPass.setBounds(252, 225, 86, 23);
		resetToLogin = new JButton("Back");
		resetToLogin.setBounds(148, 225, 65, 23);
		resetSecurityQuestion = new JLabel("Security Question: " + playersSQ + " ");
		resetSecurityQuestion.setBounds(148, 172, 287, 14);
		resetSecurityQuestion.setForeground(Color.WHITE);
		resetSecurityAnswer = new JLabel("Security Answer");
		resetSecurityAnswer.setBounds(148, 197, 94, 14);
		resetSecurityAnswer.setForeground(Color.WHITE);
		resetSecurityAnswerInput = new JTextField();
		resetSecurityAnswerInput.setBounds(252, 194, 86, 20);
		resetPasswordPage.setLayout(null);
		// displays all components in login screen

		resetPasswordPage.add(resetSecurityQuestion);
		resetPasswordPage.add(resetSecurityAnswer);
		resetPasswordPage.add(resetSecurityAnswerInput);
		resetSecurityAnswerInput.setColumns(10);
		resetPasswordPage.add(resetMyPass);
		resetPasswordPage.add(resetToLogin);
		// sets display size of input textfields
		// sets what happens when the login button is pressed

		resetToLogin.setActionCommand("Back");
		resetToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "login");
				resetSecurityAnswerInput.setText("");
			}
		});
		resetMyPass.setActionCommand("resetMyPass");

		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path + "\\res\\image\\background.gif");
		JLabel resetBG = new JLabel(background);
		resetBG.setBounds(47, 11, 400, 150);

		resetPasswordPage.add(resetBG);
		resetMyPass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (RecoverPassword.checkSecurityQuestion(currentPlayer.getUsername(),
						resetSecurityAnswerInput)) {
					pagePanels.add(ChangePassGUI.changePasswordGUI(), "changePassPage");
					pages.show(pagePanels, "changePassPage");

				} else {
					JOptionPane.showMessageDialog(null, "Invalid Answer, please try again",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		return resetPasswordPage;
	}
}