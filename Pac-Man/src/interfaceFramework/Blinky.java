package interfaceFramework;

public class Blinky extends Ghost{

	boolean exitLeft = false;
	boolean exitRight = false;
	boolean exitUp = false;
	boolean exitDown = false;
	
	public Blinky(int x, int y) {
		super(x, y);
	}

	public void movePossible(Pacman pacman, int[][] board, boolean[] ghostDirection) {
		goLeft = ghostDirection[0];
		goRight = ghostDirection[1];
		goUp = ghostDirection[2];
		goDown = ghostDirection[3];
				
		if(goLeft && (exitLeft || exitUp || exitDown)) {
			double distanceUp = distance(pacman, x, y - 1);
			double distanceLeft = distance(pacman, x - 1, y);
			double distanceDown = distance(pacman, x, y + 1);
		
			if(distanceUp < Math.min(distanceLeft, distanceDown)) {
				moveUp();
			}
			else if(distanceLeft < Math.min(distanceUp, distanceDown)) {
				moveLeft();
			}
			else if(distanceDown < Math.min(distanceUp, distanceLeft)) {
				moveDown();
			}
		}
	}
	
	public double distance(Pacman pacman, int x, int y) {
		double distance = 0.0;
		distance = Math.pow(pacman.getX() - x, 2) + Math.pow(pacman.getY() - y, 2);
		return distance;
	}
	
	public void possibleExit(int[][] board) {
		if(board[x - 1][y] == 0){
			exitLeft = true;
		}
		if(board[x + 1][y] == 0){
			exitRight = true;
		}
		if(board[x][y - 1] == 0){
			exitUp = true;
		}
		if(board[x][y + 1] == 0){
			exitDown = true;
		}
	}
}
