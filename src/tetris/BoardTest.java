package tetris;


import java.awt.event.ActionEvent;

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
	


	public BoardTest(int n) {
		for(int i = 0; i < n; i++) {
			createBoard();
		}
	}
	
	public void createBoard() {
		Board b = new Board(40, 32, true);
		TextTetrisView view = new TextTetrisView();
		
		System.out.println(view.convertToText(b));
		System.out.println("\n");
		System.out.println(b.getHeight() + "x" + b.getWidth());
		TetrisFrame frame = new TetrisFrame(b, view);
		tick(b, frame, view);
	}
	
	@SuppressWarnings("serial")
	public void tick(final Board b, final TetrisFrame frame, final TextTetrisView view ) {
		
		final Action doOneStep = new AbstractAction() {
	        public void actionPerformed(ActionEvent e) {
	            b.createRandomBoard(b.getHeight(), b.getWidth());
	            frame.updateTextArea(b, view);
	        }
	    };
	    final Timer clockTimer = new Timer(1000, doOneStep);
	    clockTimer.setCoalesce(true);
	    clockTimer.start();
	    //clockTimer.stop();
		
	}
		
	public static void main(String[] args) {
		new BoardTest(1);
	}

}
