package interfaceFramework;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pacman pacman = new Pacman();
	Map map = new Map();

	static int pixel = 18;

	boolean tunnel = false;
	static boolean goLeft = false;
	static boolean goRight = true;
	static boolean goUp = false;
	static boolean goDown = false;
	static boolean stop = false;


	Timer timer;

	public Game() {
		timer = new Timer(200, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	
	}

	public void actionPerformed(ActionEvent e) {
		revalidate();
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		map.addMap(g);
		
		if(goRight){
			g.drawImage(pacman.image1, pacman.getX()*pixel, pacman.getY()*pixel, null);
		}
		else if(goLeft){
			g.drawImage(pacman.image2, pacman.getX()*pixel, pacman.getY()*pixel, null);
		}
		else if(goUp){
			g.drawImage(pacman.image3, pacman.getX()*pixel, pacman.getY()*pixel, null);
		}
		else if(goDown){
			g.drawImage(pacman.image4, pacman.getX()*pixel, pacman.getY()*pixel, null);
		}

		if (UserControls.checkMove(pacman, map.board, tunnel)) {
			pacman.move(tunnel);
		}
	}
	
	//method that pauses game (used by p keypress)
	public void pauseSession() {
		try {
			timer.stop();
		} catch (Exception e) {
			System.out.println("There is a problem");
		}
	}
	//method that resumes game (used by r keypress)
	public void resumeSession() {
		try {
			timer.start();
		} catch (Exception e) {
			System.out.println("There is a problem");
		}
	}

	// this method checks if pacman can move in the inputted direction, and
	// then moves pacman
	@Override	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:
			
			if (pacman.getX() == 0 && pacman.getY() == 14) {
				tunnel = true;
				goLeft = true;
				goRight = false;
				goUp = false;
				goDown = false;
			}

			else if (UserControls.moveLeft(pacman, map.board)) {
				tunnel = false;
				goLeft = true;
				goRight = false;
				goUp = false;
				goDown = false;
			}

			break;

		case KeyEvent.VK_RIGHT:
			// if need to go right, check if you can go right
			if (pacman.getX() == 27 && pacman.getY() == 14) {
				tunnel = true;
				goLeft = false;
				goRight = true;
				goUp = false;
				goDown = false;
			}

			else if (UserControls.moveRight(pacman, map.board)) {
				tunnel = false;
				goLeft = false;
				goRight = true;
				goUp = false;
				goDown = false;
			}

			break;

		case KeyEvent.VK_UP:
			// if need to go up, check if you can go up
			if (UserControls.moveUp(pacman, map.board)) {
				tunnel = false;
				goLeft = false;
				goRight = false;
				goUp = true;
				goDown = false;
			}

			break;

		case KeyEvent.VK_DOWN:
			// if need to go down, check if you can go down
			if (UserControls.moveDown(pacman, map.board)) {
				tunnel = false;
				goLeft = false;
				goRight = false;
				goUp = false;
				goDown = true;
			}

			break;

		case KeyEvent.VK_P:
			pauseSession();
			break;

		case KeyEvent.VK_R:
			resumeSession();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {	
	}
	
}