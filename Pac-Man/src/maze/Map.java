package maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import player.Player;

public class Map extends JPanel implements ActionListener{

	int map[][] = new int[80][60];
	int width = map.length;
	int height = map[0].length;
	
	
	Player pacman = new Player();
	Timer timer;
	
	public Map(){
		addKeyListener(new myActionListener());
		setFocusable(true);
		timer = new Timer(1, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		makeMap(g);
		setCoords();
		fill(Color.red, g, pacman.getX(), pacman.getY());
		
	}
	
	public void makeMap(Graphics g){
		g.setColor(Color.black);
		for(int i=1; i<=width; i++){
			g.fillRect(8*(i-1), 0, 8, 8);					//top wall
			g.fillRect(8*(i-1), 8*(height-1), 8, 8);		//bottom wall
		}
		
		for(int i=1; i<=height; i++){
			g.fillRect(0, 8*(i-1), 8, 8);					//left wall
			g.fillRect(8*(width-1), 8*(i-1), 8, 8);			//right wall
		}
	
		g.setColor(Color.green);
		for(int i=2; i<width; i++){							//fill field
			for(int j=2; j<height;j++){
				g.fillRect(8*(i-1), 8*(j-1), 8, 8);
			}
		}
		
		g.setColor(Color.yellow);
		g.fillRect(0, 272, 8, 8);
		g.fillRect(632, 272, 8, 8);
		
//		g.setColor(Color.black);
	}
	
	public void fill(Color c, Graphics g, int x, int y){
		g.setColor(c);
		g.fillRect(8*x, 8*y, 8, 8);
	}
	
	public class myActionListener extends KeyAdapter {

		// this method checks if pacman can move in the inputted direction, and
		// then moves pacman
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				// if need to go left, check if you can go left first
				if(pacman.getX() == 1 && pacman.getY() == 34){
					pacman.move(78, 0);
				}
				else if(moveLeft()) {
					pacman.move(-1, 0);
				}
				else if(moveLeft() == false){
					pacman.move(0, 0);
				}
				break;
				
			case KeyEvent.VK_RIGHT:
				// if need to go right, check if you can go right
				if(pacman.getX() == 79 && pacman.getY() == 34){
					pacman.move(-79, 0);
				}
				else if(moveRight()) {
					pacman.move(1, 0);
				}
				else if(moveRight() == false){
					pacman.move(0, 0);
				}
				break;
				
			case KeyEvent.VK_UP:
				// if need to go up, check if you can go up
				if (moveUp()) {
					pacman.move(0, -1);
				}
				else{
					pacman.move(0, 0);
				}
				break;
				
			case KeyEvent.VK_DOWN:
				// if need to go down, check if you can go down
				if (moveDown()) {
					pacman.move(0, 1);
				}
				else{
					pacman.move(0, 0);
				}
				break;
			}
		}
		
		//to check if left move is possible
		public boolean moveLeft(){
			boolean result = true;
			int x = pacman.getX();
			int y = pacman.getY();

			if(map[x-1][y] == 1){
				result = false;
			}
			return result;
		}
		
		//to check if left move is possible
		public boolean moveRight(){
			boolean result = true;
			int x = pacman.getX();
			int y = pacman.getY();

			if(map[x+1][y] == 1){
				result = false;
			}
			return result;
		}
		
		//to check if left move is possible
		public boolean moveUp(){
			boolean result = true;
			int x = pacman.getX();
			int y = pacman.getY();

			if(map[x][y-1] == 1){
				result = false;
			}
			return result;
		}
		
		//to check if left move is possible
		public boolean moveDown(){
			boolean result = true;
			int x = pacman.getX();
			int y = pacman.getY();

			if(map[x][y+1] == 1){
				result = false;
			}
			return result;
		}
	}
	
	//0=space, 1=wall, 2=dot, 3 = energizer 
	public void setCoords(){
		//set space coordinates in map
		for(int x=1; x<width; x++){
			for(int y=1; y<height; y++){
				map[x][y] = 0;						
			}
		}
		//set wall coordinates in map
		for(int x=0; x<width; x++){
			map[x][0] = 1;
			map[x][height-1] = 1;
		}
		
		//set wall coordinates in map
		for(int y=0; y<height; y++){
			map[0][y] = 1;
			map[width-1][y] = 1;
		}
		map[0][34] = 0;
		map[79][34] = 0;
	}
}
