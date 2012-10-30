package interfaceFramework;
import java.util.Random;

public class Ghost {
 int x, y;
 int tileX, tileY;
 
 public Ghost(){
  x = 10;
  y = 10;
 }
 
 public void move(Ghost ghost , int map[][]){
// int[][] gmove = new int[79][59];

 Random generator = new Random();
 int randommove = generator.nextInt(4);
 
 
 if (randommove == 0 && moveRight(ghost, map )){
 x = x+1;
 }
 
 else if (randommove == 1 && moveLeft(ghost, map )){
  x = x - 1;
  }
 else if (randommove == 2 && moveUp(ghost, map )){
  y = y + 1;
	 }
 else if (randommove == 3 && moveDown(ghost, map )){
  y = y - 1;
	 
  }
}

 
 
 public int getX(){
  return x;
 }
 
 public int getY(){
  return y;
 }
 //to check if left move is possible
 public static boolean moveLeft(Ghost ghost, int map[][]){
  boolean result = true;
  int x = ghost.getX();
  int y = ghost.getY();

  if(map[x-1][y] == 1){
   result = false;
  }
  return result;
 }
 
 //to check if right move is possible
 public static boolean moveRight(Ghost ghost, int map[][]){
  boolean result = true;
  int x = ghost.getX();
  int y = ghost.getY();

  if(map[x+1][y] == 1){
   result = false;
  }
  return result;
 }
 
 //to check if up move is possible
 public static boolean moveUp(Ghost ghost, int map[][]){
  boolean result = true;
  int x = ghost.getX();
  int y = ghost.getY();

  if(map[x][y-1] == 1){
   result = false;
  }
  return result;
 }
 
 //to check if down move is possible
 public static boolean moveDown(Ghost ghost, int map[][]){
  boolean result = true;
  int x = ghost.getX();
  int y = ghost.getY();

  if(map[x][y+1] == 1){
   result = false;
  }
  return result;
 }

}
