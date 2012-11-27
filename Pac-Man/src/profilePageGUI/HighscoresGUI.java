package profilePageGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import playerManipulation.Player;

public class HighscoresGUI extends ProfilePage {
	private static JPanel personalHighscoresGUI;
	static Player currentPlayer;
	static String securityQuestionDisplayed;
	static JLabel comboBoxSelection, changeUsername, passwordLabel,
			reenterPasswordLabel;
	static JButton logout, backToProfile;
	static JTextField newUsername;
	public static JComboBox comboBox;
	static String[] comboBoxSelections = { "", "Personal Highscores",
			"Overall Highscores" };
	static JPanel highscorePanels;


	// TODO remove
	/**
	 * @wbp.parser.entryPoint
	 */

	public static JPanel displayHighscores() {
		// Create displayHighscores Panel
		personalHighscoresGUI = new JPanel();
		personalHighscoresGUI.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\highscores.gif");
		personalHighscoresGUI.setLayout(null);
		JLabel highscoresBG = new JLabel();
		highscoresBG.setBounds(155, 11, 200, 31);
		highscoresBG.setIcon(background);
		personalHighscoresGUI.add(highscoresBG);

		// dropdown list
		comboBoxSelection = new JLabel(
				"Please choose which highscores to view: ");
		comboBoxSelection.setBounds(129, 53, 238, 14);
		comboBoxSelection.setForeground(Color.WHITE);
		personalHighscoresGUI.add(comboBoxSelection);
		comboBox = new JComboBox(comboBoxSelections);
		comboBox.setBounds(129, 75, 121, 20);
		personalHighscoresGUI.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String comboBoxChoice = (String) cb.getSelectedItem();
				implementSelection(comboBoxChoice);
			}
		});
		// back button
		backToProfile = new JButton("Back");
		backToProfile.setBounds(260, 74, 84, 23);
		personalHighscoresGUI.add(backToProfile);
		backToProfile.setActionCommand("Back");
		backToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "profilePage");
			}
		});

		return personalHighscoresGUI;

	}

	public static void implementSelection(String comboBoxChoice) {
		if (comboBoxChoice.equals("Personal Highscores")) {
			pagePanels.add(PersonalHighscores.personalHighscores(),
					"personalHS");
			pages.show(pagePanels, "personalHS");


		} else if (comboBoxChoice.equals("Overall Highscores")) {

		}

	}

}
