/**
  * @author Dylan C. Woythal
  */

package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Controller;
import logic.SetupLogic;

@SuppressWarnings("serial")
public class RandomPane extends JFrame{
	
	public RandomPane( final Controller cont ){
		super();
		
		final JFrame randFrame = new JFrame( "Placement Options");
		randFrame.setLayout( new GridLayout( 2, 1 ) );

		JPanel pane1 = new JPanel();
		pane1.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button1 = new JButton( "Manual Setup" );
		button1.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				SetupLogic.setPlacingRandomly( false );
				randFrame.dispose();
			}
		});
		pane1.add(button1);
		
		JPanel pane2 = new JPanel();
		pane2.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button2 = new JButton( "Random Setup" );
		button2.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				SetupLogic.setPlacingRandomly( true );
				randFrame.dispose();
			}
		});
		pane2.add(button2);
		
		randFrame.add(pane1);
		randFrame.add(pane2);
		randFrame.setLocation( 500, 300 );
		randFrame.setVisible( true );
		randFrame.setResizable( false );
		randFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				if( Controller.getIfNumPlayersSelected() == false )
					new SetupPane( cont );
		    }
		});
		randFrame.pack();
		
	}
}
