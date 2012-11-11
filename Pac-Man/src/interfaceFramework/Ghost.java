package interfaceFramework;

import java.util.Random;

public class Ghost {
	int x, y;

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
	}
/**
 * This is the method that controls the ghosts movement. It is set to random,
 * however before a ghosts movements it checks if the upcoming coordinates 
 * are occupied with a wall.
 * @param ghost used to find a particular ghost's coordinates
 * @param board used to check upcoming coordinates for walls
 */
	public void move(Ghost ghost, int board[][]) {

		Random generator = new Random();
		int randomMove = generator.nextInt(4);

		if (randomMove == 0 && moveRight(ghost, board)) {
			x = x + 1;
		}
		else if (randomMove == 1 && moveLeft(ghost, board)) {
			x = x - 1;
		} 
		else if (randomMove == 2 && moveUp(ghost, board)) {
			y = y - 1;
		} 
		else if (randomMove == 3 && moveDown(ghost, board)) {
			y = y + 1;
		}
		else if(board[ghost.getX()-1][ghost.getY()] == 4){
			x += 37;
		}
		else if(board[ghost.getX()+1][ghost.getY()] == 4){
			x -= 37;
		}
	}
//getX() and getY() get a ghost's x and y coordinates at a specified time.
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// to check if left move is possible
	public static boolean moveLeft(Ghost ghost, int map[][]) {
		boolean result = true;
		int x = ghost.getX();
		int y = ghost.getY();
		
		if(x == 1 || (x == 27 && y<=12) || (x == 4 && (y == 14 || y==16))){
			result = false;
		}
		return result;
	}

	// to check if right move is possible
	public static boolean moveRight(Ghost ghost, int map[][]) {
		boolean result = true;
		int x = ghost.getX();
		int y = ghost.getY();

		if (x == 38 || (x == 19 && y >= 5 && y <= 12) || (x == 35 && (y == 14 || y==16))) {
			result = false;
		}
		return result;
	}

	// to check if up move is possible
	public static boolean moveUp(Ghost ghost, int map[][]) {
		boolean result = true;
		int x = ghost.getX();
		int y = ghost.getY();

		if (y == 1 || (x >= 20 && x <= 26 && y == 13) || (x<=3 && (y == 15 || y ==17)) || 
			(x>=36 && (y == 15 || y ==17))) {
			result = false;
		}
		return result;
	}

	// to check if down move is possible
	public static boolean moveDown(Ghost ghost, int map[][]) {
		boolean result = true;
		int x = ghost.getX();
		int y = ghost.getY();

		if (y == 28 || (x >= 20 && x < 26 && y == 4) || (x<=3 && (y == 13 || y ==15)) || 
				(x>=36 && (y == 13 || y ==15))) {
			result = false;
		}
		return result;
	}

}
