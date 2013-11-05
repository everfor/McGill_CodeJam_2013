package interfaceFramework;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * The class representing the ghost object and all the attributes a ghost should have. The class
 * takes care of the movement of the ghosts, and all the calculations the general ghost AI needs to
 * perform relating to the position, speed and other factors
 * 
 */
public class Ghost {
	// initializing all the necessary variables for this object
	double x, y;
	File path;
	Image inkyImg, pinkyImg, blinkyImg, clydeImg;
	static Image scared;
	double debugX, debugY;
	boolean exitLeft = false;
	boolean exitRight = false;
	boolean exitUp = false;
	boolean exitDown = false;

	double distanceUp = 9999;
	double distanceLeft = 9999;
	double distanceDown = 9999;
	double distanceRight = 9999;

	boolean goLeftGhost = true;
	boolean goRightGhost, goUpGhost, goDownGhost;
	boolean ghostDirection[] = new boolean[4];
	// different mode variables
	static boolean scatter = true;
	static boolean chase = false;
	static boolean infiniteChase;
	static boolean frightened = false;
	static boolean scatterWhilefrightened;
	static boolean chaseWhilefrightened;
	static boolean turnDirection = false;
	static int modeCounter = 0;
	// timing arrays for different levels
	static long[] level1Timing = { 7000, 20000, 7000, 20000, 5000, 20000, 5000 };
	static long[] level2To4Timing = { 7000, 20000, 7000, 20000, 5000, 1033000, 1 / 60 };
	static long[] level5PlusTiming = { 5000, 20000, 5000, 20000, 5000, 1037000, 1 / 60 };
	static long startTime = System.currentTimeMillis();
	public static long frightenedTimeStart;
	static double ghostSpeed = 1;

	//TODO REMOVE
	Image temp1, temp2, temp3, temp4;


	/**
	 * The constructs creates a ghost object in the given paramaters x and y
	 * 
	 * @param x
	 *            x coordinate of where the object should be placed on the board
	 * @param y
	 *            y coordinate of where the object should be placed on the board
	 */
	public Ghost(double x, double y) {

		this.x = x;
		this.y = y;

		path = new File("").getAbsoluteFile();
		inkyImg = new ImageIcon(path + "\\res\\image\\inky.gif").getImage();
		blinkyImg = new ImageIcon(path + "\\res\\image\\blinky.gif").getImage();
		pinkyImg = new ImageIcon(path + "\\res\\image\\pinky.gif").getImage();
		clydeImg = new ImageIcon(path + "\\res\\image\\clyde.gif").getImage();
		scared = new ImageIcon(path + "\\res\\image\\scared.gif").getImage();
		
		//TODO REMOVE
		temp1 = new ImageIcon(path + "\\res\\image\\targetc.jpg").getImage();
		temp2 = new ImageIcon(path + "\\res\\image\\targetp.jpg").getImage();
		temp3 = new ImageIcon(path + "\\res\\image\\targetb.jpg").getImage();
		temp4 = new ImageIcon(path + "\\res\\image\\targeti.jpg").getImage();

	}

	/**
	 * This is the method that controls the ghosts movement. It is set to random, however before a
	 * ghosts movements it checks if the upcoming coordinates are occupied with a wall.
	 * 
	 * @param ghost
	 *            used to find a particular ghost's coordinates
	 * @param board
	 *            used to check upcoming coordinates for walls
	 */
	public void move(double x, double y) {
		this.x += x;
		this.y += y;
	}

	/**
	 * The method takes care of the movement if the ghost based on its direction and relative to the
	 * board where all the objects exist
	 * 
	 * @param board
	 *            the board where the game is played by all the objects
	 * @param ghostDirection
	 *            the direction where the ghost is going towards, relative to the computer screen
	 */
	public void move(int board[][], boolean[] ghostDirection) {
		goLeftGhost = ghostDirection[0];
		goRightGhost = ghostDirection[1];
		goUpGhost = ghostDirection[2];
		goDownGhost = ghostDirection[3];
		// if the ghost is going left, it will stop if there is a wall, jump of
		// there is a tunnel or continue with its speed in the current direction
		if(goLeftGhost) {
			if(board[(int) (x - 1)][(int) y] == 1) {
				x += 0;
				goLeftGhost = false;
			}
			else if(board[(int) (x - 1)][(int) y] == 4) {
				x += 25;
			}
			else {
				x -= 1 * ghostSpeed;
			}
		}

		// if the ghost is going right, it will stop if there is a wall, jump of
		// there is a tunnel or continue with its speed in the current direction
		else if(goRightGhost) {
			if(board[(int) (x + 1)][(int) y] == 1) {
				x += 0;
				goRightGhost = false;
			}
			else if(board[(int) (x + 1)][(int) y] == 4) {
				x -= 25;
			}
			else {
				x += 1 * ghostSpeed;
			}
		}

		// if the ghost is going up, it will stop if there is a wall, or
		// continue with its speed in the current direction
		else if(goUpGhost) {
			if(board[(int) x][(int) (y - 1)] == 1) {
				y += 0;
				goUpGhost = false;
			}
			else {
				y -= 1 * ghostSpeed;
			}
		}

		// if the ghost is going down, it will stop if there is a wall, or
		// continue with its speed in the current direction
		else if(goDownGhost) {
			if(board[(int) x][(int) (y + 1)] == 1 || board[(int) x][(int) (y + 1)] == 5) {
				y += 0;
				goDownGhost = false;
			}
			else {
				y += 1 * ghostSpeed;
			}
		}
	}

