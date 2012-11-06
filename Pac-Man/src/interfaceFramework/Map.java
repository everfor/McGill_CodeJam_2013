package interfaceFramework;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Map extends JPanel {
	final int width = 28;
	final int height = 31;
	
	int board[][] = new int[width][height];
	
	Image wall;
	Image space;
	Image pacmanImage2;

	File path;
	static Scanner reader;
	
	public Map() {
		path = new File("").getAbsoluteFile().getParentFile();		
		wall = new ImageIcon(path + "\\resources\\wall.jpg").getImage();
		space = new ImageIcon(path + "\\resources\\space.jpg").getImage();
		
		open();
		read();
		close();
	}
	
	public void open(){
		try {
			reader = new Scanner(new File(path + "\\resources\\Map.txt"));
		} 
		catch (FileNotFoundException e) {
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
	
	public void addMap(Graphics g){
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++){
				if(board[x][y] == 1){
					g.drawImage(wall, x*Game.pixel, y*Game.pixel, null);
				}
				else if(board[x][y] == 0  || board[x][y] == 4){
					g.drawImage(space, x*Game.pixel, y*Game.pixel, null);
				}
			}
		}	
	}
	
}