/**
  * @author Dylan C. Woythal
  */

package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import logic.Controller;
import logic.GameButtonLogic;


@SuppressWarnings("serial")
public class GameButton extends JButton{
	
	private int size;
	private int xLocal;
	private int yLocal;
	private int visibility;
	
	private boolean moveable;
	private boolean targeted;
	private boolean ready;
	
	private char val;
	private Color playerColor;
	
	
	
	public GameButton( int size, int xLocal, int yLocal ){
		super();
		
		this.setPreferredSize( new Dimension( size, size ) );
		this.setBorder( null );
		
		addActnLstnr();
		
		this.size = size;
		this.xLocal = xLocal;
		this.yLocal = yLocal;
		this.visibility = 3;
		
		this.moveable = false;
		this.targeted = false;
		this.ready = true;
		
		this.val = 0;
		this.playerColor = Color.DARK_GRAY;
	}
	
	private void addActnLstnr(){
		final GameButton button = this;
		this.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				GameButtonLogic.clicked( button );
			}
		});
	}
	
	/*
	 * Setters
	 */

	public void setVisibility( int visibility ){
		this.visibility = visibility;
	}
	
	public void setMovable( boolean moveable){
		this.moveable = moveable;
		this.repaint();
	}
	
	public void setReady( boolean ready ){
		this.ready = ready;
	}
	
	public void setVal( char val ){
		this.val = val;
	}

	public void setColor( Color color ){
		this.playerColor = color;
	}
	
	/*
	 * Getters
	 */
	public int getSize1(){
		return this.size;
	}
	
	public int x(){
		return this.xLocal;
	}
	
	public int y(){
		return this.yLocal;
	}

	public int getVisibility(){
		return this.visibility;
	}
	
	public boolean isMovable(){
		return this.moveable;
	}

	public boolean getReady(){
		return this.ready;
	}
	
	public Color getPlayerColor(){
		return this.playerColor;
	}
	
	public Color getOpponetColor(){
		return (getPlayerColor() == Color.RED) ? Color.BLUE : Color.RED;
	}
	
	public String getPlayerColorString(){
		if( getPlayerColor() == Color.RED )
			return "RED";
		else if( getPlayerColor() == Color.BLUE )
			return "BLUE";
		else
			return "NONE";
	}
	
	public char getVal(){
		return this.val;
	}
	
	/*
	 * Overrides
	 */
	public void paint( Graphics g ){
		Color bg = Color.DARK_GRAY;
		if( getPlayerColorString().equals( "RED" ) ) 
			bg = Color.RED;
		else if( getPlayerColorString().equals( "BLUE" ) )
			bg = Color.BLUE;
		else if( getVal() == 'X' )
			bg = Color.BLACK;
		else
			bg = Color.DARK_GRAY;
		

		this.playerColor = bg;
		g.setColor( bg );
		g.fillRect( 0, 0, 40, 40 );
		if( getVal() == 'X' ){
			g.setColor( Controller.getPlayerTurn() );
			g.fillRect( 10, 10, 20, 20 );
		}

		if( this.moveable )
			g.setColor( Color.YELLOW );
		else
			g.setColor( Color.BLACK );
		g.drawRect( 0, 0, 40, 40 );
		g.drawRect( 1, 1, 38, 38 );
		

		if( this.targeted )
			g.setColor( Color.WHITE );
		
		if( visibility == 3 )
			g.setColor( Color.GREEN );
		
		if( this.getVisibility() >=2 ){
			if( this.getVal() == '1' )
				g.fillRect( 18, 8, 5, 25 );
			else if( this.getVal() == '2' ){
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
			}else if( this.getVal() == '3' ){
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
			}else if( this.getVal() == '4' ){
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left
			}else if( this.getVal() == '5' ){	
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left
			}else if( this.getVal() == '6' ){	
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left	
			}else if( this.getVal() == '7' ){
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar
			}else if( this.getVal() == '8' ){
				g.fillRect( 13, 8, 5, 15);  // Top Left
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar	
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
			}else if( this.getVal() == '9' ){
				g.fillRect( 13, 8, 5, 15);  // Top Left
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar	
				g.fillRect( 13, 18, 15, 5); // Middle Bar
			}
			//g.setColor( Color.WHITE );
			if( this.getVal() == 'B' ){
				g.fillRect( 13, 12, 14, 24 );
				g.fillRect(  10, 15, 20, 18 );
				g.fillRect(  7, 18, 26, 12 );
				g.fillRect( 19, 8, 2, 4 );
				g.fillRect( 21, 6, 2, 2 );
				g.fillRect( 23, 4, 6, 2 );
				g.fillRect( 29, 6, 2, 2 );
				g.fillRect( 31, 8, 4, 2 );
			}else if( this.getVal() == 'F' ){

				g.fillRect( 24, 6, 2, 30 );
				g.fillRect( 19,  6,  5, 3 );
				g.fillRect( 14, 9, 10, 3 );
				g.fillRect(  9, 12, 15, 3 );
				g.fillRect( 14, 15, 10, 3 );
				g.fillRect( 19, 18,  5, 3 );
				
			}else if( this.getVal() == 'S' ){	
				//glasses
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
				//s
				g.fillRect( 7, 5, 6, 2 );
				g.fillRect( 7, 7, 2, 2 );
				g.fillRect( 7, 9, 6, 2 );
				g.fillRect( 11, 11, 2, 2 );
				g.fillRect( 7, 13, 6, 2);
				//p
				g.fillRect( 17, 5, 2, 10 );
				g.fillRect( 21, 5, 2, 6 );
				g.fillRect( 19, 5, 2, 2 );
				g.fillRect( 19, 9, 2, 2  );
				//y
				g.fillRect( 27, 5, 2, 6 );
				g.fillRect( 31, 5, 2, 6 );
				g.fillRect( 29, 9, 2, 6 );
			}
		}
	}
	
	public String toString(){
		String s = "";
		s += getPlayerColorString().charAt( 0 );
		s += getVal();
		s += getVisibility();
//		s += (getReady() ? "T" : "F");
		return s;
	}

	public void setTargeted(boolean targeted) {
		this.targeted = targeted;
	}
}