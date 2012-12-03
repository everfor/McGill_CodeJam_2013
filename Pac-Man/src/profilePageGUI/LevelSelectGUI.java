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

public class LevelSelectGUI extends ProfilePage {
	private static JPanel levelSelected;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel comboBoxSelection, changeUsername, passwordLabel, reenterPasswordLabel;
	static JButton logout, backToProfile;
	public static JComboBox comboBoxLevels;
	static String[] comboBoxSelectionsLevels = new String[101];

	/**
	 * This method creates a JPanel for the Change Profile Details page. Here a
	 * player gets the choice to move to the designated page to change his/her
	 * selected information.
	 * 
	 * @return the JPanel for the change username page
	 * 
	 */
	public static JPanel levelSelectPage() {
		// Create changeProfileDetailsPage Panel
		for (int i = 0; i < comboBoxSelectionsLevels.length; i++) {
			comboBoxSelectionsLevels[i] = Integer.toString(i+1);
		}

		levelSelected = new JPanel();
		levelSelected.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path + "\\res\\image\\playgamebutton.gif");
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
	 * This method handles the selection of the comboBox, depending pn the
	 * choice made the player will be moved to the appropriate page
	 * 
	 * @param comboBoxChoice
	 *            a string representing the player's choice
	 */
	public static void implementSelection(String comboBoxChoice) {
		boolean inRange = checkRange(comboBoxChoice);
		if (inRange) {
			setMasterPageVisiblity(false);
			Score.setScore(0);
			Score.setLevelScore(0);
			Game.setCollided(0);
			Game.setCurrentLevel(Integer.parseInt(comboBoxChoice));
			Maze.main(null);
			Game.guest=false;
			Game.resetPositions();
		} else {
			JOptionPane.showMessageDialog(null,
					"Your highest unlocked level is: " + Player.getLevelAchieved(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

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