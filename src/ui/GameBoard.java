package ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import logic.ControllerMaker;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	
	private static GameBoard b;
	private static JFrame frame;
	private static boolean gameOver;
	private static ArrayList<ArrayList<GameButton>> buttonMatrix;
	
	public GameBoard(){
		super();

		this.setPreferredSize( new Dimension( 400, 400 ) );
		
		frame = new JFrame( "Stratego" );
		setUpGridBag();
		frame.setResizable( false );
		frame.setLocation( 600, 200 );
		frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frame.setVisible( true );
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent windowEvent) {
		    	System.exit( 0 );
		    }
		});
		frame.pack();
		ControllerMaker.create(frame, this);
	}
	
	
	public void setUpGridBag(){
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		frame.setLayout( gridbag );
		
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = GridBagConstraints.REMAINDER;
			JMenuBar menus = getMenus();
			gridbag.setConstraints( menus, c );
			frame.add( menus );
		c.gridheight = GridBagConstraints.REMAINDER;
		c.gridwidth = 1;
			gridbag.setConstraints( this, c );
			frame.add( this );
	}
	

	public JMenuBar getMenus(){
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu( "File" );
		JMenuItem newGameOpt = new JMenuItem( "New Game" );
			newGameOpt.addActionListener( new ActionListener(){
				public void actionPerformed( ActionEvent e ){
					frame.dispose();
					b = new GameBoard();
				}
			});
			
		JMenuItem quitOpt = new JMenuItem( "Quit" );
			quitOpt.addActionListener( new ActionListener(){
				public void actionPerformed( ActionEvent e ){
					System.exit( 0 );
				}
			});
		
		fileMenu.add( newGameOpt );
		fileMenu.add( quitOpt );
		menuBar.add( fileMenu );
		
		return menuBar;
	}
	
	public void play(){
		if( gameOver ){
			b.closeGame();
		}
	}
	
	public void closeGame(){
		frame.dispose();
		System.exit( 0 );
	}
	
	public String toString(){
		String s = "";
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				GameButton b = buttonMatrix.get( y ).get( x );
				s += b.getPlayerColor().charAt( 0 );
				s += b.getVal();
				s += b.getVisibility();
				s += ' ';
			}
			s += '\n';
		}
		return s;
	}
	
	public static void main( String[] args ){
		GameBoard b = new GameBoard();
		b.play();
	}
}