package interfaceFramework;

public class Blinky extends Ghost{

	boolean exitLeft = false;
	boolean exitRight = false;
	boolean exitUp = false;
	boolean exitDown = false;
	
	double distanceUp = 9999;
	double distanceLeft = 9999;
	double distanceDown = 9999;
	double distanceRight = 9999;
	
	public Blinky(int x, int y) {
		super(x, y);
	}

	public void movePossible(Pacman pacman, int[][] board, boolean[] ghostDirection) {
		goLeft = ghostDirection[0];
		goRight = ghostDirection[1];
		goUp = ghostDirection[2];
		goDown = ghostDirection[3];

		possibleExit(board);
//		if(goLeft){
//			move(-1, 0);
//		}
//		if(goRight){
//			move(1, 0);
//		}
//		if(goDown){
//			move(0, 1);
//		}
//		if(goUp){
//			move(0, -1);
//		}
		if(goLeft && (exitLeft || exitUp || exitDown)) {
			if(exitUp){
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			}
			if(exitLeft){
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			}
			if(exitDown){
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			}
		
			if(distanceUp <= Math.min(distanceLeft, distanceDown)) {
				move(board, moveUp());
				goLeft = false;
			}
			else if(distanceLeft <= Math.min(distanceUp, distanceDown)) {
				move(board, moveLeft());
			}
			else if(distanceDown <= Math.min(distanceUp, distanceLeft)) {
				move(board, moveDown());
				goLeft = false;
			}
			else if(stopped){
				move(board, moveRight());
				goLeft = false;
			}
//			System.out.println(goLeft + " " + goDown + " " +goUp + " "+ goRight);
//			System.out.println(exitLeft + " " + exitDown + " " +exitUp + " "+ exitRight+ " "+stopped);
//			System.out.println(distanceLeft + " " + distanceDown + " " + distanceUp + " " + distanceRight);
		}
		
		if(goRight && (exitRight || exitUp || exitDown)) {
			if(exitUp){
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			}
			if(exitRight){
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			}
			if(exitDown){
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			}
		
			if(distanceUp <= Math.min(distanceRight, distanceDown)) {
				move(board, moveUp());
				goRight = false;
}
			else if(distanceDown <= Math.min(distanceUp, distanceRight)) {
				move(board, moveDown());
				goRight = false;
}
			else if(distanceRight <= Math.min(distanceUp, distanceDown)) {
				move(board, moveRight());
}
			else if(stopped){
				move(board, moveLeft());
				goRight = false;
			}
		}
		
		if(goUp && (exitLeft || exitRight || exitUp)) {
			System.out.println("hi");
			if(exitUp){
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			}
			if(exitLeft){
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			}
			if(exitRight){
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
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
			else if(stopped){
				move(board, moveDown());
			}
		}
		
		if(goDown && (exitLeft || exitUp || exitRight)) {
			if(exitRight){
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			}
			if(exitLeft){
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			}
			if(exitDown){
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
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
			else if(stopped){
				move(board, moveUp());
			}
		}
	}
	
	public void possibleExit(int[][] board) {
		if(board[x - 1][y] != 1 ){
			exitLeft = true;
		}
		if(board[x - 1][y] == 1 ){
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
		if(board[x][y + 1] == 1){
			exitDown = false;
		}
	}
}
