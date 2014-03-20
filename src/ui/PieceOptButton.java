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
		else
			typeChar = (char)(type+48);
		this.setText( "" + typeChar );
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
	
	private void clicked(){
		if( cont.getSelectedPieceOpt() == 13 ){
			cont.setSelectedPieceOpt( getTypeInt() );
			Controller.piecesAry[ getTypeInt() ] --;
		} else{
			Controller.optButtonAry[ cont.getSelectedPieceOpt() ].setBackground( Color.WHITE );
			Controller.piecesAry[ cont.getSelectedPieceOpt() ]++;
			cont.setSelectedPieceOpt(getTypeInt() );
			Controller.piecesAry[ getTypeInt() ]--;
		}
		this.setBackground( Color.RED );
			System.out.println( "Selected " + typeChar );
	}
}
