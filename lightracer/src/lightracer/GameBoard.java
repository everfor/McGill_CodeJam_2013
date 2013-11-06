package lightracer;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.lang.*;
import java.util.EventListener;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 *  Authors: Alex Reiff, Aidan Petit, Syed Irtaza Raza, Jungwan Kim
 */
public class GameBoard {

	private Racer P1;
	private Racer P2;
	private Map boardmap;
    static int[][] map;
    static Obstacle[][] obstacles;
    private GamePanel game;
    private HeaderPanel header;
    private boolean gameOver = false;
    private boolean gamePaused = false;
    static int p1RoundWins = 0;
    static int p2RoundWins = 0;
    
	public static void main(String[] args)
	{
			GameBoard gb = new GameBoard();
			gb.playThreeRoundMatch();	
	}
	
	public void playThreeRoundMatch() {
		gamePaused = false;
		while(p1RoundWins < 2 && p2RoundWins < 2) {
		run();
		}
		if (p1RoundWins == 2){
			//player 1 wins the match
			//display some message and update PvP record/personal records
			game.MatchOver();
		}
		else if (p2RoundWins == 2) {
			//player 2 wins the match, same steps as with p1
			game.MatchOver();
		}
	}
	
	public void run(){
			initializeMap();
			initializeBoard();
			while (!gameOver){
			
				if(!gamePaused){
					updateBoard();
				}
				try {
			    Thread.sleep(100);
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
			updateDisplay(0.1);

		}
		else {
			announceWinner();
			gamePaused = true;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			playThreeRoundMatch();
		}
		
	}
	
	public boolean checkObstacleCollisions(){
	boolean collision = false;
	for( int x = 0; x< obstacles[0].length; x++) {
			for (int y =0; y<obstacles.length; y++) {
					
				if (x == P1.getXPosition() && y == P1.getYPosition()){
					if (obstacles[x][y] != null) {
						collision = true;
						P1.setHasCrashed(true);
					}	
				}
				if (x == P2.getXPosition() && y == P2.getYPosition()){
					if(obstacles[x][y] != null){
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
		if (currDirection == 0 ){//moving left
			currYPos = currYPos + 1; 
			if (currYPos > map[0].length -1){
				racer.setHasCrashed(true);
			}
			else {
				racer.setYPosition(currYPos); //
			}
		}
		else if( currDirection == 1){ //moving up
			currXPos = currXPos + 1; 
			if (currXPos > map[0].length -1){
				racer.setHasCrashed(true);
			}
			else {
				racer.setXPosition(currXPos);
			}
		}
		else if( currDirection == 2){ //moving right
			currYPos = currYPos - 1; 
			if (currYPos < 0){
				racer.setHasCrashed(true);
			}
			else {
				racer.setYPosition(currYPos);
			}
		}
		else if( currDirection == 3){ //moving down
			currXPos = currXPos - 1; 
			if (currXPos < 0){
				racer.setHasCrashed(true);
			}
			else {
				racer.setXPosition(currXPos);
			}
		}
	}
	
    public void updateDisplay(double timeElapsed){
    	game.updateRacer(P1);
    	game.updateRacer(P2);
    	header.updateTimer(timeElapsed);
    	game.updateCells();  	
    }
	
	public void toggleClock(){
		gamePaused = !gamePaused;
	}
	
	public void announceWinner(){
		if (P1.hasCrashed() == true && P2.hasCrashed() == true) {
			//tie
		}
		else if (P1.hasCrashed() == true) {
			p2RoundWins ++;
			header.p2Wins();
		}
		else {
			p1RoundWins ++;
			header.p1Wins();
		}
	}
	
	public void initializeMap(){
		boardmap = new Map();
		map = boardmap.getBoardMap();
		obstacles = boardmap.getBoardObstacle();
	}
	
	public void initializeBoard(){
		P1 = new Racer(1,1,10,10,0);
		P2 = new Racer(2,2,40,40,3);
		JFrame frame = new JFrame("Light Racer"); 	
		// frame.setLayout( new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			switch(key){
			case KeyEvent.VK_LEFT:
				if (P2.getDirection()!=0 && !gamePaused)
				P2.setDirection(2);
				break;
			case KeyEvent.VK_UP:
				if (P2.getDirection()!=1 && !gamePaused)
				P2.setDirection(3);
				break;
			case KeyEvent.VK_RIGHT:
				if (P2.getDirection()!=2 && !gamePaused)
				P2.setDirection(0);
				break;
			case KeyEvent.VK_DOWN:
				if (P2.getDirection()!=3 && !gamePaused)
				P2.setDirection(1);
				break;
			case KeyEvent.VK_A:
				if (P1.getDirection()!=0 && !gamePaused)
				P1.setDirection(2);
				break;
			case KeyEvent.VK_W:
				if (P1.getDirection()!=1 && !gamePaused)
				P1.setDirection(3);
				break;
			case KeyEvent.VK_D:
				if (P1.getDirection()!=2 && !gamePaused)
				P1.setDirection(0);
				break;
			case KeyEvent.VK_S:
				if (P1.getDirection()!=3 && !gamePaused)
				P1.setDirection(1);
				break;
			case KeyEvent.VK_SPACE:
				toggleClock();
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
}
		});
		header = new HeaderPanel(p1RoundWins, p2RoundWins);
		game = new GamePanel();
        frame.add(header,BorderLayout.CENTER);
        frame.add(game,BorderLayout.PAGE_END);
        frame.pack();
        frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    updateDisplay(0.0);
	}
	public static void addObstacle(int x, int y, int racerID){
		obstacles[x][y] = new Obstacle(((char) racerID));
		
	}
}