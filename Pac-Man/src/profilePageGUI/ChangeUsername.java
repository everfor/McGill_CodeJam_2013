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
 * changing a players username.
 */
public class ChangeUsername extends ChangeProfileDetailsGUI {
	private static JPanel changeUsernamePage;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel passwordLabel, newUsernameLabel, usernameLabel;
	static JPasswordField password;
	static JButton backToProfile, changeUsername;

	/**
	 * This method creates a JPanel for the page where a player is given the
	 * opportunity to change his/her username.
	 * 
	 * @return the JPanel for the change username page
	 */
	public static JPanel changeUsernamePage() {

		// Create changeUsername Panel
		changeUsernamePage = new JPanel();
		changeUsernamePage.setBackground(Color.BLACK);
		// heading gif
		File path = new File("").getAbsoluteFile();
		ImageIcon changeUsernameIcon = new ImageIcon(path
				+ "\\resources\\changeUsername.gif");
		JLabel changeUsernameHeading = new JLabel();
		changeUsernameHeading.setIcon(changeUsernameIcon);
		changeUsernamePage.add(changeUsernameHeading);
		// password fields
		passwordLabel = new JLabel("Enter Password");
		passwordLabel.setForeground(Color.WHITE);
		password = new JPasswordField();

		newUsernameLabel = new JLabel("Enter New Username");
		newUsernameLabel.setForeground(Color.WHITE);
		newUsername = new JTextField();

		usernameLabel = new JLabel("Your current Username is : "
				+ Player.getUsername());
		usernameLabel.setForeground(Color.WHITE);

		// set password field sizes
		password.setColumns(10);
		newUsername.setColumns(10);
		// add password fields
		changeUsernamePage.add(usernameLabel);
		changeUsernamePage.add(newUsernameLabel);
		changeUsernamePage.add(newUsername);
		changeUsernamePage.add(passwordLabel);
		changeUsernamePage.add(password);

		// back button
		backToProfile = new JButton("Cancel");
		changeUsernamePage.add(backToProfile);
		backToProfile.setActionCommand("Back");
		backToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "changeProfileDetailsPage");
			}
		});

		changeUsername = new JButton("Confirm Changes");
		changeUsername.setActionCommand("Confirm Changes");
		changeUsername.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JTextField usernameJTextfield = new JTextField(currentPlayer
						.getUsername());
				if (VerifyPlayer.loginCheck(password, usernameJTextfield)) {
					if (!VerifyPlayer.usernameExists(newUsername)) {
						if (SignUp.usernameLength(newUsername)) {
							if (ChangeProfileDetails.changeUsername(
									Player.getUsername(), newUsername.getText())) {
								if (Player.clearPlayer()) {
									Player currentPlayer = new Player(
											newUsername.getText());
								}
								JOptionPane
										.showMessageDialog(
												null,
												"Your Username have been successfully changed",
												"Username Changed",
												JOptionPane.INFORMATION_MESSAGE);
								newUsername.setText("");
								password.setText("");
								pages.show(pagePanels, "profilePage");
							}
						} else {
							JOptionPane
									.showMessageDialog(
											null,
											"Username is not between 6-16 characters, please try again",
											"Error", JOptionPane.ERROR_MESSAGE);
						}
					}

					else {
						JOptionPane.showMessageDialog(null,
								"Username is already taken, please try again",
								"Error", JOptionPane.ERROR_MESSAGE);
						newUsername.setText("");
						password.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Incorrect Password, please try again", "Error",
							JOptionPane.ERROR_MESSAGE);
					newUsername.setText("");
					password.setText("");
				}
			}

		});
		changeUsernamePage.add(changeUsername);

		return changeUsernamePage;
	}
}
