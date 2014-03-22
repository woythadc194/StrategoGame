package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PieceOptButton extends JButton {

	private static Controller cont;
	private int typeInt;
	private char typeChar;
	
	public PieceOptButton( int type, Controller cont ){
		super();
		if( type == 10 )
			typeChar = 'B';
		else if( type == 11 )
			typeChar = 'F';
		else if( type == 12 )
			typeChar = 'S';
		else if( type == 0 )
			typeChar = '~';
		else
			typeChar = (char)(type+48);
		
		this.setText( "" + typeChar );
		if( type == 0 )
			this.setText( "Remove" );
		
		this.typeInt = type;
		PieceOptButton.cont = cont;
		
		addAL();
		if( Controller.piecesAry[ typeInt ] == 0 )
			this.setBackground( Color.BLACK );
		else this.setBackground( Color.WHITE );
	}
	
	private void addAL(){
		final PieceOptButton pob = this;
		this.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e){
				if( pob.getBackground()!= Color.BLACK )
					pob.clicked();
			}
		});
	}
	
	private int getTypeInt(){
		return this.typeInt;
	}
	
	public void clicked(){
		
		this.setBackground( Color.RED );
			System.out.println( "Selected " + typeChar );
			
		if( this.typeChar == '~' ){
			cont.setSelectedPieceOpt(getTypeInt() );
		} else if( Controller.piecesAry[ getTypeInt() ] > 0 ){
			cont.setSelectedPieceOpt(getTypeInt() );
		}else{
			this.setBackground( Color.BLACK );
			cont.setSelectedPieceOpt( 0 );
		}
		refresh();
	}
	
	private void refresh(){
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
