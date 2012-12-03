package interfaceFramework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import playerManipulation.Player;
import profilePageGUI.ProfilePage;
import profilePageGUI.Settings;
import frontendDatabase.PlayerFrontend;
import frontendDatabase.StatisticsFrontend;

public class Game extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Pacman pacman = new Pacman();
	Map map = new Map();
	static Inky inky = new Inky(12, 14);
	static Blinky blinky = new Blinky(15, 14);
	static Pinky pinky = new Pinky(14, 14);
	static Clyde clyde = new Clyde(13, 14);
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

	long time;
	static boolean markedTime = true;
	static int currentLevel=1;//TODO remove 1
	private static int collided = 0;
	double speed = 1.0;
	static Timer timer;

	public Game() {
		timer = new Timer(150, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		inGame = true;
		pacman.livesLeft = 3;

	}

	public void actionPerformed(ActionEvent e) {
		revalidate();
		repaint();
		if (currentLevel == 1) {
			Ghost.ghostModes(Ghost.level1Timing);
		} else if (currentLevel > 1 && currentLevel < 5) {
			Ghost.ghostModes(Ghost.level2To4Timing);
		} else if (currentLevel > 4) {
			Ghost.ghostModes(Ghost.level5PlusTiming);
		}
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

		g.drawImage(inky.inkyImg, inky.getX() * pixel, inky.getY() * pixel,
				null);
		g.drawImage(pinky.pinkyImg, pinky.getX() * pixel, pinky.getY() * pixel,
				null);
		g.drawImage(blinky.blinkyImg, blinky.getX() * pixel, blinky.getY()
				* pixel, null);
		g.drawImage(clyde.clydeImg, clyde.getX() * pixel, clyde.getY() * pixel,
				null);

		// TODO remove for debugging
		// g.drawImage(clyde.temp, (int)Ghost.debugX * pixel,(int)Ghost.debugY *
		// pixel, null);
		map.addExtras(pacman, g);

		if (inGame) {
			if (Score.dotsLeft == 0) {
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
				g.setColor(Color.green);
				g.drawString("Level Completed", 60, 260);

				if (markedTime) {
					time = System.currentTimeMillis();
					markedTime = false;
				}

				else if (System.currentTimeMillis() > (time + 5000)
						&& System.currentTimeMillis() < (time + 5500)) {
					int levelScore = Score.getScore();
				
					Map.copyBoard();
					Score.setLevelScore(levelScore);
					setCurrentLevel(getCurrentLevel() + 1);
					Map.addMap(g);
					resetPositions();
					markedTime = true;
					inGame = true;
				}

			} else if (UserControls.checkMoveForStored(pacman, map.board,
					tunnel)) {
				setCurrentToStored();
				pacman.move(tunnel, speed);
			} else if (UserControls.checkMove(pacman, map.board, tunnel)) {
				pacman.move(tunnel, speed);
			}

			if (map.board[(int) pacman.getX()][(int) pacman.getY()] == 2) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				if (Settings.isSoundOn()) {
					Audio.SoundPlayer("eatdot.wav");
				}
			}

			if (map.board[(int) pacman.getX()][(int) pacman.getY()] == 3) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				Ghost.scatterWhilefrightened = Ghost.scatter;
				Ghost.chaseWhilefrightened = Ghost.chase;
				Ghost.scatter = false;
				Ghost.chase = false;
				Ghost.frightened = true;
				Ghost.frightenedTimeStart = System.currentTimeMillis();
				Ghost.turnDirection = true;
				if (Settings.isSoundOn()) {
					Audio.SoundPlayer("pacman_eatghost.wav");
				}
			}

			map.fruitVisibility(map.board);
			if (map.board[(int) pacman.getX()][(int) pacman.getY()] == 6) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				fruitEaten = true;
				map.fruitVisible = false;

				if (Settings.isSoundOn()) {
					Audio.SoundPlayer("pacman_eatfruit.wav");
				}
			}

			inky.movePossible(pacman, map.board, g);
			pinky.movePossible(pacman, map.board, g);
			blinky.movePossible(pacman, map.board, g);
			clyde.movePossible(pacman, map.board, g);

		} else {
			g.dispose();
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
			} else {
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
			} else {
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
			} else {
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
			} else {
				storedLeft = false;
				storedRight = false;
				storedUp = false;
				storedDown = true;
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

	public static void restartGame(Pacman pacman, Graphics g) {
		resetPositions();

		pacman.livesLeft--;

		Game.inGame = true;
	}
	public static void resetPositions(){
		pacman.setX(14);
		pacman.setY(23);
		blinky.setX(15);
		blinky.setY(14);
		inky.setX(12);
		inky.setY(14);
		pinky.setX(14);
		pinky.setY(14);
		clyde.setX(13);
		clyde.setY(14);
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
		if(Player.getLevelAchieved()<Game.currentLevel){
			PlayerFrontend.changeProfileDetails(username, "levelAchieved", Game.currentLevel);
		}
		Player currentPlayer = new Player(username);
		Maze.setMazeVisiblity(false);
		ProfilePage.setMasterPageVisiblity(true);
		
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}

	public static void setCurrentLevel(int currentLevel) {
		Game.currentLevel = currentLevel;
	}

	public static int getCollided() {
		return collided;
	}

	public static void setCollided(int collided) {
		Game.collided = collided;
	}
}