package logic;

import java.awt.Color;

import ui.PieceOptButton;

public class PceOptBttnLogic {

public static void clicked( PieceOptButton pob, Controller cont ){
		
	pob.setBackground( Color.RED );
			System.out.println( "Selected " + pob.getTypeChar() );
			
		if( pob.getTypeChar() == '~' ){
			cont.setSelectedPieceOpt(pob.getTypeInt() );
		} else if( Controller.piecesAry[ pob.getTypeInt() ] > 0 ){
			cont.setSelectedPieceOpt(pob.getTypeInt() );
		}else{
			pob.setBackground( Color.BLACK );
			cont.setSelectedPieceOpt( 0 );
		}
		refresh(cont);
	}
	
	private static void refresh( Controller cont ){
		for( int index=0; index<13; index++ ){
			if( index != cont.getSelectedPieceOpt() ){
				PieceOptButton pob = Controller.optButtonAry[ index ];
				if( Controller.piecesAry[index] > 0 ){
					pob.setBackground( Color.WHITE );
				}else{
					pob.setBackground( Color.BLACK );
				}
			}
		}
	}
}
