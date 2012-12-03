package interfaceFramework;

/**
 * The UserControls class checks if it's possible for pacman to move in a specific direction
 */

public class UserControls {
/** 
 * The checkMove() method checks if it is possible for pacman to move in 
 * a specific direction by ensuring that there is no wall in his upcoming
 * position. If a wall exists the method returns a false boolean to prevent pacman
 * from changing directions.
 * 
 * @param pacman used to find his coordinates
 * @param board used to check upcoming coordinates for walls
 * @param tunnel boolean that indicates if the specified coordinate is a tunnel
 * @return a boolean specifying whether Pac-Man can move in a specfic direction or not
 */

	public static boolean checkMove(Pacman pacman, int board[][], boolean tunnel){
		boolean result = true;
		int x = (int) pacman.getX();
		int y = (int) pacman.getY();
		
		checkCollision(pacman, Game.blinky);
		checkCollision(pacman, Game.pinky);
		checkCollision(pacman, Game.inky);
		checkCollision(pacman, Game.clyde);

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
	
	
	/** 
	 * The checkMoveForStored() method checks if it is possible for pacman to move in 
	 * a direction that has previously been inputted, by ensuring that there is no wall 
	 * in his upcoming position. If a wall exists the method returns a false 
	 * boolean to prevent pacman from changing directions.
	 * 
	 * @param pacman used to find his coordinates
	 * @param board used to check upcoming coordinates for walls
	 * @param tunnel boolean that indicates if the specified coordinate is a tunnel
	 * @return a boolean specifying whether Pac-Man can move in a specfic direction or not
	 */
	
	public static boolean checkMoveForStored(Pacman pacman, int board[][], boolean tunnel){
		boolean result = true;
		int x = (int) pacman.getX();
		int y = (int) pacman.getY();
		
		checkCollision(pacman, Game.blinky);
		checkCollision(pacman, Game.pinky);
		checkCollision(pacman, Game.inky);
		checkCollision(pacman, Game.clyde);

		if (board[x - 1][y] == 1 && Game.storedLeft == true) {
			result = false;	
		}
		
		else if (board[x - 1][y] == 4 && Game.storedLeft == true){
			pacman.moveTo(27,14);
		}
		
		else if ((board[x + 1][y] == 1  || board[x + 1][y] == 5) && Game.storedRight == true) {
			result = false;	
		}
	
		else if (board[x + 1][y] == 4 && Game.storedRight == true) {
			pacman.moveTo(0,14);
		}
		
		else if (board[x][y - 1] == 1 && Game.storedUp == true) {
			result = false;
		}
		
		else if ((board[x][y + 1] == 1 || board[x][y + 1] == 5) && Game.storedDown == true) {
			result = false;
		}
		
		return result;
	}
	
	public static void checkCollision(Pacman pacman, Ghost ghost){
		if(ghost.goLeftGhost && Game.goRight && (pacman.getX() + 1 == ghost.getX() && pacman.getY() == ghost.getY())){
			if(!Ghost.frightened){
				Game.inGame = false;
			}
			else{
				Game.setCollided(Game.getCollided() + 1);
				ghost.setX(12);
				ghost.setY(14);			
			}
		}
		else if(ghost.goRightGhost && Game.goLeft && (pacman.getX() - 1 == ghost.getX() && pacman.getY() == ghost.getY())){
			if(!Ghost.frightened){
				Game.inGame = false;
			}
			else{
				Game.setCollided(Game.getCollided() + 1);
				ghost.setX(12);
				ghost.setY(14);			
			}
		}
		else if(ghost.goUpGhost && Game.goDown && (pacman.getX() == ghost.getX() && pacman.getY() + 1 == ghost.getY())){
			if(!Ghost.frightened){
				Game.inGame = false;
			}
			else{
				Game.setCollided(Game.getCollided() + 1);
				ghost.setX(12);
				ghost.setY(14);			
			}
		}
		else if(ghost.goDownGhost && Game.goUp && (pacman.getX() == ghost.getX() && pacman.getY() - 1 == ghost.getY())){
			if(!Ghost.frightened){
				Game.inGame = false;
			}
			else{
				Game.setCollided(Game.getCollided() + 1);
				ghost.setX(12);
				ghost.setY(14);			
			}
		}
		else if(pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()){
			if(!Ghost.frightened){
				Game.inGame = false;
			}
			else{
				Game.setCollided(Game.getCollided() + 1);
				ghost.setX(12);
				ghost.setY(14);			
			}
		}
	}
	
	/**
	 * The moveLeft() method checks if it is possible for pacman to move left
	 * by ensuring that there is no wall in his upcoming
	 * position. If a wall exists the method returns a false boolean to prevent pacman
	 * from changing directions.
	 * 
	 * @param pacman used to find his coordinates
	 * @param board used to check upcoming coordinates for wall
	 * @return a boolean specifying whether Pac-Man can move Left or not
	 */
	
	public static boolean moveLeft(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();

		if (board[x - 1][y] == 1 || board[x - 1][y] == 5) {
			result = false;
		}
		return result;
	}

	/**
	 * The moveRight() method checks if it is possible for pacman to move Right
	 * by ensuring that there is no wall in his upcoming
	 * position. If a wall exists the method returns a false boolean to prevent pacman
	 * from changing directions.
	 * 
	 * @param pacman used to find his coordinates
	 * @param board used to check upcoming coordinates for walls
	 * @return a boolean specifying whether Pac-Man can move Right or not
	 */
	public static boolean moveRight(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();

		if (board[x + 1][y] == 1 || board[x + 1][y] == 5) {
			result = false;
		}
		return result;
	}

	/**
	 * The moveUp() method checks if it is possible for pacman to move up
	 * by ensuring that there is no wall in his upcoming
	 * position. If a wall exists the method returns a false boolean to prevent pacman
	 * from changing directions.
	 * 
	 * @param pacman used to find his coordinates
	 * @param board used to check upcoming coordinates for walls
	 * @return a boolean specifying whether Pac-Man can move up or not
	 */
	public static boolean moveUp(Pacman pacman, int board[][]) {
		boolean result = true;
		int x = (int)pacman.getX();
		int y = (int)pacman.getY();
		
		if (board[x][y - 1] == 1) {
			result = false;
		}
		return result;
	}

	/**
	 * The moveDown() method checks if it is possible for pacman to move down
	 * by ensuring that there is no wall in his upcoming
	 * position. If a wall exists the method returns a false boolean to prevent pacman
	 * from changing directions.
	 * 
	 * @param pacman used to find his coordinates
	 * @param board used to check upcoming coordinates for walls
	 * @return a boolean specifying whether Pac-Man can move down or not
	 */
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
