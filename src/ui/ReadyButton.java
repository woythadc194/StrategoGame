package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import logic.Controller;

@SuppressWarnings("serial")
public class ReadyButton extends JButton{
	
	private static Controller cont;
	public ReadyButton( Controller cont ){
		super( "READY" );
		ReadyButton.cont = cont;
		addAL();
	}
	
	private void addAL(){
		this.addActionListener( new ActionListener(){
			public void actionPerformed( ActionEvent e ){
				if( cont.testPlayrReady() ){
					cont.askIfReady();
					SelectionPanel.getFrame().dispose();
				}
			}
		});
	}
}
