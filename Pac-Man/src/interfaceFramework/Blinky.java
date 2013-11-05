package interfaceFramework;

import java.awt.Graphics;

/**
 * The class is for the blinky ghost. The class incorporates all the attributes that this ghost
 * should have
 * 
 */
public class Blinky extends Ghost {

	/**
	 * initializes a new blinky ghost in the position given in the arguments
	 * 
	 * @param x
	 *            the x coordinate where it should be placed
	 * @param y
	 *            the y coordinate where it should be placed
	 */
	public Blinky(double x, double y) {
		super(x, y);
	}

	/**
	 * Checking the possible movements that can be done by blinky if it is in scatter,chase or
	 * frightened move
	 * 
	 * @param pacman
	 *            the pacman object which is active in the game
	 * @param board
	 *            the board where the game is being played on by pacman and the ghosts
	 * @param g
	 *            the graphics which takes care of the drawing of different functions
	 */
	public void movePossible(Pacman pacman, int[][] board, Graphics g) {
		possibleExit(board);
		if(scatter) {
			Game.setNormalSpeeds();
			// movement for scattering mode based on blinky's AI
			shortestMovement(25, 3, board);
		}
		else if(chase) {
			Game.setNormalSpeeds();
			// movement for chasing mode based on blinky's AI
			shortestMovement(pacman.getX(), pacman.getY(), board);
			
		}
		else if(frightened) {
			Game.setFrightenedSpeeds();
			// movement for frightened mode based on blinky's AI
			randomMovement(board, g);
		}
	}
}