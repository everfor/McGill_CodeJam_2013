package playerManipulation;

import frontendDatabase.PlayerFrontend;
import frontendDatabase.StatisticsFrontend;

/**
 * This class creates and handles all information related to the player object.
 * A player's information is stored here during the duration of him/her logged
 * in. The class also handles getting this information as well as clearing it
 * for log out. Player information includes: username, password, security
 * answer, security question, maximum level achieved and a players high scores.
 */
public class Player {
	private static String myUsername, myPassword, mySecurityQuestion, mySecurityAnswer;
	private static int myLevelAchieved;
	private static int[] topHighScores;

	/**
	 * This Constructor is used to create a Player object when a player logs in,
	 * in order to manipulate or obtain their information prior to writing to
	 * the database
	 * 
	 * @param username
	 *            the players username
	 */
	public Player(String username) {
		myUsername = username;
		myPassword = PlayerFrontend.getInfo(username, "password");
		mySecurityQuestion = PlayerFrontend.getInfo(username, "securityQuestion");
		mySecurityAnswer = PlayerFrontend.getInfo(username, "securityAnswer");
		myLevelAchieved = Integer.parseInt(PlayerFrontend.getInfo(username, "levelAchieved"));
		topHighScores = StatisticsFrontend.getHighScores(myUsername);
	}

	/**
	 * Gets the current player's username
	 * 
	 * @return current player's username
	 */
	public static String getUsername() {
		return Player.myUsername;
	}

	/**
	 * Gets the current player's Security Question
	 * 
	 * @return current player's Security Question
	 */
	public static String getSecurityQuestion() {
		return Player.mySecurityQuestion;
	}

	/**
	 * Gets the current player's Security Answer
	 * 
	 * @return current player's Security Answer
	 */
	public static String getSecurityAnswer() {
		return Player.mySecurityAnswer;
	}

	/**
	 * Gets the maximum level achieved by the player
	 * 
	 * @return current player's maximum level achieved
	 */
	public static int getLevelAchieved() {
		return Player.myLevelAchieved;
	}

	/**
	 * Sets player object to empty strings in order to log the Player out.
	 * 
	 * @return returns true if clears player data correctly, false otherwise
	 */
	public static boolean clearPlayer() {
		boolean cleared = false;
		myUsername = "";
		myPassword = "";
		mySecurityQuestion = "";
		mySecurityAnswer = "";
		myLevelAchieved = 0;
		if (myUsername == "" && myPassword == "" && mySecurityQuestion == ""
				&& mySecurityAnswer == "" && myLevelAchieved == 0) {
			cleared = true;
		}
		return cleared;
	}

	/**
	 * Gets the the current player's highest 10 scores
	 * 
	 * @return an array with the players highest 10 scores
	 */
	public static int[] getHighScores() {
		return Player.topHighScores;
	}

	/**
	 * Given a player's new score it attempts to add it to his/her top 10 high
	 * scores. It will only be added if it is at least higher that the player's
	 * current 10th highest score
	 * 
	 * @param newScore
	 *            the score that was obtained by the player
	 */
	public void setNewHighScore(int newScore) {
		StatisticsFrontend.setHighScores(this.getUsername(), newScore);
		// updating the highscores array in this player object
		Player.topHighScores = StatisticsFrontend.getHighScores(getUsername());
	}

}
