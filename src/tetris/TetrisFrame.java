package tetris;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class TetrisFrame extends JFrame implements BoardListener {
	
	private static final long serialVersionUID = 1L; 
	private TetrisComponent tComponent;
	private Board board;
	private JLabel gameLabel;
	private JLabel levelLabel;
	
	
	public TetrisFrame(Board b, TetrisComponent tComponent) {
		super("YAM Productions");
		board = b;
		this.tComponent = tComponent;
		System.out.println(tComponent);
		board.addBoardListener(tComponent);
		board.addBoardListener(this);
		gameLabel = new JLabel();
		levelLabel = new JLabel();
		createGUI(board);

	}
	
	
	private void createGUI(Board b) {
		makeMenubar(this);
		gameLabel.setText("Game is running");
		levelLabel.setText("		Level: 1");
		this.setLayout(new BorderLayout());
		this.add(levelLabel, BorderLayout.NORTH);
		this.add(tComponent, BorderLayout.CENTER);
		this.add(gameLabel, BorderLayout.SOUTH);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLocationRelativeTo(null); //centers the frame
		this.setVisible(true);
		
		//Set focus on tetris component in order for keyboard to work
		this.tComponent.requestFocusInWindow();
		
	}
    
    /**
     *  Create the Swing menu and its content.
     */
    private void makeMenubar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        //Now we are ready to create a menu and add it to the menu bar:
        JMenu fileMenu = new JMenu("File");
        menubar.add(fileMenu);
        
        JMenuItem saveItem = new JMenuItem("New Game");
        saveItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { restartGame(); }
        });
        fileMenu.add(saveItem);
        
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { quit(); }
        });
        fileMenu.add(quitItem);
        
        
        //Help menu
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("About Sirtet");
        helpMenu.add(aboutItem);

    }
    /**
     * Restarts the game
     */
    private void restartGame() {
    	board.clearBoard();
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }
    
    /**
     * Method that gets triggered by the board class every time the board changes
     */
	@Override
	public void boardChanged() {
		if(board.gameOver()) {
			gameLabel.setText("Game Over!");
		}else {
			gameLabel.setText("Game is running");
		}
		this.repaint();
			
		
	}

}
