package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	
	private static Controller cont;
	private static GameBoard b;
	private static JFrame frame;
	private static boolean gameOver;
	private static int [] piecesAry;
	private static ArrayList<ArrayList<GameButton>> buttonMatrix;
	
	public GameBoard(){
		super();
		
		makePiecesAry();
				
		GameBoard.cont = new Controller();
		cont.addGameBoard( this );
		
		this.gameOver = false;
		this.setPreferredSize( new Dimension( 400, 400 ) );
		
		frame = new JFrame( "Stratego" );
		setUpGridBag();
		frame.setResizable( false );
		frame.setLocation( 600, 200 );
		frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		frame.setVisible( true );
		frame.pack();
		
		cont.addFrame( frame );
				
		addButtons();
		addPieces();
		cont.addButtonMatrix( buttonMatrix );
		System.out.println( this );

	}
	
	/*
	 * Param: None
	 * Function: set indices in array
	 * Returns: none
	 */
	public void makePiecesAry(){
		piecesAry = new int[13];
		piecesAry[1] = 1;
		piecesAry[2] = 1;
		piecesAry[3] = 2;
		piecesAry[4] = 3;
		piecesAry[5] = 4;
		piecesAry[6] = 4;
		piecesAry[7] = 4;
		piecesAry[8] = 5;
		piecesAry[9] = 8;
		piecesAry[10] = 6;
		piecesAry[11] = 1;
		piecesAry[12] = 1;
	}
	
	public void addButtons(){
		FlowLayout flow = new FlowLayout();
		flow.setHgap( 0 );
		flow.setVgap( 0 );
		this.setLayout( flow );
		
		buttonMatrix = new ArrayList<ArrayList<GameButton>>();
		for( int i=0; i<10; i++ )
			buttonMatrix.add( new ArrayList<GameButton>() );
		
		for( int y=0; y<10; y++ ){
			for( int x=0; x<10; x++ ){
				final GameButton button = new GameButton( 40, x, y, cont );
				button.addActionListener( new ActionListener(){
					public void actionPerformed( ActionEvent e ){
						button.clicked();
					}
				});
				button.setBackground( Color.BLACK );
				
				buttonMatrix.get( x ).add( button );
				this.add( button );
			}
		}
		//this.repaint();
	}
	
	public void addPieces(){
		String player = "NONE";
		/*
		 * 	1 = Showing only to Red
		 *  2 = Showing only to Blue
		 *  3 = Showing to both
		 */
		for( int y=0; y<10; y++ )
			for( int x=0; x<10; x++ )
				buttonMatrix.get( x ).get( y ).setPiece( new Piece( 3,  '~' , x, y, player, cont ) );

		/*
		 * Randomly placing pieces in the 40 spaces on both sides
		 */
		player = "RED";
		Random rand = new Random();
		for( int y=0; y<4; y++ ){
			for( int x=0; x<10; x++ ){
				int id = rand.nextInt( 12 ) + 1;
				while( piecesAry[id] == 0 )
					id = rand.nextInt(12) + 1;
				piecesAry[id] --;
				char c = 'X';
				if( id > 9 ){
					if( id == 10 )
						c = 'B';
					if( id == 11 )
						c = 'F';
					if( id == 12 )
						c = 'S';
				} else {
					c = ("" + id).charAt( 0 );
				}
				//FIXME
				buttonMatrix.get( x ).get( y ).setPiece( new Piece( 3, c , x, y, player, cont ) );
			}
		}

/* now right blue part */
		makePiecesAry();
		player = "BLUE";
		addHumanPlayer(player);
		
/* now set up neutral zone */
		player = "NONE";
		/*
		 * The spaces you aren't allowed to move into
		 */
		for( int y=4; y<6; y++ )
			for( int x=2; x<8; x++ ){
				if( x==4 )
					x = 6;
				buttonMatrix.get( x ).get( y ).setPiece( new Piece( 3, 'X', x, y, player, cont ) );
			}
	}
	
	public void addHumanPlayer(String player){
		int yMin = 0, yMax = 0;
		if( player.equals( "BLUE" ) ){
			yMin = 6;
			yMax = 10;
		}else{
			;
		}
		
		cont.makePiecesAry();
		
		for( int y=yMin; y<yMax; y++ )
			for(int x=0; x<10; x++ ){
				buttonMatrix.get( x ).get( y ).setPiece( new Piece( 3,  '~' , x, y, "NONE", cont ) );
				buttonMatrix.get( x ).get( y ).setReady( false );
			}
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
		
			
		/**/
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
				s += b.getPiece().getPlayer().charAt( 0 );
				s += b.getPiece().getVal();
				s += b.getPiece().visStatus();
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