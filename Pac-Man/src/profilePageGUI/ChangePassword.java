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

import logging.SignUp;
import playerManipulation.ChangeProfileDetails;
import playerManipulation.Player;
import playerManipulation.VerifyPlayer;
/**
 * The class deals with the entire graphical user interface related to
 * changing a players password.
 */
public class ChangePassword extends ChangeProfileDetailsGUI {
	private static JPanel changePasswordPage;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel oldPasswordLabel, newpasswordLabel, reenterNewPasswordLabel;
	static JPasswordField oldPassword, newPassword, reenterNewPassword;
	static JButton backToProfile, changePassword;

	/**
	 * This method creates a JPanel for the page where a player is given the
	 * opportunity to change his/her password.
	 * 
	 * @return the JPanel for the change password page
	 */
	public static JPanel changePasswordPage() {

		// Create change Password Panel
		changePasswordPage = new JPanel();
		changePasswordPage.setBackground(Color.BLACK);
		// heading gif
		File path = new File("").getAbsoluteFile();
		ImageIcon changePassIcon = new ImageIcon(path
				+ "\\resources\\changePassword.gif");
		changePasswordPage.setLayout(null);
		JLabel changePassHeading = new JLabel();
		changePassHeading.setBounds(74, 11, 350, 38);
		changePassHeading.setIcon(changePassIcon);
		changePasswordPage.add(changePassHeading);
		// password fields
		oldPasswordLabel = new JLabel("Enter Old Password");
		oldPasswordLabel.setBounds(120, 73, 116, 14);
		oldPasswordLabel.setForeground(Color.WHITE);
		oldPassword = new JPasswordField();
		oldPassword.setBounds(268, 70, 86, 20);
		newpasswordLabel = new JLabel("Enter New Password");
		newpasswordLabel.setBounds(120, 104, 138, 14);
		newpasswordLabel.setForeground(Color.WHITE);
		newPassword = new JPasswordField();
		newPassword.setBounds(268, 101, 86, 20);
		reenterNewPasswordLabel = new JLabel("Re-Enter New Password");
		reenterNewPasswordLabel.setBounds(120, 136, 138, 14);
		reenterNewPasswordLabel.setForeground(Color.WHITE);
		reenterNewPassword = new JPasswordField();
		reenterNewPassword.setBounds(268, 132, 86, 20);
		// set password field sizes
		oldPassword.setColumns(10);
		newPassword.setColumns(10);
		reenterNewPassword.setColumns(10);
		// add password fields
		changePasswordPage.add(oldPasswordLabel);
		changePasswordPage.add(oldPassword);
		changePasswordPage.add(newpasswordLabel);
		changePasswordPage.add(newPassword);
		changePasswordPage.add(reenterNewPasswordLabel);
		changePasswordPage.add(reenterNewPassword);
		changePassword = new JButton("Confirm Changes");
		changePassword.setBounds(120, 174, 137, 23);
		changePassword.setActionCommand("Confirm Changes");
		changePassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JTextField username = new JTextField(Player.getUsername());
				if (VerifyPlayer.loginCheck(oldPassword, username)) {
					if (SignUp.passwordLength(newPassword)) {
						if (ChangeProfileDetails.changePassword(
								Player.getUsername(), newPassword,
								reenterNewPassword)) {
							if (Player.clearPlayer()) {
								Player currentPlayer = new Player(username
										.getText());
							}
							JOptionPane
									.showMessageDialog(
											null,
											"Your password have been successfully changed",
											"Password Changed",
											JOptionPane.INFORMATION_MESSAGE);
							newPassword.setText("");
							reenterNewPassword.setText("");
							oldPassword.setText("");
							pages.show(pagePanels, "profilePage");
						} else {
							JOptionPane
									.showMessageDialog(
											null,
											"An error occured while recovering your password, please try again",
											"Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane
								.showMessageDialog(
										null,
										"Password is not between 8-45 characters, please try again",
										"Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				else {
					JOptionPane.showMessageDialog(null,
							"Incorrect password, Please try again", "Error",
							JOptionPane.ERROR_MESSAGE);
					newPassword.setText("");
					reenterNewPassword.setText("");
					oldPassword.setText("");
				}
			}
		});
		changePasswordPage.add(changePassword);
		// back button
		backToProfile = new JButton("Cancel");
		backToProfile.setBounds(268, 174, 83, 23);
		changePasswordPage.add(backToProfile);
		backToProfile.setActionCommand("Back");
		backToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "changeProfileDetailsPage");
			}
		});

		return changePasswordPage;
	}
}
