package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class GameButton extends JButton{

	private Piece p;
	private int xLocal;
	private int yLocal;
	private Color color;
	private int size;
	private static Controller cont;
	private boolean moveToFromSelected;
	private boolean ready;
	
	public static boolean optPaneOpen = false;
	
	public GameButton( int size, int xLocal, int yLocal, Controller cont ){
		super();
		this.ready = true;
		this.moveToFromSelected = false;
		this.setPreferredSize( new Dimension( size, size ) );
		this.size = size;
		this.xLocal = xLocal;
		this.yLocal = yLocal;
		this.setBorder( null );
		GameButton.cont = cont;
		this.color = Color.black;
	}
	
	public void setMovable( boolean moveable){
		this.moveToFromSelected = moveable;
		this.repaint();
	}
	
	public void setReady( boolean ready ){
		this.ready = ready;
	}
	
	public boolean isReady(){
		return this.ready;
	}
	
	public boolean getMovable(){
		return this.moveToFromSelected;
	}
	
	public void setPiece( Piece p ){
		this.p = p;
		this.repaint();
	}
	
	public int getButtonSize(){
		return this.size;
	}
	
	public void removePiece(){
		this.p = null;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public int getXLocal(){
		return this.xLocal;
	}
	
	public int getYLocal(){
		return this.yLocal;
	}
	
	public Piece getPiece(){
		return this.p;
	}
	
	public void clicked(){
		if( !ready ){
			if( p.getVal()!= '~' ){
				int x = 0;
				for( int index = 0; index < 13; index ++ )
					if( cont.charIndexAry[index] == p.getVal() )
						x = index;
				Controller.piecesAry [ x ] ++;
			}
			if( cont.getSelectedPieceOpt() != 0 ){
				this.setPiece( new Piece( 2, Controller.charIndexAry[cont.getSelectedPieceOpt()] , this.getXLocal(), this.getYLocal(), "BLUE", cont ) );
			} else{
				this.setPiece( new Piece( 3, '~', this.getXLocal(), this.getYLocal(), "NONE", cont ) );
			}
			int targetType = cont.getSelectedPieceOpt();
			if( targetType!= 0 )
				Controller.piecesAry[ targetType ]--;
			Controller.optButtonAry[ targetType ].clicked();
			cont.testReady();
			System.out.println( "PIECE NOT READY" );
		}else if(cont.isReady()){
			if( p.getVal() != 'X' ){
				if( cont.selectionMade == false ){
					if( p.getVal() == '~' || p.getVal() == 'B' || p.getVal() == 'F' )
						return;
					if( cont.redTurn && this.getPiece().getPlayer().equals( "RED" ) )
						cont.setSelectedButton( this );
					else if(!cont.redTurn && this.getPiece().getPlayer().equals( "BLUE" ) )
						cont.setSelectedButton( this );
				}else{
					// same button clicked twice removes it from selection
					if( this.getXLocal()==cont.selectedButton.getXLocal() && this.getYLocal()==cont.getSelectedButton().getYLocal() )
						cont.clearSelectedButton();
					// highlighted in white
					else if( this.moveToFromSelected )
						setUpBattle();
					// same color piece as already selected
					else if( cont.getSelectedButton().getPiece().getPlayer().equals( this.getPiece().getPlayer() ) )
						// but not a bomb or flag
						if( this.getPiece().getVal() == 'B' || this.getPiece().getVal() == 'F' )
							return;
						else
							//set selected button as a different one
							cont.setSelectedButton( this );
				}
			}
		}else{
			System.out.println( "GAME NOT READY" );
		}
	}
	
	private void waitTime( long time ){
		long start = System.currentTimeMillis();
		while( System.currentTimeMillis() - start < time ){
			;
		}
	}
	
	public void setUpBattle(){
		GameButton attacker = cont.getSelectedButton();
		attacker.repaint();
		GameButton defender = this;
		this.getPiece().visStatus();
		this.repaint();
		commenceBattle(attacker, defender ); 
	}
	
	public void commenceBattle( GameButton attacker, GameButton defender ){
		waitTime( 10 );
	
		String result = attacker.getPiece().battle( defender.getPiece() );
		
		if( result.equals( "INVALID" ) ){
			;
		} else if( result.equals( "NEITHER" ) ){
			attacker.setPiece( new Piece( 4, '~', attacker.getXLocal(), attacker.getYLocal(), "BLACK", cont ) );
			defender.setPiece( new Piece( 4, '~', defender.getXLocal(), defender.getYLocal(), "BLACK", cont ) );
		} else if( result.equals( "WIN" ) ){
			JOptionPane.showMessageDialog( this, "GameOver!" );
			System.exit( 0 );
		} else {
			if( result.equals( attacker.getPiece().getPlayer() ) ){
				defender.setPiece( attacker.getPiece() );
			}else{
			}
			attacker.setPiece( new Piece( 4, '~', attacker.getXLocal(), attacker.getYLocal(), "BLACK", cont ) );

		}
		attacker.repaint();
		defender.repaint();
		cont.clearSelectedButton();
		cont.switchTurns();
		System.out.println( cont.getGameBoard() );

	}
	
	public String toString(){
		if( p == null )
			return "X";
		return "" + p.getVal() + "  Player:" + p.getPlayer();
	}
	
	public void paint( Graphics g ){
		Color bg = Color.DARK_GRAY;
		if(p == null){
			bg = Color.DARK_GRAY;
		} else {
			if( p.getPlayer().equals( "RED" ) ) 
				bg = Color.RED;
			else if( p.getPlayer().equals( "BLUE" ) )
				bg = Color.BLUE;
			else if( p.getVal() == 'X' )
				bg = Color.BLACK;
			else
				bg = Color.DARK_GRAY;
		}

		this.color = bg;
		g.setColor( bg );
		g.fillRect( 0, 0, 40, 40 );
		if( p.getVal() == 'X' ){
			if( cont.redTurn )
				g.setColor( Color.RED );
			else
				g.setColor( Color.BLUE );
			g.fillRect( 10, 10, 20, 20 );
		}
		
		if( this.moveToFromSelected == true )
			g.setColor( Color.YELLOW );
		else
			g.setColor( Color.BLACK );
		g.drawRect( 0, 0, 40, 40 );
		g.drawRect( 1, 1, 38, 38 );
		
		if( p.visStatus() >=2 ){
			if( this.getPiece().getVal() == '1' )
				g.fillRect( 18, 8, 5, 25 );
			else if( this.getPiece().getVal() == '2' ){
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
			}else if( this.getPiece().getVal() == '3' ){
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
			}else if( this.getPiece().getVal() == '4' ){
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left
			}else if( this.getPiece().getVal() == '5' ){	
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left
			}else if( this.getPiece().getVal() == '6' ){	
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left	
			}else if( this.getPiece().getVal() == '7' ){
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar
			}else if( this.getPiece().getVal() == '8' ){
				g.fillRect( 13, 8, 5, 15);  // Top Left
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar	
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
			}else if( this.getPiece().getVal() == '9' ){
				g.fillRect( 13, 8, 5, 15);  // Top Left
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar	
				g.fillRect( 13, 18, 15, 5); // Middle Bar
			}
			//g.setColor( Color.WHITE );
			if( this.getPiece().getVal() == 'B' ){
				g.fillRect( 13, 12, 14, 24 );
				g.fillRect(  10, 15, 20, 18 );
				g.fillRect(  7, 18, 26, 12 );
				g.fillRect( 19, 8, 2, 4 );
				g.fillRect( 21, 6, 2, 2 );
				g.fillRect( 23, 4, 6, 2 );
				g.fillRect( 29, 6, 2, 2 );
				g.fillRect( 31, 8, 4, 2 );
			}else if( this.getPiece().getVal() == 'F' ){

				g.fillRect( 24, 6, 2, 30 );
				g.fillRect( 19,  6,  5, 3 );
				g.fillRect( 14, 9, 10, 3 );
				g.fillRect(  9, 12, 15, 3 );
				g.fillRect( 14, 15, 10, 3 );
				g.fillRect( 19, 18,  5, 3 );
				
			}else if( this.getPiece().getVal() == 'S' ){	
				g.fillRect( 4, 18,  2, 3 );
				g.fillRect( 6, 18,  11, 10 );
				g.fillRect( 5, 21, 1, 6 );
				g.fillRect( 7, 28, 9, 1 );
				g.fillRect( 8, 29, 7, 1 );
				g.fillRect( 34, 18, 2, 3 );
				g.fillRect( 23, 18, 11, 10 );
				g.fillRect( 34, 21, 1, 6 );
				g.fillRect( 24, 28, 9, 1 );
				g.fillRect( 25, 29, 7, 1 );
				g.fillRect( 19, 20, 2, 2 );
				g.fillRect( 17, 19, 1, 6 );
				g.fillRect( 18, 20, 1, 3 );
				g.fillRect( 21, 20, 1, 3 );
				g.fillRect( 22, 19, 1, 6 );
				
				g.fillRect( 7, 5, 6, 2 );
				g.fillRect( 7, 7, 2, 2 );
				g.fillRect( 7, 9, 6, 2 );
				g.fillRect( 11, 11, 2, 2 );
				g.fillRect( 7, 13, 6, 2);
				
				g.fillRect( 17, 5, 2, 10 );
				g.fillRect( 21, 5, 2, 6 );
				g.fillRect( 19, 5, 2, 2 );
				g.fillRect( 19, 9, 2, 2  );
				
				g.fillRect( 27, 5, 2, 6 );
				g.fillRect( 31, 5, 2, 6 );
				g.fillRect( 29, 9, 2, 6 );
			}
		}
	}
}