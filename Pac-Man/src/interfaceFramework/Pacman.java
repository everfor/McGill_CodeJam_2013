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
//move
	/**
	 * Moves pacman one step in a direction that has been specified by the 
	 * player. The specified coordinates are checked to ensure that it is not
	 * occupied by a wall using go**** methods. If no direction has been inputed by the player, 
	 * pacman will continue to move in the previously inputed direction. 
	 * @param tunnel boolean that indicates if the specified coordinate is a tunnel
	 */
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
//getX() and getY() get pacmans x and y coordinates at a specified time.
	
		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
