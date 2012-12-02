package interfaceFramework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * The class creates the Map with a specific size and 
 * includes the wall, space, dot, energizer, score, lives, door and life icons
 * 
 * 
 * 
 */
public class Map extends JPanel {

	private static final long serialVersionUID = 1L;

	final static int width = 28;
	final static int height = 33; //2 more for extra space at bottom
	static boolean fruitVisible = false; 

	int board[][] = new int[width][height];
	
	Image wall, space, dot, energizer, score, lives, door, life, cherry;

	File path;
	static Scanner reader;
	
	/**
	 * Constructor which adds the images corresponding to:
	 * wall, space, dot, energizer, score, lives, door
	 */
	public Map() {
		path = new File("").getAbsoluteFile();		
		wall = new ImageIcon(path + "\\res\\wall.jpg").getImage();
		space = new ImageIcon(path + "\\res\\space.jpg").getImage();
		dot = new ImageIcon(path + "\\res\\dot.jpg").getImage();
		energizer = new ImageIcon(path + "\\res\\energizer.jpg").getImage();
		score = new ImageIcon(path + "\\res\\score.jpg").getImage();
		lives = new ImageIcon(path + "\\res\\lives.jpg").getImage();
		door = new ImageIcon(path + "\\res\\door.jpg").getImage();
		life = new ImageIcon(path + "\\res\\life.jpg").getImage();
		cherry = new ImageIcon(path + "\\res\\cherry.jpg").getImage();
		
		open();
		read();
		close();
	}
	/**
	 * Method that reads the structure of the map from a text file, Map.txt
	 * 
	 * @return void
	 */	
	public void open(){
		try {
			reader = new Scanner(new File(path + "\\res\\Map.txt"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method that imports the data found in the reader from the open method to an array 
	 * which it then uses to create a rough structure of the board
	 * 
	 * @return void
	 */	
	public void read(){
		String temp[] = new String[height];
		while(reader.hasNext()){
			for(int i=0; i<height;i++){
				temp[i] = (reader.nextLine());
			}
		}
		
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++){
				board[x][y] = Integer.parseInt(temp[y].substring(x,x+1));
			}
		}
	}
	/**
	 * Method that closes the aforementioned reader
	 * 
	 * @return void
	 */	
	
	public void close(){
		reader.close();
	}
	/**
	 * Method that creates the map using the rough structure made in the read method
	 * It resolves that structure into one with pixels and graphics corresponding to:
	 * wall, space, dot, energizer and door
	 * 
	 * @param g
	 * 		Graphics object 
	 * 
	 * @return void
	 */	
	public void addMap(Graphics g){
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++){
				if(board[x][y] == 1){
					g.drawImage(wall, x*Game.pixel, y*Game.pixel, null);
				}
				else if(board[x][y] == 2){
					g.drawImage(dot, x*Game.pixel, y*Game.pixel, null);
				}
				else if(board[x][y] == 3){
					g.drawImage(energizer, x*Game.pixel, y*Game.pixel, null);
				}
				else if(board[x][y] == 5){
					g.drawImage(door, x*Game.pixel, y*Game.pixel, null);
				}
				else if(board[x][y] == 0  || board[x][y] == 4){
					g.drawImage(space, x*Game.pixel, y*Game.pixel, null);
				}
				else if (board[x][y] == 6){					
					g.drawImage(cherry, 14*Game.pixel, 17*Game.pixel, null);
				}
			}
		}
	}
	/**
	 * Method that creates additional graphics:  score and lives
	 * It also sets fonts, colours and appropriate images for these particular graphics
	
	 * @param g
	 * 		Graphics object 
	 * 
	 * @param pacman
	 * 		object of Pacman class, which contains the charactersitics of Pac-Man
	 * 
	 * @return void
	 */	
	public void addExtras(Pacman pacman, Graphics g){
		g.drawImage(score, 0, 31*Game.pixel, null);
		g.drawImage(lives, 13*Game.pixel, 31*Game.pixel, null);
		
		int score = Score.getScore(board);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
	    g.setColor(Color.yellow);
		g.drawString("" + score, (int) (5.3*Game.pixel), (int) (32.5*Game.pixel));
		
		if(Game.inGame){
			if(pacman.livesLeft == 3){
				g.drawImage(life, 18*Game.pixel, (int) (31.2*Game.pixel), null);
				g.drawImage(life, (int) (19.8*Game.pixel), (int) (31.2*Game.pixel), null);
			}
			
			if(pacman.livesLeft == 2){
				g.drawImage(life, 18*Game.pixel, (int) (31.2*Game.pixel), null);
			}
		}
				
		if(!Game.inGame){
			if(pacman.livesLeft == 3){
				g.drawImage(life, 18*Game.pixel, (int) (31.2*Game.pixel), null);
			}
			
			else if(pacman.livesLeft == 2){
				g.drawImage(null, 18*Game.pixel, (int) (31.2*Game.pixel), null);
				pacman.livesLeft = 1;
				Game.inGame = true;
			}
			else if(pacman.livesLeft == 1) {
				g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
				g.setColor(Color.RED);
				g.drawString("Game Over", (int) (125), (int) (250));
			}
		 }
	}
	
	/**
	 * Method that check whether the fruit has been eaten and sets fruitVisible accordingly
	 * @param board
	 * 
	 * @return void
	 */	
	public void fruitVisibility(int[][] board){
		if(Score.getScore(board) >= 100 && Game.fruitEaten == false){	
			board[14][17] = 6;
			fruitVisible = true;
		}
	}
}