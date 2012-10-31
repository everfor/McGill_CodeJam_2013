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
	char pacManDirection;

	int map[][] = new int[40][30];
	//	final int newmap [][]= {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	//			{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	//			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
	//			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
	//			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
	//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	//			{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
	//			{1,0,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,0,1},
	//			{1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
	//			{1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
	//			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
	//			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1},
	//			{1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1},
	//			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
	//			{1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,1,1,1,1,0,1,1,1,1,0,1},
	//			{1,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,1},
	//			{1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
	//			{1,1,1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,1,0,1,1,0,1,1,0,1,1,1},
	//			{1,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,1},
	//			{1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
	//			{1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1},
	//			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	//			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};


	int width = map.length;
	int height = map[0].length;
	boolean tunnel = false;
	static boolean goLeft = false;
	static boolean goRight = false;
	static boolean goUp = false;
	static boolean goDown = false;
	static boolean stop = false;

	Pacman pacman = new Pacman();
	Ghost ghost = new Ghost(10, 10); 
	Ghost ghost2 = new Ghost(30, 25);
	Ghost ghost3 = new Ghost(13, 20);
	Ghost ghost4 = new Ghost(30, 5);
	Timer timer;

	public Map(){
		addKeyListener(new myActionListener());
		setFocusable(true);
		timer = new Timer(120, this);
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
		fill(Color.white, g, ghost2.getX(), ghost2.getY());
		fill(Color.darkGray, g, ghost3.getX(), ghost3.getY());
		fill(Color.cyan, g, ghost4.getX(), ghost4.getY());
		if(UserControls.checkMove(pacman, map, tunnel)){
			pacman.move(tunnel);

		}
		
		ghost.move(ghost, map);
		ghost2.move(ghost2, map);
		ghost3.move(ghost3, map);
		ghost4.move(ghost4, map);
		checkCollision ();
	}

	public void checkCollision (){
		if ((pacman.getX() == ghost.getX() && pacman.getY() == ghost.getY())||
				(pacman.getX() == ghost2.getX() && pacman.getY() == ghost2.getY())||
				(pacman.getX() == ghost3.getX() && pacman.getY() == ghost3.getY())||
				(pacman.getX() == ghost4.getX() && pacman.getY() == ghost4.getY())) {
			pauseSession();
		}
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
			g.fillRect(16*(i-1), 0, 16, 16);     				//top wall
			g.fillRect(16*(i-1), 16*(height-1), 16, 16);  		//bottom wall
		}

		for(int i=1; i<=height; i++){
			g.fillRect(0, 16*(i-1), 16, 16);    				//left wall
			g.fillRect(16*(width-1), 16*(i-1), 16, 16);   		//right wall
		}

		g.setColor(Color.green);
		for(int i=2; i<width; i++){       						//fill field
			for(int j=2; j<height;j++){
				g.fillRect(16*(i-1), 16*(j-1), 16, 16);
			}
		}

		g.setColor(Color.blue);									//tunnel
		g.fillRect(0, 16*15, 16, 16);
		g.fillRect(16*39, 16*15, 16, 16);

		g.setColor(Color.black);								//obstacle wall
		for(int i=0; i<=5; i++){
			g.fillRect(16*20,16*(i+6),16,16);
			g.fillRect(16*26,16*(i),16,16);
			g.fillRect(16*26,16*(i+6),16,16);
			g.fillRect(16*(i+20), 16*5,16,16);
			g.fillRect(16*(i+20), 16*12,16,16);
			g.fillRect(16*26, 16*12,16,16);
		}
		g.setColor(Color.black);
		g.fillRect(16, 16*14, 16, 16);
		g.fillRect(16, 16*16, 16, 16);
		g.fillRect(16*2, 16*14, 16, 16);
		g.fillRect(16*2, 16*16, 16, 16);
		g.fillRect(16*3, 16*14, 16, 16);
		g.fillRect(16*3, 16*16, 16, 16);
		g.fillRect(16*36, 16*14, 16, 16);
		g.fillRect(16*36, 16*16, 16, 16);
		g.fillRect(16*37, 16*14, 16, 16);
		g.fillRect(16*37, 16*16, 16, 16);
		g.fillRect(16*38, 16*14, 16, 16);
		g.fillRect(16*38, 16*16, 16, 16);
	}

	public void fill(Color c, Graphics g, int x, int y){
		g.setColor(c);
		g.fillRect(16*x, 16*y, 16, 16);
	}

	public class myActionListener extends KeyAdapter {
		// char pacManDirection;
		// this method checks if pacman can move in the inputted direction, and
		// then moves pacman
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();

			switch (key) {
			case KeyEvent.VK_LEFT:
				// if need to go left, check if you can go left first

				if(pacman.getX() == 1 && pacman.getY() == 15){
					pacman.moveTo(38,15);
				}

				if (pacman.getX() == 1 && pacman.getY() == 15) {
					tunnel = true;
					goLeft = true;
					goRight = false;
					goUp = false;
					goDown = false;
				} 

				else if (UserControls.moveLeft(pacman, map)) {
					tunnel = false;
					goLeft = true;
					goRight = false;
					goUp = false;
					goDown = false;
				}

				break;

			case KeyEvent.VK_RIGHT:
				// if need to go right, check if you can go right
				if (pacman.getX() == 38 && pacman.getY() == 15) {
					tunnel = true;
					goLeft = false;
					goRight = true;
					goUp = false;
					goDown = false;
				} 

				else if (UserControls.moveRight(pacman, map)) {
					tunnel = false;
					goLeft = false;
					goRight = true;
					goUp = false;
					goDown = false;
				}

				break;

			case KeyEvent.VK_UP:
				// if need to go up, check if you can go up
				if (UserControls.moveUp(pacman, map)) {
					tunnel = false;
					goLeft = false;
					goRight = false;
					goUp = true;
					goDown = false;

				}

				break;

			case KeyEvent.VK_DOWN:
				// if need to go down, check if you can go down
				if (UserControls.moveDown(pacman, map)) {
					tunnel = false;
					goLeft = false;
					goRight = false;
					goUp = false;
					goDown = true;
				}

				break;

			case KeyEvent.VK_P:
				pauseSession();
				break;

			case KeyEvent.VK_R:
				resumeSession();
				break;
			}

		}
	}

	public void setCoords(){
		//0=space, 1=wall, 2=dot, 3 = energizer 4=tunnel
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

		for(int y=1; y<=12; y++){
			map[26][y] = 1;
		}

		for(int y=5; y<=12; y++){
			map[20][y] = 1;
		}

		for(int x=20; x<=26; x++){
			map[x][5] = 1;
			map[x][12] = 1;
		}

		map[1][14] = 1;
		map[1][16] = 1;
		map[2][14] = 1;
		map[2][16] = 1;
		map[3][14] = 1;
		map[3][16] = 1;

		map[36][14] = 1;
		map[36][16] = 1;
		map[37][14] = 1;
		map[37][16] = 1;
		map[38][14] = 1;
		map[38][16] = 1;

		//tunnel coordinates in map
		map[0][15] = 4;
		map[39][15] = 4;
	}
}
