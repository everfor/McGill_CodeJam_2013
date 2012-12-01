package profilePageGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logging.LoginLogout;
import playerManipulation.Player;
import frontendDatabase.PlayerFrontend;
import frontendDatabase.StatisticsFrontend;
/**
 * The class deals with the entire graphical user interface related to changing profile details
 * here a player is given an option via a comboBox to which details of their account 
 * they wish the change.
 */
public class ChangeProfileDetailsGUI extends ProfilePage {
	private static JPanel changePasswordPage;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel comboBoxSelection, changeUsername, passwordLabel,
			reenterPasswordLabel;
	static JButton logout, backToProfile, deleteAccount;
	static JTextField newUsername;
	public static JComboBox comboBox;
	static String[] comboBoxSelections = { "", "Username", "Password",
			"Security Question" };

	/**
	 * This method creates a JPanel for the Change Profile Details page. Here a
	 * player gets the choice to move to the designated page to change his/her
	 * selected information.
	 * 
	 * @return the JPanel for the change username page
	 */
	public static JPanel changeProfileDetailsPage() {
		// Create changeProfileDetailsPage Panel
		changePasswordPage = new JPanel();
		changePasswordPage.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\changePorfileDetails.gif");
		changePasswordPage.setLayout(null);
		JLabel profileDetailsBackground = new JLabel();
		profileDetailsBackground.setBounds(74, 11, 350, 38);
		profileDetailsBackground.setIcon(background);
		changePasswordPage.add(profileDetailsBackground);

		// dropdown list
		comboBoxSelection = new JLabel("Please choose a field to update: ");
		comboBoxSelection.setBounds(53, 77, 209, 14);
		comboBoxSelection.setForeground(Color.WHITE);
		changePasswordPage.add(comboBoxSelection);
		comboBox = new JComboBox(comboBoxSelections);
		comboBox.setBounds(245, 74, 110, 20);
		changePasswordPage.add(comboBox);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String comboBoxChoice = (String) cb.getSelectedItem();
				implementSelection(comboBoxChoice);
			}
		});

		// back button
		backToProfile = new JButton("Back");
		backToProfile.setBounds(367, 73, 74, 23);
		changePasswordPage.add(backToProfile);
		backToProfile.setActionCommand("Back");
		backToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "profilePage");
			}
		});
		// delete Account button
		final JTextField usernameJText = new JTextField(
				currentPlayer.getUsername());
		deleteAccount = new JButton("Delete Account");
		deleteAccount.setBounds(177, 120, 134, 23);
		deleteAccount.setBackground(Color.RED);
		changePasswordPage.add(deleteAccount);
		deleteAccount.setActionCommand("Back");
		deleteAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int button = JOptionPane.YES_NO_OPTION;
				button = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to delete your account?",
						"Warning", button);
				if (button == JOptionPane.YES_OPTION) {
					if (StatisticsFrontend.removePlayerStats(currentPlayer.getUsername())&&PlayerFrontend.deleteProfile(usernameJText)) {
						JOptionPane.showMessageDialog(null,
								"Your account has been deleted",
								"Username Deleted",
								JOptionPane.INFORMATION_MESSAGE);
						LoginLogout.logout();
					}
				}
			}
		});
		return changePasswordPage;

	}

	/**
	 * This method handles the selection of the comboBox, depending pn the
	 * choice made the player will be moved to the appropriate page
	 * 
	 * @param comboBoxChoice
	 *            a string representing the player's choice
	 */
	public static void implementSelection(String comboBoxChoice) {
		if (comboBoxChoice.equals("Username")) {
			pagePanels.add(ChangeUsername.changeUsernamePage(),
					"changeUsernamePage");
			pages.show(pagePanels, "changeUsernamePage");
		} else if (comboBoxChoice.equals("Password")) {
			pagePanels.add(ChangePassword.changePasswordPage(),
					"changePassPage");
			pages.show(pagePanels, "changePassPage");
		} else if (comboBoxChoice.equals("Security Question")) {
			pagePanels.add(ChangeSecurity.changeSecurityPage(),
					"changeSecurityPage");
			pages.show(pagePanels, "changeSecurityPage");
		}
	}

}
