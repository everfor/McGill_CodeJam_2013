import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class GamePanel extends JPanel {
	Cell[][] cells;
	GridBagConstraints gbc;
	JLabel p1;
	JLabel p2;
	JLabel timer;
	int p1Wins = 0;
	int p2Wins = 0;
	double time = 0.0;
	
    public GamePanel() {
        setLayout(new GridBagLayout());
        cells = new Cell[(GameBoard.map[0]).length][GameBoard.map.length];
        gbc = new GridBagConstraints();
        makeComponents();
    }
    
    public void makeComponents(){
    	p1 = new JLabel(Integer.toString(p1Wins));
    	p1.setFont(new Font("Times", Font.BOLD, 16));
    	p1.setForeground(Color.RED);
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(p1,gbc);
    	
        p2 = new JLabel(Integer.toString(p2Wins));
    	p2.setFont(new Font("Times", Font.BOLD, 16));
    	p2.setForeground(Color.BLUE);
    	gbc.gridx = GameBoard.map.length - 1;
    	gbc.gridy = 0;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(p2,gbc);
    	
        timer = new JLabel(Double.toString(time));
    	timer.setFont(new Font("Times", Font.BOLD, 16));
    	gbc.gridx = GameBoard.map.length/2 - 1;
    	gbc.gridy = 0;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(timer,gbc);
    	
    	makeCells();
    }
    
    public void makeCells(){

        int cols = (GameBoard.map[0]).length; int rows = GameBoard.map.length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                gbc.gridx = col;
                gbc.gridy = row + 2;
                Cell cell = new Cell();
                Border border = null;
                if(row == 0){
                	if (col == 0){
                        border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
                	}
                	else if (col < cols-1) {
                        border = new MatteBorder(1, 0, 0, 0, Color.BLACK);
                    }
                    else {
                        border = new MatteBorder(1, 0, 0, 1, Color.BLACK);
                    }
                }
                else if (row < rows-1) {
                	if (col == 0){
                        border = new MatteBorder(0, 1, 0, 0, Color.BLACK);
                	}
                	else if (col < cols-1) {
                        border = new MatteBorder(0, 0, 0, 0, Color.BLACK);
                    }
                    else {
                        border = new MatteBorder(0, 0, 0, 1, Color.BLACK);
                    }
                }
                else {
                	if (col == 0){
                        border = new MatteBorder(0, 1, 1, 0, Color.BLACK);
                	}
                	else if (col < cols-1) {
                        border = new MatteBorder(0, 0, 1, 0, Color.BLACK);
                    }
                    else {
                        border = new MatteBorder(0, 0, 1, 1, Color.BLACK);
                    }
                }
                cell.setBorder(border);
                cell.setBackground(Color.WHITE);
                
                if (GameBoard.map[row][col] == 1){
                	cell.setBackground(Color.BLUE);
                			
                }
                else if(GameBoard.map[row][col] == 2){
                	cell.setBackground(Color.RED);
                }
                else if(GameBoard.obstacles[row][col] != null){
                	if(GameBoard.obstacles[row][col].getType() == Obstacle.Owner.BOARD)
                    	cell.setBackground(Color.BLACK);
                }
                cells[row][col] = cell;
                add(cell, gbc);
            }
        }
    }
    
    public void updateDisplay(Racer r1, Racer r2,double timeElapsed){
    	updateRacer(r1);
    	updateRacer(r2);
    	updateTimer(timeElapsed);
    	updateCells();
    	
    }
    
    public void updateRacer(Racer r){
    	Cell curr = cells[r.getXPosition()][r.getYPosition()];
    	if(r.getColour() == 1){
    		curr.setBackground(Color.RED);
    	}
    	
    	else if(r.getColour() == 2){
    		curr.setBackground(Color.BLUE);
    	}
    }
    
    public void updateTimer(double interval){
    	time += interval;
    	timer.setText(Double.toString(time));
    }
    
    public void updateCells(){
    	for (int row = 0; row < GameBoard.map.length; row ++){
		    for (int col = 0; col < GameBoard.map[0].length; col++){
		    	Cell cell = cells[row][col];
                if(GameBoard.obstacles[row][col] != null) {
                	Obstacle curr = GameBoard.obstacles[row][col];	
                	if(curr.getType() == Obstacle.Owner.P1){
                		cell.setBackground(Color.PINK);
                	}
                    else if(curr.getType() == Obstacle.Owner.P2) {
                    	cell.setBackground(Color.CYAN);
                    }
                    else if(curr.getType() == Obstacle.Owner.BOARD) {
                    	cell.setBackground(Color.BLACK);
                    }
                	
                }
                else{
                //    cell.setBackground(Color.WHITE);
                }
		    }
    	}
    }
    
    public void p1Wins(){
    	p1Wins++;
    	p1.setText(Integer.toString(p1Wins));
    }
    
    public void p2Wins(){
    	p2Wins++;
    	p2.setText(Integer.toString(p2Wins));	
    }
}