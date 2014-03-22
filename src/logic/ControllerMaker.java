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
		cont.setIndexMap( getCharIndexMap() );
		cont.setGameButtonMatrix( getGameButtonMatrix() );
		cont.makeNumPiecesAry();

	}
	
	private static Map<Integer, Character> getCharIndexMap(){
	
		Map<Integer, Character> indexMap = new TreeMap<Integer, Character>();
		for( int index=0; index<13; index++ ){
			char value;
			if( index == 0 )
				value = 'X';
			else if( index == 10 )
				value = 'B';
			else if( index == 11 )
				value = 'F';
			else if( index == 12 )
				value = 'S';
			else
				value = ("" + index).charAt(0);
			indexMap.put( index, value);
		}
		return indexMap;
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
		for( int y=4; y<6; y++ )
			for( int x=2; x<8; x++ ){
				if( x==4 )
					x = 6;
				GameButtonLogic.alterButton(buttonMatrix.get( x ).get( y ), 3, 'X', player);
			}
	}
	
	public void addHumanPlayer(String player){
	}		
}
