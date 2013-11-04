import java.awt.GridLayout;
import java.lang.*;
import java.util.EventListener;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 *  Authors: Aidan Petit, Syed Irtaza Raza, Jungwan Kim
 */
public class GameBoard {

	private Racer P1;
	private Racer P2;
    static int[][] map;
    static Obstacle[][] obstacles;
    private GamePanel game;
    private boolean gamePaused = false;
    
	public static void main(String[] args)
	{	   
		GameBoard gb = new GameBoard();
		gb.run();
	}
	


	
	public void run(){
		hardCodeMap();
		initializeBoard();
		while (!gamePaused){
			updateBoard();
			try {
			    Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void updateBoard(){
		if (checkObstacleCollisions() == false){ //no collision, add light trails, update position of Racers, draw map
			addObstacle(P1.getXPosition(),P1.getYPosition(), '1');
			addObstacle(P2.getXPosition(),P2.getYPosition(), '2');
			updateRacerPosition(P1);
			updateRacerPosition(P2);
			game.updateDisplay(P1, P2, 0.0);

		}
		else {
			announceWinner();
			gamePaused = true;
		}
		
	}
	
	public boolean checkObstacleCollisions(){
	boolean collision = false;
	for( int i = 0; i< obstacles[0].length; i++) {
			for (int j =0; j<obstacles.length; j++) {
					
				if (i == P1.getXPosition() && j == P1.getYPosition()){
					if (obstacles[i][j] != null) {
						collision = true;
						P1.setHasCrashed(true);
					}	
				}
				if (i == P2.getXPosition() && j == P2.getYPosition()){
					if(obstacles[i][j] != null){
						collision = true;
						P2.setHasCrashed(true);
					}
				}
			}
	}
	if (P1.getXPosition()==P2.getXPosition() && P1.getYPosition()==P2.getYPosition()) {
		collision = true;
		P1.setHasCrashed(true);
		P2.setHasCrashed(true);
	}
		return collision;
	}
	
	public void updateRacerPosition(Racer racer){
	int currDirection = racer.getDirection();
	int currXPos = racer.getXPosition();
	int currYPos = racer.getYPosition();
		if (currDirection == 0 ){//moving upwards
			currYPos = currYPos + 1; 
			racer.setYPosition(currYPos); //
		}
		else if( currDirection == 1){ //moving right
			currXPos = currXPos + 1; 
			racer.setXPosition(currXPos);
		}
		else if( currDirection == 2){ //moving down
			currYPos = currYPos - 1; 
			racer.setYPosition(currYPos);
		}
		else if( currDirection == 3){ //moving left
			currXPos = currXPos - 1; 
			racer.setXPosition(currXPos);
		}
	}
	
	public void toggleClock(){
		gamePaused = !gamePaused;
	}
	
	public void hardCodeMap(){
		int rows = 50; int cols = 50;
		map = new int[rows][cols];
		obstacles = new Obstacle[rows][cols];
		for (int row = 0; row < rows; row ++){
		    for (int col = 0; col < cols; col++){
		        map[row][col] = 0;
		        obstacles[row][col] = null;
		    }
		}
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col<cols; col++) {
				if ((row == 0)||(row == 49) ){ 
				    obstacles[row][col] = new Obstacle('0');
				}
				else {
					obstacles[row][0] = new Obstacle('0');
					obstacles[row][49] = new Obstacle('0');
				}
			}
		}

	}
	
	public void announceWinner(){
		if (P1.hasCrashed() == true && P2.hasCrashed() == true) {
			
		}
		else if (P1.hasCrashed() == true) {
			game.p2Wins();
		}
		else {
			game.p1Wins();
		}
	}
	
	public void initializeBoard(){
		P1 = new Racer(1,1,10,10,0);
		P2 = new Racer(2,2,40,40,3);
		JFrame frame = new JFrame("Light Racer"); 
		
		frame.setLayout( new GridLayout(1,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game = new GamePanel();
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			switch(key){
			case KeyEvent.VK_LEFT:
				if (P2.getDirection()!=0)
				P2.setDirection(2);
				break;
			case KeyEvent.VK_UP:
				if (P2.getDirection()!=1)
				P2.setDirection(3);
				break;
			case KeyEvent.VK_RIGHT:
				if (P2.getDirection()!=2)
				P2.setDirection(0);
				break;
			case KeyEvent.VK_DOWN:
				if (P2.getDirection()!=3)
				P2.setDirection(1);
				break;
			case KeyEvent.VK_A:
				if (P1.getDirection()!=0)
				P1.setDirection(2);
				break;
			case KeyEvent.VK_W:
				if (P1.getDirection()!=1)
				P1.setDirection(3);
				break;
			case KeyEvent.VK_D:
				if (P1.getDirection()!=2)
				P1.setDirection(0);
				break;
			case KeyEvent.VK_S:
				if (P1.getDirection()!=3)
				P1.setDirection(1);
				break;
			}
		}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    game.updateDisplay(P1, P2, 0.0);
	}
	public static void addObstacle(int x, int y, int racerID){
		obstacles[x][y] = new Obstacle(((char) racerID));
		
	}
}