package logic;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.JFrame;

import ui.GameBoard;
import ui.GameButton;
import ui.SelectionPanel;

public class ControllerMaker {

	private static Controller cont;
	
	public static void create( JFrame frame, GameBoard gb ){

		cont = new Controller();
		cont.setFrame( frame );
		cont.setGameBoard( gb );
		cont.setGameButtonMatrix( getGameButtonMatrix() );
		cont.makeNumPiecesAry();

	}
	
	public static ArrayList<ArrayList<GameButton>> getGameButtonMatrix(){
		FlowLayout flow = new FlowLayout();
		flow.setHgap( 0 );
		flow.setVgap( 0 );
		cont.getGameBoard().setLayout( flow );

		ArrayList<ArrayList<GameButton>> buttonMatrix = new ArrayList<ArrayList<GameButton>>();
		buttonMatrix = new ArrayList<ArrayList<GameButton>>();
		for( int i=0; i<10; i++ )
			buttonMatrix.add( new ArrayList<GameButton>() );
		
		for( int y=0; y<10; y++ ){
			for( int x=0; x<10; x++ ){
				GameButton button = new GameButton( 40, x, y, cont );
				button.setBackground( Color.BLACK );
				buttonMatrix.get( x ).add( button );
				
				if( y<4 )
					GameButtonLogic.alterButton(button, 3, '~', "RED");
				else if( y>=6 )
					GameButtonLogic.alterButton(button, 3, '~', "BLUE");
				else if( (y==4 || y==5) && (x==2 || x==4 || x==6 || x==7 ) )
					GameButtonLogic.alterButton(button, 3, 'X', "NONE");
				else
					GameButtonLogic.alterButton(button, 3, '~', "NONE");
				
				cont.getGameBoard().add( button );
			}
		}
		return buttonMatrix;
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
				GameButtonLogic.alterButton(buttonMatrix.get( x ).get( y ), 3, '~', player);

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
				char val = 'X';
				if( id > 9 ){
					if( id == 10 )
						val = 'B';
					else if( id == 11 )
						val = 'F';
					else if( id == 12 )
						val = 'S';
				}else
					val = ("" + id).charAt( 0 );
				GameButtonLogic.alterButton(buttonMatrix.get( x ).get( y ), 1, val, player);
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
		
	}
	
	public void addHumanPlayer(String player){
	}		
}
