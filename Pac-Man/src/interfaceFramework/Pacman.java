package interfaceFramework;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Pacman {
	double x, y;
	Image image1, image2, image3, image4;
	File path;
	
	public Pacman() {
		x = 1;
		y = 1;
	
		path = new File("").getAbsoluteFile();
		image1 = new ImageIcon(path + "\\resources\\pacman1.gif").getImage();
		image2 = new ImageIcon(path + "\\resources\\pacman2.gif").getImage();
		image3 = new ImageIcon(path + "\\resources\\pacman3.gif").getImage();
		image4 = new ImageIcon(path + "\\resources\\pacman4.gif").getImage();

	}
	
	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void move(int dx, int dy){
		x += dx;
		y += dy;
	}
	//move
	/**
	 * Moves pacman one step in a direction that has been specified by the 
	 * player. The specified coordinates are checked to ensure that it is not
	 * occupied by a wall using go**** methods. If no direction has been inputed by the player, 
	 * pacman will continue to move in the previously inputed direction. 
	 * @param tunnel boolean that indicates if the specified coordinate is a tunnel
	 */
	public void move(boolean tunnel, double speed) {
		if(Game.goLeft){
			if(tunnel){
				x += 27;
			}
			else{
				x -= 1 * speed;
			}
		}
		
		else if(Game.goRight){
			if(tunnel){
				x -= 27;
			}
			else{
				x += 1 * speed;
			}
		}
		
		else if(Game.goUp){
			y -= 1 * speed;
		}
		
		else if(Game.goDown){
			y += 1 * speed;
		}
	}
	
		public double getX() {
			return x;
		}

		public double getY() {
			return y;
		}
	}