	/**
	 * The method gets the current x position of the ghost relative to the board
	 * 
	 * @return the current x position of the ghost relative to the board
	 */
	public double getX() {
		return x;
	}

	/**
	 * The method gets the current y position of the ghost relative to the board
	 * 
	 * @return the current y position of the ghost relative to the board
	 */
	public double getY() {
		return y;
	}

	/**
	 * The method sets the current x position of the ghost relative to the board
	 * 
	 * @return
	 */
	public void setX(double newX) {
		x = newX;
	}

	/**
	 * The method sets the current y position of the ghost relative to the board
	 * 
	 * @return
	 */
	public void setY(double newY) {
		y = newY;
	}

	/**
	 * Setting the four directions' variables so that the ghost can move left *
	 * 
	 * @return the array of booleans so that the ghost can move left
	 */
	public boolean[] moveLeft() {
		boolean[] direction = new boolean[4];
		direction[0] = true;
		direction[1] = false;
		direction[2] = false;
		direction[3] = false;

		return direction;
	}

	/**
	 * Setting the four directions' variables so that the ghost can move right
	 * 
	 * @return the array of booleans so that the ghost can move right
	 */

	public boolean[] moveRight() {
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = true;
		direction[2] = false;
		direction[3] = false;

		return direction;
	}

	/**
	 * Setting the four directions' variables so that the ghost can move up *
	 * 
	 * @return the array of booleans so that the ghost can move up
	 */
	public boolean[] moveUp() {
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = true;
		direction[3] = false;

		return direction;
	}

	/**
	 * Setting the four directions' variables so that the ghost can move down
	 * 
	 * @return the array of booleans so that the ghost can move down
	 */

	public boolean[] moveDown() {
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = false;
		direction[3] = true;

		return direction;
	}

	/**
	 * The method finds the euclidian distance between two coordinates
	 * 
	 * @param tileX
	 *            the tile where the x coordinate is on
	 * @param tileY
	 *            the tile where the y coordinate is on
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the distanve between the two tiles
	 */
	public double distance(double tileX, double tileY, double x, double y) {
		double distance = 0;
		debugX = tileX;
		debugY = tileY;
		distance = Math.pow(tileX - x, 2) + Math.pow(tileY - y, 2);
		return distance;
	}

	/**
	 * the method ensures the timing in which each ghost needs to stay in different modes liek
	 * scatter,chase and infinitechase
	 * 
	 * @param levelTiming
	 *            the timing for each mode for the ghost depending on the factors like level
	 */
	public static void ghostModes(long[] levelTiming) {
		// checking if the necessary time has passed
		if((System.currentTimeMillis() > (startTime + levelTiming[modeCounter])) && !infiniteChase && !frightened) {

			startTime = System.currentTimeMillis();
			modeCounter++;

			if(scatter) {
				scatter = false;
				chase = true;
			}
			else if(chase) {
				chase = false;
				scatter = true;
			}
			if(modeCounter == 6) {
				infiniteChase = true;
				chase = true;
				scatter = false;
			}
		}
	}

