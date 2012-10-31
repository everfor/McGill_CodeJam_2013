package interfaceFramework;

import java.util.Random;

public class Ghost {
	int x, y;

	public Ghost(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(Ghost ghost, int map[][]) {

		Random generator = new Random();
		int randomMove = generator.nextInt(4);

		if (randomMove == 0 && moveRight(ghost, map)) {
			x = x + 1;
		}
		else if (randomMove == 1 && moveLeft(ghost, map)) {
			x = x - 1;
		} 
		else if (randomMove == 2 && moveUp(ghost, map)) {
			y = y - 1;
		} 
		else if (randomMove == 3 && moveDown(ghost, map)) {
			y = y + 1;
		}
		else if(map[ghost.getX()-1][ghost.getY()] == 4){
			x += 37;
		}
		else if(map[ghost.getX()+1][ghost.getY()] == 4){
			x -= 37;
		}
	}

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
