package interfaceFramework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements ActionListener {
	Pacman pacman = new Pacman();
	Map map = new Map();

	static int pixel = 18;

	boolean tunnel = false;
	//begins pacman in stationary position
	static boolean goLeft = false;
	static boolean goRight = false;
	static boolean goUp = false;
	static boolean goDown = false;
	static boolean stop = false;

	// initialize pacman and ghosts

//	Ghost ghost = new Ghost(7, 5);
//	Ghost ghost2 = new Ghost(30, 8);
//	Ghost ghost3 = new Ghost(4, 20);
//	Ghost ghost4 = new Ghost(32, 22);
	Timer timer;

	public Game() {
		timer = new Timer(25, this);
		timer.start();
		
		addKeyListener(new myActionListener());
		setFocusable(true);
	
	}

	public void actionPerformed(ActionEvent e) {
		revalidate();
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		map.addMap(g);
		
		// color pacman and ghosts
//		fill(Color.yellow, g, pacman.getX(), pacman.getY());
//		fill(Color.red, g, ghost.getX(), ghost.getY());
//		fill(Color.white, g, ghost2.getX(), ghost2.getY());
//		fill(Color.darkGray, g, ghost3.getX(), ghost3.getY());
//		fill(Color.cyan, g, ghost4.getX(), ghost4.getY());		

		// check if pacman can move in a particular direction and then move
//		if (UserControls.checkMove(pacman, map.board, tunnel)) {
//			pacman.move(tunnel);
//
//		}
		g.drawImage(pacman.image2, pacman.getX()*pixel, pacman.getY()*pixel, null);
		
//		revalidate();
//		repaint();
		// move ghosts
//		ghost.move(ghost, map);
//		ghost2.move(ghost2, map);
//		ghost3.move(ghost3, map);
//		ghost4.move(ghost4, map);
		
		// checks if pacman has collided with a ghost
//		checkCollision();
	}
	

	// pauses game if Pacman and a ghost have collided
//	public void checkCollision() {
//		if ((pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY())
//				|| (pacman.getX() == ghost2.getX() && pacman.getY() == ghost2
//						.getY())
//				|| (pacman.getX() == ghost3.getX() && pacman.getY() == ghost3
//						.getY())
//				|| (pacman.getX() == ghost4.getX() && pacman.getY() == ghost4
//						.getY())) {
//			pauseSession();
//		}
//	}

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

//	public class myActionListener extends KeyAdapter {
//
//		/**
//		 * This method checks keypressed events and acts accordingly depending
//		 * on the particular key. This includes pacman's movements, pause and resume
//		 */
//		public void keyPressed(KeyEvent e) {
//			int key = e.getKeyCode();
//
//			switch (key) {
//			case KeyEvent.VK_LEFT:
//				// if need to go left, check if you can go left first
//
//				if (pacman.getX() == 0 && pacman.getY() == 14) {
//					pacman.moveTo(27, 14);
//				}
//
//				if (pacman.getX() == 0 && pacman.getY() == 14) {
//					tunnel = true;
//					goLeft = true;
//					goRight = false;
//					goUp = false;
//					goDown = false;
//				}
//
//				else if (UserControls.moveLeft(pacman, map.board)) {
//					tunnel = false;
//					goLeft = true;
//					goRight = false;
//					goUp = false;
//					goDown = false;
//				}
//
//				break;
//
//			case KeyEvent.VK_RIGHT:
//				// if need to go right, check if you can go right
//				if (pacman.getX() == 27 && pacman.getY() == 14) {
//					tunnel = true;
//					goLeft = false;
//					goRight = true;
//					goUp = false;
//					goDown = false;
//				}
//
//				else if (UserControls.moveRight(pacman, map.board)) {
//					tunnel = false;
//					goLeft = false;
//					goRight = true;
//					goUp = false;
//					goDown = false;
//				}
//
//				break;
//
//			case KeyEvent.VK_UP:
//				// if need to go up, check if you can go up
//				if (UserControls.moveUp(pacman, map.board)) {
//					tunnel = false;
//					goLeft = false;
//					goRight = false;
//					goUp = true;
//					goDown = false;
//
//				}
//
//				break;
//
//			case KeyEvent.VK_DOWN:
//				// if need to go down, check if you can go down
//				if (UserControls.moveDown(pacman, map.board)) {
//					tunnel = false;
//					goLeft = false;
//					goRight = false;
//					goUp = false;
//					goDown = true;
//				}
//
//				break;
//
//			case KeyEvent.VK_P:
//				pauseSession();
//				break;
//
//			case KeyEvent.VK_R:
//				resumeSession();
//				break;
//			}
//			repaint();
//		}
//	}

	
	public class myActionListener extends KeyAdapter {

		// this method checks if pacman can move in the inputted direction, and
		// then moves pacman
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				// if need to go left, check if you can go left first
				if(pacman.getX() == 0 && pacman.getY() == 14){
					pacman.move(27, 0);
				}
				else if(interfaceFramework.UserControls.moveLeft(pacman, map.board)) {
					pacman.move(-1, 0);
				}
				else if(!interfaceFramework.UserControls.moveLeft(pacman, map.board)){
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_RIGHT:
				// if need to go right, check if you can go right
				
				if(pacman.getX() == 27 && pacman.getY() == 14){
					pacman.move(-27, 0);
				}
				else if(interfaceFramework.UserControls.moveRight(pacman, map.board)){

					pacman.move(1, 0);					
				}
				else if(!interfaceFramework.UserControls.moveRight(pacman, map.board)){
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_UP:
				// if need to go up, check if you can go up
				if (interfaceFramework.UserControls.moveUp(pacman, map.board)) {
					pacman.move(0, -1);
				}
				else{
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_DOWN:
				// if need to go down, check if you can go down
				if (interfaceFramework.UserControls.moveDown(pacman, map.board)) {
					pacman.move(0, 1);
				}
				else{
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_P:
				pauseSession();
				break;

			case KeyEvent.VK_R:
				resumeSession();
				break;
			}
			repaint();
		}
	}
	
}