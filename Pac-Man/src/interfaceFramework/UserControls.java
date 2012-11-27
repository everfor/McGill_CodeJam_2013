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
		int x = (int) pacman.getX();
		int y = (int) pacman.getY();
		
		if (board[x - 1][y] == 1 && Game.goLeft == true) {
			result = false;
			Game.stop = false;
		}
		else if (board[x - 1][y] == 4 && Game.goLeft == true){
			pacman.moveTo(27,14);
		}
		
		else if ((board[x + 1][y] == 1  || board[x + 1][y] == 5) && Game.goRight == true) {
			result = false;
			Game.stop = false;
		}
		
		else if (board[x + 1][y] == 4 && Game.goRight == true) {
			pacman.moveTo(0,14);
		}
		
		else if (board[x][y - 1] == 1 && Game.goUp == true) {
			result = false;
			Game.stop = false;
		}
		
		else if ((board[x][y + 1] == 1 || board[x][y + 1] == 5) && Game.goDown == true) {
			result = false;
			Game.stop = false;
		}
		return result;
	}
	
	// to check if left move is possible
	public static boolean moveLeft(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();

		if (board[x - 1][y] == 1 || board[x - 1][y] == 5) {
			result = false;
		}
		return result;
	}

	// to check if right move is possible
	public static boolean moveRight(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();

		if (board[x + 1][y] == 1 || board[x + 1][y] == 5) {
			result = false;
		}
		return result;
	}

	// to check if up move is possible
	public static boolean moveUp(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();
		
		if (board[x][y - 1] == 1) {
			result = false;
		}
		return result;
	}

	// to check if down move is possible
	public static boolean moveDown(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();

		if (board[x][y + 1] == 1 || board[x][y + 1] == 5) {
			result = false;
		}
		return result;
	}

}
