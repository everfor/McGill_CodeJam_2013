package interfaceFramework;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

public class Ghost {
	int x, y;
	File path;
	Image image1;
	boolean goLeft, goRight, goUp, goDown;
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
 * @param ghost used to find a particular ghost's coordinates
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
			}
			else {
				x -= 1;
			}
		}
		
		else if(goRight) {
			if(board[x + 1][y] == 1){
				x += 0;
				goRight = false;
			}
			else {
				x += 1;
			}
		}
		
		else if(goUp) {
			if(board[x][y - 1] == 1){
				y += 0;
				goUp = false;
			}
			else {
				y -= 1;
			}
		}
		
		else if(goDown) {
			if(board[x][y + 1] == 1){
				y += 0;
				goDown = false;
			}
			else {
				y += 1;
			}
		}
}

	//getX() and getY() get a ghost's x and y coordinates at a specified time.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean[] moveLeft(){
		boolean[] direction = new boolean[4];
		direction[0] = true;
		direction[1] = false;
		direction[2] = false;
		direction[3] = false;
		
		return direction;
	}
	
	public boolean[] moveRight(){
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = true;
		direction[2] = false;
		direction[3] = false;
		
		return direction;
	}
	
	public boolean[] moveUp(){
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = true;
		direction[3] = false;
		
		return direction;
	}
	
	public boolean[] moveDown(){
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = false;
		direction[3] = true;
		
		return direction;
	}
}
