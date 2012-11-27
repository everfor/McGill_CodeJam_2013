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

public class ProfilePage extends LoggingGUI {
	private static JPanel personalGUI;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JButton playGame, highscores, logout, changeProfileDetails;

	// TODO remove
	/**
	 * @wbp.parser.entryPoint
	 */

	public static JPanel profilePage() {
		// Create profilepage Panel
		personalGUI = new JPanel();
		personalGUI.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\profileBackground.gif");// TODO make a method to
														// do this
		JLabel profilePageBackground = new JLabel();
		profilePageBackground.setIcon(background);
		personalGUI.add(profilePageBackground);
		// initialize playGame button
		ImageIcon playGameBG = new ImageIcon(path
				+ "\\resources\\playgamebutton.gif");
		JButton playGame = new JButton(playGameBG);
		playGame.setBackground(Color.BLACK);
		playGame.setBorderPainted(false);
		playGame.setOpaque(false);
		playGame.setFocusPainted(false);
		personalGUI.add(playGame);
		// initialize highscores button
		ImageIcon highscoresBG = new ImageIcon(path
				+ "\\resources\\highscores.gif");
		JButton highscores = new JButton(highscoresBG);
		highscores.setBackground(Color.BLACK);
		highscores.setBorderPainted(false);
		highscores.setOpaque(false);
		highscores.setFocusPainted(false);
		personalGUI.add(highscores);
		// initialize change profile details button
		ImageIcon changeProfileDetailsBG = new ImageIcon(path
				+ "\\resources\\changePorfileDetails.gif");
		JButton changeProfileDetails = new JButton(changeProfileDetailsBG);
		changeProfileDetails.setBackground(Color.BLACK);
		changeProfileDetails.setBorderPainted(false);
		changeProfileDetails.setOpaque(false);
		changeProfileDetails.setFocusPainted(false);
		personalGUI.add(changeProfileDetails);
		// initialize logout button
		ImageIcon logoutBG = new ImageIcon(path + "\\resources\\logout.gif");
		JButton logout = new JButton(logoutBG);
		logout.setBackground(Color.BLACK);
		logout.setBorderPainted(false);
		logout.setOpaque(false);
		logout.setFocusPainted(false);
		personalGUI.add(logout);

		// set button actions
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
				pagePanels.add(
						ChangeProfileDetailsGUI.changeProfileDetailsPage(),
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
		return personalGUI;

	}
}