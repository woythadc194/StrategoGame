/**
  * @author Dylan C. Woythal
  */

package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import logic.Controller;
import logic.PceOptBttnLogic;

@SuppressWarnings("serial")
public class PieceOptButton extends JButton {

	private int typeInt;
	private char typeChar;
	
	public PieceOptButton( int type ){
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
		addAL();
		if( Controller.getPiecesAry()[ typeInt ] == 0 )
			this.setBackground( Color.BLACK );
		else this.setBackground( Color.WHITE );
	}
	
	private void addAL(){
		final PieceOptButton pob = this;
		this.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e){
				if( pob.getBackground()!= Color.BLACK )
					PceOptBttnLogic.clicked( pob );
			}
		});
	}
	
	public char getTypeChar(){
		return this.typeChar;
	}
	
	public int getTypeInt(){
		return this.typeInt;
	}
}
