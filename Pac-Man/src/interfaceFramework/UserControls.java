package interfaceFramework;

import interfaceFramework.Pacman;

public class UserControls {

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
