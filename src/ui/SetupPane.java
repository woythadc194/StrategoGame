package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SetupPane extends JPanel{

	public SetupPane(){
		super();
		JFrame frame = new JFrame("Welcome");
		
		JPanel pane1 = new JPanel();
		JButton button1 = new JButton( "1 Player" );
		button1.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				SetupLogic.setPlayers( 1 );
			}
		});
		JPanel pane2 = new JPanel();
		
		
	}
}
