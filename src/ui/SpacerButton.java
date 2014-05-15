package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SpacerButton extends JButton {

	private boolean isPlayerButton;
	private char val;

	public SpacerButton( Dimension d ){
		super();
		this.setBorder( null );
		this.setPreferredSize( d );
		this.isPlayerButton = false;
	}

	public SpacerButton( Dimension d, char val ){
		super();
		this.setBorder( null );
		this.setPreferredSize( d );
		this.val = val;
		this.isPlayerButton = true;
	}

	public void paint( Graphics g ){
		if( isPlayerButton ){
			if( val == '1' )
				g.fillRect( 18, 8, 5, 25 );
			else if( val == '2' ){
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
			}else if( val == '3' ){
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
			}else if( val == '4' ){
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left
			}else if( val == '5' ){	
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left
			}else if( val == '6' ){	
				g.fillRect( 13, 8, 15, 5);	// Top Bar
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 5, 15);  // Top Left	
			}else if( val == '7' ){
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar
			}else if( val == '8' ){
				g.fillRect( 13, 8, 5, 15);  // Top Left
				g.fillRect( 13, 18, 5, 15); // Bottom Left
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar	
				g.fillRect( 13, 18, 15, 5); // Middle Bar
				g.fillRect( 13, 28, 15, 5); // Bottom Bar
			}else if( val == '9' ){
				g.fillRect( 13, 8, 5, 15);  // Top Left
				g.fillRect( 23, 8, 5, 15);  // Top Right
				g.fillRect( 23, 18, 5, 15); // Bottom Right
				g.fillRect( 13, 8, 15, 5);	// Top Bar	
				g.fillRect( 13, 18, 15, 5); // Middle Bar
			}
			//g.setColor( Color.WHITE );
			if( val == 'B' ){
				g.fillRect( 13, 12, 14, 24 );
				g.fillRect(  10, 15, 20, 18 );
				g.fillRect(  7, 18, 26, 12 );
				g.fillRect( 19, 8, 2, 4 );
				g.fillRect( 21, 6, 2, 2 );
				g.fillRect( 23, 4, 6, 2 );
				g.fillRect( 29, 6, 2, 2 );
				g.fillRect( 31, 8, 4, 2 );
			}else if( val == 'F' ){

				g.fillRect( 24, 6, 2, 30 );
				g.fillRect( 19,  6,  5, 3 );
				g.fillRect( 14, 9, 10, 3 );
				g.fillRect(  9, 12, 15, 3 );
				g.fillRect( 14, 15, 10, 3 );
				g.fillRect( 19, 18,  5, 3 );

			}else if( val == 'S' ){	
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
		} else {
			g.setColor( Color.BLACK );
			g.fillRect( 0, 0, this.getWidth(), this.getHeight() );
		}
	}
}