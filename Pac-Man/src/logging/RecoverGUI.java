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

import frontendDatabase.PlayerFrontend;

import playerManipulation.Player;

/**
 * The class deals with the entire graphical user interface related to
 * recovering a password for a specific username
 */

public class RecoverGUI extends LoggingGUI {
	private static JPanel recoverPage;
	private static JTextField recoverUsername;
	private static JLabel recoverUsernameLabel;
	private static JButton recoverMyPass, recoverToLogin;
	static Player currentPlayer;
	static String securityQuestionDisplayed;

	/**
	 * The method creates the GUI panel for recovering a password and dealing
	 * with all the inputs from the username.
	 * 
	 * @return the JPanel to be inputted for the recover password page
	 */
	public static JPanel recoverPasswordGUI() {
		// Create Login Panel
		recoverPage = new JPanel();
		recoverPage.setBackground(Color.BLACK);
		// initialize all components to be displayed
		recoverUsernameLabel = new JLabel("Username");
		recoverUsernameLabel.setBounds(88, 221, 71, 14);
		recoverUsernameLabel.setForeground(Color.WHITE);
		recoverUsername = new JTextField();
		recoverUsername.setBounds(159, 218, 86, 20);
		recoverMyPass = new JButton("Submit");
		recoverMyPass.setBounds(255, 217, 76, 23);
		recoverToLogin = new JButton("Back");
		recoverToLogin.setBounds(341, 217, 73, 23);
		recoverPage.setLayout(null);
		// displays all components in login screen
		recoverPage.add(recoverUsernameLabel);
		recoverPage.add(recoverUsername);
		recoverPage.add(recoverMyPass);
		recoverPage.add(recoverToLogin);
		// sets display size of input textfields
		recoverUsername.setColumns(10);
		// sets what happens when the login button is pressed
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\background.gif");
		JLabel recoverBackground = new JLabel();
		recoverBackground.setBounds(47, 11, 400, 150);
		recoverBackground.setIcon(background);
		recoverPage.add(recoverBackground);

		recoverToLogin.setActionCommand("Back");
		recoverToLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "login");
			}
		});
		recoverMyPass.setActionCommand("Submit");
		recoverMyPass.addActionListener(new ActionListener() {
			// waiting for an action to be performed in order to recover the
			// password
			@Override
			public void actionPerformed(ActionEvent event) {
				if (PlayerFrontend.playerExists(recoverUsername)) {
					currentPlayer = new Player(recoverUsername.getText());
					securityQuestionDisplayed = currentPlayer
							.getSecurityQuestion();
					pagePanels.add(ResetGUI
							.resetPasswordGUI(securityQuestionDisplayed),
							"resetPasswordPage");
					pages.show(pagePanels, "resetPasswordPage");
					recoverUsername.setText("");
				} else {
					JOptionPane.showMessageDialog(null,
							"Username does not exist, please try again",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		return recoverPage;
	}

}
