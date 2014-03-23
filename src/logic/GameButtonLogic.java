package logic;

import java.awt.Color;

import javax.swing.JOptionPane;

import ui.GameButton;

public class GameButtonLogic {
	
	private static Controller cont;
	
	public static void alterButton(GameButton button, int visibility, char val, String player){
		if( player.equals("RED") )
			button.setColor( Color.RED );
		else if( player.equals( "BLUE" ) )
			button.setColor( Color.BLUE );
		else
			button.setColor( Color.DARK_GRAY );
		
		button.setVisibility(visibility);
		button.setVal(val);
		button.setPlayer(player);
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
		
		int yMin = cont.getSULogic().getYMin();
		int yMax = cont.getSULogic().getYMax();
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
		SetupLogic s = cont.SULogic;
		if( cont.getSelectedPieceOpt() != 0 )
			GameButtonLogic.alterButton(button, 2, Controller.getCharIndexAry()[cont.getSelectedPieceOpt()], cont.getSULogic().getCurrentPlayer() );
		else
			alterButton(button, 3, '~', "NONE");
		
		int targetType = cont.getSelectedPieceOpt();
		if( targetType!= 0 )
			Controller.getPiecesAry()[ targetType ]--;
		PceOptBttnLogic.clicked( Controller.getOptButtonAry()[ targetType ] , cont);
		cont.testReady();
	}
	
	private static void midGameClick( GameButton button, Controller cont ){

		if( button.getVal() != 'X' ){
			if( cont.getSelectionMade() == false ){
				if( button.getVal() == '~' || button.getVal() == 'B' || button.getVal() == 'F' )
					return;
				if( Controller.getRedTurn() && button.getPlayer().equals( "RED" ) )
					Controller.setSelectedButton( button );
				else if(!Controller.getRedTurn() && button.getPlayer().equals( "BLUE" ) )
					Controller.setSelectedButton( button );
			}else{
				// same button clicked twice removes it from selection
				if( button.getXLocal()==Controller.getSelectedButton().getXLocal() && button.getYLocal()==Controller.getSelectedButton().getYLocal() )
					Controller.clearSelectedButton();
				// highlighted in white
				
				//FIXME
				else if( button.getMovable() )
					setUpBattle( button );

				// same color piece as already selected
				else if( Controller.getSelectedButton().getPlayer().equals( button.getPlayer() ) )
					// but not a bomb or flag
					if( button.getVal() == 'B' || button.getVal() == 'F' )
						return;
					else
						//set selected button as a different one
						Controller.setSelectedButton( button );
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
			alterButton(attacker, 3, '~', "NONE");
			alterButton(defender, 3, '~', "NONE");
		} else if( result.equals( "WIN" ) ){
			JOptionPane.showMessageDialog( null, "GameOver!" );
			System.exit( 0 );
		} else {
			if( result.equals( attacker.getPlayer() ) )
				if( defender.getVal()!= '~' )
					alterButton(defender, 3, attacker.getVal(), attacker.getPlayer() );
				else
					alterButton(defender, defender.getVisibility(), attacker.getVal(), attacker.getPlayer() );
			alterButton(attacker, 3, '~', "NONE" );
		}
		attacker.repaint();
		defender.repaint();
		Controller.clearSelectedButton();
		cont.switchTurns();
		System.out.println( cont.getGameBoard() );

	}
	
	public static String getBattleResult( GameButton attacker, GameButton defender ){
		//System.out.println("DEBUG piece.battle()   " + attacker.getPlayer() + ":" + attacker.getVal() + "    " + p.getPlayer() + ":" + p.getVal() );
		if( attacker.getPlayer().equals( defender.getPlayer() ) )
			return "INVALID";
		if( attacker.getVal() == '~' || attacker.getVal() == 'F' || attacker.getVal() == 'B')
			return "INVALID";
		if( attacker.getVal() == '1' ){
			if( defender.getVal() == '1' ){
				return "NEITHER";
			} else if( defender.getVal() == '2' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '3' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '4' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '2' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return "NEITHER";
			} else if( defender.getVal() == '3' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '4' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '3' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return "NEITHER";
			} else if( defender.getVal() == '4' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '4' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return "NEITHER";
			} else if( defender.getVal() == '5' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '5' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '5' ){
				return "NEITHER";
			} else if( defender.getVal() == '6' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '6' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '6' ){
				return "NEITHER";
			} else if( defender.getVal() == '7' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '7' ){
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '7' ){
				return "NEITHER";
			} else if( defender.getVal() == '8' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "NEITHER";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '8' ){ //MINER
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '7' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '8' ){
				return "NEITHER";
			} else if( defender.getVal() == '9' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == '9' ){ //SCOUT
			if( defender.getVal() == '1' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '7' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '8' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '9' ){
				return "NEITHER";
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return attacker.getPlayer();
			}
		} else if( attacker.getVal() == 'S' ){
			if( defender.getVal() == '1' ){
				return attacker.getPlayer();
			} else if( defender.getVal() == '2' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '3' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '4' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '5' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '6' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '7' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '8' ){
				return defender.getPlayer();
			} else if( defender.getVal() == '9' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'B' ){
				return defender.getPlayer();
			} else if( defender.getVal() == 'F' ){
				return "WIN";
			} else if( defender.getVal() == 'S' ){
				return "NEITHER";
			}
		}
		
		if( defender.getVal() == '~' )
			return attacker.getPlayer();

		System.out.println("DEBUG X");
		return "INVALID";
	}
}
