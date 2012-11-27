package playerManipulation;

import frontendDatabase.PlayerFrontend;
import frontendDatabase.StatisticsFrontend;

public class Player {
	private static String myUsername, myPassword, mySecurityQuestion,
			mySecurityAnswer;
	private static int myLevelAchieved;
	private static int[] topHighScores;

	public Player(String username) {
		myUsername = username;
		myPassword = PlayerFrontend.getInfo(username, "password");
		mySecurityQuestion = PlayerFrontend.getInfo(username,
				"securityQuestion");
		mySecurityAnswer = PlayerFrontend.getInfo(username, "securityAnswer");
		myLevelAchieved = Integer.parseInt(PlayerFrontend.getInfo(username,"levelAchieved"));
		topHighScores=StatisticsFrontend.getHighScores(myUsername);
	}

	
	public static String getUsername() {
		return Player.myUsername;
	}

	public static String getSecurityQuestion() {
		return Player.mySecurityQuestion;
	}

	public static String getSecurityAnswer() {
		return Player.mySecurityAnswer;
	}

	public static int getLevelAchieved() {
		return Player.myLevelAchieved;
	}

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

	public static int[] getHighScores() {
		return Player.topHighScores;
	}

	public void setNewHighScore(int newScore) {
		StatisticsFrontend.setHighScores(this.getUsername(), newScore);
		// updating the highscores array in this player object
		Player.topHighScores = StatisticsFrontend.getHighScores(getUsername());
	}

	public void displayHighScores() {
		for (int i = 0; i < topHighScores.length; i++) {
			System.out.println(i + ": " + topHighScores[i]);
		}
	}
}
