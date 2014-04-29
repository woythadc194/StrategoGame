package logic;

import ui.GameButton;

public class Battle {

	public static String getResult( GameButton attacker, GameButton defender ){
		//System.out.println("DEBUG piece.battle()   " + attacker.getPlayer() + ":" + attacker.getVal() + "    " + p.getPlayer() + ":" + p.getVal() );
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

		//System.out.println("DEBUG Invalid Battle");
		return "INVALID";
	}
}
