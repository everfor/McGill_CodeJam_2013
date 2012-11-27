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
import javax.swing.JPasswordField;

import playerManipulation.ChangeProfileDetails;

/**
 * The class deals with the entire graphical user interface related to changing
 * a password
 * 
 */
public class ChangePassGUI extends ResetGUI {
	private static JPanel changePasswordPage;
	private static JPasswordField recoverNewPassword,
			reenterRecoverNewPassword;
	private static JLabel recoverNewPasswordLabel,
			reenterRecoverNewPasswordLabel;
	private static JButton newPassword, cancelRecover;
	static String securityQuestionDisplayed;

	/**
	 * The method creates a panel for changeing the password
	 * 
	 * @return a Jpanel for this GUI 
	 */
	public static JPanel changePasswordGUI() {
		// Create Login Panel
		changePasswordPage = new JPanel();
		changePasswordPage.setBackground(Color.BLACK);
		// initialize all components to be displayed
		newPassword = new JButton("Submit");
		newPassword.setBounds(260, 245, 113, 23);
		cancelRecover = new JButton("Cancel");
		cancelRecover.setBounds(135, 245, 113, 23);
		recoverNewPasswordLabel = new JLabel("Enter New Password");
		recoverNewPasswordLabel.setBounds(137, 184, 142, 14);
		recoverNewPasswordLabel.setForeground(Color.WHITE);
		recoverNewPassword = new JPasswordField();
		recoverNewPassword.setBounds(287, 181, 86, 20);
		reenterRecoverNewPasswordLabel = new JLabel("Re-enter New Password");
		reenterRecoverNewPasswordLabel.setBounds(135, 213, 146, 14);
		reenterRecoverNewPasswordLabel.setForeground(Color.WHITE);
		reenterRecoverNewPassword = new JPasswordField();
		reenterRecoverNewPassword.setBounds(287, 208, 86, 20);
		changePasswordPage.setLayout(null);
		// displays all components in login screen
		changePasswordPage.add(recoverNewPasswordLabel);
		changePasswordPage.add(recoverNewPassword);
		changePasswordPage.add(reenterRecoverNewPasswordLabel);
		changePasswordPage.add(reenterRecoverNewPassword);
		changePasswordPage.add(newPassword);
		changePasswordPage.add(cancelRecover);
		recoverNewPassword.setColumns(10);
		reenterRecoverNewPassword.setColumns(10);
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\background.gif");
		JLabel changePassBG = new JLabel(background);
		changePassBG.setBounds(47, 11, 400, 150);
		changePasswordPage.add(changePassBG);

		// Listening for a new password to be inputed.
		newPassword.setActionCommand("newPassword");
		newPassword.addActionListener(new ActionListener() {
			@Override
			// if the action was performed, the method comunicates to change
			// profile details in order to fullfill the command
			public void actionPerformed(ActionEvent event) {
				if (SignUp.passwordLength(recoverNewPassword)) {
					if (ChangeProfileDetails.changePassword(
							currentPlayer.getUsername(), recoverNewPassword,
							reenterRecoverNewPassword)) {
						JOptionPane.showMessageDialog(null,
								"Your password have been successfully changed",
								"Password Changed",
								JOptionPane.INFORMATION_MESSAGE);
						recoverNewPassword.setText("");
						reenterRecoverNewPassword.setText("");
						pages.show(pagePanels, "login");
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
		});
		// If the player needs to go back and cancel this process of recovering
		// the password
		cancelRecover.setActionCommand("cancelRecover");
		cancelRecover.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "login");
				recoverNewPassword.setText("");
				reenterRecoverNewPassword.setText("");
			}
		});
		return changePasswordPage;
	}
}
