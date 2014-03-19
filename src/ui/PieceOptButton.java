package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PieceOptButton extends JButton {

	private static Controller cont;
	private int type;
	
	public PieceOptButton( int type, Controller cont ){
		super();
		if( type == 10 )
			this.setText( "B" );
		else if( type == 11 )
			this.setText( "F" );
		else if( type == 12 )
			this.setText( "S" );
		else
			this.setText( "" + type );
		
		this.type = type;
		this.cont = cont;
		addAL();
	}
	
	private void addAL(){
		final PieceOptButton pob = this;
		this.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e){
				pob.clicked();
			}
		});
	}
	
	private void clicked(){
		;
	}
}
