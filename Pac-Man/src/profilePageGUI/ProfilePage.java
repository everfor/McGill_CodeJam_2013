package profilePageGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logging.LoggingGUI;
import logging.LoginLogout;
import playerManipulation.Player;

/**
 * The class deals with the entire graphical user interface related to the
 * player's profile page where he/she can select to do one of the following
 * options: Play game, change profile details, logout or view highscores.
 */
public class ProfilePage extends LoggingGUI {
	private static JPanel profilePageGUI;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JButton playGame, highscores, logout, changeProfileDetails, settings;
	static JLabel username;

	/**
	 * This method creates a JPanel for the player's profile page this is where
	 * a player is given the option to either play game, change his/her profile
	 * details, view highscores and logout
	 * 
	 * @return the JPanel for the Profile page
	 * @wbp.parser.entryPoint
	 */
	public static JPanel profilePage() {
		// Create profilepage Panel
		profilePageGUI = new JPanel();
		profilePageGUI.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		// The following gif and all its occurences were extracted from:
		// http://www.thepcmanwebsite.com/media/pacman_flash/
		ImageIcon background = new ImageIcon(path + "\\res\\image\\profileBackground.gif");
		profilePageGUI.setLayout(null);
		JLabel profilePageBackground = new JLabel();
		profilePageBackground.setBounds(0, 23, 510, 286);
		profilePageBackground.setIcon(background);
		profilePageGUI.add(profilePageBackground);
		//add username to profile page
		username = new JLabel(Player.getUsername());
		username.setForeground(Color.RED);
		username.setBounds(10, 571, 142, 27);
		// initialize playGame button
		ImageIcon playGameBG = new ImageIcon(path + "\\res\\image\\playgamebutton.gif");
		JButton playGame = new JButton(playGameBG);
		playGame.setBounds(168, 332, 174, 36);
		playGame.setBackground(Color.BLACK);
		playGame.setBorderPainted(false);
		playGame.setOpaque(false);
		playGame.setFocusPainted(false);
		profilePageGUI.add(playGame);
		// initialize highscores button
		ImageIcon highscoresBG = new ImageIcon(path + "\\res\\image\\highscores.gif");
		JButton highscores = new JButton(highscoresBG);
		highscores.setBounds(155, 391, 200, 31);
		highscores.setBackground(Color.BLACK);
		highscores.setBorderPainted(false);
		highscores.setOpaque(false);
		highscores.setFocusPainted(false);
		profilePageGUI.add(highscores);
		// initialize change profile details button
		ImageIcon changeProfileDetailsBG = new ImageIcon(path
				+ "\\res\\image\\changePorfileDetails.gif");
		JButton changeProfileDetails = new JButton(changeProfileDetailsBG);
		changeProfileDetails.setBounds(80, 445, 350, 38);
		changeProfileDetails.setBackground(Color.BLACK);
		changeProfileDetails.setBorderPainted(false);
		changeProfileDetails.setOpaque(false);
		changeProfileDetails.setFocusPainted(false);
		profilePageGUI.add(changeProfileDetails);
		// initialize logout button
		ImageIcon logoutBG = new ImageIcon(path + "\\res\\image\\logout.gif");
		JButton logout = new JButton(logoutBG);
		logout.setBounds(194, 560, 122, 36);
		logout.setBackground(Color.BLACK);
		logout.setBorderPainted(false);
		logout.setOpaque(false);
		logout.setFocusPainted(false);
		profilePageGUI.add(logout);
		
		// settings button
		ImageIcon settingImage = new ImageIcon(path + "\\res\\image\\settings.gif");
		
																					
		JButton settings = new JButton(settingImage);
		settings.setBounds(155, 506, 200, 31);
		settings.setBackground(Color.BLACK);
		settings.setBorderPainted(false);
		settings.setOpaque(false);
		settings.setFocusPainted(false);
		profilePageGUI.add(settings);
		profilePageGUI.add(username);
		// set button actions
		// playGame button
		playGame.setActionCommand("playGame");
		playGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pagePanels.add(LevelSelectGUI.levelSelectPage(), "LevelSelectGUI");
				pages.show(pagePanels, "LevelSelectGUI");

			}
		});

		// logout button
		logout.setActionCommand("logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				LoginLogout.logout();
			}
		});
		// change profile details button
		changeProfileDetails.setActionCommand("changeProfileDetails");
		changeProfileDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pagePanels.add(ChangeProfileDetailsGUI.changeProfileDetailsPage(),
						"changeProfileDetailsPage");
				pages.show(pagePanels, "changeProfileDetailsPage");
			}
		});
		// Highscores button
		highscores.setActionCommand("highscores");
		highscores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pagePanels.add(HighscoresGUI.displayHighscores(), "highscores");
				pages.show(pagePanels, "highscores");
			}
		});
		// settings button
		settings.setActionCommand("settings");
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pagePanels.add(Settings.settingsPage(), "settingsPage");
				pages.show(pagePanels, "settingsPage");
			}
		});
		return profilePageGUI;

	}
/**
 * This method sets the visibility of the profile page and all its pages according to the 
 * boolean paramater
 * @param choice the boolean that decides to set visibility true or false.
 */
	public static void setMasterPageVisiblity(boolean choice) {
		masterPage.setVisible(choice);
	}
}