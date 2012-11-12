package playerManipulation;
import FrontendDatabase.playerFrontend;
public class Player {
private String myUsername, myPassword, mySecurityQuestion, mySecurityAnswer;
private int myLevelAchieved;

public Player(String username){
	myUsername = username;
	myPassword = playerFrontend.getInfo(username,"password");
	mySecurityQuestion= playerFrontend.getInfo(username,"securityQuestion");
	mySecurityAnswer= playerFrontend.getInfo(username,"securityAnswer");
	myLevelAchieved= Integer.parseInt(playerFrontend.getInfo(username,"levelAchieved"));
}
public String getUsername(){
	return this.myUsername;
}
public String getSecurityQuestion(){
	return this.mySecurityQuestion;
}

}
