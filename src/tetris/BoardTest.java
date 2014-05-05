package tetris;

import java.awt.Color;
import java.util.EnumMap;


/**
 * 
 * @author Daniel
 *
 */
public class BoardTest implements Runnable {
	
	private static final Color PURPLE_COLOR = new Color(128,0,128);
	private EnumMap<SquareType, java.awt.Color> mColorMap;
	private Board board;
	private TetrisComponent tComponent;
	private boolean gameOver;
	private Thread thread;


	public BoardTest() {
		gameOver = false;
		setUpColorMap();
		createBoard();
		run();
	}
	
	private void createBoard() {
		board = new Board(20, 26);
		thread = new Thread(this);
		TextTetrisView view = new TextTetrisView();
		tComponent = new TetrisComponent(board, mColorMap);
		System.out.println(view.convertToText(board));
		System.out.println("\n");
		System.out.println(board.getWidth() + "x" + board.getHeight());
		new TetrisFrame(board, tComponent);
	}
	
	/**
	 * The game loop
	 */
	public void run() {
		while(!this.gameOver) {
			board.updateBoard();
		}
		
	}
	
    /**
     * Method setting up the Color Map
     */
    private void setUpColorMap(){
        this.mColorMap = new EnumMap<SquareType, java.awt.Color>(SquareType.class);
        this.mColorMap.put(SquareType.I, Color.CYAN);
        this.mColorMap.put(SquareType.J, Color.BLUE);
        this.mColorMap.put(SquareType.L, Color.ORANGE);
        this.mColorMap.put(SquareType.O, Color.YELLOW);
        this.mColorMap.put(SquareType.Z, Color.RED);
        this.mColorMap.put(SquareType.S, Color.GREEN);
        this.mColorMap.put(SquareType.T, PURPLE_COLOR);
        this.mColorMap.put(SquareType.EMPTY, Color.WHITE);
        this.mColorMap.put(SquareType.OUTSIDE, Color.BLACK);
    }
		
	public static void main(String[] args) {
		new BoardTest();
	}

}
