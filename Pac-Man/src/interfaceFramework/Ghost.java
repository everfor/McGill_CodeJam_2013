package interfaceFramework;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;

public class Ghost {
	double x, y;
	File path;
	Image inkyImg, pinkyImg, blinkyImg, clydeImg;
	static Image scared;
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
	
	static boolean scatter = true;
	static boolean chase = false;
	static boolean frightened = false;
	static boolean scatterWhilefrightened;
	static boolean chaseWhilefrightened;
	static boolean turnDirection = false;
	static int modeCounter = 0;
	static long[] level1Timing = { 7000, 20000, 7000, 20000, 5000, 20000, 5000 };
	static long[] level2To4Timing = { 7000, 20000, 7000, 20000, 5000, 1033000, 1/60 };
	static long[] level5PlusTiming = { 5000, 20000, 5000, 20000, 5000, 1037000, 1/60 };
	static long startTime = System.currentTimeMillis();
	static boolean infiniteChase;
	public static long frightenedTimeStart;
	static double ghostSpeed = 0.9;

	public Ghost(double x, double y) {

		this.x = x;
		this.y = y;

		path = new File("").getAbsoluteFile();
		inkyImg = new ImageIcon(path + "\\res\\image\\inky.gif").getImage();
		blinkyImg = new ImageIcon(path + "\\res\\image\\blinky.gif").getImage();
		pinkyImg = new ImageIcon(path + "\\res\\image\\pinky.gif").getImage();
		clydeImg = new ImageIcon(path + "\\res\\image\\clyde.gif").getImage();
		scared = new ImageIcon(path + "\\res\\image\\scared.gif").getImage();
	}

