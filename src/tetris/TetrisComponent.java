package tetris;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class TetrisComponent extends JComponent {

	private static final long serialVersionUID = 1L;
	private Board board;
	
	public TetrisComponent(Board board) {
		this.board = board;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return null;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
	}
}
