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
public class SetupPane extends JFrame{

	public SetupPane( final Controller cont ){
		super();
		
		final JFrame frame = new JFrame("Welcome");
		frame.setLayout( new GridLayout( 2, 1 ) );

		JPanel pane1 = new JPanel();
		pane1.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button1 = new JButton( "1 Player" );
		button1.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				SetupLogic.setPlayers( 1, cont );
				frame.dispose();
			}
		});
		pane1.add(button1);
		
		JPanel pane2 = new JPanel();
		pane2.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button2 = new JButton( "2 Player" );
		button2.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				SetupLogic.setPlayers( 2, cont );
				frame.dispose();
			}
		});
		pane2.add(button2);
		
		frame.add(pane1);
		frame.add(pane2);
		frame.setLocation( 500, 300 );
		frame.setVisible( true );
		frame.setResizable( false );
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				if( Controller.getIfNumPlayersSelected() == false )
					new SetupPane( cont );
		    }
		});
		frame.pack();
		
	}
}
