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
/**
 * This class deals with the major aspects of gameplay
 */
public class Game extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	static Pacman pacman = new Pacman();
	Map map = new Map();
	static Inky inky = new Inky(12, 14);
	static Blinky blinky = new Blinky(14, 11);
	static Pinky pinky = new Pinky(15, 14);
	static Clyde clyde = new Clyde(13, 14);
	static int pixel = 18;

	static boolean inGame = true;
	public static boolean guest;
	static String username = Player.getUsername();
	boolean tunnel = false;

	static boolean goLeft = true;
	static boolean goRight = false;
	static boolean goUp = false;
	static boolean goDown = false;
	static boolean stop = false;

	static boolean storedLeft = true;
	static boolean storedRight = false;
	static boolean storedUp = false;
	static boolean storedDown = false;
	static boolean fruitEaten = false;

	boolean pause;
	static boolean moved;
	long time;
	static boolean markedTime = true;
	static int currentLevel;
	private static int collided = 0;
	static double speed = 1;
	static Timer timer;

	public Game() {
		timer = new Timer(150, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		inGame = true;
		pacman.livesLeft = 3;

	}

	/**
	 * sets the ghost characteristics specific for the level
	 */
	public void actionPerformed(ActionEvent e) {
		revalidate();
		repaint();
		if(currentLevel == 1) {
			Ghost.ghostModes(Ghost.level1Timing);
		}
		else if(currentLevel > 1 && currentLevel < 5) {
			Ghost.ghostModes(Ghost.level2To4Timing);
		}
		else if(currentLevel > 4) {
			Ghost.ghostModes(Ghost.level5PlusTiming);
		}
	}

	/**
	 * Paints in most of the graphics, take @param g, an object of Graphics Paints the appropriate
	 * direction-facing images for Pac-Man Also, paints the apt images for ghosts and frightened
	 * ghosts Paints in colours and fonts of the level graphic Finally, it also paints the fruits
	 * and consumption of collectables when required
	 */
	public void paint(Graphics g) {
		super.paint(g);
		map.addMap(g);
		//draw the pacman image based on its facing-direction
		if(goRight) {
			g.drawImage(pacman.image1, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		}
		else if(goLeft) {
			g.drawImage(pacman.image2, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		}
		else if(goUp) {
			g.drawImage(pacman.image3, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		}
		else if(goDown) {
			g.drawImage(pacman.image4, (int) pacman.getX() * pixel, (int) pacman.getY() * pixel, null);
		}

		//draws the ghosts image
		g.drawImage(inky.inkyImg, (int) inky.getX() * pixel, (int) inky.getY() * pixel, null);
		g.drawImage(pinky.pinkyImg, (int) pinky.getX() * pixel, (int) pinky.getY() * pixel, null);
		g.drawImage(blinky.blinkyImg, (int) blinky.getX() * pixel, (int) blinky.getY() * pixel, null);
		g.drawImage(clyde.clydeImg, (int) clyde.getX() * pixel, (int) clyde.getY() * pixel, null);
	
		//adds the extra features on the gameplay like score, leves left, level.
		map.addExtras(pacman, g);

		if(inGame) {
			if(Score.dotsLeft == 0) {
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
				g.setColor(Color.green);
				g.drawString("Level Completed", 60, 260);

				if(markedTime) {
					time = System.currentTimeMillis();
					markedTime = false;
				}

				else if(System.currentTimeMillis() > (time + 5000) && System.currentTimeMillis() < (time + 5500)) {
					int levelScore = Score.getScore();
					Map.copyBoard();
					Score.setLevelScore(levelScore);
					setCurrentLevel(getCurrentLevel() + 1);
					Map.addMap(g);
					markedTime = true;
					inGame = true;
				}
			}
			//continuous movement of pacman
			else if(UserControls.checkMoveForStored(pacman, map.board, tunnel)) {
				setCurrentToStored();
				pacman.move(tunnel, speed);
			}
			else if(UserControls.checkMove(pacman, map.board, tunnel)) {
				pacman.move(tunnel, speed);
			}

			if(map.board[(int) pacman.getX()][(int) pacman.getY()] == 2) {
				if(Settings.isSoundOn()) {
					Audio.SoundPlayer("eatdot.wav");
				}
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;

			}
			
			//start frightened mode when energizer eaten
			if(map.board[(int) pacman.getX()][(int) pacman.getY()] == 3) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				Ghost.scatterWhilefrightened = Ghost.scatter;
				Ghost.chaseWhilefrightened = Ghost.chase;
				Ghost.scatter = false;
				Ghost.chase = false;
				Ghost.frightened = true;

				Ghost.frightenedTimeStart = System.currentTimeMillis();
				Ghost.turnDirection = true;
				if(Settings.isSoundOn()) {
					Audio.SoundPlayer("pacman_eatghost.wav");
				}
			}
			
			map.fruitVisibility(map.board);
			
			//eat fruit
			if(map.board[(int) pacman.getX()][(int) pacman.getY()] == 6) {
				map.board[(int) pacman.getX()][(int) pacman.getY()] = 0;
				fruitEaten = true;
				map.fruitVisible = false;

				if(Settings.isSoundOn()) {
					Audio.SoundPlayer("pacman_eatfruit.wav");
				}
			}
			
			//ghosts exit from their starting position
			if(!moved) {
				pinky.move(0, -3);
				inky.move(0, -3);
				clyde.move(0, -3);
				moved = true;
			}
			//ghosts movement
			else {
				inky.movePossible(pacman, map.board, g);
				pinky.movePossible(pacman, map.board, g);
				clyde.movePossible(pacman, map.board, g);
			}
			blinky.movePossible(pacman, map.board, g);

			
			//pauses session
			if(pause) {
				pauseSession(g);
			}
		}
		else {
			g.dispose();
		}
	}

	/**
	 * method that pauses game (used by p keypress)
	 */
	public void pauseSession(Graphics g) {
		try {
			timer.stop();
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
			g.setColor(Color.yellow);
			g.drawString("PAUSED", 200, 250);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			g.drawString("Press 'R' to Resume", 163, 286);

		}
		catch (Exception e) {
			System.out.println("There is a problem");
		}
	}

	/**
	 * method that resumes game (used by r keypress)
	 */
	public void resumeSession() {
		try {
			timer.start();
			pause = false;
		}
		catch (Exception e) {
			System.out.println("There is a problem");
		}
	}

	/**
	 * this method checks if pacman can move in the inputted direction, and then moves pacman
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch (key) {
		case KeyEvent.VK_LEFT:

			if(pacman.getX() == 0 && pacman.getY() == 14) {
				tunnel = true;
				goLeft = true;
				goRight = false;
				goUp = false;
				goDown = false;
				setStoredToCurrent();
			}

			else if(UserControls.moveLeft(pacman, map.board)) {
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
			if(pacman.getX() == 27 && pacman.getY() == 14) {
				tunnel = true;
				goLeft = false;
				goRight = true;
				goUp = false;
				goDown = false;
				setStoredToCurrent();
			}

			else if(UserControls.moveRight(pacman, map.board)) {
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
			if(UserControls.moveUp(pacman, map.board)) {
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
			if(UserControls.moveDown(pacman, map.board)) {
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

		case KeyEvent.VK_SPACE:
			pause = true;
			break;

		case KeyEvent.VK_P:
			pause = true;
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

	/**
	 * restarts the game based on loss of life
	 * 
	 * @param pacman
	 *            object of Pacman
	 * @param g
	 *            object of Graphics
	 */
	public static void restartGame(Pacman pacman, Graphics g) {
		resetPositions();
		pacman.livesLeft--;
		Game.inGame = true;
	}

	//starting positions
	public static void resetPositions() {
		pacman.setX(14);
		pacman.setY(23);
		blinky.setX(14);
		blinky.setY(11);
		inky.setX(12);
		inky.setY(14);
		pinky.setX(15);
		pinky.setY(14);
		clyde.setX(13);
		clyde.setY(14);
	}

	/**
	 * If no turn available, stores the turn's direction and runs in its next avalable incarnation
	 */
	public void setCurrentToStored() {
		goLeft = storedLeft;
		goRight = storedRight;
		goUp = storedUp;
		goDown = storedDown;

	}

	/**
	 * If no turn available, stores the turn's direction and runs in its next avalable incarnation
	 */
	public void setStoredToCurrent() {
		storedLeft = goLeft;
		storedRight = goRight;
		storedUp = goUp;
		storedDown = goDown;
	}

	/**
	 * Method to deal with the end of Game sets High Scores, shifts Player to profile page
	 */
	public static void endOfGame() {
		if(!guest) {
			StatisticsFrontend.setHighScores(username, Score.getScore());
			if(Player.getLevelAchieved() < Game.currentLevel) {
				PlayerFrontend.changeProfileDetails(username, "levelAchieved", Game.currentLevel);
			}
			Player currentPlayer = new Player(username);
		}
		Maze.setMazeVisiblity(false);
		ProfilePage.setMasterPageVisiblity(true);
	}

	/**
	 * @returns the current Level
	 */
	public static int getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * sets the currentLevel
	 */
	public static void setCurrentLevel(int currentLevel) {
		Game.currentLevel = currentLevel;
	}

	/**
	 * @returns an integer representing collided
	 */
	public static int getCollided() {
		return collided;
	}

	/**
	 * Sets the collided integer
	 * 
	 * @param collided
	 */
	public static void setCollided(int collided) {
		Game.collided = collided;
	}

	/**
	 * This method sets the speeds for pacman and the ghosts in the frightened state
	 */
	public static void setFrightenedSpeeds() {
		Game.speed = 1;
		Ghost.ghostSpeed = 0.5;
	}

	/**
	 * This method sets the speeds for pacman and the ghosts in normal state
	 */
	public static void setNormalSpeeds() {
		Game.speed = 1;
		Ghost.ghostSpeed = 0.95;
	}
}