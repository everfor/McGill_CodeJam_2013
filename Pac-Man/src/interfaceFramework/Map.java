package interfaceFramework;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static int width = 28;
	final static int height = 33; //2 more for extra space at bottom
	
	int board[][] = new int[width][height];
	
	Image wall, space, dot, energizer, score, lives, door, life;

	File path;
	static Scanner reader;
	
	public Map() {
		path = new File("").getAbsoluteFile();		
		wall = new ImageIcon(path + "\\resources\\wall.jpg").getImage();
		space = new ImageIcon(path + "\\resources\\space.jpg").getImage();
		dot = new ImageIcon(path + "\\resources\\dot.jpg").getImage();
		energizer = new ImageIcon(path + "\\resources\\energizer.jpg").getImage();
		score = new ImageIcon(path + "\\resources\\score.jpg").getImage();
		lives = new ImageIcon(path + "\\resources\\lives.jpg").getImage();
		door = new ImageIcon(path + "\\resources\\door.jpg").getImage();
		life = new ImageIcon(path + "\\resources\\life.jpg").getImage();
		
		open();
		read();
		close();
	}
	
	public void open(){
		try {
			reader = new Scanner(new File(path + "\\resources\\Map.txt"));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void close(){
		reader.close();
	}
	
	//0-space, 1-wall, 2-dot, 3-energizer, 4-tunnel
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
			}
		}
	}
	
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
//				pacman.livesLeft = 2;
//				Game.inGame = true;
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
	
}