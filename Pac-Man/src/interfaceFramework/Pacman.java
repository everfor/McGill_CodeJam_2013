package interfaceFramework;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

/**
 * The class deals with the movement characteristics of Pac-Man
 * 
 * 
 */

public class Pacman {
	double x, y;
	Image image1, image2, image3, image4;
	File path;
	int livesLeft = 3;

	/**
	 * This constructor defines the starting position of PacMan and the image paths which show the
	 * four direction-based images of Pac-Man
	 * 
	 */
	public Pacman() {
		x = 14; // 14 starting position x
		y = 23; // 23 starting position y

		path = new File("").getAbsoluteFile();
		image1 = new ImageIcon(path + "\\res\\image\\pacman1.gif").getImage();
		image2 = new ImageIcon(path + "\\res\\image\\pacman2.gif").getImage();
		image3 = new ImageIcon(path + "\\res\\image\\pacman3.gif").getImage();
		image4 = new ImageIcon(path + "\\res\\image\\pacman4.gif").getImage();

	}

	/**
	 * This method deals with the x and y coordinates of the destination of Pac-Man
	 */
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;

	}

	/**
	 * This method deals with the change x and y coordinates corresponding to a movement by Pac-Man
	 * 
	 */
	public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	// move
	/**
	 * Moves pacman one step in a direction that has been specified by the player. The specified
	 * coordinates are checked to ensure that it is not occupied by a wall using go**** methods. If
	 * no direction has been inputed by the player, pacman will continue to move in the previously
	 * inputed direction.
	 * 
	 * @param tunnel
	 *            boolean that indicates if the specified coordinate is a tunnel
	 * 
	 * @param speed
	 *            double that displays the speed of the character
	 */
	public void move(boolean tunnel, double speed) {
		if(Game.goLeft) {
			if(tunnel) {
				x += 27;
			}
			else {
				x -= 1 * speed;
			}
		}

		else if(Game.goRight) {
			if(tunnel) {
				x -= 27;
			}
			else {
				x += 1 * speed;
			}
		}

		else if(Game.goUp) {
			y -= 1 * speed;
		}

		else if(Game.goDown) {
			y += 1 * speed;
		}
	}

	/**
	 * This method sets x coordinates Pac-Man
	 * 
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * This method sets y coordinates Pac-Man
	 * 
	 */

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * This method gets x coordinates Pac-Man
	 * 
	 **/
	public double getX() {
		return x;
	}

	/**
	 * This method gets y coordinates Pac-Man
	 * 
	 */

	public double getY() {
		return y;
	}
}
