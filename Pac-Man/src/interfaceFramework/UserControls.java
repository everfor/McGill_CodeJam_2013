package interfaceFramework;
import interfaceFramework.Pacman;

public class UserControls {

		  //to check if left move is possible
		  public static boolean moveLeft(Pacman pacman, int map[][]){
		   boolean result = true;
		   int x = pacman.getX();
		   int y = pacman.getY();

		   if(map[x-1][y] == 1){
		    result = false;
		   }
		   return result;
		  }
		  
		  //to check if right move is possible
		  public static boolean moveRight(Pacman pacman, int map[][]){
		   boolean result = true;
		   int x = pacman.getX();
		   int y = pacman.getY();

		   if(map[x+1][y] == 1){
		    result = false;
		   }
		   return result;
		  }
		  
		  //to check if up move is possible
		  public static boolean moveUp(Pacman pacman, int map[][]){
		   boolean result = true;
		   int x = pacman.getX();
		   int y = pacman.getY();

		   if(map[x][y-1] == 1){
		    result = false;
		   }
		   return result;
		  }
		  
		  //to check if down move is possible
		  public static boolean moveDown(Pacman pacman, int map[][]){
		   boolean result = true;
		   int x = pacman.getX();
		   int y = pacman.getY();

		   if(map[x][y+1] == 1){
		    result = false;
		   }
		   return result;
		  }
		
	 }
	

	