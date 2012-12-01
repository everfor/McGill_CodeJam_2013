package interfaceFramework;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import playerManipulation.Player;
import profilePageGUI.ProfilePage;
import frontendDatabase.StatisticsFrontend;

public class Game extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Pacman pacman = new Pacman();
	Map map = new Map();
	static Blinky blinky = new Blinky(20, 5);
	static int pixel = 18;
	static boolean inGame = true;
	static String username = Player.getUsername();
	boolean tunnel = false;
	static boolean goLeft = false;
	static boolean goRight = true;
	static boolean goUp = false;
	static boolean goDown = false;
	static boolean stop = false;

	double speed = 1.0;
	static Timer timer;

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

		if (goRight) {
			g.drawImage(pacman.image1, (int) pacman.getX() * pixel,
					(int) pacman.getY() * pixel, null);
		} else if (goLeft) {
			g.drawImage(pacman.image2, (int) pacman.getX() * pixel,
					(int) pacman.getY() * pixel, null);
		} else if (goUp) {
			g.drawImage(pacman.image3, (int) pacman.getX() * pixel,
					(int) pacman.getY() * pixel, null);
		} else if (goDown) {
			g.drawImage(pacman.image4, (int) pacman.getX() * pixel,
					(int) pacman.getY() * pixel, null);
		}

		g.drawImage(blinky.blinky, blinky.getX() * pixel,
				blinky.getY() * pixel, null);
		map.addExtras(pacman, g);

		if (inGame) {
			if (UserControls.checkMove(pacman, map.board, tunnel)) {
				pacman.move(tunnel, speed);
			}

			if (map.board[(int) pacman.getX()][(int) pacman.getY()] == 2) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				Audio.SoundPlayer("eatdot.wav");
			}

			if (map.board[(int) pacman.getX()][(int) pacman.getY()] == 3) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				Audio.SoundPlayer("eatdot.wav");
			}

			// ghost1.move(map.board, ghost1.moveLeft());
			blinky.movePossible(pacman, map.board);
			checkCollision();
		}

		else {
			g.dispose();
		}
	}

	public void checkCollision() {

		if (!blinky.goDownGhost
				&& !blinky.goUpGhost
				&& !blinky.goRightGhost
				&& !blinky.goLeftGhost
				&& !stop
				&& ((int) pacman.getX() == blinky.getX() && (int) pacman.getY() == blinky
						.getY())) {
			inGame = false;
		}

		else if (((!blinky.goDownGhost && !blinky.goUpGhost
				&& !blinky.goRightGhost && !blinky.goLeftGhost) || (!stop))
				&& ((int) pacman.getX() == blinky.getX() && (int) pacman.getY() == blinky
						.getY())) {
			inGame = false;
		}
		// else if(!stop && ((int) pacman.getX() == ghost1.getX() && (int)
		// pacman.getY() == ghost1.getY())) {
		// inGame = false;
		// }

		// else if(goRight && ghost1.goLeft && (ghost1.getX() - pacman.getX() ==
		// 1)){
		// System.out.println("Hi");
		// inGame = false;
		// }
		// System.out.println(pacman.getX() + " " + ghost1.getX());
		// System.out.println(goRight + " " + ghost1.goLeft);

		else if ((goDown && blinky.goUpGhost) || (goUp && blinky.goDownGhost)
				|| (goRight && blinky.goLeftGhost)
				|| (goLeft && blinky.goRightGhost)) {
			if (Math.abs(pacman.getX() - Game.blinky.getX()) < 1.1
					&& Math.abs(pacman.getY() - Game.blinky.getY()) < 1.1) {
				if (goDown) {
					pacman.move(0, 1);
				} else if (goUp) {
					pacman.move(0, -1);
				} else if (goLeft) {
					pacman.move(-1, 0);
				} else if (goRight) {
					pacman.move(1, 0);
				}
				inGame = false;
			}

		}
		if (inGame == false) {
			Audio.SoundPlayer("die.wav");
			endOfGame();
		}

		// else if ((((int) pacman.getX() + 1 == ghost1.getX() && (int)
		// pacman.getY() == ghost1.getY()) && goLeft)
		// || (((int) pacman.getX() - 1 == ghost1.getX() && (int) pacman.getY()
		// == ghost1.getY()) && goRight)
		// || (((int) pacman.getX() == ghost1.getX() && (int) pacman.getY() - 1
		// == ghost1.getY()) && goDown)
		// || (((int) pacman.getX() == ghost1.getX() && (int) pacman.getY() + 1
		// == ghost1.getY()) && goUp)) {
		// inGame = false;
		// }

		// if((ghost1.goUp || ghost1.goDown) && (goLeft || goRight) ||
		// (ghost1.goLeft || ghost1.goRight) && (goUp || goDown) ){
		// if(Math.abs(pacman.getX()-Game.ghost1.getX()) < 0.75 &&
		// Math.abs(pacman.getY() - Game.ghost1.getY()) < 0.75){
		// inGame = false;
		// }
		// }
		// else if(Math.abs(pacman.getX()-Game.ghost1.getX()) < 1.75 &&
		// Math.abs(pacman.getY() - Game.ghost1.getY()) < 1.75){
		// inGame = false;
		// }
	}

	// method that pauses game (used by p keypress)
	public void pauseSession() {
		try {
			timer.stop();
		} catch (Exception e) {
			System.out.println("There is a problem");
		}
	}

	// method that resumes game (used by r keypress)
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

		case KeyEvent.VK_N:
			inGame = false;
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

	public static void endOfGame() {
		StatisticsFrontend.setHighScores(username, Score.getScore());
		Player currentPlayer = new Player(username);
		Maze.setMazeVisiblity(false);
		ProfilePage.setMasterPageVisiblity(true);

	}
}