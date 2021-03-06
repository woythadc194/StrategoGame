/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import parsing.SetupParser;

import ui.GameButton;
import ui.SelectionPanel;

public class SetupLogic {
	
	private static Controller cont;
	private static Color currentPlayer;
	private static int yMin;
	private static int yMax;
	private static SelectionPanel SPane;
	
	public SetupLogic( Controller cont ){
		SetupLogic.cont = cont;
		;
	}
	
	public static void setPlayers( int players, Controller cont ) throws FileNotFoundException{
		Controller.setIfNumPlayersSelected( true );
		
		if( players==1 ){
			Controller.setNumPlayers( players );
			ArrayList<ArrayList<GameButton>> list = SetupParser.getAIList( );
			for( int y=0; y<4; y++ ){
				for( int x=0; x<10; x++ ){
					GameButton b1 = list.get( y ).get( x );
					GameButton b2 = Controller.getButtonMatrix()[ x ][ -y+3 ];
					GameButtonLogic.alterButton(b2, b1.getVisibility(), b1.getVal(), b1.getPlayerColor() );
					b2.setReady( true );
				}
			}
			cont.constructRandomPane();
		}
		Controller.setPiecesAry( ControllerMaker.makeNumPiecesAry() );
	}

	public static void setPlacing( int placementType ) throws FileNotFoundException{
		if( placementType == 1 )
			setHumanPlayers( 1 );
		else if( placementType == 0 )
			setHumanAsRandom( );
		else
			setHumanAsParsed();
	}
	
	/*
	 * human player
	 * visibility = 2
	 */
	public static void setHumanPlayers( int players ){
		SPane = new SelectionPanel( cont );
		SPane.addToCont();
		Controller.setPiecesAry( ControllerMaker.makeNumPiecesAry() );
		if( players == 0)
			return;
		currentPlayer = (players == 1 ? Color.BLUE : Color.RED);
		yMin = (currentPlayer == Color.RED ? 0 : 6);
		yMax = (currentPlayer == Color.RED ? 4 : 10);

		Controller.setPiecesAry(ControllerMaker.makeNumPiecesAry());
		for( int y=yMin; y<yMax; y++ )
			for(int x=0; x<10; x++ ){
				GameButton button = Controller.getButtonMatrix()[ x ][ y ];
				GameButtonLogic.alterButton( button, 1, '~', button.getPlayerColor() );
				button.setReady( false );
			}
		
	}

	/*
	 * human player
	 * visibility = 2
	 */
	public static void setHumanAsRandom( ){
		Random rand = new Random();
		for( int y=6; y<10; y++ )
			for( int x=0; x<10; x++ ){
				int opt = rand.nextInt(12)+1;
				while( Controller.getPiecesAry()[opt] == 0 ){
					opt = rand.nextInt(12)+1;
				}
				GameButton button = Controller.getButtonMatrix()[ x ][ y ];
				GameButtonLogic.alterButton( button, 2, Controller.getCharIndexAry()[opt], button.getPlayerColor() );
				button.repaint();
				Controller.getPiecesAry()[opt]--;
			}
		System.out.println(cont.getBoard());
	}
	
	public static void setHumanAsParsed() throws FileNotFoundException{
		ArrayList<ArrayList<GameButton>> list = SetupParser.getHumanList( );
		for( int x=0; x<10; x++ ){
			for( int y=0; y<4; y++ ){
				GameButton b1 = list.get( y ).get( x );
				GameButton b2 = Controller.getButtonMatrix()[ x ][ y+6 ];
				GameButtonLogic.alterButton(b2, b1.getVisibility(), b1.getVal(), b1.getPlayerColor() );
				b2.setReady( true );
				b2.repaint();
			}
		}
	}
	
	public static Color getCurrentPlayer(){
		return currentPlayer;
	}
	
	public static int getYMin(){
		return yMin;
	}
	
	public static int getYMax(){
		return yMax;
	}
}
