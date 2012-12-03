package interfaceFramework;

import java.awt.Graphics;

/**
 * The class for the pinky ghost. The class incorporates all the attributes that
 * this ghost should have
 * 
 */
public class Pinky extends Ghost {

	boolean exitLeft = false;
	boolean exitRight = false;
	boolean exitUp = false;
	boolean exitDown = false;
	static int xOffset = 0;
	static int yOffset = 0;
	double distanceUp = 9999;
	double distanceLeft = 9999;
	double distanceDown = 9999;
	double distanceRight = 9999;

	/**
	 * initializes a new pinky ghost in the position given in the arguments
	 * 
	 * @param x
	 *            the x coordinate where it should be placed
	 * @param y
	 *            the y coordinate where it should be placed
	 */
	public Pinky(double x, double y) {
		super(x, y);
	}

	/**
	 * Checking the possible movements that can be done by pinky if it is in
	 * scatter,chase or frightened move
	 * 
	 * @param pacman
	 *            the pacman object which is active in the game
	 * @param board
	 *            the board where the game is being played on by pacman and the
	 *            ghosts
	 * @param g
	 *            the graphics which takes care of the drawing of different
	 *            functions
	 */
	public void movePossible(Pacman pacman, int[][] board, Graphics g) {
		possibleExit(board);
		if (scatter) {
			Game.setNormalSpeeds();
			shortestMovement(4, 3, board);
		} else if (chase) {
			Game.setNormalSpeeds();
			shortestMovement(pacman.getX() + xOffset, pacman.getY() + yOffset,
					board);
		} else if (frightened) {
			randomMovement(board, g);
			Game.setFrightenedSpeeds();
		}
	}

	/**
	 * Changing pinky's offset based on pinky's ai and the position of pacman
	 */
	public static void changeOffset() {
		if (Game.goRight) {
			xOffset = 4;
			yOffset = 0;
		} else if (Game.goLeft) {
			xOffset = -4;
			yOffset = 0;
		} else if (Game.goDown) {
			xOffset = 0;
			yOffset = 4;
		} else if (Game.goUp) {
			xOffset = -4;
			yOffset = -4;
		}
	}

}
