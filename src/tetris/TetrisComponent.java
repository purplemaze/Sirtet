package tetris;

import tetris.input.Keyboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EnumMap;

import javax.swing.JComponent;

public class TetrisComponent extends JComponent implements BoardListener {

	private static final long serialVersionUID = 1L;
	private Board board;
    private static final int BLOCK_SIZE = 15;
    private static final int BLOCK_SPACING = 1;
    private final EnumMap<SquareType, java.awt.Color> mColorMap;
    private Keyboard keyboard;
	
	public TetrisComponent(Board board, EnumMap<SquareType, java.awt.Color> mColorMap) {
		this.board = board;
		this.mColorMap = mColorMap;
		keyboard = new Keyboard(board);
		this.addKeyListener(keyboard);
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		super.getPreferredSize();

        return new Dimension((this.board.getWidth() -2)  * (BLOCK_SIZE + BLOCK_SPACING), 
        		(this.board.getHeight() -2) * (BLOCK_SIZE + BLOCK_SPACING));
    }
		
    @Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g; //castas till Graphics2D

        g2d.setColor(Color.BLACK);
        //g2d.drawRect(0, 0, this.getWidth() , this.getHeight());
        g2d.fillRect(0, 0, this.getWidth() +1 , this.getHeight() +1);
        //paint the board
        for (int x = 0; x < this.board.getWidth() -1; x++) {
            for (int y = 0; y < this.board.getHeight()-1; y++) {
            	SquareType temp = board.getSquaretype(x+1, y+1);
                g2d.setColor(this.mColorMap.get(temp)); //compensate for OUTSIDE block
                //if(temp != SquareType.EMPTY) {
                if(true) {
	                g2d.fillRect((BLOCK_SIZE + BLOCK_SPACING) * x,
	                        (BLOCK_SIZE + BLOCK_SPACING) * y,
	                        BLOCK_SIZE, BLOCK_SIZE);
	                }
            }
        }
        if(board.getFalling() != null)
        paintFalling(g2d);    
	}
    
    /**
     * Paints the falling tetromino
     * @param g2d
     */
    private void paintFalling(Graphics2D g2d) {
        Poly poly = board.getFalling();
        for (int x = 0; x < poly.getPolyLength(); x++) {
        	for(int y = 0; y < poly.getPolyLength(); y++ ) {
        		if(poly.getPoly()[x][y] != SquareType.EMPTY) {
        			g2d.setColor(this.mColorMap.get(poly.getPoly()[x][y]));
        			g2d.fillRect((BLOCK_SIZE + BLOCK_SPACING ) * (board.getFallingPostiton().x + x-1),  //compensate for OUTSIDE block
                            (BLOCK_SIZE + BLOCK_SPACING) * (board.getFallingPostiton().y + y-1),
                            BLOCK_SIZE, BLOCK_SIZE);
                    
                    /*
                    g2d.fillRect((BLOCK_SIZE) * (board.getFallingPostiton().x + x-1),  //compensate for OUTSIDE block
                            (BLOCK_SIZE) * (board.getFallingPostiton().y + y-1),
                            BLOCK_SIZE, BLOCK_SIZE);
                            */
        		}
        	}
        }  
    }
     
	@Override
	public void boardChanged() {
		this.repaint();	
	}
}
