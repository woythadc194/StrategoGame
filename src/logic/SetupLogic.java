package logic;

import java.awt.Color;

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
				GameButtonLogic.alterButton( button, 3, '~', button.getPlayer() );
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
