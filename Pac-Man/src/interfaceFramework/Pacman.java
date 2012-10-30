package interfaceFramework;

public class Pacman {
		 int x, y;
		 int tileX, tileY;
		 
		 public Pacman(){
		  x = 1;
		  y = 1;
		 }
		 
		 public void move(int dx, int dy){
		  x = x + dx;
		  y = y + dy;
		 }
		 
		 public int getX(){
		  return x;
		 }
		 
		 public int getY(){
		  return y;
		 }
		}


