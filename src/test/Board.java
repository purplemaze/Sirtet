package test;

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
	private Point fallingPosition; //top left corner of the Polyomino
	private boolean gameOver;
	private ArrayList<BoardListener> boardListeners; 
	
	/**
	 * Constructor 1
	 * Empty Board
	 * @param height
	 * @param width
	 */
	public Board(int width, int height) {
		this.rand = new Random();
		this.height = height;
		this.width = width;
		boardListeners = new ArrayList<BoardListener>();
		squares = new SquareType[width][height];
		falling = null;
		fallingPosition = new Point(0,0);
		gameOver = false;
		createEmptyBoard(width, height);	
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
	private void createEmptyBoard(int cols, int rows) {
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
	 * @param x
	 * @param y
	 * @return
	 */
	public SquareType getSquaretype(int x, int y) { 
		return squares[x][y];
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
        this.fallingPosition.setLocation(getWidth()/2 - 2, 0);  //set x = y , y = x
        return falling;
    }
	
	
	/**
	 * Try's to move the falling Poly
	 * @param poly
	 * @return
	 */
	private boolean tryMove(Poly poly) {		
		if(movePoly(poly)){
			this.fallingPosition.setLocation(this.fallingPosition.getX(), this.fallingPosition.getY() +1);
			return true;
		}
		System.out.println(fallingPosition.getY());
		addPolyToBoard(poly);
		return false;
	}
	
	/**
	 * 
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePoly(Poly poly) {
		//använde PolyYlimit för att kolla alla koordinater i x led
		boolean clearToMove = true;
		//int yLimit = calcPolyYLimit(poly);
		int yLimit = poly.getPolyYlimit();
		for(int x = 0; x < poly.getPolyLength(); x++){
			if(poly.getPoly()[x][yLimit] != SquareType.EMPTY) {
				if(!(this.getSquaretype(this.fallingPosition.x + x, this.fallingPosition.y + yLimit +1) == SquareType.EMPTY)) //true if empty
					return false;
			}
		}
		/*
		if(this.getSquaretype(this.fallingPosition.x, this.fallingPosition.y + yLimit +1) == SquareType.EMPTY)
			return true;
		*/
		return clearToMove;
		
	}
	
	/**
	 * Calculates the lowest part of the poly on the "y axis"
	 * @param poly
	 */
	private int calcPolyYLimit(Poly poly) {
		int yLimit = 0;
		for(int y = 0; y < poly.getPolyLength(); y++) {
			for(int x = 0; x < poly.getPolyLength(); x++) {
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					yLimit = y;
				}
			}
		}
		return yLimit;
	}
	
	/**
	 * Adds a fallen Poly to the game board
	 */
	private void addPolyToBoard(Poly poly) {
		for(int x = 0; x < poly.getPolyLength(); x++) {
			for(int y = 0; y < poly.getPolyLength(); y++) {
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					squares[this.getFallingPostiton().x + x][this.getFallingPostiton().y +y] = poly.getPoly()[x][y];
				}
			}
		}
	}
	
	/**
	 * Updates the game
	 */
	public void tick() {
		if(falling == null){
			Poly tempFalling = createNewFalling();
			if(tryMove(tempFalling)) {
				this.falling = tempFalling;	
			}else {
				System.out.println("Game over");
				this.clearBoard();
			}
		}else {
			if(tryMove(this.falling)) {
				
			}else {
				this.falling = null;
			}
		}
		notifyListeners();
	}
	
	/**
	 * Clears the board
	 */
	public void clearBoard() {
		createEmptyBoard(this.width, this.height);
	}

	public static void main(String[] args) {
		int height = 22;
		int width = 12;
		int counter = 0;
		Board b = new Board(width, height);
		System.out.println(b.squares.length);
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				//System.out.println(y);
				System.out.println(b.squares[x][y]);
				counter++;
			}
		}
		System.out.println(counter);
	}
}
