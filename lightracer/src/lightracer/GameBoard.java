/**
 * 
 */
package lightracer;

/**
 * @author jungwankim
 *
 */
public class GameBoard {

	private Racer racer2;
	private Racer racer;
	private Obstacle obstacle;
	private Map map;

	/**
	 * 
	 */
	public GameBoard(Map map2, Obstacle obstacle2, Player player3, Player player4, Racer racer3, Racer racer4) {
		map = map2;
		// TODO Auto-generated constructor stub
		obstacle = obstacle2;
		racer2 = racer3;
		racer = racer4;
	}

}
