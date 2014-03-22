package logic;

import ui.SelectionPanel;

public class SetupLogic {
	

	public static void setPlayers( int numPlayers ){
		
		SelectionPanel SPane = new SelectionPanel( cont );
		SPane.addToCont();
		System.out.println( cont.getGameBoard() );
		
		
		int yMin = 0, yMax = 0;
		if( player.equals( "BLUE" ) ){
			yMin = 6;
			yMax = 10;
		}else{
			;
		}
		
		cont.makePiecesAry();
		
		for( int y=yMin; y<yMax; y++ )
			for(int x=0; x<10; x++ ){
				GameButtonLogic.alterButton(buttonMatrix.get( x ).get( y ), 3, '~', "NONE");
				buttonMatrix.get( x ).get( y ).setReady( false );
			}
	}
	}
}
