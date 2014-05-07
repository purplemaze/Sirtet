package tetris;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

/**
 * Game Board Class
 * 
 * @author Daniel C
 * @version 1
 *
 */
public class Board {
	
	private static final int TICK_COUNT = 200;
	private SquareType[][] squares;
	private final int height;
	private final int width;
	private Random rand;
	private Poly falling;
	private Point fallingPosition; //top left corner of the Polyomino
	private boolean gameOver;
	private ArrayList<BoardListener> boardListeners; 
    /**
     * The tick Action, determines which function to call when the game updates.
     */
    @SuppressWarnings("serial")
	private final Action tick = new AbstractAction() {
		public void actionPerformed(ActionEvent e) {
            tick();
        }
    };
    
    /**
     * The timer that decides when to update the game.
     */
    private final Timer clockTimer = new Timer(TICK_COUNT, tick);
	
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
		notifyListeners();
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
	 * Sets a SquareType at given x and y
	 * @param x
	 * @param y
	 * @param type
	 */
	private void setSquareTypeAt(int x, int y, SquareType type) {
		this.squares[x][y] = type;
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
        this.fallingPosition.setLocation(getWidth()/2 - falling.getPolyLength()/2, 0);  //set x = y , y = x
        return falling;
    }
	
	/**
	 * Try's to move the falling Poly
	 * @param poly
	 * @return
	 */
	private boolean tryMove(Poly poly) {		
		if(movePolyY(poly)){
			this.fallingPosition.setLocation(this.fallingPosition.getX(), this.fallingPosition.getY() +1);
			return true;
		}
		System.out.println(fallingPosition.getY());
		return false;
	}
	
	/**
	 * Try's to move the falling Poly
	 * @param poly
	 * @param i: if i == 0 --> move left, if i == 1 --> move right
	 */
	private void tryMoveX(Poly poly, int i) {
		if(poly == null)
			return;
		//Move left
		if(i == 0 && movePolyLeft(poly))
			this.fallingPosition.setLocation(this.fallingPosition.getX() -1 , this.fallingPosition.getY());
		//Move right
		if(i == 1 && movePolyRight(poly))
			this.fallingPosition.setLocation(this.fallingPosition.getX() +1 , this.fallingPosition.getY());
	}
	
	/**
	 * Player movement of the Poly
	 * @param i
	 */
	public void playerMovePoly(int i) {
		//37 = left
		if(i == 37)
			tryMoveX(this.falling, 0);
		//39 = rigth
		if(i == 39)
			tryMoveX(this.falling, 1);
		//40 = down
		//check movePoly() first
		if(i == 40 && this.falling != null)
			tryMove(this.falling);
		
		if(i == 38 && this.falling != null) {
			Poly temp = this.falling.rotateRight();
			if(this.movePolyY(temp) && this.movePolyRight(temp) )
				this.falling = this.falling.rotateRight();
		}
		
		this.notifyListeners();
	}
	
	/**
	 * Checks if it is possible to move the Poly to the left
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePolyRight(Poly poly) {
		boolean clearToMove = true;
		for(int x = 0; x < poly.getPolyLength(); x++){
			for(int y = 0; y < poly.getPolyLength(); y++){
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					if(!(this.getSquaretype(this.fallingPosition.x + x + 1, this.fallingPosition.y + y) == SquareType.EMPTY)) 
						return false;		
				}
			}
		}
		return clearToMove;
	}
	
	/**
	 * Checks if it is possible to move the Poly to the left
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePolyLeft(Poly poly) {
		boolean clearToMove = true;
		for(int x = 0; x < poly.getPolyLength(); x++){
			for(int y = 0; y < poly.getPolyLength(); y++){
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					if(!(this.getSquaretype(this.fallingPosition.x + x - 1, this.fallingPosition.y + y) == SquareType.EMPTY)) 
						return false;		
				}
			}
		}
		return clearToMove;
	}
	
	/**
	 * 
	 * @param poly
	 * @param point
	 * @return
	 */
	private boolean movePolyY(Poly poly) {
		boolean clearToMove = true;
		for(int x = 0; x < poly.getPolyLength(); x++){
			for(int y = 0; y < poly.getPolyLength(); y++){
				if(poly.getPoly()[x][y] != SquareType.EMPTY) {
					if(!(this.getSquaretype(this.fallingPosition.x + x, this.fallingPosition.y + y +1) == SquareType.EMPTY)) //true if empty
						return false;
				}
			}
		}
		return clearToMove;
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
	
	private void checkRows(Poly poly) {;
		for(int y = 1; y < this.height -1 ; y++) {
			boolean fullRow = true;
			for(int x = 1; x < this.width -1; x++) {
				if(this.squares[x][y] == SquareType.EMPTY)
					fullRow = false;
			}
			if(fullRow) { 
				removeRow(y);
			}
		}
		this.notifyListeners();
	}
	
	/**
	 * 
	 * @param y
	 */
	private void removeRow(int y) {
		for(int x = 1; x < this.width -1; x++) {
			this.squares[x][y] = SquareType.EMPTY;
		}
		pushRows(y);
	}
	
	/**
	 * Pushes the rows over the deleted row down
	 * @param maxY
	 */
	private void pushRows(int y) {
		System.out.println(y);
        for (int i = y; i > 0 ; i--) {
            for (int x = 1; x < this.width - 1; x++) {
            	if(this.getSquaretype(x, i - 1) == SquareType.OUTSIDE) return;
                this.setSquareTypeAt(x, i, this.getSquaretype(x, i - 1));
            }
        }
    }
	 
	/**
	 * Methods that gets called upon a game tick update.
	 */
    public void updateBoard(){
        clockTimer.setCoalesce(true);
        clockTimer.start();
    }
	
	/**
	 * Updates the game board
	 */
	public void tick() {
		//checks rows
		checkRows(this.falling);
		//Game over
		if(this.gameOver == true)
			return;
		//Create a new falling Poly
		if(falling == null){
			Poly tempFalling = createNewFalling();
			if(tryMove(tempFalling)) {
				this.falling = tempFalling;	
			}else {
				System.out.println("Game over");
				this.gameOver = true;
			}
		//Try to move the falling Poly	
		}else {
			if(tryMove(this.falling)) {
				
			}else {
				addPolyToBoard(this.falling);
				this.falling = null;
			}
		}
		notifyListeners();
	}
	public boolean gameOver() {
		return this.gameOver;
	}
	
	/**
	 * Clears the board
	 */
	public void clearBoard() {
		this.gameOver = false;
		this.falling = null;
		createEmptyBoard(this.width, this.height);
	}
	/*
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
	*/
}
