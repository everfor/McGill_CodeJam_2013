package interfaceFramework;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import interfaceFramework.Pacman;
import interfaceFramework.Ghost;;


public class Map extends JPanel implements ActionListener{

	int map[][] = new int[40][30];
	final int newmap [][]= {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
			{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
			{1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
			{1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
			{1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
			{1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
			{1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
			{1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
			{1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

	
	
	int width = map.length;
	int height = map[0].length;


	Pacman pacman = new Pacman();
	Ghost ghost = new Ghost(); 
	Timer timer;

	public Map(){
		addKeyListener(new myActionListener());
		setFocusable(true);
		timer = new Timer(30, this);
		timer.start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();  
	}

	public void paint(Graphics g){
		super.paint(g);
		makeMap(g);
		setCoords();
		fill(Color.yellow, g, pacman.getX(), pacman.getY());
		fill(Color.red, g, ghost.getX(), ghost.getY());
		ghost.move(ghost, map);
	}

	public void pauseSession(){
		try{
			timer.stop();
		}
		catch(Exception e){
			System.out.println("There is a problem");
		}
	}
	public void resumeSession(){
		try{
			timer.start();
		}
		catch(Exception e){
			System.out.println("There is a problem");
		}
	}

	public void makeMap(Graphics g){
		g.setColor(Color.black);
		for(int i=1; i<=width; i++){
			g.fillRect(16*(i-1), 0, 16, 16);     //top wall
			g.fillRect(16*(i-1), 16*(height-1), 16, 16);  //bottom wall
		
		}

		for(int i=1; i<=height; i++){
			g.fillRect(0, 16*(i-1), 16, 16);     //left wall
			g.fillRect(16*(width-1), 16*(i-1), 16, 16);   //right wall
		}

		g.setColor(Color.green);
		for(int i=2; i<width; i++){       //fill field
			for(int j=2; j<height;j++){
				g.fillRect(16*(i-1), 16*(j-1), 16, 16);
			}
		}

		g.setColor(Color.yellow);
		g.fillRect(0, 240, 16, 16);
		g.fillRect(624, 240, 16, 16);

		//  g.setColor(Color.black);
	}

	public void fill(Color c, Graphics g, int x, int y){
		g.setColor(c);
		g.fillRect(16*x, 16*y, 16, 16);
	}

	public class myActionListener extends KeyAdapter {

		// this method checks if pacman can move in the inputted direction, and
		// then moves pacman
			public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				// if need to go left, check if you can go left first
				if(pacman.getX() == 0 && pacman.getY() == 15){
					pacman.move(39, 0);
				}
				else if(interfaceFramework.UserControls.moveLeft(pacman, map)) {
					pacman.move(-1, 0);
				}
				else if(!interfaceFramework.UserControls.moveLeft(pacman, map)){
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_RIGHT:
				// if need to go right, check if you can go right
				if(pacman.getX() == 39 && pacman.getY() == 15){
					pacman.move(-39, 0);
				}
				else if(interfaceFramework.UserControls.moveRight(pacman, map)){
					pacman.move(1, 0);
				}
				else if(!interfaceFramework.UserControls.moveRight(pacman, map)){
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_UP:
				// if need to go up, check if you can go up
				if (interfaceFramework.UserControls.moveUp(pacman, map)) {
					pacman.move(0, -1);
				}
				else{
					pacman.move(0, 0);
				}
				break;

			case KeyEvent.VK_DOWN:
				// if need to go down, check if you can go down
				if (interfaceFramework.UserControls.moveDown(pacman, map)) {
					pacman.move(0, 1);
				}
				else{
					pacman.move(0, 0);
				}
				break;
			case KeyEvent.VK_P:
				pauseSession();

				break;

			case KeyEvent.VK_R:
				resumeSession();
				break;
			}


			if(pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY()){
				pauseSession();
			}
		}


	}

	public void setCoords(){
		//0=space, 1=wall, 2=dot, 3 = energizer 
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
		map[0][15] = 0;
		map[39][15] = 0;
	}
}
