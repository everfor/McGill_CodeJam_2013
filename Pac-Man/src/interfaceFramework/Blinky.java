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

	public void movePossible(Pacman pacman, int[][] board) {

		possibleExit(board);

		System.out.println("Go -    LDUR: " + goLeftGhost + " " + goDownGhost + " " +goUpGhost + " "+ goRightGhost);
		System.out.println("Exit -  LDUR: " + exitLeft + " " + exitDown + " " +exitUp + " "+ exitRight);
		System.out.println("Dist -  LDUR: " + distanceLeft + " " + distanceDown + " " + distanceUp + " " + distanceRight);
		
		if(goLeftGhost && (exitLeft || exitUp || exitDown)) {
//				((exitLeft && exitUp) || (exitLeft && exitDown) || (exitUp && exitDown))) {
			
			if(exitUp){
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitLeft){
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitDown){
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			}
			else {
				distanceDown = 9999;
			}
		
			if(distanceUp <= Math.min(distanceLeft, distanceDown)) {
				System.out.println("1");
//				goUp = true;
				goLeftGhost = false;
				move(board, moveUp());
				
			}
			else if(distanceLeft <= Math.min(distanceUp, distanceDown)) {
				System.out.println("2");
				move(board, moveLeft());
			}
			else if(distanceDown <= Math.min(distanceUp, distanceLeft)) {
				System.out.println("3");
				goLeftGhost = false;
//				goDown = true;
				move(board, moveDown());
			}
			else {
				goLeftGhost = false;
//				goRight = true;
				System.out.println("4");
				move(board, moveRight());
			}
		
		}
		
		if(goRightGhost && (exitRight || exitUp || exitDown)) {
			System.out.println("right");
			if(exitUp){
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			}	
			else {
				distanceUp = 9999;
			}
			if(exitRight){
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
			if(exitDown){
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			}
			else {
				distanceDown = 9999;
			}
		
			if(distanceUp <= Math.min(distanceRight, distanceDown)) {
				System.out.println("a");
				goRightGhost = false;
				move(board, moveUp());
			}
			else if(distanceDown <= Math.min(distanceUp, distanceRight)) {
				System.out.println("b");
				goRightGhost = false;
				move(board, moveDown());
			}
			else if(distanceRight <= Math.min(distanceUp, distanceDown)) {
				System.out.println("c");
				move(board, moveRight());
			}
			else {
				System.out.println("d");
				goRightGhost = false;
				move(board, moveLeft());
			}
		}
		
		if(goUpGhost && (exitLeft || exitRight || exitUp)) {
			if(exitUp){
				distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			}
			else {
				distanceUp = 9999;
			}
			if(exitLeft){
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitRight){
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
		
			if(distanceUp <= Math.min(distanceLeft, distanceRight)) {
				move(board, moveUp());
			}
			else if(distanceLeft <= Math.min(distanceUp, distanceRight)) {
				goUpGhost = false;
				move(board, moveLeft());
			}
			else if(distanceRight <= Math.min(distanceUp, distanceLeft)) {
				goUpGhost = false;
				move(board, moveRight());
			}
			else {
				goUpGhost = false;
				move(board, moveDown());
			}
		}
		
		if(goDownGhost && (exitLeft || exitUp || exitRight)) {
			System.out.println("down");
			if(exitRight){
				distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			}
			else {
				distanceRight = 9999;
			}
			if(exitLeft){
				distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			}
			else {
				distanceLeft = 9999;
			}
			if(exitDown){
				distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			}
			else {
				distanceDown = 9999;
			}
		
			if(distanceLeft <= Math.min(distanceRight, distanceDown)) {
				goDownGhost = false;
				move(board, moveLeft());
			}
			else if(distanceDown <= Math.min(distanceRight, distanceLeft)) {
				move(board, moveDown());
			}
			else if(distanceRight <= Math.min(distanceLeft, distanceDown)) {
				goDownGhost = false;
				move(board, moveRight());
			}
			else {
				goDownGhost = false;
				move(board, moveUp());
			}
		}
		if(!goDownGhost && !goUpGhost && !goLeftGhost && !goRightGhost) {
			distanceRight = distance(pacman.getX(), pacman.getY(), x + 1, y);
			distanceLeft = distance(pacman.getX(), pacman.getY(), x - 1, y);
			distanceUp = distance(pacman.getX(), pacman.getY(), x, y - 1);
			distanceDown = distance(pacman.getX(), pacman.getY(), x, y + 1);
			
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
		
	
	
	public void possibleExit(int[][] board) {
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
