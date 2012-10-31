package interfaceFramework;

import interfaceFramework.Pacman;

public class UserControls {
/**
 * The checkMove() method checks if it is possible for pacman to move in 
 * a specific direction by ensuring that there is no wall in his upcoming
 * position. If a wall exists the method returns a false boolean to prevent pacman
 * from changing directions.
 * @param pacman used to find his coordinates
 * @param map used to check upcoming coordinates for walls
 * @param tunnel boolean that indicates if the specified coordinate is a tunnel
 * @return
 */
	public static boolean checkMove(Pacman pacman, int map[][], boolean tunnel){
		boolean result = true;
		int x = pacman.getX();
		int y = pacman.getY();
		
		if (map[x - 1][y] == 1 && Map.goLeft == true) {
			result = false;
		}
		else if (map[x - 1][y] == 4 && Map.goLeft == true){
			pacman.moveTo(38,15);
		}
		
		else if (map[x + 1][y] == 1 && Map.goRight == true) {
			result = false;
		}
		
		else if (map[x + 1][y] == 4 && Map.goRight == true) {
			pacman.moveTo(1,15);
		}
		
		else if (map[x][y - 1] == 1 && Map.goUp == true) {
			result = false;
		}
		
		else if (map[x][y + 1] == 1 && Map.goDown == true) {
			result = false;
		}
		return result;
	}

	// to check if left move is possible
	public static boolean moveLeft(Pacman pacman, int map[][]) {
		boolean result = true;
		int x = pacman.getX();
		int y = pacman.getY();

		if (map[x - 1][y] == 1) {
			result = false;
		}
		return result;
	}

	// to check if right move is possible
	public static boolean moveRight(Pacman pacman, int map[][]) {
		boolean result = true;
		int x = pacman.getX();
		int y = pacman.getY();

		if (map[x + 1][y] == 1) {
			result = false;
		}
		return result;
	}

	// to check if up move is possible
	public static boolean moveUp(Pacman pacman, int map[][]) {
		boolean result = true;
		int x = pacman.getX();
		int y = pacman.getY();
		
		if (map[x][y - 1] == 1) {
			result = false;
		}
		return result;
	}

	// to check if down move is possible
	public static boolean moveDown(Pacman pacman, int map[][]) {
		boolean result = true;
		int x = pacman.getX();
		int y = pacman.getY();

		if (map[x][y + 1] == 1) {
			result = false;
		}
		return result;
	}

}