	/**
	 * This is the method that controls the ghosts movement. It is set to
	 * random, however before a ghosts movements it checks if the upcoming
	 * coordinates are occupied with a wall.
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

	public void move(int board[][], boolean[] ghostDirection) {
		goLeftGhost = ghostDirection[0];
		goRightGhost = ghostDirection[1];
		goUpGhost = ghostDirection[2];
		goDownGhost = ghostDirection[3];

		if (goLeftGhost) {
			if (board[(int) (x - 1)][(int) y] == 1) {
				x += 0;
				goLeftGhost = false;
			} else if (board[(int) (x - 1)][(int) y] == 4) {
				x += 25;
			} else {
				x -= 1 * ghostSpeed;
			}
		}

		else if (goRightGhost) {
			if (board[(int) (x + 1)][(int) y] == 1) {
				x += 0;
				goRightGhost = false;
			} else if (board[(int) (x + 1)][(int) y] == 4) {
				x -= 25;
			} else {
				x += 1 * ghostSpeed;
			}
		}

		else if (goUpGhost) {
			if (board[(int) x][(int) (y - 1)] == 1) {
				y += 0;
				goUpGhost = false;
			} else {
				y -= 1 * ghostSpeed;
			}
		}

		else if (goDownGhost) {
			if (board[(int) x][(int) (y + 1)] == 1 || board[(int) x][(int) (y + 1)] == 5) {
				y += 0;
				goDownGhost = false;
			} else {
				y += 1 * ghostSpeed;
			}
		}
	}

	// getX() and getY() get a ghost's x and y coordinates at a specified time.
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double newX) {
		x = newX;
	}

	public void setY(double newY) {
		y = newY; 
	}

	public boolean[] moveLeft() {
		boolean[] direction = new boolean[4];
		direction[0] = true;
		direction[1] = false;
		direction[2] = false;
		direction[3] = false;

		return direction;
	}

	public boolean[] moveRight() {
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = true;
		direction[2] = false;
		direction[3] = false;

		return direction;
	}

	public boolean[] moveUp() {
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = true;
		direction[3] = false;

		return direction;
	}

	public boolean[] moveDown() {
		boolean[] direction = new boolean[4];
		direction[0] = false;
		direction[1] = false;
		direction[2] = false;
		direction[3] = true;

		return direction;
	}

	public double distance(double tileX, double tileY, double x, double y) {
		double distance = 0;
		distance = Math.pow(tileX - x, 2) + Math.pow(tileY - y, 2);
		return distance;
	}

	public static void ghostModes(long []levelTiming) {
		if ((System.currentTimeMillis() > (startTime + levelTiming[modeCounter]))
				&& !infiniteChase&&!frightened) {
			
			startTime = System.currentTimeMillis();
			modeCounter++;
			
			if (scatter) {
				scatter = false;
				chase = true;
			} else if (chase) {
				chase = false;
				scatter = true;
			}
			if (modeCounter == 6) {
				infiniteChase = true;
				chase = true;
				scatter = false;
			}
		}
	}
	
	public void shortestMovement(double targetX, double targetY, int[][] board){
		if(goLeftGhost && (exitLeft || exitUp || exitDown)) {
			if(exitUp){
				distanceUp = distance(targetX, targetY, x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitLeft){
				distanceLeft = distance(targetX, targetY, x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitDown){
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
		
		if(goRightGhost && (exitRight || exitUp || exitDown)) {
			if(exitUp){
				distanceUp = distance(targetX, targetY, x, y - 1);
			}	
			else {
				distanceUp = 9999;
			}
			if(exitRight){
				distanceRight = distance(targetX, targetY, x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
			if(exitDown){
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
		
		if(goUpGhost && (exitLeft || exitRight || exitUp)) {
			if(exitUp){
				distanceUp = distance(targetX, targetY, x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitLeft){
				distanceLeft = distance(targetX, targetY, x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitRight){
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
		
		if(goDownGhost && (exitLeft || exitUp || exitRight)) {
			if(exitRight){
				distanceRight = distance(targetX, targetY, x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
			if(exitLeft){
				distanceLeft = distance(targetX, targetY, x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitDown){
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
		
		if(!goDownGhost && !goUpGhost && !goLeftGhost && !goRightGhost) {
			distanceRight = distance(targetX, targetY, x + 1, y);
			distanceLeft = distance(targetX, targetY, x - 1, y);
			distanceUp = distance(targetX, targetY, x, y - 1);
			distanceDown = distance(targetX, targetY, x, y + 1);
			
			if(distanceLeft <= Math.min(Math.min(distanceRight, distanceDown), distanceUp) && exitLeft) {
				goLeftGhost = true;
			}
			else if(distanceDown <= Math.min(Math.min(distanceRight, distanceLeft), distanceUp) && exitDown) {
				goDownGhost = true;
			}
			else if(distanceRight <= Math.min(Math.min(distanceLeft, distanceDown), distanceUp) && exitRight) {
				goRightGhost = true;
			}
			else if(distanceUp <= Math.min(Math.min(distanceLeft, distanceRight), distanceDown) && exitUp) {
				goUpGhost = true;
			}
		}
	}
	
	public void randomMovement(int[][] board, Graphics g) {
		if (System.currentTimeMillis() > Ghost.frightenedTimeStart+5000){
			 frightened= false;
			 chase = Ghost.chaseWhilefrightened;
			 scatter = Ghost.scatterWhilefrightened;
			 startTime -= 5000;
		 }
		g.drawImage(Ghost.scared, (int) getX()*Game.pixel, (int) getY()*Game.pixel, null);
		
		if(goRightGhost && turnDirection){
			move(board, moveLeft());
			turnDirection = false;
		}
		
		else if(goLeftGhost && turnDirection){
			move(board, moveRight());
			turnDirection = false;
		}
		
		else if(goUpGhost && turnDirection){
			move(board, moveDown());
			turnDirection = false;
		}
		
		else if(goDownGhost && turnDirection){
			move(board, moveUp());
			turnDirection = false;
		}
		
		else if (goUpGhost && ((exitLeft && exitUp) || (exitLeft && exitRight) || (exitRight && exitUp))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if (randomMove == 0 && exitLeft) {
				move(board, moveLeft());
			} 
			else if (randomMove == 1 && exitUp) {
				move(board, moveUp());
			} 
			else if (randomMove == 2 && exitRight) {
				move(board, moveRight());
			}
		}
		
		else if (goUpGhost){
			if (exitUp) {
				move(board, moveUp());
			} 
			else if (exitLeft) {
				move(board, moveLeft());
			} 
			else if (exitRight) {
				move(board, moveRight());
			}
		}
		
		else if (goLeftGhost && ((exitLeft && exitUp) || (exitLeft && exitDown) || (exitDown && exitUp))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if (randomMove == 0 && exitLeft) {
				move(board, moveLeft());
			} 
			else if (randomMove == 1 && exitUp) {
				move(board, moveUp());
			} 
			else if (randomMove == 2 && exitDown) {
				move(board, moveDown());
			}
		}
		
		else if (goLeftGhost){
			if (exitUp) {
				move(board, moveUp());
			} 
			else if (exitLeft) {
				move(board, moveLeft());
			} 
			else if (exitDown) {
				move(board, moveDown());
			}
		}
		
		else if (goDownGhost && ((exitLeft && exitDown) || (exitLeft && exitRight) || (exitDown && exitRight))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if (randomMove == 0 && exitLeft) {
				move(board, moveLeft());
			} 
			else if (randomMove == 1 && exitRight) {
				move(board, moveRight());
			} 
			else if (randomMove == 2 && exitDown) {
				move(board, moveDown());
			}
		}
		
		else if (goDownGhost){	
			if (exitDown) {
				move(board, moveDown());
			} 
			else if (exitLeft) {
				move(board, moveLeft());
			} 
			else if (exitRight) {
				move(board, moveRight());
			}
		}
		
		else if (goRightGhost && ((exitRight && exitUp) || (exitRight && exitDown) || (exitDown && exitUp))) {
			Random generator = new Random();
			int randomMove = generator.nextInt(3);

			if (randomMove == 0 && exitRight) {
				move(board, moveRight());
			} 
			else if (randomMove == 1 && exitUp) {
				move(board, moveUp());
			} 
			else if (randomMove == 2 && exitDown) {
				move(board, moveDown());
			}
		}
		
		else if (goRightGhost){
			if (exitUp) {
				move(board, moveUp());
			} 
			else if (exitRight) {
				move(board, moveRight());
			} 
			else if (exitDown) {
				move(board, moveDown());
			}
		}	
	}
	
	public void possibleExit(int[][] board) {
		if(board[(int) x][(int) y] != 4){
			if(board[(int) (x - 1)][(int) y] != 1){
				exitLeft = true;
			}
			if(board[(int) (x - 1)][(int) y] == 1){
				exitLeft = false;
			}
			if(board[(int) (x + 1)][(int) y] != 1){
				exitRight = true;
			}
			if(board[(int) (x + 1)][(int) y] == 1){
				exitRight = false;
			}
			if(board[(int) x][(int) (y - 1)] != 1){
				exitUp = true;
			}
			if(board[(int) x][(int) (y - 1)] == 1){
				exitUp = false;
			}
			if(board[(int) x][(int) (y + 1)] != 1){
				exitDown = true;
			}
			if(board[(int) x][(int) (y + 1)] == 1 || board[(int) x][(int) (y + 1)] == 5){
				exitDown = false;
			}
		}
	}

}
