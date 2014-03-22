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
	
	public String battle( Piece p ){
		//System.out.println("DEBUG piece.battle()   " + this.getPlayer() + ":" + this.getVal() + "    " + p.getPlayer() + ":" + p.getVal() );
		if( this.getPlayer().equals( p.getPlayer() ) )
			return "INVALID";
		if( this.getVal() == '~' || this.getVal() == 'F' || this.getVal() == 'B')
			return "INVALID";
		if( this.getVal() == '1' ){
			if( p.getVal() == '1' ){
				return "NEITHER";
			} else if( p.getVal() == '2' ){
				return this.getPlayer();
			} else if( p.getVal() == '3' ){
				return this.getPlayer();
			} else if( p.getVal() == '4' ){
				return this.getPlayer();
			} else if( p.getVal() == '5' ){
				return this.getPlayer();
			} else if( p.getVal() == '6' ){
				return this.getPlayer();
			} else if( p.getVal() == '7' ){
				return this.getPlayer();
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '2' ){
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return "NEITHER";
			} else if( p.getVal() == '3' ){
				return this.getPlayer();
			} else if( p.getVal() == '4' ){
				return this.getPlayer();
			} else if( p.getVal() == '5' ){
				return this.getPlayer();
			} else if( p.getVal() == '6' ){
				return this.getPlayer();
			} else if( p.getVal() == '7' ){
				return this.getPlayer();
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '3' ){
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return "NEITHER";
			} else if( p.getVal() == '4' ){
				return this.getPlayer();
			} else if( p.getVal() == '5' ){
				return this.getPlayer();
			} else if( p.getVal() == '6' ){
				return this.getPlayer();
			} else if( p.getVal() == '7' ){
				return this.getPlayer();
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '4' ){
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return "NEITHER";
			} else if( p.getVal() == '5' ){
				return this.getPlayer();
			} else if( p.getVal() == '6' ){
				return this.getPlayer();
			} else if( p.getVal() == '7' ){
				return this.getPlayer();
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '5' ){
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return p.getPlayer();
			} else if( p.getVal() == '5' ){
				return "NEITHER";
			} else if( p.getVal() == '6' ){
				return this.getPlayer();
			} else if( p.getVal() == '7' ){
				return this.getPlayer();
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '6' ){
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return p.getPlayer();
			} else if( p.getVal() == '5' ){
				return p.getPlayer();
			} else if( p.getVal() == '6' ){
				return "NEITHER";
			} else if( p.getVal() == '7' ){
				return this.getPlayer();
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '7' ){
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return p.getPlayer();
			} else if( p.getVal() == '5' ){
				return p.getPlayer();
			} else if( p.getVal() == '6' ){
				return p.getPlayer();
			} else if( p.getVal() == '7' ){
				return "NEITHER";
			} else if( p.getVal() == '8' ){
				return this.getPlayer();
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "NEITHER";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '8' ){ //MINER
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return p.getPlayer();
			} else if( p.getVal() == '5' ){
				return p.getPlayer();
			} else if( p.getVal() == '6' ){
				return p.getPlayer();
			} else if( p.getVal() == '7' ){
				return p.getPlayer();
			} else if( p.getVal() == '8' ){
				return "NEITHER";
			} else if( p.getVal() == '9' ){
				return this.getPlayer();
			} else if( p.getVal() == 'B' ){
				return this.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == '9' ){ //SCOUT
			if( p.getVal() == '1' ){
				return p.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return p.getPlayer();
			} else if( p.getVal() == '5' ){
				return p.getPlayer();
			} else if( p.getVal() == '6' ){
				return p.getPlayer();
			} else if( p.getVal() == '7' ){
				return p.getPlayer();
			} else if( p.getVal() == '8' ){
				return p.getPlayer();
			} else if( p.getVal() == '9' ){
				return "NEITHER";
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return this.getPlayer();
			}
		} else if( this.getVal() == 'S' ){
			if( p.getVal() == '1' ){
				return this.getPlayer();
			} else if( p.getVal() == '2' ){
				return p.getPlayer();
			} else if( p.getVal() == '3' ){
				return p.getPlayer();
			} else if( p.getVal() == '4' ){
				return p.getPlayer();
			} else if( p.getVal() == '5' ){
				return p.getPlayer();
			} else if( p.getVal() == '6' ){
				return p.getPlayer();
			} else if( p.getVal() == '7' ){
				return p.getPlayer();
			} else if( p.getVal() == '8' ){
				return p.getPlayer();
			} else if( p.getVal() == '9' ){
				return p.getPlayer();
			} else if( p.getVal() == 'B' ){
				return p.getPlayer();
			} else if( p.getVal() == 'F' ){
				return "WIN";
			} else if( p.getVal() == 'S' ){
				return "NEITHER";
			}
		}
		
		if( p.getVal() == '~' )
			return this.getPlayer();

		System.out.println("DEBUG X");
		return "INVALID";
	}
}