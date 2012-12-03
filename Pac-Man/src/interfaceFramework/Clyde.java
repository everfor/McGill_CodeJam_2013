package interfaceFramework;

import java.awt.Graphics;
import java.util.Random;

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

	public Clyde(int x, int y) {
		super(x, y);
	}

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

	public void movePossible(Pacman pacman, int[][] board, Graphics g) {
		if(scatter){
			insideProximityScatter(board);
		}
		else if(frightened){
			if (System.currentTimeMillis() >Ghost.frightenedTimeStart+5000){
				 frightened= false;
				 Ghost.chase = Ghost.chaseWhilefrightened;
				 Ghost.scatter = Ghost.scatterWhilefrightened;
			 }
			possibleExit(board);
			g.drawImage(Ghost.scared, getX()*Game.pixel, getY()*Game.pixel, null);
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
		
		else{	
			if (withinProximity(pacman)) {
				insideProximityScatter(board);
			}
			else{
				outsideProximityChase(pacman, board);
			}
		}	
	}
	public void insideProximityScatter(int[][] board){
		possibleExit(board);
		if (goLeftGhost && (exitLeft || exitUp || exitDown)) {
			if (exitUp) {
				distanceUp = distance(4, 28, x, y - 1);
			} 
			else {
				distanceUp = 9999;
			}
			if (exitLeft) {
				distanceLeft = distance(4, 28, x - 1, y);
			} 
			else {
				distanceLeft = 9999;
			}
			if (exitDown) {
				distanceDown = distance(4, 28, x, y + 1);
			} 
			else {
				distanceDown = 9999;
			}

			if (distanceUp <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveUp());
			} 
			else if (distanceLeft <= Math.min(distanceUp, distanceDown)) {
				move(board, moveLeft());
			} 
			else if (distanceDown <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveDown());
			}
			else {
				move(board, moveRight());
			}

		}

		if (goRightGhost && (exitRight || exitUp || exitDown)) {
			if (exitUp) {
				distanceUp = distance(4, 28, x, y - 1);
			} 
			else {
				distanceUp = 9999;
			}
			if (exitRight) {
				distanceRight = distance(4, 28, x + 1, y);
			} 
			else {
				distanceRight = 9999;
			}
			if (exitDown) {
				distanceDown = distance(4, 28, x, y + 1);
			} 
			else {
				distanceDown = 9999;
			}

			if (distanceUp <= Math.min(distanceRight, distanceDown)) {
				move(board, moveUp());
			} 
			else if (distanceDown <= Math.min(distanceUp, distanceRight)) {
				move(board, moveDown());
			} 
			else if (distanceRight <= Math.min(distanceUp, distanceDown)) {
				move(board, moveRight());
			} 
			else {
				move(board, moveLeft());
			}
		}

		if (goUpGhost && (exitLeft || exitRight || exitUp)) {
			if (exitUp) {
				distanceUp = distance(4, 28, x, y - 1);
			} 
			else {
				distanceUp = 9999;
			}
			if (exitLeft) {
				distanceLeft = distance(4, 28, x - 1, y);
			} 
			else {
				distanceLeft = 9999;
			}
			if (exitRight) {
				distanceRight = distance(4, 28, x + 1, y);
			}
			else {
				distanceRight = 9999;
			}

			if (distanceUp <= Math.min(distanceLeft, distanceRight)) {
				move(board, moveUp());
			} 
			else if (distanceLeft <= Math.min(distanceUp, distanceRight)) {
				move(board, moveLeft());
			} 
			else if (distanceRight <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveRight());
			} 
			else {
				move(board, moveDown());
			}
		}

		if (goDownGhost && (exitLeft || exitUp || exitRight)) {
			if (exitRight) {
				distanceRight = distance(4, 28, x + 1, y);
			} 
			else {
				distanceRight = 9999;
			}
			if (exitLeft) {
				distanceLeft = distance(4, 28, x - 1, y);
			} 
			else {
				distanceLeft = 9999;
			}
			if (exitDown) {
				distanceDown = distance(4, 28, x, y + 1);
			} 
			else {
				distanceDown = 9999;
			}

			if (distanceLeft <= Math.min(distanceRight, distanceDown)) {
				move(board, moveLeft());
			} 
			else if (distanceDown <= Math.min(distanceRight, distanceLeft)) {
				move(board, moveDown());
			} 
			else if (distanceRight <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveRight());
			} 
			else {
				move(board, moveUp());
			}
		}
		if (!goDownGhost && !goUpGhost && !goLeftGhost && !goRightGhost) {
			distanceRight = distance(4, 28, x + 1, y);
			distanceLeft = distance(4, 28, x - 1, y);
			distanceUp = distance(4, 28, x, y - 1);
			distanceDown = distance(4, 28, x, y + 1);

			if (distanceLeft <= Math.min(Math.min(distanceRight, distanceDown), distanceUp) && exitLeft) {
				goLeftGhost = true;
			}
			else if (distanceDown <= Math.min(Math.min(distanceRight, distanceLeft), distanceUp) && exitDown) {
				goDownGhost = true;
			} 
			else if (distanceRight <= Math.min(Math.min(distanceLeft, distanceDown), distanceUp) && exitRight) {
				goRightGhost = true;
			} 
			else if (distanceUp <= Math.min(Math.min(distanceLeft, distanceRight), distanceDown) && exitUp) {
				goUpGhost = true;
			}
		}
	}
	
	public void outsideProximityChase(Pacman pacman, int[][] board){
		possibleExit(board);
		if (goLeftGhost && (exitLeft || exitUp || exitDown)) {
			if (exitUp) {
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			} 
			else {
				distanceUp = 9999;
			}
			if (exitLeft) {
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			} 
			else {
				distanceLeft = 9999;
			}
			if (exitDown) {
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			} 
			else {
				distanceDown = 9999;
			}

			if (distanceUp <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveUp());
			} 
			else if (distanceLeft <= Math.min(distanceUp, distanceDown)) {
				move(board, moveLeft());
			} 
			else if (distanceDown <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveDown());
			}
			else {
				move(board, moveRight());
			}

		}

		if (goRightGhost && (exitRight || exitUp || exitDown)) {
			if (exitUp) {
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			} 
			else {
				distanceUp = 9999;
			}
			if (exitRight) {
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			} 
			else {
				distanceRight = 9999;
			}
			if (exitDown) {
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			} 
			else {
				distanceDown = 9999;
			}

			if (distanceUp <= Math.min(distanceRight, distanceDown)) {
				move(board, moveUp());
			} 
			else if (distanceDown <= Math.min(distanceUp, distanceRight)) {
				move(board, moveDown());
			} 
			else if (distanceRight <= Math.min(distanceUp, distanceDown)) {
				move(board, moveRight());
			} 
			else {
				move(board, moveLeft());
			}
		}

		if (goUpGhost && (exitLeft || exitRight || exitUp)) {
			if (exitUp) {
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			} 
			else {
				distanceUp = 9999;
			}
			if (exitLeft) {
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			} 
			else {
				distanceLeft = 9999;
			}
			if (exitRight) {
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			}
			else {
				distanceRight = 9999;
			}

			if (distanceUp <= Math.min(distanceLeft, distanceRight)) {
				move(board, moveUp());
			} 
			else if (distanceLeft <= Math.min(distanceUp, distanceRight)) {
				move(board, moveLeft());
			} 
			else if (distanceRight <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveRight());
			} 
			else {
				move(board, moveDown());
			}
		}

		if (goDownGhost && (exitLeft || exitUp || exitRight)) {
			if (exitRight) {
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			} 
			else {
				distanceRight = 9999;
			}
			if (exitLeft) {
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			} 
			else {
				distanceLeft = 9999;
			}
			if (exitDown) {
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			} 
			else {
				distanceDown = 9999;
			}

			if (distanceLeft <= Math.min(distanceRight, distanceDown)) {
				move(board, moveLeft());
			} 
			else if (distanceDown <= Math.min(distanceRight, distanceLeft)) {
				move(board, moveDown());
			} 
			else if (distanceRight <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveRight());
			} 
			else {
				move(board, moveUp());
			}
		}
		if (!goDownGhost && !goUpGhost && !goLeftGhost && !goRightGhost) {
			distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);

			if (distanceLeft <= Math.min(Math.min(distanceRight, distanceDown), distanceUp) && exitLeft) {
				goLeftGhost = true;
			}
			else if (distanceDown <= Math.min(Math.min(distanceRight, distanceLeft), distanceUp) && exitDown) {
				goDownGhost = true;
			} 
			else if (distanceRight <= Math.min(Math.min(distanceLeft, distanceDown), distanceUp) && exitRight) {
				goRightGhost = true;
			} 
			else if (distanceUp <= Math.min(Math.min(distanceLeft, distanceRight), distanceDown) && exitUp) {
				goUpGhost = true;
			}
		}
	}

	public void possibleExit(int[][] board) {
		if(board[x][y] != 4){
			if(board[x - 1][y] != 1){
				exitLeft = true;
			}
			if(board[x - 1][y] == 1){
				exitLeft = false;
			}
			if(board[x + 1][y] != 1){
				exitRight = true;
			}
			if(board[x + 1][y] == 1){
				exitRight = false;
			}
			if(board[x][y - 1] != 1){
				exitUp = true;
			}
			if(board[x][y - 1] == 1){
				exitUp = false;
			}
			if(board[x][y + 1] != 1){
				exitDown = true;
			}
			if(board[x][y + 1] == 1 || board[x][y + 1] == 5){
				exitDown = false;
			}
		}
	}
}
