/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import parsing.SetupParser;

import ui.GameButton;
import ui.SelectionPanel;

public class SetupLogic {
	
	private static Color currentPlayer;
	private static int yMin;
	private static int yMax;
	private static SelectionPanel SPane;
	
	public SetupLogic(){
		;
	}
	
	public static void setPlayers( int players, Controller cont ) throws FileNotFoundException{
		System.out.println( players );
		Controller.setIfNumPlayersSelected( true );
		
		if( players==1 ){
			ArrayList<ArrayList<GameButton>> list = SetupParser.getAIList( cont );
			for( int y=0; y<4; y++ ){
				for( int x=0; x<10; x++ ){
					GameButton b1 = list.get( y ).get( x );
					GameButton b2 = cont.getButtonMatrix().get( x ).get( -y+3 );
					GameButtonLogic.alterButton(b2, b1.getVisibility(), b1.getVal(), b1.getPlayerColor() );
					b2.setReady( true );
				}
			}
		}
		
		
		Controller.setPiecesAry( ControllerMaker.makeNumPiecesAry() );
		SPane = new SelectionPanel( cont );
		SPane.addToCont();
		setHumanPlayers( players, cont );
	}
	
	public static void setHumanPlayers( int players, Controller cont ){
		Controller.setPiecesAry( ControllerMaker.makeNumPiecesAry() );
		if( players == 0)
			return;
		currentPlayer = (players == 1 ? Color.BLUE : Color.RED);
		yMin = (currentPlayer == Color.RED ? 0 : 6);
		yMax = (currentPlayer == Color.RED ? 4 : 10);

		Controller.setPiecesAry(ControllerMaker.makeNumPiecesAry());
		for( int y=yMin; y<yMax; y++ )
			for(int x=0; x<10; x++ ){
				GameButton button = cont.getButtonMatrix().get( x ).get( y );
				GameButtonLogic.alterButton( button, 3, '~', button.getPlayerColor() );
				button.setReady( false );
			}
		
	}
	
	public static void killSPane(){
		SPane.dispose();
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
