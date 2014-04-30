package tetris;

import java.util.Random;

/**
 * Game Board Class
 * 
 * @author Daniel C
 * @version 1
 *
 */
public class Board {
	
	private SquareType[][] squares;
	private final int height;
	private final int width;
	private Random rand;
	private Poly falling;
	private SquareType[][] fallingPosition;
	private int fallingPositionY;
	private int fallingPositionX;
	
	/**
	 * Constructor 1
	 * Empty Board
	 * @param height
	 * @param width
	 */
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		squares = new SquareType[height][width];
		falling = null;
		fallingPosition = new SquareType[height][width];
		createBoard(height, width);	
	}
	
	/**
	 * Constructor 2
	 * Random Board
	 * @param height
	 * @param width
	 */
	public Board(int height, int width, boolean random) {
		this.rand = new Random();
		this.height = height;
		this.width = width;
		squares = new SquareType[height][width];
		falling = null;
		fallingPosition = new SquareType[height][width];
		createRandomBoard(height, width);		
	}
	
	/**
	 * Creates a random game board
	 * @param cols
	 * @param rows
	 */
	public void createRandomBoard(int cols, int rows) {
		for(int y = 0; y < cols; y++) {
			for(int x = 0; x < rows; x++) {
				if(y == 0 || x == 0) {
					squares[y][x] = SquareType.OUTSIDE;
				}else if(y == cols-1 || x == rows-1) {
					squares[y][x] = SquareType.OUTSIDE;
				}else {
					squares[y][x] = SquareType.values()[rand.nextInt(SquareType.values().length -1)];
				}
			}
		}
	}
	
	/**
	 * Creates an empty game board
	 * @param cols
	 * @param rows
	 */
	private void createBoard(int cols, int rows) {
		for(int y = 0; y < cols; y++) {
			for(int x = 0; x < rows; x++) {
				if(y == 0 || x == 0) {
					squares[y][x] = SquareType.OUTSIDE;
				}else if(y == cols-1 || x == rows-1) {
					squares[y][x] = SquareType.OUTSIDE;
				}else {
					squares[y][x] = SquareType.EMPTY;
				}
			}
		}
	}
	
	/**
	 * Returns the height of the Board
	 * @return
	 */
	public int getHeight() {
		return height;	
	}
	
	/**
	 * Returns the width of the Board
	 * @return
	 */
	public int getWidth() {
		return width;	
	}
	
	/**
	 * Returns the SquareType at xy coordinate
	 * @param y
	 * @param x
	 * @return
	 */
	public SquareType getSquaretype(int y, int x) {
		return squares[y][x];
	}
	
	/**
	 * Returns the squares
	 * @return
	 */
	public SquareType[][] getBoard() {
		return squares;
	}
	
	/**
	 * 
	 * @return
	 */
	public Poly getFalling() {
		return falling;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFallingPositionY() {
		return fallingPositionY;	
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFallingPositionX() {
		return fallingPositionX;	
	}
			
	/**
	 * 
	 * @return
	 */
	public SquareType[][] getFallingPostiton() {
		return fallingPosition;
	}
	
	public static void main(String[] args) {
		int height = 22;
		int width = 12;
		int counter = 0;
		Board b = new Board(height, width);
		System.out.println(b.squares.length);
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				//System.out.println(y);
				System.out.println(b.squares[y][x]);
				counter++;
			}
		}
		System.out.println(counter);
	}

}
