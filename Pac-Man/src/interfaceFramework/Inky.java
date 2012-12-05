package interfaceFramework;

import java.awt.Graphics;

/**
 * The class is for the ghost, inky. The class incorporates all the attributes that
 * this ghost should have
 * 
 */
public class Inky extends Ghost {

	boolean exitLeft = false;
	boolean exitRight = false;
	boolean exitUp = false;
	boolean exitDown = false;
	double tileX = 0;
	double tileY = 0;
	double newXTile = 0;
	double newYTile = 0;
	double distanceUp = 9999;
	double distanceLeft = 9999;
	double distanceDown = 9999;
	double distanceRight = 9999;

	/**
	 * initializes a new inky ghost in the position given in the arguments
	 * 
	 * @param x
	 *            the x coordinate where it should be placed
	 * @param y
	 *            the y coordinate where it should be placed
	 */
	public Inky(double x, double y) {
		super(x, y);
	}

	/**
	 * Checking the possible movements that can be done by inky if it is in
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
		// movement for scattering mode based on inky's AI
		if (scatter) {
			Game.setNormalSpeeds();
			shortestMovement(25, 28, board);
			// movement for chasing mode based on inky's AI
		} else if (chase) {
			Game.setNormalSpeeds();
			changeTargetTile();
			shortestMovement(newXTile, newYTile, board);
			
			// movement for frightened mode based on blinky's AI
		} else if (frightened) {
			randomMovement(board, g);
			Game.setFrightenedSpeeds();
		}
	}

	/**
	 * the method computes the target tile for inky, based on its own AI, and
	 * changes it if necessary
	 */
	public void changeTargetTile() {
		if (Game.goRight) {
			tileX = Game.pacman.getX() + 2;
			tileY = Game.pacman.getY();
		} else if (Game.goLeft) {
			tileX = Game.pacman.getX() - 2;
			tileY = Game.pacman.getY();
		} else if (Game.goDown) {
			tileX = Game.pacman.getX();
			tileY = Game.pacman.getY() + 2;
		} else if (Game.goUp) {
			tileX = Game.pacman.getX() - 2;
			tileY = Game.pacman.getY() - 2;
		}
		// changing the target tile
		if (Game.pacman.getX() >= Game.blinky.getX()
				&& Game.pacman.getY() >= Game.blinky.getY()) {
			newXTile = Game.blinky.getX() + 2
					* Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() + 2
					* Math.abs(tileY - Game.blinky.getY());
		} else if (Game.pacman.getX() >= Game.blinky.getX()
				&& Game.pacman.getY() < Game.blinky.getY()) {
			newXTile = Game.blinky.getX() + 2
					* Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() - 2
					* Math.abs(tileY - Game.blinky.getY());
		} else if (Game.pacman.getX() < Game.blinky.getX()
				&& Game.pacman.getY() >= Game.blinky.getY()) {
			newXTile = Game.blinky.getX() - 2
					* Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() + 2
					* Math.abs(tileY - Game.blinky.getY());
		} else if (Game.pacman.getX() < Game.blinky.getX()
				&& Game.pacman.getY() < Game.blinky.getY()) {
			newXTile = Game.blinky.getX() - 2
					* Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() - 2
					* Math.abs(tileY - Game.blinky.getY());
		}
	}
}