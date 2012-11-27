package interfaceFramework;

public class Score {
	static int dots = 240;
	static int dotsLeft;
	static int energizers = 4;
	static int energizersLeft;
	static int score;
	
	public static int getScore(int[][] board){
		score = 0;
		dotsLeft = countDots(board);
		energizersLeft = countEnergizers(board);
		
		score = 10*(dots - dotsLeft) + 50*(energizers - energizersLeft);
		return score;
	}
	
	public static int countDots(int[][] board){
		int dotsLeft = 0;
		for(int y=0; y<Map.height; y++){
			for(int x=0; x<Map.width; x++){
				if(board[x][y] == 2){
					dotsLeft++;
				}
			}
		}
		return dotsLeft;
	}
	
	public static int countEnergizers(int[][] board){
		int energizersLeft = 0;
		for(int y=0; y<Map.height; y++){
			for(int x=0; x<Map.width; x++){
				if(board[x][y] == 3){
					energizersLeft++;
				}
			}
		}
		return energizersLeft;
	}
	
	public static int getHighScore() {
		return score; 
	}
}
