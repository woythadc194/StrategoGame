/**
  * @author Dylan C. Woythal
  */

package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import logic.Controller;
import logic.SetupLogic;

@SuppressWarnings("serial")
public class Board extends JPanel {
	
	private static Board b;
	private static JFrame frame;
	private static JPanel field;
	private static JPanel graveyard;
	private static boolean gameOver;
	
	public Board(){
		super();

		this.setPreferredSize( new Dimension( 400, 400 ) );
		
		FlowLayout flow = new FlowLayout( );
		flow.setHgap( 0 );
		flow.setVgap( 0 );
		
		field = new JPanel();
		field.setLayout( flow );
		field.setPreferredSize( new Dimension( 580, 400 ) );

		graveyard = new JPanel();
		graveyard.setPreferredSize( new Dimension( 160, 400 ) );
		
		field.add( this );
		field.add( new SpacerButton( new Dimension ( 20, 400 ) ) );
		field.add( graveyard );
		
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
		
		Controller cont = new Controller(frame, this);
		cont.setSULogic( new SetupLogic( cont ) );
	}
	
	public static JPanel getGraveyard(){
		return Board.graveyard;
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
			gridbag.setConstraints( field, c );
			frame.add( field );
	}
	

	public JMenuBar getMenus(){
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu( "File" );
		JMenuItem newGameOpt = new JMenuItem( "New Game" );
			newGameOpt.addActionListener( new ActionListener(){
				public void actionPerformed( ActionEvent e ){
					frame.dispose();
					b = new Board();
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
				GameButton button = Controller.getButtonMatrix()[ y ][ x ];
				s+= ( button + " " );
			}
			s += "\n";
		}
		return s;
	}
}