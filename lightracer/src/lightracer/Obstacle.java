package lightracer;
// This class will represent a single obstacle on the game board

public class Obstacle {
	private Owner type;

	//The different types of obstacles we can have
	public enum Owner {
		BOARD, P1, P2
	};

	//changes the type of obstacle
	public void setType(Owner t) {
		this.type = t;
	}
	
	//gets the type of the obstacle
	public Owner getType() {
		return type;
	}

	public Obstacle(char obstacleString) {
		switch (obstacleString) {
		case '0':
			setType(Owner.BOARD);
		break;
		case '1':
			setType(Owner.P1);
		break;
		case '2':
			setType(Owner.P2);
		break;
		}
	}

	//checking the type of the obstacle
	public boolean isType(Owner type) {
		if (this.type == type){
			return true;
		}
		return false;
	}


	//checking if an obstacle is blocked so a racer can not pass
	public boolean isBlocked() {
		return isType(Owner.BOARD) || isType(Owner.P1) || isType(Owner.P2);
	}
}