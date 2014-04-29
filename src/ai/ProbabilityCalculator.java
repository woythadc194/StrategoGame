package ai;

import java.awt.Color;
import java.util.ArrayList;

import logic.Battle;
import logic.Controller;
import ui.GameButton;

public class ProbabilityCalculator {

	private static double redWins = 0, blueWins = 0, totalBattles = 0;
	
	public ProbabilityCalculator(){
		redWins = 0;
		blueWins = 0;
		totalBattles = 0;
	}
	
	public void decideAttackOrDefend( Color playerColor){
		ArrayList<ArrayList<GameButton>> buttonMatrix = Controller.getButtonMatrix();
		
		for( ArrayList<GameButton> listA : buttonMatrix ){
			for( GameButton attacker : listA ){
				for( ArrayList<GameButton> listB : buttonMatrix ){
					for( GameButton defender : listB ){
						if( 
						  ( attacker.getColor() == Color.RED && defender.getColor() == Color.BLUE ) ||
						  ( defender.getColor() == Color.RED && attacker.getColor() == Color.BLUE )
						  ){
							String result = Battle.getResult( attacker, defender );
							if( result.equals( "RED" ) ){
								redWins++;
								totalBattles ++;
							}else if (result.equals( "BLUE" ) ){
								blueWins++;
								totalBattles ++;
							}
						}
					}
				}
			}
		}
		System.out.println( "Total Battles: " + (int)totalBattles );
		System.out.println( "Red wins: " + (int)redWins + " = " + (redWins/totalBattles*100) + "%" );
		System.out.println( "Blue wins " + (int)blueWins + " = " + (blueWins/totalBattles*100) + "%\n" );
	}
}
