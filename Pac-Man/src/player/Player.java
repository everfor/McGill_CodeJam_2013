package player;

public class Player {
	int x, y;
	
	public Player(){
		x = 10;
		y = 10;
	}
	
	public void move(int dx, int dy){
		x = x + dx;
		y = y + dy;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
