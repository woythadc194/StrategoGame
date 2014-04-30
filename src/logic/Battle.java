package logic;

import java.awt.Color;

import ui.GameButton;

public class Battle {

	public static String getResult( GameButton attacker, GameButton defender ){
		if( attacker.getPlayerColorString().equals( defender.getPlayerColorString() ) )
			return "INVALID";
		if( attacker.getVal() == '~' || attacker.getVal() == 'F' || attacker.getVal() == 'B')
			return "INVALID";
		if( attacker.getVal() == '1' ){
			if( defender.getVal() == '1' ){
				return "NEITHER";
			} else if( defender.getVal() == '2' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '2' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return "NEITHER";
			} else if( defender.getVal() == '3' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '3' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return "NEITHER";
			} else if( defender.getVal() == '4' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '4' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return "NEITHER";
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '5' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return "NEITHER";
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '6' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return "NEITHER";
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '7' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return "NEITHER";
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "NEITHER";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '8' ){ //MINER
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return "NEITHER";
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '9' ){ //SCOUT
			if( defender.getVal() == '1' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return "NEITHER";
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == 'S' ){
			if( defender.getVal() == '1' ){
				return attacker.getPlayerColorString();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '7' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '8' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == '9' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayerColorString();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return "NEITHER";
			}
		}
		
		if( defender.getVal() == '~' )
			return attacker.getPlayerColorString();
		return "INVALID";
	}
	
	public static String getResult( GameButton attacker, char val ){
		if( attacker.getVal() == '~' || attacker.getVal() == 'F' || attacker.getVal() == 'B')
			return "INVALID";
		if( attacker.getVal() == '1' ){
			if(  val == '1' ){
				return "NEITHER";
			} else if(  val == '2' ){
				return attacker.getPlayerColorString();
			} else if(  val == '3' ){
				return attacker.getPlayerColorString();
			} else if(  val == '4' ){
				return attacker.getPlayerColorString();
			} else if(  val == '5' ){
				return attacker.getPlayerColorString();
			} else if(  val == '6' ){
				return attacker.getPlayerColorString();
			} else if(  val == '7' ){
				return attacker.getPlayerColorString();
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '2' ){
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return "NEITHER";
			} else if(  val == '3' ){
				return attacker.getPlayerColorString();
			} else if(  val == '4' ){
				return attacker.getPlayerColorString();
			} else if(  val == '5' ){
				return attacker.getPlayerColorString();
			} else if(  val == '6' ){
				return attacker.getPlayerColorString();
			} else if(  val == '7' ){
				return attacker.getPlayerColorString();
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '3' ){
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return "NEITHER";
			} else if(  val == '4' ){
				return attacker.getPlayerColorString();
			} else if(  val == '5' ){
				return attacker.getPlayerColorString();
			} else if(  val == '6' ){
				return attacker.getPlayerColorString();
			} else if(  val == '7' ){
				return attacker.getPlayerColorString();
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '4' ){
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return "NEITHER";
			} else if(  val == '5' ){
				return attacker.getPlayerColorString();
			} else if(  val == '6' ){
				return attacker.getPlayerColorString();
			} else if(  val == '7' ){
				return attacker.getPlayerColorString();
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '5' ){
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '5' ){
				return "NEITHER";
			} else if(  val == '6' ){
				return attacker.getPlayerColorString();
			} else if(  val == '7' ){
				return attacker.getPlayerColorString();
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '6' ){
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '5' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '6' ){
				return "NEITHER";
			} else if(  val == '7' ){
				return attacker.getPlayerColorString();
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '7' ){
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '5' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '6' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '7' ){
				return "NEITHER";
			} else if(  val == '8' ){
				return attacker.getPlayerColorString();
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "NEITHER";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '8' ){ //MINER
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '5' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '6' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '7' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '8' ){
				return "NEITHER";
			} else if(  val == '9' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'B' ){
				return attacker.getPlayerColorString();
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == '9' ){ //SCOUT
			if(  val == '1' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '5' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '6' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '7' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '8' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '9' ){
				return "NEITHER";
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return attacker.getPlayerColorString();
			}
		} else if( attacker.getVal() == 'S' ){
			if(  val == '1' ){
				return attacker.getPlayerColorString();
			} else if(  val == '2' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '3' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '4' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '5' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '6' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '7' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '8' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == '9' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'B' ){
				return (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";
			} else if(  val == 'F' ){
				return "WIN";
			} else if(  val == 'S' ){
				return "NEITHER";
			}
		}
		return "INVALID";
	}
}
