package interfaceFramework;

public class UserControls {
/**
 * The checkMove() method checks if it is possible for pacman to move in 
 * a specific direction by ensuring that there is no wall in his upcoming
 * position. If a wall exists the method returns a false boolean to prevent pacman
 * from changing directions.
 * @param pacman used to find his coordinates
 * @param board used to check upcoming coordinates for walls
 * @param tunnel boolean that indicates if the specified coordinate is a tunnel
 * @return
 */


	public static boolean checkMove(Pacman pacman, int board[][], boolean tunnel){
		boolean result = true;
		int x = pacman.getX();
		int y = pacman.getY();
		
		if (board[x - 1][y] == 1 && Game.goLeft == true) {
			result = false;
		}
		else if (board[x - 1][y] == 4 && Game.goLeft == true){
			pacman.moveTo(27,14);
		}
		
		else if (board[x + 1][y] == 1 && Game.goRight == true) {
			result = false;
		}
		
		else if (board[x + 1][y] == 4 && Game.goRight == true) {
			pacman.moveTo(0,14);
		}
		
		else if (board[x][y - 1] == 1 && Game.goUp == true) {
			result = false;
		}
		
		else if (board[x][y + 1] == 1 && Game.goDown == true) {
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
