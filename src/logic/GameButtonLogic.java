/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;

import javax.swing.JOptionPane;

import ui.GameButton;

public class GameButtonLogic {
	
	private static Controller cont;
	
	public static void alterButton(GameButton button, int visibility, char val, Color playerColor){
		button.setColor( playerColor );
		button.setVisibility(visibility);
		button.setVal(val);
	}

	public static void clicked( GameButton button, Controller cont ){
		GameButtonLogic.cont = cont;
		if( button.getReady() == false )
			preGameClick( button, cont );
		else if(cont.isReady())
			midGameClick( button, cont );
		else
			JOptionPane.showConfirmDialog( null, "GAME NOT READY" );
	}

	private static void preGameClick( GameButton button, Controller cont ){
		
		Controller.getSULogic();
		int yMin = SetupLogic.getYMin();
		Controller.getSULogic();
		int yMax = SetupLogic.getYMax();
		int yLocal = button.getYLocal();
		if( yLocal < yMin || yLocal > yMax )
			return;
		
		if( button.getVal()!= '~' ){
			int x = 0;
			for( int index = 0; index < 13; index ++ )
				if( Controller.getCharIndexAry()[index] == button.getVal() )
					x = index;
			Controller.getPiecesAry()[ x ] ++;
		}
		if( cont.getSelectedPieceOpt() != 0 ) {
			Controller.getSULogic();
			GameButtonLogic.alterButton(button, 2, Controller.getCharIndexAry()[cont.getSelectedPieceOpt()], SetupLogic.getCurrentPlayer() );
		} else
			alterButton( button, 3, '~', Color.DARK_GRAY );
		
		int targetType = cont.getSelectedPieceOpt();
		if( targetType!= 0 )
			Controller.getPiecesAry()[ targetType ]--;
		PceOptBttnLogic.clicked( Controller.getOptButtonAry()[ targetType ] , cont);
		cont.testPlayerReady();
	}
	
	private static void midGameClick( GameButton button, Controller cont ){

		if( button.getVal() != 'X' ){
			if( cont.getSelectionMade() == false ){
				if( button.getVal() == '~' || button.getVal() == 'B' || button.getVal() == 'F' )
					return;
				if( Controller.getRedTurn() && button.getPlayerColorString().equals( "RED" ) )
					Controller.setSelectedButton( button );
				else if(!Controller.getRedTurn() && button.getPlayerColorString().equals( "BLUE" ) )
					Controller.setSelectedButton( button );
			}else{
				// same button clicked twice removes it from selection
				if( button.getXLocal()==Controller.getSelectedButton().getXLocal() && button.getYLocal()==Controller.getSelectedButton().getYLocal() )
					Controller.clearSelectedButton();
				// highlighted in white
				
				else if( button.getMovable() )
					setUpBattle( button );

				// same color piece as already selected
				else if( Controller.getSelectedButton().getPlayerColorString().equals( button.getPlayerColorString() ) )
					// but not a bomb or flag
					if( button.getVal() == 'B' || button.getVal() == 'F' )
						return;
					else
						//set selected button as a different one
						Controller.setSelectedButton( button );
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
	
		String result = getBattleResult( attacker, defender );
		
		if( result.equals( "INVALID" ) ){
			;
		} else if( result.equals( "NEITHER" ) ){
			alterButton(attacker, 3, '~', Color.DARK_GRAY);
			alterButton(defender, 3, '~', Color.DARK_GRAY);
		} else if( result.equals( "WIN" ) ){
			JOptionPane.showMessageDialog( null, "GameOver!" );
			System.exit( 0 );
		} else {
			if( result.equals( attacker.getPlayerColorString() ) )
				if( defender.getVal()!= '~' )
					alterButton(defender, 3, attacker.getVal(), attacker.getPlayerColor() );
				else
					alterButton(defender, defender.getVisibility(), attacker.getVal(), attacker.getPlayerColor() );
			alterButton(attacker, 3, '~', Color.DARK_GRAY );
		}
		attacker.repaint();
		defender.repaint();
		Controller.clearSelectedButton();
		cont.switchTurns();
/*
 * TODO	
 * 		System.out.println( cont.getGameBoard() );
		*/

	}
	
	public static String getBattleResult( GameButton attacker, GameButton defender ){
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

		System.out.println("DEBUG X");
		return "INVALID";
	}
}