	/**
	 * the method finds the shortest movement from where it is to the target x,y tile in the board
	 * 
	 * @param targetX
	 *            the target x tile where the ghost should go
	 * @param targetY
	 *            the target y tile where the ghost should go
	 * @param board
	 *            the board where the objects are placed on
	 */
	public void shortestMovement(double targetX, double targetY, int[][] board) {
		// decides which turn should the ghost take to catch the pacman using the shortest path
		// when it reaches an intersection when travelling right to left. and then takes that turn.
		if(goLeftGhost && (exitLeft || exitUp || exitDown)) {
			if(exitUp) {
				distanceUp = distance(targetX, targetY, x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitLeft) {
				distanceLeft = distance(targetX, targetY, x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitDown) {
				distanceDown = distance(targetX, targetY, x, y + 1);
			}
			else {
				distanceDown = 9999;
			}

			if(distanceUp <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveUp());
			}
			else if(distanceLeft <= Math.min(distanceUp, distanceDown)) {
				move(board, moveLeft());
			}
			else if(distanceDown <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveDown());
			}
			else {
				move(board, moveRight());
			}
		}

		// decides which turn should the ghost take to catch the pacman using the shortest path
		// when it reaches an intersection when travelling left to right. and then takes that turn.
		if(goRightGhost && (exitRight || exitUp || exitDown)) {
			if(exitUp) {
				distanceUp = distance(targetX, targetY, x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitRight) {
				distanceRight = distance(targetX, targetY, x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
			if(exitDown) {
				distanceDown = distance(targetX, targetY, x, y + 1);
			}
			else {
				distanceDown = 9999;
			}

			if(distanceUp <= Math.min(distanceRight, distanceDown)) {
				move(board, moveUp());
			}
			else if(distanceDown <= Math.min(distanceUp, distanceRight)) {
				move(board, moveDown());
			}
			else if(distanceRight <= Math.min(distanceUp, distanceDown)) {
				move(board, moveRight());
			}
			else {
				move(board, moveLeft());
			}
		}

		// decides which turn should the ghost take to catch the pacman using the shortest path
		// when it reaches an intersection when travelling down to up. and then takes that turn.
		if(goUpGhost && (exitLeft || exitRight || exitUp)) {
			if(exitUp) {
				distanceUp = distance(targetX, targetY, x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitLeft) {
				distanceLeft = distance(targetX, targetY, x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitRight) {
				distanceRight = distance(targetX, targetY, x + 1, y);
			}
			else {
				distanceRight = 9999;
			}

			if(distanceUp <= Math.min(distanceLeft, distanceRight)) {
				move(board, moveUp());
			}
			else if(distanceLeft <= Math.min(distanceUp, distanceRight)) {
				move(board, moveLeft());
			}
			else if(distanceRight <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveRight());
			}
			else {
				move(board, moveDown());
			}
		}

		// decides which turn should the ghost take to catch the pacman using the shortest path
		// when it reaches an intersection when travelling up to down. and then takes that turn.
		if(goDownGhost && (exitLeft || exitUp || exitRight)) {
			if(exitRight) {
				distanceRight = distance(targetX, targetY, x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
			if(exitLeft) {
				distanceLeft = distance(targetX, targetY, x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitDown) {
				distanceDown = distance(targetX, targetY, x, y + 1);
			}
			else {
				distanceDown = 9999;
			}

			if(distanceLeft <= Math.min(distanceRight, distanceDown)) {
				move(board, moveLeft());
			}
			else if(distanceDown <= Math.min(distanceRight, distanceLeft)) {
				move(board, moveDown());
			}
			else if(distanceRight <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveRight());
			}
			else {
				move(board, moveUp());
			}
		}
		// decides which turn should the ghost take to catch the pacman using the shortest path
		// when it reaches a wall. and then takes that turn.
		if(!goDownGhost && !goUpGhost && !goLeftGhost && !goRightGhost) {
			distanceRight = distance(targetX, targetY, x + 1, y);
			distanceLeft = distance(targetX, targetY, x - 1, y);
			distanceUp = distance(targetX, targetY, x, y - 1);
			distanceDown = distance(targetX, targetY, x, y + 1);

			if(distanceLeft <= Math.min(Math.min(distanceRight, distanceDown), distanceUp)
					&& exitLeft) {
				goLeftGhost = true;
			}
			else if(distanceDown <= Math.min(Math.min(distanceRight, distanceLeft), distanceUp)
					&& exitDown) {
				goDownGhost = true;
			}
			else if(distanceRight <= Math.min(Math.min(distanceLeft, distanceDown), distanceUp)
					&& exitRight) {
				goRightGhost = true;
			}
			else if(distanceUp <= Math.min(Math.min(distanceLeft, distanceRight), distanceDown)
					&& exitUp) {
				goUpGhost = true;
			}
		}
	}

	/**
	 * the method generates a random movement on the board for a specific ghost
	 * 
	 * @param board
	 *            the board where the objects are interacting on
	 * @param g
	 *            the graphics that draws them
	 */
	public void randomMovement(int[][] board, Graphics g) {
		if(System.currentTimeMillis() > Ghost.frightenedTimeStart + 5000) {
			frightened = false;
			chase = Ghost.chaseWhilefrightened;
			scatter = Ghost.scatterWhilefrightened;
			startTime -= 5000;
		}
		g.drawImage(Ghost.scared, (int) getX() * Game.pixel, (int) getY() * Game.pixel, null);

		if(goRightGhost && turnDirection) {
			move(board, moveLeft());
			turnDirection = false;
		}

		else if(goLeftGhost && turnDirection) {
			move(board, moveRight());
			turnDirection = false;
		}

		else if(goUpGhost && turnDirection) {
			move(board, moveDown());
			turnDirection = false;
		}

		else if(goDownGhost && turnDirection) {
			move(board, moveUp());
			turnDirection = false;
		}

		else if(goUpGhost && ((exitLeft && exitUp) || (exitLeft && exitRight) || (exitRight && exitUp))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if(randomMove == 0 && exitLeft) {
				move(board, moveLeft());
			}
			else if(randomMove == 1 && exitUp) {
				move(board, moveUp());
			}
			else if(randomMove == 2 && exitRight) {
				move(board, moveRight());
			}
		}

		else if(goUpGhost) {
			if(exitUp) {
				move(board, moveUp());
			}
			else if(exitLeft) {
				move(board, moveLeft());
			}
			else if(exitRight) {
				move(board, moveRight());
			}
		}

		else if(goLeftGhost && ((exitLeft && exitUp) || (exitLeft && exitDown) || (exitDown && exitUp))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if(randomMove == 0 && exitLeft) {
				move(board, moveLeft());
			}
			else if(randomMove == 1 && exitUp) {
				move(board, moveUp());
			}
			else if(randomMove == 2 && exitDown) {
				move(board, moveDown());
			}
		}

		else if(goLeftGhost) {
			if(exitUp) {
				move(board, moveUp());
			}
			else if(exitLeft) {
				move(board, moveLeft());
			}
			else if(exitDown) {
				move(board, moveDown());
			}
		}

		else if(goDownGhost && ((exitLeft && exitDown) || (exitLeft && exitRight) || (exitDown && exitRight))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if(randomMove == 0 && exitLeft) {
				move(board, moveLeft());
			}
			else if(randomMove == 1 && exitRight) {
				move(board, moveRight());
			}
			else if(randomMove == 2 && exitDown) {
				move(board, moveDown());
			}
		}

		else if(goDownGhost) {
			if(exitDown) {
				move(board, moveDown());
			}
			else if(exitLeft) {
				move(board, moveLeft());
			}
			else if(exitRight) {
				move(board, moveRight());
			}
		}

		else if(goRightGhost && ((exitRight && exitUp) || (exitRight && exitDown) || (exitDown && exitUp))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if(randomMove == 0 && exitRight) {
				move(board, moveRight());
			}
			else if(randomMove == 1 && exitUp) {
				move(board, moveUp());
			}
			else if(randomMove == 2 && exitDown) {
				move(board, moveDown());
			}
		}

		else if(goRightGhost) {
			if(exitUp) {
				move(board, moveUp());
			}
			else if(exitRight) {
				move(board, moveRight());
			}
			else if(exitDown) {
				move(board, moveDown());
			}
		}
	}

	/**
	 * the method finds which possible exits, out of the four ones, are available for a ghost
	 * 
	 * @param board
	 *            the board where the objects are interacting on
	 */
	public void possibleExit(int[][] board) {
		if(board[(int) x][(int) y] != 4) {
			if(board[(int) (x - 1)][(int) y] != 1) {
				exitLeft = true;
			}
			if(board[(int) (x - 1)][(int) y] == 1) {
				exitLeft = false;
			}
			if(board[(int) (x + 1)][(int) y] != 1) {
				exitRight = true;
			}
			if(board[(int) (x + 1)][(int) y] == 1) {
				exitRight = false;
			}
			if(board[(int) x][(int) (y - 1)] != 1) {
				exitUp = true;
			}
			if(board[(int) x][(int) (y - 1)] == 1) {
				exitUp = false;
			}
			if(board[(int) x][(int) (y + 1)] != 1) {
				exitDown = true;
			}
			if(board[(int) x][(int) (y + 1)] == 1 || board[(int) x][(int) (y + 1)] == 5) {
				exitDown = false;
			}
		}
	}

}
