/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;

import javax.swing.JOptionPane;

import ai.ProbabilityCalculator;

import ui.GameButton;

public class GameButtonLogic {
	
	public static void alterButton(GameButton button, int visibility, char val, Color playerColor){
		button.setColor( playerColor );
		button.setVisibility(visibility);
		button.setVal(val);
	}

	public static void clicked( GameButton button ){
		if( button.getReady() == false )
			preGameClick( button );
		else if(Controller.isReady())
			midGameClick( button );
		else
			JOptionPane.showConfirmDialog( null, "GAME NOT READY" );
	}

	private static void preGameClick( GameButton button ){
		
		int yMin = SetupLogic.getYMin();
		int yMax = SetupLogic.getYMax();
		int yLocal = button.y();
		if( yLocal < yMin || yLocal > yMax )
			return;
		
		if( button.getVal()!= '~' ){
			int x = 0;
			for( int index = 0; index < 13; index ++ )
				if( Controller.getCharIndexAry()[index] == button.getVal() )
					x = index;
			Controller.getPiecesAry()[ x ] ++;
		}
		if( Controller.getSelectedPieceOpt() != 0 ) {
			GameButtonLogic.alterButton(button, 2, Controller.getCharIndexAry()[Controller.getSelectedPieceOpt()], SetupLogic.getCurrentPlayer() );
		} else
			alterButton( button, 3, '~', button.getPlayerColor() );
		
		int targetType = Controller.getSelectedPieceOpt();
		if( targetType!= 0 )
			Controller.getPiecesAry()[ targetType ]--;
		PceOptBttnLogic.clicked( Controller.getOptButtonAry()[ targetType ] );
	}
	
	private static void midGameClick( GameButton button ){

		//Initial click isn't an X
		if( button.getVal() != 'X' ){
			/*
			 * If a selection hasnt been made:
			 * 	If  ~, B, or F was clicked, do nothing
			 * 	Else  make battle since two buttons of non X were selected
			 */
			if( Controller.getSelectionMade() == false ){
				if( button.getVal() == '~' || button.getVal() == 'B' || button.getVal() == 'F' )
					return;
				if( Controller.getPlayerTurn() == button.getPlayerColor() ){
					Controller.setSelectedButton( button );
					ProbabilityCalculator.findTargets( button, true );
				}
			}else{
				// same button clicked twice removes it from selection and clears targets
				if( button.x()==Controller.getSelectedButton().x() && button.y()==Controller.getSelectedButton().y() ){
					Controller.clearSelectedButton();
					ProbabilityCalculator.clearTargets();
				}
				
				else if( button.isMovable() )
					setUpBattle( button );

				// same color piece as already selected
				else if( Controller.getSelectedButton().getPlayerColor() == button.getPlayerColor() )
					// but not a bomb or flag
					if( button.getVal() == 'B' || button.getVal() == 'F' )
						return;
					else{//set selected button as a different one
						ProbabilityCalculator.clearTargets();
						Controller.setSelectedButton( button );
						ProbabilityCalculator.findTargets( button, true );
					}
			}
		}
	}
	
	private static void waitTime( long time ){
		long start = System.currentTimeMillis();
		while( System.currentTimeMillis() - start < time ){
			;
		}
	}

	public static void setUpBattle( GameButton button ){
		GameButton attacker = Controller.getSelectedButton();
		attacker.repaint();
		GameButton defender = button;
		commenceBattle(attacker, defender );
	}
	
	public static void commenceBattle( GameButton attacker, GameButton defender ){
		waitTime( 10 );
	
		String result = Battle.getResult( attacker, defender );
//		System.out.println( attacker + " vs " + defender + " " + result );																			//DEBUG
			
		if( result.equals( "INVALID" ) ){
			;
		} else if( result.equals( "NEITHER" ) ){
			alterButton(attacker, 3, '~', Color.DARK_GRAY);
			alterButton(defender, 3, '~', Color.DARK_GRAY);
		} else if( result.equals( "WIN" ) ){
			JOptionPane.showMessageDialog( null, "GameOver!" );
			System.exit( 0 );
		} else {
			if( result.equals( attacker.getPlayerColorString() ) ){ //attacker won
				if( defender.getVal()!= '~' ){
					alterButton(defender, 3, attacker.getVal(), attacker.getPlayerColor() );
				}else{ //moving to empty space
//					System.out.println( "MOVING TO EMPTY SPACE" );																							//DEBUG
					alterButton(defender, attacker.getVisibility(), attacker.getVal(), attacker.getPlayerColor() );
				}
			} else { //defender won
				alterButton(defender, 3, defender.getVal(), defender.getPlayerColor() );
			}
			alterButton(attacker, 3, '~', Color.DARK_GRAY );
		}
		attacker.repaint();
		defender.repaint();
		Controller.clearSelectedButton();
		Controller.switchTurns();

	}
	
	
}
