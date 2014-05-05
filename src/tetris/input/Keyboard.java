package tetris.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tetris.Board;

public class Keyboard implements KeyListener {


	private boolean[] keys;
	private Board b;
	public boolean up, down, left, right, space;

	public Keyboard(Board b) {
		this.b = b;
		keys = new boolean[120];
		setUp();
	}
	
	private void setUp() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		for(int i = 0; i < keys.length; i++)
			if(keys[i]) {
				b.playerMovePoly(i);
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	
}
