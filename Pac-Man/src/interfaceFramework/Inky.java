package interfaceFramework;

import java.awt.Graphics;
import java.util.Random;

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

	public Inky(int x, int y) {
		super(x, y);
	}

	public void movePossible(Pacman pacman, int[][] board, Graphics g) {
		possibleExit(board);
		if(scatter){
			if(goLeftGhost && (exitLeft || exitUp || exitDown)) {
				changeTargetTile();
				if(exitUp){
					distanceUp = distance(25, 28, x , y - 1 );
				}
				else {
					distanceUp = 9999;
				}
				if(exitLeft){
					distanceLeft = distance(25, 28, x - 1 , y);
				}
				else {
					distanceLeft = 9999;
				}
				if(exitDown){
					distanceDown = distance(25, 28, x , y + 1 );
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
				changeTargetTile();
				if(exitUp){
					distanceUp = distance(25, 28, x , y - 1 );
				}	
				else {
					distanceUp = 9999;
				}
				if(exitRight){
					distanceRight = distance(25, 28, x + 1 , y );
				}
				else {
					distanceRight = 9999;
				}
				if(exitDown){
					distanceDown = distance(25, 28, x , y + 1 );
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
				changeTargetTile();
				if(exitUp){
					distanceUp = distance(25, 28, x , y - 1 );
				}
				else {
					distanceUp = 9999;
				}
				if(exitLeft){
					distanceLeft = distance(25, 28, x - 1 , y );
				}
				else {
					distanceLeft = 9999;
				}
				if(exitRight){
					distanceRight = distance(25, 28, x + 1 , y );
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
				changeTargetTile();
				if(exitRight){
					distanceRight = distance(25, 28, x + 1, y );
				}
				else {
					distanceRight = 9999;
				}
				if(exitLeft){
					distanceLeft = distance(25, 28, x - 1, y );
				}
				else {
					distanceLeft = 9999;
				}
				if(exitDown){
					distanceDown = distance(25, 28, x, y + 1 );
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
				distanceRight = distance(25, 28, x + 1, y );
				distanceLeft = distance(25, 28, x - 1, y );
				distanceUp = distance(25, 28, x, y - 1 );
				distanceDown = distance(25, 28, x, y + 1 );
				
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
		else if(frightened){
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
		else {	
			if(goLeftGhost && (exitLeft || exitUp || exitDown)) {
				changeTargetTile();
				if(exitUp){
					distanceUp = distance(newXTile, newYTile, x , y - 1 );
				}
				else {
					distanceUp = 9999;
				}
				if(exitLeft){
					distanceLeft = distance(newXTile, newYTile, x - 1 , y);
				}
				else {
					distanceLeft = 9999;
				}
				if(exitDown){
					distanceDown = distance(newXTile, newYTile, x , y + 1 );
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
				changeTargetTile();
				if(exitUp){
					distanceUp = distance(newXTile, newYTile, x , y - 1 );
				}	
				else {
					distanceUp = 9999;
				}
				if(exitRight){
					distanceRight = distance(newXTile, newYTile, x + 1 , y );
				}
				else {
					distanceRight = 9999;
				}
				if(exitDown){
					distanceDown = distance(newXTile, newYTile, x , y + 1 );
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
				changeTargetTile();
				if(exitUp){
					distanceUp = distance(newXTile, newYTile, x , y - 1 );
				}
				else {
					distanceUp = 9999;
				}
				if(exitLeft){
					distanceLeft = distance(newXTile, newYTile, x - 1 , y );
				}
				else {
					distanceLeft = 9999;
				}
				if(exitRight){
					distanceRight = distance(newXTile, newYTile, x + 1 , y );
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
				changeTargetTile();
				if(exitRight){
					distanceRight = distance(newXTile, newYTile, x + 1, y );
				}
				else {
					distanceRight = 9999;
				}
				if(exitLeft){
					distanceLeft = distance(newXTile, newYTile, x - 1, y );
				}
				else {
					distanceLeft = 9999;
				}
				if(exitDown){
					distanceDown = distance(newXTile, newYTile, x, y + 1 );
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
				distanceRight = distance(newXTile, newYTile, x + 1, y );
				distanceLeft = distance(newXTile, newYTile, x - 1, y );
				distanceUp = distance(newXTile, newYTile, x, y - 1 );
				distanceDown = distance(newXTile, newYTile, x, y + 1 );
				
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
	public void changeTargetTile(){
		if(Game.goRight){
			tileX = Game.pacman.getX() + 2;
			tileY = Game.pacman.getY();
		}
		else if(Game.goLeft){
			tileX = Game.pacman.getX() - 2;
			tileY = Game.pacman.getY();
		}
		else if(Game.goDown){
			tileX = Game.pacman.getX();
			tileY = Game.pacman.getY() + 2;
		}
		else if(Game.goUp){
			tileX = Game.pacman.getX() - 2;
			tileY = Game.pacman.getY() - 2;
		}

		if(Game.pacman.getX() >= Game.blinky.getX() && Game.pacman.getY() >= Game.blinky.getY()) {
			newXTile = Game.blinky.getX() + 2*Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() + 2*Math.abs(tileY - Game.blinky.getY());  
		}
		else if(Game.pacman.getX() >= Game.blinky.getX() && Game.pacman.getY() < Game.blinky.getY()) {
			newXTile = Game.blinky.getX() + 2*Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() - 2*Math.abs(tileY - Game.blinky.getY());  
		}
		else if(Game.pacman.getX() < Game.blinky.getX() && Game.pacman.getY() >= Game.blinky.getY()) {
			newXTile = Game.blinky.getX() - 2*Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() + 2*Math.abs(tileY - Game.blinky.getY());  
		}
		else if(Game.pacman.getX() < Game.blinky.getX() && Game.pacman.getY() < Game.blinky.getY()) {
			newXTile = Game.blinky.getX() - 2*Math.abs(tileX - Game.blinky.getX());
			newYTile = Game.blinky.getY() - 2*Math.abs(tileY - Game.blinky.getY());  
		}
	}
	
}
