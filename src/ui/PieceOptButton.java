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
		if( Controller.piecesAry[typeInt] == 0)
			this.setBackground( Color.black );
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
		this.setBackground( Color.RED );
		if(cont.getSelectedPieceOpt() == 13){
			cont.setSelectedPieceOpt(typeInt);
			Controller.piecesAry[typeInt] --;
		} else{
			Controller.optButtonAry[typeInt].setBackground(null);
			Controller.piecesAry[cont.getSelectedPieceOpt()]++;
			cont.setSelectedPieceOpt(typeInt);
			Controller.piecesAry[typeInt]--;
		}
			System.out.println( "Selected " + typeChar );
	}
}
