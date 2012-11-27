package profilePageGUI;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import playerManipulation.Player;

public class PersonalHighscores extends HighscoresGUI {
	static JPanel personalHighscoresGUI;
	static int[] topHighScores;
	static JLabel phs1, phs2, phs3, phs4, phs5, phs6, phs7, phs8, phs9, phs10;

	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel personalHighscores() {
		// Create profilepage Panel
		personalHighscoresGUI = new JPanel();
		personalHighscoresGUI.setBackground(Color.BLACK);
		// background gif
		File path = new File("").getAbsoluteFile();
		ImageIcon background = new ImageIcon(path
				+ "\\resources\\highscores.gif");// TODO make a method to
		personalHighscoresGUI.setLayout(null);
		// do this
		JLabel personalHSBackground = new JLabel();
		personalHSBackground.setBounds(88, 0, 350, 38);
		personalHSBackground.setIcon(background);
		personalHighscoresGUI.add(personalHSBackground);
		// subheading
		ImageIcon subHeading = new ImageIcon(path
				+ "\\resources\\personalHS.gif");
		personalHSBackground.setIcon(subHeading);
		// dropdown list
		comboBoxSelection = new JLabel(
				"Please choose which highscores to view: ");
		comboBoxSelection.setForeground(Color.WHITE);
		personalHighscoresGUI.add(comboBoxSelection);
		personalHighscoresGUI.add(comboBox);
		personalHighscoresGUI.add(backToProfile);
		// display personal high scores
		topHighScores = Player.getHighScores();
		phs1 = new JLabel(Integer.toString(topHighScores[0]));
		phs1.setBounds(237, 201, 6, 14);
		phs2 = new JLabel(Integer.toString(topHighScores[1]));
		phs2.setBounds(237, 223, 6, 14);
		phs3 = new JLabel(Integer.toString(topHighScores[2]));
		phs3.setBounds(237, 240, 6, 14);
		phs4 = new JLabel(Integer.toString(topHighScores[3]));
		phs4.setBounds(237, 260, 6, 14);
		phs5 = new JLabel(Integer.toString(topHighScores[4]));
		phs5.setBounds(237, 275, 6, 14);
		phs6 = new JLabel(Integer.toString(topHighScores[5]));
		phs6.setBounds(237, 294, 6, 14);
		phs7 = new JLabel(Integer.toString(topHighScores[6]));
		phs7.setBounds(237, 310, 6, 14);
		phs8 = new JLabel(Integer.toString(topHighScores[7]));
		phs8.setBounds(237, 329, 6, 14);
		phs9 = new JLabel(Integer.toString(topHighScores[8]));
		phs9.setBounds(237, 347, 6, 14);
		phs10 = new JLabel(Integer.toString(topHighScores[9]));
		phs10.setBounds(237, 366, 6, 14);
		// set highscores to white
		phs1.setForeground(Color.WHITE);
		phs2.setForeground(Color.WHITE);
		phs3.setForeground(Color.WHITE);
		phs4.setForeground(Color.WHITE);
		phs5.setForeground(Color.WHITE);
		phs6.setForeground(Color.WHITE);
		phs7.setForeground(Color.WHITE);
		phs8.setForeground(Color.WHITE);
		phs9.setForeground(Color.WHITE);
		phs10.setForeground(Color.WHITE);
		// add them to page
		personalHighscoresGUI.add(phs1);
		personalHighscoresGUI.add(phs2);
		personalHighscoresGUI.add(phs3);
		personalHighscoresGUI.add(phs4);
		personalHighscoresGUI.add(phs5);
		personalHighscoresGUI.add(phs6);
		personalHighscoresGUI.add(phs7);
		personalHighscoresGUI.add(phs8);
		personalHighscoresGUI.add(phs9);
		personalHighscoresGUI.add(phs10);
		// 10th one has problem

		return personalHighscoresGUI;
	}

}
