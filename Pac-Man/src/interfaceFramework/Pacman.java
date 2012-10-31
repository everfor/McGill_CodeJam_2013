package interfaceFramework;

public class Pacman {
	int x, y;

	public Pacman() {
		x = 1;
		y = 1;
	}
	
	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void move(boolean tunnel) {
		if(Map.goLeft){
			if(tunnel){
				x += 37;
			}
			else{
				x -= 1;
			}
		}
		
		else if(Map.goRight){
			if(tunnel){
				x -= 37;
			}
			else{
				x += 1;
			}
		}
		
		else if(Map.goUp){
			y -= 1;
		}
		
		else if(Map.goDown){
			y += 1;
		}
	}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
