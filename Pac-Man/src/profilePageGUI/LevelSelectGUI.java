package profilePageGUI;

import interfaceFramework.Game;
import interfaceFramework.Maze;
import interfaceFramework.Score;

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

import playerManipulation.Player;
/**
 * The class deals with the entire graphical user interface related to
 * Level selection process prior to starting a new game.
 * 
 */
public class LevelSelectGUI extends ProfilePage {
	private static JPanel levelSelected;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel comboBoxSelection, changeUsername, passwordLabel,
			reenterPasswordLabel;
	static JButton logout, backToProfile;
	public static JComboBox comboBoxLevels;
	static String[] comboBoxSelectionsLevels = new String[101];

	/**
	 * This method creates a JPanel for the Level Selection page. Here a
	 * player gets the choice to start at an unlocked level. He/She will
	 * only be allowed to play at a level that he/she has already unlocked
	 * 
	 * @return the JPanel for the change username page
	 * 
	 */
	public static JPanel levelSelectPage() {
		// Create changeProfileDetailsPage Panel
		for (int i = 0; i < comboBoxSelectionsLevels.length; i++) {
			comboBoxSelectionsLevels[i] = Integer.toString(i + 1);
		}

		levelSelected = new JPanel();
		levelSelected.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\res\\image\\playgamebutton.gif");
		levelSelected.setLayout(null);
		JLabel levelBackground = new JLabel();
		levelBackground.setBounds(150, 11, 350, 38);
		levelBackground.setIcon(background);
		levelSelected.add(levelBackground);
		// levels unlocked
		for (int i = 1; i < currentPlayer.getLevelAchieved(); i++) {
			Integer.toString(i);
		}
		// dropdown list
		comboBoxSelection = new JLabel("Choose Level: ");
		comboBoxSelection.setBounds(109, 77, 209, 14);
		comboBoxSelection.setForeground(Color.WHITE);
		levelSelected.add(comboBoxSelection);
		comboBoxLevels = new JComboBox(comboBoxSelectionsLevels);
		comboBoxLevels.setBounds(203, 74, 110, 20);
		levelSelected.add(comboBoxLevels);

		comboBoxLevels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String comboBoxChoice = (String) cb.getSelectedItem();
				implementSelection(comboBoxChoice);
			}
		});

		// back button
		backToProfile = new JButton("Back");
		backToProfile.setBounds(328, 73, 74, 23);
		levelSelected.add(backToProfile);
		backToProfile.setActionCommand("Back");
		backToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "profilePage");
			}
		});

		return levelSelected;

	}

	/**
	 * This method handles the selection of the comboBox, depending on the
	 * choice made the player will either be told that he hasnt unlocked the 
	 * selected level or he will be redirected to a new game in the selected
	 * level.
	 * 	 * 
	 * @param comboBoxChoice
	 *            a string representing the player's choice
	 */
	public static void implementSelection(String comboBoxChoice) {
		boolean inRange = checkRange(comboBoxChoice);
		/*
		 * if the level selected is in range start a new game with a new score
		 * in the given level
		 */
		if (inRange) {
			setMasterPageVisiblity(false);
			Score.setScore(0);
			Score.setLevelScore(0);
			Game.setCollided(0);
			Game.setCurrentLevel(Integer.parseInt(comboBoxChoice));
			Maze.main(null);
			Game.guest = false;
			Game.resetPositions();
		} else {
			/*If the level is not in range then tell the playe that he hasnt unlocked the
			level and display his highest level reached*/
			JOptionPane.showMessageDialog(
					null,
					"Your highest unlocked level is: "
							+ Player.getLevelAchieved(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * This method checks the choice made by the player in the comboBox and
	 * accordingly returns whether the player has unlocked the selected level or
	 * not.
	 * 
	 * @param comboBoxChoice
	 *            the selection made in the comboBox
	 * @return true if the player has already unlocked the level, false
	 *         otherwise.
	 */
	public static boolean checkRange(String comboBoxChoice) {
		boolean inRange = false;
		int levelChosen = Integer.parseInt(comboBoxChoice);
		int maxLevel = Player.getLevelAchieved();
		if (maxLevel >= levelChosen) {
			inRange = true;
		}
		return inRange;
	}

}