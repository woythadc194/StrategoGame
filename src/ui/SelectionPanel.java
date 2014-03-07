package ui;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SelectionPanel extends JPanel {

	public String type;
	public String player;
	private Color color;
	@SuppressWarnings("unused")
	private static Controller cont;
	
	public SelectionPanel( Controller cont ){
		super();
		SelectionPanel.cont = cont;
		this.player = "BLACK";
	}
	
	public void setType( String type, Color c ){
		this.type = type;
		this.color = c;
	}
	
	public void setPlayer( String player ){
		this.player = player;
		repaint();
	}
	
	public void paint( Graphics g ){
		if( player.equals( "RED" ) )
			g.setColor( Color.RED );
		else if( player.equals( "BLUE" ) )
			g.setColor( Color.BLUE );
		else
			g.setColor( new Color( 238, 238, 238 ) );
		g.fillRect( 38, 162, 75, 75 );
		g.setColor( color );
		g.fillRect( 50, 175, 50, 50 );
		g.setColor( Color.black );
		g.drawRect( 38, 162, 75, 75 );
		g.drawRect( 39, 163, 73, 73 );
		g.drawRect( 50, 175, 50, 50 );
		g.drawRect( 51, 176, 48, 48 );
		
				
	}
}