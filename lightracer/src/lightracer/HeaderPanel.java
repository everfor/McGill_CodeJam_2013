import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * Authors: Alex Reiff
 */

public class HeaderPanel extends JPanel {
	GridBagConstraints gbc;
	JLabel p1;
	JLabel p2;
	JLabel timer;
	JLabel display;
	int p1Wins = 0;
	int p2Wins = 0;
	double time = 0.0;
	
    public HeaderPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        init();
    }
    
    public void init(){
    	p1 = new JLabel(Integer.toString(p1Wins));
    	p1.setFont(new Font("Times", Font.BOLD, 16));
    	p1.setForeground(Color.RED);
    	gbc.gridx = 0;
    	gbc.gridy = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(p1,gbc);
    	
        p2 = new JLabel(Integer.toString(p2Wins));
    	p2.setFont(new Font("Times", Font.BOLD, 16));
    	p2.setForeground(Color.BLUE);
    	gbc.gridx = 2;
    	gbc.gridy = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(p2,gbc);
    	
        timer = new JLabel(Double.toString(time));
    	timer.setFont(new Font("Times", Font.BOLD, 16));
    	gbc.gridx = 1;
    	gbc.gridy = 1;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(timer,gbc);
    	
    	display = new JLabel("Welcome to Lightracer");
    	display.setFont(new Font("Times", Font.BOLD, 16));
    	gbc.gridx = 0;
    	gbc.gridy = 0;
    	gbc.fill = GridBagConstraints.HORIZONTAL;
    	add(display,gbc);
    }
    
    public void updateTimer(double interval){
    	time += interval;
    	time = Math.floor(time * 100) / 100;
    	timer.setText(Double.toString(time));
    }
    
    public void p1Wins(){
    	p1Wins++;
    	p1.setText(Integer.toString(p1Wins));
    	display.setText("Player 1 Wins!");
    }
    
    public void p2Wins(){
    	p2Wins++;
    	p2.setText(Integer.toString(p2Wins));	
    	display.setText("Player 2 Wins!");
    }
}