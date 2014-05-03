package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class TetrisFrame extends JFrame {
	
	private static final long serialVersionUID = 1L; 
	private TetrisComponent tComponent;
	
	
	public TetrisFrame(Board b, TetrisComponent tComponent) {
		super("YAM Productions");
		this.tComponent = tComponent;
		System.out.println(tComponent);
		b.addBoardListener(tComponent);
		createGUI(b);

	}
	
	
	private void createGUI(Board b) {
		makeMenubar(this);
		
		this.setLayout(new BorderLayout());
		//this.add(textArea, BorderLayout.SOUTH);
		this.add(tComponent, BorderLayout.CENTER);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLocationRelativeTo(null); //centers the frame
		this.setVisible(true);
		
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
        
        JMenuItem quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { quit(); }
        });
        fileMenu.add(quitItem);
        
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);
        
        //Help menu
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("About Sirtet");
        helpMenu.add(aboutItem);

    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }

}
