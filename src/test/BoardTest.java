package test;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.EnumMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;


/**
 * Skriv en testklass, BoardTest, som skapar en tom spelplan, 
 * konverterar den till en sträng med hjälp av TetrisTextView-klassen, och skriver ut
 * @author Daniel
 *
 */
public class BoardTest{
	
	private static final Color PURPLE_COLOR = new Color(128,0,128);
	private EnumMap<SquareType, java.awt.Color> mColorMap;

	public BoardTest(int n) {
		setUpColorMap();
		for(int i = 0; i < n; i++) {
			createBoard();
		}
	}
	
	private void createBoard() {
		Board b = new Board(20, 26);
		TextTetrisView view = new TextTetrisView();
		TetrisComponent tComponent = new TetrisComponent(b, mColorMap);
		
		System.out.println(view.convertToText(b));
		System.out.println("\n");
		System.out.println(b.getWidth() + "x" + b.getHeight());
		TetrisFrame frame = new TetrisFrame(b, tComponent);
		tick(b, frame);
	}
	
	@SuppressWarnings("serial")
	private void tick(final Board b, final TetrisFrame frame) {
		
		final Action doOneStep = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            //b.createRandomBoard(b.getHeight(), b.getWidth());
	        	b.tick();
	            //frame.updateComponent();
	        }
	    };
	    final Timer clockTimer = new Timer(50, doOneStep);
	    clockTimer.setCoalesce(true);
	    clockTimer.start();
	    //clockTimer.stop();
		
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
		new BoardTest(1);
	}

}
