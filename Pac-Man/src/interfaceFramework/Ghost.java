package interfaceFramework;

import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

public class Ghost {
	int x, y;
	File path;
	static double debugX, debugY;
	Image inkyImg, pinkyImg, blinkyImg, clydeImg, temp;
	boolean goLeftGhost = true;
	boolean goRightGhost, goUpGhost, goDownGhost, stopped;
	boolean ghostDirection[] = new boolean[4]; 
	boolean scatter = false;
	boolean chase;
	boolean frightened = true;
	
	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
		
		path = new File("").getAbsoluteFile();
		inkyImg = new ImageIcon(path + "\\res\\image\\inky.gif").getImage();
		blinkyImg = new ImageIcon(path + "\\res\\image\\blinky.gif").getImage();
		pinkyImg = new ImageIcon(path + "\\res\\image\\pinky.gif").getImage();
		clydeImg = new ImageIcon(path + "\\res\\image\\clyde.gif").getImage();
		temp = new ImageIcon(path + "\\res\\image\\target.gif").getImage();
	}
/**
 * This is the method that controls the ghosts movement. It is set to random,
 * however before a ghosts movements it checks if the upcoming coordinates 
 * are occupied with a wall.
 * @param ghost used to find a particular ghost's coordinates
 * @param board used to check upcoming coordinates for walls
 */
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}
	
	public void move(int board[][], boolean[] ghostDirection) {
		goLeftGhost = ghostDirection[0];
		goRightGhost = ghostDirection[1];
		goUpGhost = ghostDirection[2];
		goDownGhost = ghostDirection[3];
		
		if(goLeftGhost) {
			if(board[x - 1][y] == 1){
				x += 0;
				goLeftGhost = false;
			}
			else if(board[x - 1][y] == 4){
				x += 25;
			}
			else {
				x -= 1;
			}
		}
		
		else if(goRightGhost) {
			if(board[x + 1][y] == 1){
				x += 0;
				goRightGhost = false;
			}
			else if(board[x + 1][y] == 4){
				x -= 25;
			}
			else {
				x += 1;
			}
		}
		
		else if(goUpGhost) {
			if(board[x][y - 1] == 1){
				y += 0;
				goUpGhost = false;
			}
			else {
				y -= 1;
			}
		}
		
		else if(goDownGhost) {
			if(board[x][y + 1] == 1 || board[x][y + 1] == 5){
				y += 0;
				goDownGhost = false;
			}
			else {
				y += 1;
			}
		}
	}	
	public void setDirection(){
		goRightGhost = true;
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
	
	public double distance(double tileX, double tileY, int x, int y) {
		double distance = 0;
		debugX = tileX;
		debugY = tileY;
		distance = Math.pow(tileX - x, 2) + Math.pow(tileY - y, 2);
		return distance;
	}
}
