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
import java.io.FileNotFoundException;

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
		randFrame.setLayout( new GridLayout( 3, 1 ) );
		
		JPanel pane1 = new JPanel();
		pane1.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button1 = new JButton( "Random Setup" );
		button1.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				try {
					SetupLogic.setPlacing( 0 );
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				randFrame.dispose();
			}
		});
		pane1.add(button1);

		JPanel pane2 = new JPanel();
		pane2.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button2 = new JButton( "Manual Setup" );
		button2.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				try {
					SetupLogic.setPlacing( 1 );
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				randFrame.dispose();
			}
		});
		pane2.add(button2);
		
		JPanel pane3 = new JPanel();
		pane3.setPreferredSize( new Dimension ( 200, 40 ) );
		JButton button3 = new JButton( "Parsed Setup" );
		button3.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				try {
					SetupLogic.setPlacing( 2 );
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				randFrame.dispose();
			}
		});
		pane3.add(button3);
		
		randFrame.add(pane1);
		randFrame.add(pane2);
		randFrame.add(pane3);
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
