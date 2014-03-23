package logic;

import java.awt.Color;
import java.util.Random;

import ui.GameButton;
import ui.SelectionPanel;

public class SetupLogic {
	
	private static Color currentPlayer;
	private static int yMin;
	private static int yMax;

	public SetupLogic( Controller cont ){
		Controller.setSULogic( this );
	}
	
	public static void setPlayers( int players, Controller cont ){
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
					GameButtonLogic.alterButton( cont.getButtonMatrix().get( x ).get( y ), 1, val, Color.RED );
				}
			}
		}
		setHumanPlayers( players, cont );
	}
		
	private static void setHumanPlayers( int players, Controller cont ){
		if( players == 0)
			return;
		currentPlayer = (players == 2 ? Color.RED : Color.BLUE);
		yMin = (currentPlayer == Color.RED ? 0 : 6);
		yMax = (currentPlayer == Color.RED ? 4 : 10);

		SelectionPanel SPane = new SelectionPanel( cont );
		SPane.addToCont();
		System.out.println( cont.getGameBoard() );
		
		cont.makeNumPiecesAry();
		for( int y=yMin; y<yMax; y++ )
			for(int x=0; x<10; x++ ){
				GameButton button = cont.getButtonMatrix().get( x ).get( y );
				GameButtonLogic.alterButton( button, 3, '~', button.getPlayerColor() );
				button.setReady( false );
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
