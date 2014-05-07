package logic;

import java.awt.Color;

import ui.GameButton;

public class Battle {

	public static String getResult( GameButton attacker, GameButton defender ){
		
		int attackWeight = Controller.getCharIndex( attacker.getVal() );
		int defendWeight = Controller.getCharIndex( defender.getVal() );
		
		String attackerColor = attacker.getPlayerColorString();
		String defenderColor = defender.getPlayerColorString();
		
		//Invalid attacks
		if( attackerColor.equals( defenderColor ) || attackWeight == 10 || attackWeight == 11 || attackWeight == 13 )
			return "INVALID";
		
		//Empty space
		if( defendWeight == 0 )
			return attackerColor;
		
		//bomb
		if( defendWeight == 10 )
			if( attackWeight != 8 )
				return defenderColor;
			else
				return attackerColor;
		
		//flag
		if( defendWeight == 11 )
			return "WIN";
		
		//spy
		if( defendWeight == 12 )
			return attackerColor;
		if( attackWeight == 12 )
			if( defendWeight != 1 )
				return defenderColor;
			else
				return attackerColor;
		
		//All other cases
		if( defendWeight > attackWeight )
			return attackerColor;
		else if( defendWeight < attackWeight )
			return defenderColor;
		else
			return "NEITHER";
		
	}
	
	public static String getResult( GameButton attacker, char val ){
		
		int attackWeight = Controller.getCharIndex( attacker.getVal() );
		int defendWeight = Controller.getCharIndex( val );
		
		String attackerColor = attacker.getPlayerColorString();
		String defenderColor = (attacker.getOpponetColor() == Color.RED ) ? "BLUE" : "RED" ;
		
		//Invalid attacks
		if(  attackWeight == 0 || attackWeight == 10 || attackWeight == 11 || attackWeight == 13 )
			return "INVALID";
		
		//bomb
		if( defendWeight == 10 )
			if( attackWeight != 8 )
				return defenderColor;
			else
				return attackerColor;
		
		//flag
		if( defendWeight == 11 )
			return "WIN";
		
		//spy
		if( defendWeight == 12 )
			return attackerColor;
		if( attackWeight == 12 )
			if( defendWeight != 1 )
				return defenderColor;
			else
				return attackerColor;
		
		//All other cases
		if( defendWeight < attackWeight )
			return attackerColor;
		else if( defendWeight > attackWeight )
			return defenderColor;
		else
			return "NEITHER";
		
	}
}
