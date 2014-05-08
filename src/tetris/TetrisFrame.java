package tetris;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class TetrisFrame extends JFrame implements BoardListener {
	
	private static final long serialVersionUID = 1L; 
	private TetrisComponent tComponent;
	private Board board;
	private JLabel mainLabel;
	private JLabel gameLabel;
	private JLabel levelLabel;
	private JLabel levelLabel1;
	private JLabel scoreLabel;
	private final JMenuItem pauseItem;
	
	
	public TetrisFrame(Board b, TetrisComponent tComponent) {
		super("YAM Productions");
		board = b;
		this.tComponent = tComponent;
		System.out.println(tComponent);
		board.addBoardListener(tComponent);
		board.addBoardListener(this);
		gameLabel = new JLabel();
		levelLabel = new JLabel();
		levelLabel1 = new JLabel();
		scoreLabel = new JLabel();
		mainLabel = new JLabel();
		pauseItem = new JMenuItem("Pause");
		createGUI(board);

	}
	
	
	private void createGUI(Board b) {
		makeMenubar(this);
		gameLabel.setText("Game is running");
		mainLabel.setText("Next block : Z");
		levelLabel.setText("Level: " + board.getLevel());
		levelLabel1.setText("Level: " + board.getLevel());
		scoreLabel.setText("Score: " + board.getScore());
		this.setLayout(new BorderLayout());
		this.add(levelLabel1, BorderLayout.NORTH);
		JPanel main = new JPanel(new FlowLayout()); //PREFERRED!
		main.add(tComponent);
		main.add(mainLabel);
		//this.add(tComponent, BorderLayout.CENTER);
		this.add(main, BorderLayout.CENTER);
		//this.add(gameLabel, BorderLayout.SOUTH);
		JPanel p = new JPanel(new FlowLayout()); //PREFERRED!
		p.add(gameLabel);
		p.add(levelLabel);
		p.add(scoreLabel);
		this.add(p, BorderLayout.SOUTH);
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

        pauseItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { pause(pauseItem); }
        });
        fileMenu.add(pauseItem);
        
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
    
    private void pause(JMenuItem pauseItem) {
    	if(board.gameOver()) return;
    	board.setPause();
    	if(board.getPause()) {
    		pauseItem.setText("Unpause");
    	}else {
    		pauseItem.setText("Pause");
    	}
    }
    
    /**
     * Method that gets triggered by the board class every time the board changes
     */
	@Override
	public void boardChanged() {
		this.levelLabel.setText("Level: " + board.getLevel());
		this.scoreLabel.setText("Score: " + board.getScore());
		if(board.gameOver()) {
			gameLabel.setText("Game Over!");
		}else if(board.getPause()) {
			gameLabel.setText("Game is paused");
			pauseItem.setText("Unpause");
			
		}else {
			gameLabel.setText("Game is running");
			pauseItem.setText("Pause");
		}
		this.repaint();
			
		
	}

}
