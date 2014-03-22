package ui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Controller;

@SuppressWarnings("serial")
public class SelectionPanel extends JFrame {
	
	private static Controller cont;
	
	public SelectionPanel( Controller cont ){
		super();
		SelectionPanel.cont = cont;
		setUpGui();
	}
	
	public void addToCont(){
		cont.addSelectionPanel( this );
	}
	public void setUpGui(){
		final JFrame newFrame = new JFrame();
		JPanel pane1 = new JPanel();
		JPanel pane2 = new JPanel();
		JPanel pane3 = new JPanel();
		
		PieceOptButton [] newAry = new PieceOptButton[13];
		PieceOptButton b1 = new PieceOptButton( 1, cont ); 		PieceOptButton b2 = new PieceOptButton( 2, cont );
		PieceOptButton b3 = new PieceOptButton( 3, cont ); 		PieceOptButton b4 = new PieceOptButton( 4, cont );
		PieceOptButton b5 = new PieceOptButton( 5, cont );		PieceOptButton b6 = new PieceOptButton( 6, cont );
		PieceOptButton b7 = new PieceOptButton( 7, cont );		PieceOptButton b8 = new PieceOptButton( 8, cont );
		PieceOptButton b9 = new PieceOptButton( 9, cont );		PieceOptButton bB = new PieceOptButton( 10, cont );
		PieceOptButton bF = new PieceOptButton( 11, cont );		PieceOptButton bS = new PieceOptButton( 12, cont );
		PieceOptButton remove = new PieceOptButton( 0, cont );
		
		newAry[1] = b1; newAry[2] = b2; newAry[3] = b3; newAry[4] = b4; newAry[5] = b5; newAry[6] = b6; 
		newAry[7] = b7; newAry[8] = b8; newAry[9] = b9; newAry[10] = bB; newAry[11] = bF; newAry[12] = bS; newAry[0] = remove;
		cont.setOptButtonAry( newAry );
		
		newFrame.setLayout( new GridLayout( 3, 1 ) );
		pane1.add( b1 );		pane1.add( b2 );		pane1.add( b3 );		
		pane1.add( b4 );		pane1.add( b5 );		pane1.add( b6 );		
		pane2.add( b7 );		pane2.add( b8 );		pane2.add( b9 );		
		pane2.add( bB );		pane2.add( bF );		pane2.add( bS );
		
		pane3.add( remove );

		newFrame.add( pane1 );
		newFrame.add( pane2 );
		newFrame.add( pane3 );
		newFrame.setVisible( true );
		newFrame.setResizable( false );
		newFrame.setLocation( 500,  400 );
		newFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				if( cont.isReady() == false )
					new SelectionPanel( cont );
		    }
		});
		newFrame.pack();
	}
}