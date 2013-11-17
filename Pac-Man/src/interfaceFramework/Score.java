package interfaceFramework;

/**
 * The class deals with the aspects of the Score
 * 
 */
public class Score {
	static int dots = 240;
	static int dotsLeft;
	static int energizers = 4;
	static int energizersLeft;
	private static int score;
	static int levelScore;
	static int fruitConsumed;
	static int fruit;

	/**
	 * This method gets the Score based on a formula calculation using the countDots and
	 * countEnergizers methods
	 * 
	 * @param board
	 *            takes a two dimensional array of the map/board itself
	 * 
	 * @return score returns the score based on dots and energizers consumed
	 */
	public static int getScore(int[][] board) {
		score = 0;
		dotsLeft = countDots(board);
		energizersLeft = countEnergizers(board);
		fruit = countFruit();

		score = levelScore + 10 * (dots - dotsLeft) + 50 * (energizers - energizersLeft) + 100 * fruit + 400
				* Game.getCollided();
		return score;
	}

	/**
	 * This method returns the highscore achieved
	 */
	public static int getScore() {
		return score;
	}

	public static void setLevelScore(int newScore) {
		levelScore = newScore;
	}

	/**
	 * This method counts the amount of dots existing on the board
	 * 
	 * @param board
	 *            takes a two dimensional array of the map/board itself
	 * 
	 * @return dotsLeft returns the number of dots left
	 */

	public static int countDots(int[][] board) {
		int dotsLeft = 0;
		for (int y = 0; y < Map.height; y++) {
			for (int x = 0; x < Map.width; x++) {
				if(board[x][y] == 2) {
					dotsLeft++;
				}
			}
		}
		return dotsLeft;
	}

	/**
	 * This method counts the amount of energizers existing on the board
	 * 
	 * @param board
	 *            takes a two dimensional array of the map/board itself
	 * 
	 * @return dotsLeft returns the number of energizers left
	 */
	public static int countEnergizers(int[][] board) {
		int energizersLeft = 0;
		for (int y = 0; y < Map.height; y++) {
			for (int x = 0; x < Map.width; x++) {
				if(board[x][y] == 3) {
					energizersLeft++;
				}
			}
		}
		return energizersLeft;
	}

	/**
	 * This method counts the amount of fruit eaten by Pac-Man
	 * 
	 * @return fruitconsumed returns the number of fruits consumed
	 */
	public static int countFruit() {
		if(Game.fruitEaten == false) {
			fruitConsumed = 0;
		}
		else {
			fruitConsumed = 1;
		}
		return fruitConsumed;
	}

	public static void setScore(int score) {
		Score.score = score;
	}
}