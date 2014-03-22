package ui;

import logic.Controller;


public class Piece {
	
	
	private char val;
	private int x;
	private int y;
	private int visibility;
	private String player;
	
	@SuppressWarnings("unused")
	private static Controller cont;
	
	public Piece( int visibility, char val, int x, int y, String player, Controller cont ){
		this.val = val;
		this.x = x;
		this.y = y;
		this.visibility = visibility;
		this.player = player;
		Piece.cont = cont;
	}

	public void setVis( int vis){
		this.visibility = vis;
	}
	
	public int visStatus( ){
		return visibility;
		
	}
	
	public char getVal(){
		return val;
	}
	
	public String getPlayer(){
		return this.player;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
}