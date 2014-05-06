package logic;

import java.awt.Color;

import ui.GameButton;

public class Battle {

	public static String getResult( GameButton attacker, GameButton defender ){
		char valD = defender.getVal();
		Color colD = defender.getPlayerColor();
		String strD = defender.getPlayerColorString();
		
		char valA = attacker.getVal();
		Color colA = attacker.getPlayerColor();
		String strA = attacker.getPlayerColorString();
		
		if( colD == colA )
			return "INVALID";
		if( valA == '~' || valA == 'F' || valA == 'B')
			return "INVALID";
		if( valA == '1' ){
			if( valD == '1' ){
				return "NEITHER";
			} else if( valD == '2' ){
				return strA;
			} else if( valD == '3' ){
				return strA;
			} else if( valD == '4' ){
				return strA;
			} else if( valD == '5' ){
				return strA;
			} else if( valD == '6' ){
				return strA;
			} else if( valD == '7' ){
				return strA;
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '2' ){
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return "NEITHER";
			} else if( valD == '3' ){
				return strA;
			} else if( valD == '4' ){
				return strA;
			} else if( valD == '5' ){
				return strA;
			} else if( valD == '6' ){
				return strA;
			} else if( valD == '7' ){
				return strA;
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '3' ){
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return "NEITHER";
			} else if( valD == '4' ){
				return strA;
			} else if( valD == '5' ){
				return strA;
			} else if( valD == '6' ){
				return strA;
			} else if( valD == '7' ){
				return strA;
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '4' ){
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return "NEITHER";
			} else if( valD == '5' ){
				return strA;
			} else if( valD == '6' ){
				return strA;
			} else if( valD == '7' ){
				return strA;
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '5' ){
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return strD;
			} else if( valD == '5' ){
				return "NEITHER";
			} else if( valD == '6' ){
				return strA;
			} else if( valD == '7' ){
				return strA;
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '6' ){
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return strD;
			} else if( valD == '5' ){
				return strD;
			} else if( valD == '6' ){
				return "NEITHER";
			} else if( valD == '7' ){
				return strA;
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '7' ){
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return strD;
			} else if( valD == '5' ){
				return strD;
			} else if( valD == '6' ){
				return strD;
			} else if( valD == '7' ){
				return "NEITHER";
			} else if( valD == '8' ){
				return strA;
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "NEITHER";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '8' ){ //MINER
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return strD;
			} else if( valD == '5' ){
				return strD;
			} else if( valD == '6' ){
				return strD;
			} else if( valD == '7' ){
				return strD;
			} else if( valD == '8' ){
				return "NEITHER";
			} else if( valD == '9' ){
				return strA;
			} else if( valD == 'B' ){
				return strA;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == '9' ){ //SCOUT
			if( valD == '1' ){
				return strD;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return strD;
			} else if( valD == '5' ){
				return strD;
			} else if( valD == '6' ){
				return strD;
			} else if( valD == '7' ){
				return strD;
			} else if( valD == '8' ){
				return strD;
			} else if( valD == '9' ){
				return "NEITHER";
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return strA;
			}
		} else if( valA == 'S' ){
			if( valD == '1' ){
				return strA;
			} else if( valD == '2' ){
				return strD;
			} else if( valD == '3' ){
				return strD;
			} else if( valD == '4' ){
				return strD;
			} else if( valD == '5' ){
				return strD;
			} else if( valD == '6' ){
				return strD;
			} else if( valD == '7' ){
				return strD;
			} else if( valD == '8' ){
				return strD;
			} else if( valD == '9' ){
				return strD;
			} else if( valD == 'B' ){
				return strD;
			} else if( valD == 'F' ){
				return "WIN";
			} else if( valD == 'S' ){
				return "NEITHER";
			}
		}
		
		if( valD == '~' )
			return strA;
		return "INVALID";
	}
	
	public static String getResult( GameButton attacker, char valD ){
		
		char valA = attacker.getVal();
		String strA = attacker.getPlayerColorString();

		String strD = (attacker.getPlayerColor() == Color.RED) ? "BLUE" : "RED";

		if( valA == '~' || valA == 'F' || valA == 'B')
			return "INVALID";
		if( valA == '1' ){
			if(  valD == '1' ){
				return "NEITHER";
			} else if(  valD == '2' ){
				return strA;
			} else if(  valD == '3' ){
				return strA;
			} else if(  valD == '4' ){
				return strA;
			} else if(  valD == '5' ){
				return strA;
			} else if(  valD == '6' ){
				return strA;
			} else if(  valD == '7' ){
				return strA;
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '2' ){
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return "NEITHER";
			} else if(  valD == '3' ){
				return strA;
			} else if(  valD == '4' ){
				return strA;
			} else if(  valD == '5' ){
				return strA;
			} else if(  valD == '6' ){
				return strA;
			} else if(  valD == '7' ){
				return strA;
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '3' ){
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return "NEITHER";
			} else if(  valD == '4' ){
				return strA;
			} else if(  valD == '5' ){
				return strA;
			} else if(  valD == '6' ){
				return strA;
			} else if(  valD == '7' ){
				return strA;
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '4' ){
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return "NEITHER";
			} else if(  valD == '5' ){
				return strA;
			} else if(  valD == '6' ){
				return strA;
			} else if(  valD == '7' ){
				return strA;
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '5' ){
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return strD;
			} else if(  valD == '5' ){
				return "NEITHER";
			} else if(  valD == '6' ){
				return strA;
			} else if(  valD == '7' ){
				return strA;
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '6' ){
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return strD;
			} else if(  valD == '5' ){
				return strD;
			} else if(  valD == '6' ){
				return "NEITHER";
			} else if(  valD == '7' ){
				return strA;
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '7' ){
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return strD;
			} else if(  valD == '5' ){
				return strD;
			} else if(  valD == '6' ){
				return strD;
			} else if(  valD == '7' ){
				return "NEITHER";
			} else if(  valD == '8' ){
				return strA;
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "NEITHER";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '8' ){ //MINER
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return strD;
			} else if(  valD == '5' ){
				return strD;
			} else if(  valD == '6' ){
				return strD;
			} else if(  valD == '7' ){
				return strD;
			} else if(  valD == '8' ){
				return "NEITHER";
			} else if(  valD == '9' ){
				return strA;
			} else if(  valD == 'B' ){
				return strA;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == '9' ){ //SCOUT
			if(  valD == '1' ){
				return strD;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return strD;
			} else if(  valD == '5' ){
				return strD;
			} else if(  valD == '6' ){
				return strD;
			} else if(  valD == '7' ){
				return strD;
			} else if(  valD == '8' ){
				return strD;
			} else if(  valD == '9' ){
				return "NEITHER";
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return strA;
			}
		} else if( valA == 'S' ){
			if(  valD == '1' ){
				return strA;
			} else if(  valD == '2' ){
				return strD;
			} else if(  valD == '3' ){
				return strD;
			} else if(  valD == '4' ){
				return strD;
			} else if(  valD == '5' ){
				return strD;
			} else if(  valD == '6' ){
				return strD;
			} else if(  valD == '7' ){
				return strD;
			} else if(  valD == '8' ){
				return strD;
			} else if(  valD == '9' ){
				return strD;
			} else if(  valD == 'B' ){
				return strD;
			} else if(  valD == 'F' ){
				return "WIN";
			} else if(  valD == 'S' ){
				return "NEITHER";
			}
		}
		return "INVALID";
	}
}
