package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EnumMap;

import javax.swing.JComponent;

public class TetrisComponent extends JComponent implements BoardListener {

	private static final long serialVersionUID = 1L;
	private Board board;
    private static final int BLOCK_SIZE = 40;
    private static final int BLOCK_SPACING = 1;
    private final EnumMap<SquareType, java.awt.Color> mColorMap;
	
	public TetrisComponent(Board board, EnumMap<SquareType, java.awt.Color> mColorMap) {
		this.board = board;
		this.mColorMap = mColorMap;
	}
	
	
	@Override
	public Dimension getPreferredSize() {
		super.getPreferredSize();

        return new Dimension(this.board.getHeight() * (BLOCK_SIZE + BLOCK_SPACING), 
        		this.board.getWidth() * (BLOCK_SIZE + BLOCK_SPACING));
    }
		
	
	
    @Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g; //castas till Graphics2D

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        for (int y = 0; y < this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
            	System.out.println(board.getSquaretype(y, x));
                g2d.setColor(this.mColorMap.get(board.getSquaretype(y, x)));
                g2d.fillRect((BLOCK_SIZE + BLOCK_SPACING) * y,
                        (BLOCK_SIZE + BLOCK_SPACING) * x,
                        BLOCK_SIZE, BLOCK_SIZE);
            }
        }
   
	}
    
	@Override
	public void boardChanged() {
		this.repaint();		
	}
}
