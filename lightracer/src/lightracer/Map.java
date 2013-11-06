package lightracer;

import java.io.*;
import java.util.Scanner;

public class Map {

	private int heightMap = 50;
	private int widthMap = 50;
	private int[][] boardMap;
	private Obstacle[][] boardObstacle;
	private File mapPath;
	private static Scanner lineReader;
	
	
	public int[][] getBoardMap() {
		return boardMap;
	}
	
	public Obstacle[][] getBoardObstacle() {
		return boardObstacle;
	}

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
			lineReader = new Scanner(new File(mapPath + "/src/defaultMap.txt"));
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
		    initializeSize();
		    
			for (int yPixel = 0; yPixel < heightMap; yPixel++) {
				for (int xPixel = 0; xPixel < widthMap; xPixel++) {
					int valueinPosition = Integer.parseInt(temparyArray[yPixel].substring(xPixel, xPixel+1));
					boardMap[xPixel][yPixel] = valueinPosition;
					boardObstacle[xPixel][yPixel] = null;
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
	public void initializeSize(){
		 boardMap = new int[widthMap][heightMap];
		 boardObstacle = new Obstacle[widthMap][heightMap];		 
	}
}
