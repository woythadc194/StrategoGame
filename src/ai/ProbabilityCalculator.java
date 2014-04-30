package ai;

import java.awt.Color;
import java.util.ArrayList;

import logic.Battle;
import logic.Controller;
import ui.GameButton;

public class ProbabilityCalculator {

	private static double redWins = 0, blueWins = 0, totalBattles = 0;
	private static ArrayList<ArrayList<GameButton>> buttonMatrix;
	
	public ProbabilityCalculator(){
		redWins = 0;
		blueWins = 0;
		totalBattles = 0;
	}
	
	public void getAllBattleStats( Color playerColor){
		
		buttonMatrix = Controller.getButtonMatrix();
		
		for( ArrayList<GameButton> listA : buttonMatrix ){
			for( GameButton attacker : listA ){
				for( ArrayList<GameButton> listB : buttonMatrix ){
					for( GameButton defender : listB ){
						if( attacker.getPlayerColor()==playerColor && ( defender.getPlayerColor()!=playerColor  && defender.getPlayerColor()!=Color.BLACK )){
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
	
	public static ArrayList<GameButton> findTargets( GameButton attacker ){
		buttonMatrix = Controller.getButtonMatrix();
		ArrayList<GameButton> seenList = new ArrayList<GameButton>();
		ArrayList<GameButton> attackable = new ArrayList<GameButton>();
		seenList.add(attacker);
		findMoves( attacker.getPlayerColor(), attacker, seenList, attackable );
		for( GameButton defender : attackable )
			defender.setTargeted( true );
		return attackable;
	}
	
	/**
	 * 
	 * @param b Button to be tested for movement
	 * @param seenList Buttons already looked at for original button
	 * @param attackable Buttons that can be attacked
	 */
	private static void findMoves( Color playerColor, GameButton b, ArrayList<GameButton> seenList, ArrayList<GameButton> attackable){
		GameButton defender;
		try{ 
			defender = buttonMatrix.get( b.x() ).get( b.y()-1 );
			if( !seenList.contains( defender ) ){
				if( defender.getPlayerColor() != playerColor && defender.getPlayerColor()!= Color.BLACK ){
					if(defender.getPlayerColor() == Color.DARK_GRAY )
						findMoves( playerColor, defender, seenList, attackable );
					else
						attackable.add( defender );
				}
			}
		} catch(Exception e){}
		
		try{ 
			defender = buttonMatrix.get( b.x() ).get( b.y()+1 );
			if( !seenList.contains( defender ) ){
				seenList.add( defender );
				if( defender.getPlayerColor() != playerColor && defender.getPlayerColor()!= Color.BLACK ){
					if(defender.getPlayerColor() == Color.DARK_GRAY )
						findMoves( playerColor, defender, seenList, attackable );
					else
						attackable.add( defender );
				}
			}
		} catch(Exception e){}
		
		try{ 
			defender = buttonMatrix.get( b.x()-1 ).get( b.y() ); 
			if( !seenList.contains( defender ) ){
				seenList.add( defender );
				if( defender.getPlayerColor() != playerColor && defender.getPlayerColor()!= Color.BLACK ){
					if(defender.getPlayerColor() == Color.DARK_GRAY )
						findMoves( playerColor, defender, seenList, attackable );
					else
						attackable.add( defender );
				}
			}
		} catch(Exception e){}
		
		try{ 
			defender = buttonMatrix.get( b.x()+1 ).get( b.y() ); 
			if( !seenList.contains( defender ) ){
				if( defender.getPlayerColor() != playerColor && defender.getPlayerColor()!= Color.BLACK ){
					System.out.println( defender.getPlayerColorString() );
					if(defender.getPlayerColor() == Color.DARK_GRAY )
						findMoves( playerColor, defender, seenList, attackable );
					else
						attackable.add( defender );
				}
			}
		} catch(Exception e){}
	}

	public static void clearTargets() {
		for( ArrayList<GameButton> list : buttonMatrix )
			for( GameButton button : list )
				button.setTargeted( false );
	}
}
