package profilePageGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import playerManipulation.Player;
import frontendDatabase.StatisticsFrontend;
/**
 * The class deals with the entire graphical user interface related to highscores.
 * Both personal and global highscores are displayed in this class. 
 *
 */
public class HighscoresGUI extends ProfilePage {
	static JPanel personalHighscoresGUI;
	static int[] personalHighScores;
	static ArrayList<String> globalHighScores;
	static JLabel phs1, phs2, phs3, phs4, phs5, phs6, phs7, phs8, phs9, phs10;
	static JLabel ghs1, ghs2, ghs3, ghs4, ghs5, ghs6, ghs7, ghs8, ghs9, ghs10;
	static JLabel personalHSBackground, globalHSBackground, line;
	static JButton highscoresToProfile;

	/**
	 * This method creates a JPanel for the page where a player gets to view
	 * his/her personal highscores as well as the lobal highscores.
	 * 
	 * @return the JPanel for the highscores page
	 */
	public static JPanel displayHighscores() {
		// Create profilepage Panel
		personalHighscoresGUI = new JPanel();
		personalHighscoresGUI.setBackground(Color.BLACK);
		personalHighscoresGUI.setLayout(null);
		// Heading
		File path = new File("").getAbsoluteFile();
		ImageIcon highscoresImage = new ImageIcon(path
				+ "\\resources\\globalHS.gif");// TODO make a method to
		globalHSBackground = new JLabel();
		globalHSBackground.setLocation(250, 11);
		globalHSBackground.setSize(230, 25);
		globalHSBackground.setIcon(highscoresImage);
		personalHighscoresGUI.add(globalHSBackground);
		// line
		ImageIcon lineImage = new ImageIcon(path + "\\resources\\line.jpg");// TODO
																			// make
																			// a
																			// method
																			// to
		line = new JLabel();
		line.setLocation(250, 50);
		line.setSize(10, 490);
		line.setIcon(lineImage);
		personalHighscoresGUI.add(line);
		// personal Highscores subheading
		ImageIcon subHeading = new ImageIcon(path
				+ "\\resources\\personalHS.gif");
		personalHSBackground = new JLabel();
		personalHSBackground.setBounds(10, 11, 230, 25);
		personalHSBackground.setIcon(subHeading);
		personalHighscoresGUI.add(personalHSBackground);
		// display personal high scores
		personalHighScores = Player.getHighScores();

		phs1 = new JLabel(Integer.toString(personalHighScores[0]));
		phs1.setHorizontalAlignment(SwingConstants.CENTER);
		phs1.setBounds(20, 47, 65, 14);
		phs2 = new JLabel(Integer.toString(personalHighScores[1]));
		phs2.setHorizontalAlignment(SwingConstants.CENTER);
		phs2.setBounds(20, 104, 65, 14);
		phs3 = new JLabel(Integer.toString(personalHighScores[2]));
		phs3.setHorizontalAlignment(SwingConstants.CENTER);
		phs3.setBounds(20, 161, 65, 14);
		phs4 = new JLabel(Integer.toString(personalHighScores[3]));
		phs4.setHorizontalAlignment(SwingConstants.CENTER);
		phs4.setBounds(20, 218, 65, 14);
		phs5 = new JLabel(Integer.toString(personalHighScores[4]));
		phs5.setHorizontalAlignment(SwingConstants.CENTER);
		phs5.setBounds(20, 275, 65, 14);
		phs6 = new JLabel(Integer.toString(personalHighScores[5]));
		phs6.setHorizontalAlignment(SwingConstants.CENTER);
		phs6.setBounds(20, 332, 65, 14);
		phs7 = new JLabel(Integer.toString(personalHighScores[6]));
		phs7.setHorizontalAlignment(SwingConstants.CENTER);
		phs7.setBounds(20, 389, 65, 14);
		phs8 = new JLabel(Integer.toString(personalHighScores[7]));
		phs8.setHorizontalAlignment(SwingConstants.CENTER);
		phs8.setBounds(20, 446, 65, 14);
		phs9 = new JLabel(Integer.toString(personalHighScores[8]));
		phs9.setHorizontalAlignment(SwingConstants.CENTER);
		phs9.setBounds(20, 503, 65, 14);
		phs10 = new JLabel(Integer.toString(personalHighScores[9]));
		phs10.setHorizontalAlignment(SwingConstants.CENTER);
		phs10.setBounds(20, 560, 65, 14);
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

		globalHighScores = StatisticsFrontend.getGlobalHighScores();
		// global highscores
		ghs1 = new JLabel(globalHighScores.get(0));
		ghs1.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs1.setBounds(304, 43, 176, 14);
		ghs2 = new JLabel(globalHighScores.get(1));
		ghs2.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs2.setBounds(304, 100, 176, 14);
		ghs3 = new JLabel(globalHighScores.get(2));
		ghs3.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs3.setBounds(304, 157, 176, 14);
		ghs4 = new JLabel(globalHighScores.get(3));
		ghs4.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs4.setBounds(304, 214, 176, 14);
		ghs5 = new JLabel(globalHighScores.get(4));
		ghs5.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs5.setBounds(304, 271, 176, 14);
		ghs6 = new JLabel(globalHighScores.get(5));
		ghs6.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs6.setBounds(304, 328, 176, 14);
		ghs7 = new JLabel(globalHighScores.get(6));
		ghs7.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs7.setBounds(304, 385, 176, 14);
		ghs8 = new JLabel(globalHighScores.get(7));
		ghs8.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs8.setBounds(304, 442, 176, 14);
		ghs9 = new JLabel(globalHighScores.get(8));
		ghs9.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs9.setBounds(304, 499, 176, 14);
		ghs10 = new JLabel(globalHighScores.get(9));
		ghs10.setHorizontalAlignment(SwingConstants.RIGHT);
		ghs10.setBounds(304, 556, 176, 14);
		// set highscores to white
		ghs1.setForeground(Color.WHITE);
		ghs2.setForeground(Color.WHITE);
		ghs3.setForeground(Color.WHITE);
		ghs4.setForeground(Color.WHITE);
		ghs5.setForeground(Color.WHITE);
		ghs6.setForeground(Color.WHITE);
		ghs7.setForeground(Color.WHITE);
		ghs8.setForeground(Color.WHITE);
		ghs9.setForeground(Color.WHITE);
		ghs10.setForeground(Color.WHITE);
		// add them to page
		personalHighscoresGUI.add(ghs1);
		personalHighscoresGUI.add(ghs2);
		personalHighscoresGUI.add(ghs3);
		personalHighscoresGUI.add(ghs4);
		personalHighscoresGUI.add(ghs5);
		personalHighscoresGUI.add(ghs6);
		personalHighscoresGUI.add(ghs7);
		personalHighscoresGUI.add(ghs8);
		personalHighscoresGUI.add(ghs9);
		personalHighscoresGUI.add(ghs10);

		// back button
		highscoresToProfile = new JButton("Back");
		highscoresToProfile.setLocation(207, 551);
		highscoresToProfile.setSize(95, 32);
		personalHighscoresGUI.add(highscoresToProfile);
		highscoresToProfile.setActionCommand("Back");
		highscoresToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "profilePage");
			}
		});

		return personalHighscoresGUI;
	}
}
