/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;

import ui.PieceOptButton;

public class PceOptBttnLogic {

	public static void clicked( PieceOptButton pob ){
		
		pob.setBackground( SetupLogic.getCurrentPlayer() );
			
		if( pob.getTypeChar() == '~' ){
			Controller.setSelectedPieceOpt(pob.getTypeInt() );
		} else if( Controller.getPiecesAry()[ pob.getTypeInt() ] > 0 ){
			Controller.setSelectedPieceOpt(pob.getTypeInt() );
		}else{
			pob.setBackground( Color.BLACK );
			Controller.setSelectedPieceOpt( 0 );
		}
		refresh();
	}
	
	private static void refresh( ){
		for( int index=0; index<13; index++ ){
			if( index != Controller.getSelectedPieceOpt() ){
				PieceOptButton pob = Controller.getOptButtonAry()[ index ];
				if( Controller.getPiecesAry()[index] > 0 ){
					pob.setBackground( Color.WHITE );
				}else{
					pob.setBackground( Color.BLACK );
				}
			}
		}
	}
}
