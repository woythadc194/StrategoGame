/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;
import java.util.Random;

import ui.GameButton;
import ui.SelectionPanel;

public class SetupLogic {
	
	private static Color currentPlayer;
	private static int yMin;
	private static int yMax;
	
	public SetupLogic(){
		;
	}
	
	public static void setPlayers( int players, Controller cont ){
		System.out.println( players );
		Controller.setIfNumPlayersSelected( true );
		if( players==1 ){
			Random rand = new Random();
			for( int y=0; y<4; y++ ){
				for( int x=0; x<10; x++ ){
					int id = rand.nextInt( 12 ) + 1;
					while( Controller.getPiecesAry()[id] == 0 )
						id = rand.nextInt(12) + 1;
					Controller.getPiecesAry()[id] --;
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
					//Temp set to 3
					GameButtonLogic.alterButton( cont.getButtonMatrix().get( x ).get( y ), 3, val, Color.RED );
				}
			}
		}
		setHumanPlayers( players, cont );

	}
	
	private static void waitTime( long time ){
		long start = System.currentTimeMillis();
		while( System.currentTimeMillis() - start < time ){
			;
		}
	}
		
	private static void setHumanPlayers( int players, Controller cont ){
		Controller.setPiecesAry( ControllerMaker.makeNumPiecesAry() );
		if( players == 0)
			return;
		currentPlayer = (players == 1 ? Color.BLUE : Color.RED);
		yMin = (currentPlayer == Color.RED ? 0 : 6);
		yMax = (currentPlayer == Color.RED ? 4 : 10);

		SelectionPanel SPane = new SelectionPanel( cont );
		SPane.addToCont();
		
		Controller.setPiecesAry(ControllerMaker.makeNumPiecesAry());
		for( int y=yMin; y<yMax; y++ )
			for(int x=0; x<10; x++ ){
				GameButton button = cont.getButtonMatrix().get( x ).get( y );
				GameButtonLogic.alterButton( button, 3, '~', button.getPlayerColor() );
				button.setReady( false );
			}
		
		/*
		while( Controller.playerNotReady( yMin, yMax ) ){
				waitTime( 100 );
				System.out.println("ERROR");
			}
		*/
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
