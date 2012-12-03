package interfaceFramework;

import java.awt.Graphics;

/**
 * The class for the clyde ghost. The class incorporates all the attributes that
 * this ghost should have
 * 
 */
public class Clyde extends Ghost {

	boolean exitLeft = false;
	boolean exitRight = false;
	boolean exitUp = false;
	boolean exitDown = false;

	static boolean inProximity;

	double distanceUp = 9999;
	double distanceLeft = 9999;
	double distanceDown = 9999;
	double distanceRight = 9999;

	/**
	 * initializes a new clyde ghost in the position given in the arguments
	 * 
	 * @param x
	 *            the x coordinate where it should be placed
	 * @param y
	 *            the y coordinate where it should be placed
	 */
	public Clyde(double x, double y) {
		super(x, y);
	}

	/**
	 * Checking the possible movements that can be done by clyde if it is in
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
		if (scatter) {
			Game.setNormalSpeeds();
			insideProximityScatter(board);
		} 
		else if (frightened) {
			possibleExit(board);
			randomMovement(board, g);
			Game.setFrightenedSpeeds();
		}
		else if (chase){
			Game.setNormalSpeeds();
			if (withinProximity(pacman)) {
				insideProximityScatter(board);
			} else {
				outsideProximityChase(pacman, board);
			}
		}
	}

	/**
	 * Checking if pacman is close enough to clyde
	 * 
	 * @param pacman
	 *            the pacman object which needs to be checked in relation to
	 *            clyde
	 * @return true if pacman is within clyde's proximity, false otherwise
	 */
	public boolean withinProximity(Pacman pacman) {
		inProximity = true;
		if (distance(pacman.getX(), pacman.getY(), x, y - 1) >= 64) {
			inProximity = false;
		} 
		else {
			inProximity = true;
		}
		return inProximity;
	}

	/**
	 * Checking if pacman is close enough to clyde, when in scatter mode. checks
	 * for clyde for possible exists towards pacman
	 * 
	 * @param board
	 *            the board so that clyde can check the distance from pacman,
	 *            when clyde is in scatter mode
	 * @return
	 */
	public void insideProximityScatter(int[][] board) {
		possibleExit(board);
		shortestMovement(4, 28, board);
	}

	/**
	 * Checking if pacman is close enough to clyde, when in scatter mode. checks
	 * for clyde for possible exists towards pacman
	 * 
	 * @param board
	 *            the board so that clyde can check the distance from pacman,
	 *            when clyde is in chase mode
	 * @param board the board which pacman and clyde are on
	 * @return
	 */
	public void outsideProximityChase(Pacman pacman, int[][] board) {
		possibleExit(board);
		shortestMovement(pacman.getX(), pacman.getY(), board);
	
	}

}
