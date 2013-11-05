
public class Racer {
	
	private int racerID;	//private Player racerID;
	private int colour;
	private int xPosition; //two integers represent location coordinates
	private int yPosition; 
	private boolean hasCrashed;
	private int direction; 
	/* 0 - up
	 * 1 - right
	 * 2  - down
	 * 3 - left
	 */

	//public Racer(Player racerID, int colour){
	public Racer(int racerID, int colour, int xPosition, int yPosition, int direction){
		this.racerID = racerID ;
		this.colour = colour;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.hasCrashed = false;
		this.direction = direction;
	}	
	public boolean hasCrashed() {
		return hasCrashed;
	}
	public void setHasCrashed(boolean hasCrashed) {
		this.hasCrashed = true;
	}
	public void setPlayer(int racerID) {
		this.racerID = racerID;
	}
	public int getPlayer(){
		return this.racerID;
	}
	public void setColour(int colour){
	/*For colors, I was thinking of having a key be something like
	 * 1 - Red
	 * 2 - Blue
	 * 3 - Green
	 * 4 - Yellow
	 * and then we can add more options as we see fit/once we start using JSwing
	 * 
	 */
		this.colour = colour;
	}
	public int getColour() {
		return this.colour;
	}
	public int getXPosition() {
		return xPosition;
	}
	public void setXPosition(int xPosition) {
		this.xPosition = xPosition;
	}
	public int getYPosition() {
		return yPosition;
	}
	public void setYPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	public void addLightTrail(){
	/* 
	 * since GameBoard has a 0 to many association with Obstance, I was thinking
	 * we could keep an array/list of Obstacles in GameBoard which will be scanned and
	 * drawn when updateBoard is called.
	 */
	 GameBoard.addObstacle(xPosition, yPosition, racerID);
		//This method addObstacle() would add the position of the new LightTrail to the
		//list of obstancles.
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
}