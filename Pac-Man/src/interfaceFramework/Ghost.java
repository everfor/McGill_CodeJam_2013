package interfaceFramework;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * The Ghost class deals with all the movements of the Ghost character
 */


public class Ghost {
	int x, y;
	File path;
	Image image1;
	boolean goLeft, goRight, goUp, goDown,stopped;
	boolean ghostDirection[] = new boolean[4]; 

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
		
		path = new File("").getAbsoluteFile();
		image1 = new ImageIcon(path + "\\resources\\ghost1.gif").getImage();
	}
/**
 * This is the method that controls the ghosts movement. It is set to random,
 * however before a ghosts movements it checks if the upcoming coordinates 
 * are occupied with a wall.
 * 
 * @param ghost Direction
 * 			 used to find a particular ghost's coordinates
 * @param board used to check upcoming coordinates for walls
 */
	public void move(int board[][], boolean[] ghostDirection) {
		goLeft = ghostDirection[0];
		goRight = ghostDirection[1];
		goUp = ghostDirection[2];
		goDown = ghostDirection[3];
		
		if(goLeft) {
			if(board[x - 1][y] == 1){
				x += 0;                                                              
				goLeft = false;
				stopped = true;
			}
			else {
				stopped = false;
				x -= 1;
			}
		}
		
		else if(goRight) {
			if(board[x + 1][y] == 1){
				x += 0;
				goRight = false;
				//stopped = true;
			}
			else {
				x += 1;
				//stopped = false;
			}
		}
		
		else if(goUp) {
			if(board[x][y - 1] == 1){
				y += 0;
				goUp = false;
				//stopped = true;

			}
			else {
				y -= 1;
//				stopped = false;
			}
		}
		
		else if(goDown) {
			if(board[x][y + 1] == 1){
				y += 0;
				goDown = false;
//				stopped = true;
			}
			else {
				y += 1;
//				stopped = false;
			}
		}
}
	/**
	 * getX return's Ghost's x coordinates at a specified time
	 */

	public int getX() {
		return x;
	}
	/**
	 * getY return's Ghost's y coordinates at a specified time
	 */
	public int getY() {
		return y;
	}

	/**
	 * This is the method that moves the ghost Left
	 * it sets the direction array index 0 (which corresponds to a left movement) to true
	 * @return direction
	 * 		returns the boolean array specifying the direction
	 * 		
	 */	
	public boolean[] moveLeft(){
		boolean[] direction = new boolean[4];
		direction[0] = true;
		direction[1] = false;
		direction[2] = false;
		direction[3] = false;
		
		return direction;
	}
	/**
	 * This is the method that moves the ghost Right
	 * it sets the direction array index 1 (which corresponds to a right movement) to true
	 * @return direction
	 * 		returns the boolean array specifying the direction
	 * 		
	 */	
	public boolean[] moveRight(){
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = true;
		direction[2] = false;
		direction[3] = false;
		
		return direction;
	}
	/**
	 * This is the method that moves the ghost Up
	 * it sets the direction array index 2 (which corresponds to a up movement) to true
	 * @return direction
	 * 		returns the boolean array specifying the direction
	 * 		
	 */	
	public boolean[] moveUp(){
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = true;
		direction[3] = false;
		
		return direction;
	}
	/**
	 * This is the method that moves the ghost Down
	 * it sets the direction array index 3 (which corresponds to a down movement) to true
	 * @return direction
	 * 		returns the boolean array specifying the direction
	 * 		
	 */	
	public boolean[] moveDown(){
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = false;
		direction[3] = true;
		
		return direction;
	}
	
	
	/**
	 * This is the method that calculates the distance between the Pac-Man and the Ghost
	 *
	 *@param pacman
	 *			takes the pacman object as a parameter in order to get the pacman's coordinates
	 *@param x
	 *			x-coordinates of ghost
	 *@param y
	 *			y-coordinates of ghost
	 * @return distance
	 * 			returns the distance of Pac-Man from the ghost
	 * 		
	 */	
	


	public double distance(double tileX, double tileY, int x, int y) {
		  double distance = 0;
		  distance = Math.pow(tileX - x, 2) + Math.pow(tileY - y, 2);
		  return distance;
		 }
}
