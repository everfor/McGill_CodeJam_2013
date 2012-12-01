package profilePageGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import playerManipulation.ChangeProfileDetails;
import playerManipulation.Player;
import playerManipulation.VerifyPlayer;
/**
 * The class deals with the entire graphical user interface related to
 * changing a players security question and security answer.
 */
public class ChangeSecurity extends ChangeProfileDetailsGUI {
	private static JPanel changeSecurityPage;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel passwordLabel, securityQuestionLabel, securityAnswerLabel;
	static JTextField securityQuestion, securityAnswer;
	static JPasswordField password;
	static JButton backToProfile, changeSecurity;
	/**
	 * This method creates a JPanel for the page where a player is given the
	 * opportunity to change his/her Security Question and Answer.
	 * 
	 * @return the JPanel for the change Security Question and Answer
	 */
	public static JPanel changeSecurityPage() {

		// Create changeSecurity Panel
		changeSecurityPage = new JPanel();
		changeSecurityPage.setBackground(Color.BLACK);
		// heading gif
		File path = new File("").getAbsoluteFile();
		ImageIcon changeSecurityIcon = new ImageIcon(path
				+ "\\res\\changeSecurity.gif");
		changeSecurityPage.setLayout(null);
		JLabel changeSecurityHeading = new JLabel();
		changeSecurityHeading.setBounds(35, 11, 439, 38);
		changeSecurityHeading.setIcon(changeSecurityIcon);
		changeSecurityPage.add(changeSecurityHeading);
		// password fields
		passwordLabel = new JLabel("Enter Password");
		passwordLabel.setBounds(121, 138, 149, 14);
		passwordLabel.setForeground(Color.WHITE);
		password = new JPasswordField();
		password.setBounds(292, 132, 86, 20);

		securityQuestionLabel = new JLabel("Enter New Security Question");
		securityQuestionLabel.setBounds(121, 78, 177, 14);
		securityQuestionLabel.setForeground(Color.WHITE);
		securityQuestion = new JTextField();
		securityQuestion.setBounds(292, 72, 86, 20);

		securityAnswerLabel = new JLabel("Enter New Security Answer");
		securityAnswerLabel.setBounds(121, 109, 166, 14);
		securityAnswerLabel.setForeground(Color.WHITE);
		securityAnswer = new JTextField();
		securityAnswer.setBounds(292, 103, 86, 20);

		// set password and Security field sizes
		password.setColumns(10);
		securityAnswer.setColumns(10);
		securityQuestion.setColumns(10);
		// add password fields
		changeSecurityPage.add(securityQuestionLabel);
		changeSecurityPage.add(securityQuestion);
		changeSecurityPage.add(securityAnswerLabel);
		changeSecurityPage.add(securityAnswer);
		changeSecurityPage.add(passwordLabel);
		changeSecurityPage.add(password);

		// back button
		backToProfile = new JButton("Cancel");
		backToProfile.setBounds(150, 163, 75, 23);
		changeSecurityPage.add(backToProfile);
		backToProfile.setActionCommand("Back");
		backToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "profilePage");
			}
		});

		changeSecurity = new JButton("Confirm Changes");
		changeSecurity.setBounds(235, 163, 144, 23);
		changeSecurity.setActionCommand("Confirm Changes");
		changeSecurity.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JTextField usernameJTextfield = new JTextField(currentPlayer
						.getUsername());
				if (VerifyPlayer.loginCheck(password, usernameJTextfield)) {
					if (ChangeProfileDetails.changeSecurity(
							securityQuestion.getText(),
							securityAnswer.getText())) {
						if (Player.clearPlayer()) {
							Player currentPlayer = new Player(
									usernameJTextfield.getText());
						}
						JOptionPane
								.showMessageDialog(
										null,
										"Your Security information has been successfully changed",
										"Security information Changed",
										JOptionPane.INFORMATION_MESSAGE);
						securityAnswer.setText("");
						password.setText("");
						securityQuestion.setText("");
						pages.show(pagePanels, "profilePage");
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"An error occured while updating your information, please try again",
										"Error", JOptionPane.ERROR_MESSAGE);
						securityAnswer.setText("");
						password.setText("");
						securityQuestion.setText("");
					}

				} else {
					JOptionPane.showMessageDialog(null,
							"Incorrect Password, please try again", "Error",
							JOptionPane.ERROR_MESSAGE);
					securityAnswer.setText("");
					password.setText("");
					securityQuestion.setText("");
				}
			}
		});
		changeSecurityPage.add(changeSecurity);

		return changeSecurityPage;
	}
}
