/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;
import java.awt.FlowLayout;

import ui.GameButton;

public class ControllerMaker {

	public static int [] makeNumPiecesAry(){
		int [] piecesAry = new int[13];
		piecesAry[0] = 1;
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
		return piecesAry;
	}
	
	public static char [] makeCharIndexAry(){
		char [] charIndex = new char[14];
		charIndex[0] = '~';
		charIndex[1] = '1';
		charIndex[2] = '2';
		charIndex[3] = '3';
		charIndex[4] = '4';
		charIndex[5] = '5';
		charIndex[6] = '6';
		charIndex[7] = '7';
		charIndex[8] = '8';
		charIndex[9] = '9';
		charIndex[10] = 'B';
		charIndex[11] = 'F';
		charIndex[12] = 'S';
		charIndex[13] = 'X';
		return charIndex;
	}
	
	public static GameButton[][] getGameButtonMatrix( Controller cont ){
		FlowLayout flow = new FlowLayout();
		flow.setHgap( 0 );
		flow.setVgap( 0 );
		cont.getBoard().setLayout( flow );

		GameButton[][] buttonMatrix = new GameButton[ 10 ][ 10 ];
		
		for( int y=0; y<10; y++ ){
			for( int x=0; x<10; x++ ){
				GameButton button = new GameButton( 40, x, y );
				button.setBackground( Color.BLACK );
				buttonMatrix[ x ][ y ] = button;
				
				if( y<4 )
					GameButtonLogic.alterButton(button, 3, '~', Color.RED);
				else if( y>=6 )
					GameButtonLogic.alterButton(button, 3, '~', Color.BLUE );
				else if( (y==4 || y==5) && (x==2 || x==3 || x==6 || x==7 ) ){
					GameButtonLogic.alterButton(button, 3, 'X', Color.DARK_GRAY );
					button.setReady( true );
				}else{
					GameButtonLogic.alterButton(button, 3, '~', Color.DARK_GRAY );
					button.setReady( true );
				}
				
				cont.getBoard().add( button );
			}
		}
		return buttonMatrix;
	}
}
