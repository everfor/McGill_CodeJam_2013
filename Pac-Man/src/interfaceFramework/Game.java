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
	static Pacman pacman = new Pacman();
	Map map = new Map();
	static Inky inky = new Inky(20, 5);
	static Blinky blinky = new Blinky(21, 5);
	static Pinky pinky = new Pinky(21, 12);
	static Clyde clyde = new Clyde(2, 1);
	static int pixel = 18;
	static boolean inGame = true;
	static String username = Player.getUsername();
	boolean tunnel = false;
	static boolean goLeft = false;
	static boolean goRight = true;
	static boolean goUp = false;
	static boolean goDown = false;
	static boolean stop = false;
	
	static boolean storedLeft = false;
	static boolean storedRight = true;
	static boolean storedUp = false;
	static boolean storedDown = false;
	
	static boolean fruitEaten = false; 
	double speed = 1.0;
	static Timer timer;

	public Game() {
		timer = new Timer(200, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		inGame = true;
	}

	public void actionPerformed(ActionEvent e) {
		revalidate();
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		map.addMap(g);

		if (goRight) {
			g.drawImage(pacman.image1, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		} else if (goLeft) {
			g.drawImage(pacman.image2, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		} else if (goUp) {
			g.drawImage(pacman.image3, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		} else if (goDown) {
			g.drawImage(pacman.image4, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		}

		g.drawImage(inky.inkyImg, inky.getX() * pixel, inky.getY() * pixel, null);
		g.drawImage(pinky.pinkyImg, pinky.getX() * pixel, pinky.getY() * pixel, null);
		g.drawImage(blinky.blinkyImg, blinky.getX() * pixel, blinky.getY() * pixel, null);
		g.drawImage(clyde.clydeImg, clyde.getX() * pixel, clyde.getY() * pixel, null);

		//TODO remove for debugging
//		g.drawImage(clyde.temp, (int)Ghost.debugX * pixel,(int)Ghost.debugY * pixel, null);
		map.addExtras(pacman, g);

		if (inGame) {
			if (UserControls.checkMoveForStored(pacman, map.board, tunnel)) {
				setCurrentToStored();
				pacman.move(tunnel, speed);
			}
			else if (UserControls.checkMove(pacman, map.board, tunnel)) {
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
			
			map.fruitVisibility(map.board);
			if (map.board[(int) pacman.getX()][(int) pacman.getY()] == 6) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				fruitEaten = true;
				map.fruitVisible  = false; 
				
				Audio.SoundPlayer("eatdot.wav");
			}
			
			inky.movePossible(pacman, map.board);
			checkCollision(inky);
			pinky.movePossible(pacman, map.board);
			checkCollision(pinky);			
			blinky.movePossible(pacman, map.board);
			checkCollision(blinky);			
			clyde.movePossible(pacman, map.board);
			checkCollision(clyde);
		}

		else {
			g.dispose();
		}
	}

	public void checkCollision(Ghost ghost) {

		if (!ghost.goDownGhost && !ghost.goUpGhost && !ghost.goRightGhost && !ghost.goLeftGhost
				&& !stop && ((int) pacman.getX() == ghost.getX() && (int) pacman.getY() == ghost.getY())) {
			inGame = false;
		}

		else if (((!ghost.goDownGhost && !ghost.goUpGhost && !ghost.goRightGhost && !ghost.goLeftGhost) || (!stop))
				&& ((int) pacman.getX() == ghost.getX() && (int) pacman.getY() == ghost.getY())) {
			inGame = false;
		}
		else if ((goDown && ghost.goUpGhost) || (goUp && ghost.goDownGhost) || (goRight && ghost.goLeftGhost)
				|| (goLeft && ghost.goRightGhost)) {
			if (Math.abs(pacman.getX() - ghost.getX()) < 1.1 && Math.abs(pacman.getY() - ghost.getY()) < 1.1) {
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
				setStoredToCurrent();
			}

			else if (UserControls.moveLeft(pacman, map.board)) {
				tunnel = false;
				goLeft = true;
				goRight = false;
				goUp = false;
				goDown = false;
				setStoredToCurrent();
			} 
			else {
				storedLeft = true;
				storedRight = false;
				storedUp = false;
				storedDown = false;
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
				setStoredToCurrent();
			}

			else if (UserControls.moveRight(pacman, map.board)) {
				tunnel = false;
				goLeft = false;
				goRight = true;
				goUp = false;
				goDown = false;
				setStoredToCurrent();
			} 
			else {
				storedLeft = false;
				storedRight = true;
				storedUp = false;
				storedDown = false;
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
				setStoredToCurrent();
			} 
			else {
				storedLeft = false;
				storedRight = false;
				storedUp = true;
				storedDown = false;
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
				setStoredToCurrent();
			} 
			else {
				storedLeft = false;
				storedRight = false;
				storedUp = false;
				storedDown = true;
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

	public static void restartGame(Pacman pacman, Graphics g) {
		pacman.setX(14);
		pacman.setY(23);
		pacman.livesLeft--;
		Audio.SoundPlayer("pacman_beginning.wav");
		Game.inGame = true;
	}

	public static void pauseTime(Graphics g) {

	}

	public void setCurrentToStored() {
		goLeft = storedLeft;
		goRight = storedRight;
		goUp = storedUp;
		goDown = storedDown;

	}

	public void setStoredToCurrent() {
		storedLeft = goLeft;
		storedRight = goRight;
		storedUp = goUp;
		storedDown = goDown;
	}

	public static void endOfGame() {
		StatisticsFrontend.setHighScores(username, Score.getScore());
		Player currentPlayer = new Player(username);
		Maze.setMazeVisiblity(false);
		ProfilePage.setMasterPageVisiblity(true);

	}
}