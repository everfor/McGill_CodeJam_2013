package interfaceFramework;

import javax.swing.JFrame;

public class Maze extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Maze();
	}

	/**
	 * Create the frame.
	 */
	public Maze() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(656, 518);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Pac-Man");
		add(new Map());
	}

}
