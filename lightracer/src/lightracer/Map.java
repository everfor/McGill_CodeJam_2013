
package lightracer;

import java.io.*;
import java.util.Scanner;

public class Map {

	private final static int heightMap = 3;
	private final static int widthMap = 3;
	public final static int[][] boardMap = new int [widthMap][heightMap];
	private File mapPath;
	private static Scanner lineReader;
	
	/*
	 * default map constructor
	 */
	
	public Map() {
		openMapfile();
		readMapfile();
		closeMapfile();
	}

	/*
	public Map(File mapFile) {
		selectMapfile(mapFile);
		readMapfile();
		closeMapfile();
	}	
	*/
	
	public void openMapfile() {
		try {
			mapPath = new File("").getAbsoluteFile();
			lineReader = new Scanner(new File(mapPath + "/src/map/defaultMap.txt"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readMapfile() {
		try {
			String[] temparyArray = new String[heightMap];
			while (lineReader.hasNext()) {
				for (int line = 0; line < heightMap; line++) {
					temparyArray[line] = lineReader.nextLine();
				}
			}
		
			for (int yPixel = 0; yPixel < heightMap; yPixel++) {
				for (int xPixel = 0; xPixel < widthMap; xPixel++) {
					boardMap[xPixel][yPixel] = Integer.parseInt(temparyArray[yPixel].substring(xPixel, xPixel+1));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeMapfile() {
		lineReader.close();
	}
	
	/*
	public void selectMapfile() {
		
	}
	*/
}
