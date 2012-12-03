package profilePageGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Settings extends ProfilePage {
	private static boolean soundOn = true;
	public static JPanel settingsGUI;
	public static JLabel settingHeader, sound;
	private static JRadioButton rdbtnOn, rdbtnOff;
	public static JButton settingsToProfile;

	public static boolean isSoundOn() {
		return soundOn;
	}

	public static void setSoundOn(boolean soundOn) {
		Settings.soundOn = soundOn;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static JPanel settingsPage() {
		// Create settingsPage Panel
		settingsGUI = new JPanel();
		settingsGUI.setBackground(Color.BLACK);
		settingsGUI.setLayout(null);
		// Heading
		File path = new File("").getAbsoluteFile();
		ImageIcon settingImage = new ImageIcon(path + "\\res\\image\\settings.gif");// TODO
																					// make
																					// a
																					// method
																					// to
		settingHeader = new JLabel();
		settingHeader.setLocation(140, 11);
		settingHeader.setSize(230, 25);
		settingHeader.setIcon(settingImage);
		settingsGUI.add(settingHeader);
		// sound on and off
		sound = new JLabel("Sound Options: ");
		sound.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sound.setLocation(127, 47);
		sound.setSize(256, 63);
		sound.setForeground(Color.WHITE);
		settingsGUI.add(sound);

		rdbtnOn = new JRadioButton("On");
		rdbtnOn.setForeground(Color.BLACK);
		rdbtnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSoundOn(true);
			}
		});
		rdbtnOn.setBounds(262, 70, 49, 23);
		settingsGUI.add(rdbtnOn);

		rdbtnOff = new JRadioButton("Off");
		rdbtnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setSoundOn(false);
			}
		});
		rdbtnOff.setBounds(310, 70, 49, 23);
		settingsGUI.add(rdbtnOff);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnOn);
		group.add(rdbtnOff);
		// Listener for radio buttons
		// back button
		settingsToProfile = new JButton("Back");
		settingsToProfile.setBounds(217, 369, 75, 23);
		settingsGUI.add(settingsToProfile);
		settingsToProfile.setActionCommand("Back");
		settingsToProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				pages.show(pagePanels, "profilePage");
			}
		});
		// show which radio button is active
		if (isSoundOn()) {
			rdbtnOn.setSelected(true);
		} else {
			rdbtnOff.setSelected(true);
		}
		return settingsGUI;
	}
}
