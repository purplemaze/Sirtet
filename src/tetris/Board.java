package tetris;

import java.awt.Point;
import java.util.ArrayList;
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
	private Point fallingPosition;
	private boolean fallingTetro;
	private ArrayList<BoardListener> boardListeners; 
	
	/**
	 * Constructor 1
	 * Empty Board
	 * @param height
	 * @param width
	 */
	public Board(int height, int width) {
		this.rand = new Random();
		this.height = height;
		this.width = width;
		fallingTetro = false;
		boardListeners = new ArrayList<BoardListener>();
		squares = new SquareType[height][width];
		falling = null;
		fallingPosition = new Point(0,0);
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
		boardListeners = new ArrayList<BoardListener>();
		squares = new SquareType[height][width];
		falling = null;
		fallingPosition = null;
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
		notifyListeners();
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
	
	public void setFalling(Poly p, int y, int x) {
		falling = p;
	}
	
	/**
	 * Returns the falling Poly
	 * @return
	 */
	public Poly getFalling() {
		return falling;
	}
	
			
	/**
	 * 
	 * @return
	 */
	public Point getFallingPostiton() {
		return fallingPosition;
	}
	
	/**
	 * 
	 * @param bl
	 */
	public void addBoardListener(BoardListener bl) {
		boardListeners.add(bl);
	}
	
	/**
	 * 
	 */
	private void notifyListeners() {
		if(boardListeners.isEmpty()) {
			return;
		}else {
			for(BoardListener element: boardListeners) {
				element.boardChanged();
			}
		}
	}
	
	/**
	 * Creates a new falling Poly
	 * @return
	 */
	private Poly createNewFalling() {
        TetrominoMaker tetroMaker = new TetrominoMaker();
        Poly falling;
        int temp = 1 + rand.nextInt(SquareType.values().length -2);
        falling = tetroMaker.getPoly(temp);
        //centers the polly
        this.fallingPosition.setLocation(0, getHeight()/2 - 2);  //set x = y , y = x
       // this.fallingPosition.setLocation(0, getHeight()/2);  //set x = y , y = x
        return falling;
    }
		
	public void tick() {	
		this.falling = createNewFalling();	
		notifyListeners();
	}
	
	/*
	public void tick() {
		if(fallingTetro) {
			if(fallingPositionY == getHeight() -3 || fallingPositionX == getWidth() -3) {
				fallingTetro = false;
			}
			SquareType type = getSquaretype(fallingPositionY, fallingPositionX);
			squares[fallingPositionY][fallingPositionX] = SquareType.EMPTY; 
			fallingPositionX ++;
			squares[fallingPositionY][fallingPositionX] = type; 
		}else {
			int temp = 1 + rand.nextInt(SquareType.values().length -2);
			int y = getHeight()/2; 
			int x = 1;
			fallingPositionY= y;
			fallingPositionX= x;
			squares[y][x] = SquareType.values()[temp]; 
			fallingTetro = true;
		}
		
		notifyListeners();
	}
	*/
	
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
