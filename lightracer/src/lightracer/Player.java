package lightracer;

public class Player {
	private String userID;
	private String password;
	//private Record record;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	/*public Record getRecord() {
		return record;
	}
	*/
	/*
	public void setRecord(Record record) {
		this.record = record;
	}
	*/
	// implement updateRecord() method
	// checks recordlist for PvP record
	
	public Player(String userID, String password) {
	this.userID = userID;
	this.password = password;

	}
}
