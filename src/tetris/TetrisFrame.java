package tetris;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;


public class TetrisFrame extends JFrame {
	
	private JTextArea textArea;
	private static final long serialVersionUID = 1L; //
	
	public TetrisFrame(Board b, TextTetrisView tView) {
		super("YAM Productions");
		textArea = new JTextArea(b.getWidth(), b.getHeight()); // int rows, int cols
		createGUI(b, tView);
	}
	
	private void createGUI(Board b, TextTetrisView tView) {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new FlowLayout());
		makeMenubar(this);
		textArea.setFont(new Font("monospaced", Font.PLAIN, 14));
		textArea.setText(tView.convertToText(b));
		this.setLayout(new BorderLayout());
		this.add(textArea, BorderLayout.CENTER);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFocusable(true);
		this.setLocationRelativeTo(null); //centers the frame
		this.setVisible(true);
		
	}
	
	public void updateTextArea(Board b, TextTetrisView tView) {
		textArea.setText(tView.convertToText(b));	
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
        saveItem.addActionListener(new SaveActionListener());
        fileMenu.add(saveItem);
        
        //Help menu
        JMenu helpMenu = new JMenu("Help");
        menubar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("About Sirtet");
        aboutItem.addActionListener(new AboutActionListener());
        helpMenu.add(aboutItem);

    }
    
   
	class OpenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.out.println("Open");
		}
	}
	
	class QuitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// perform quit action
		}
	}
	
	class SaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// perform quit action
		}
	}

	class AboutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// perform quit action
		}
	}
	
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }

}
