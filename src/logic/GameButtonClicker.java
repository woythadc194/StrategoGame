package logic;

import java.awt.Color;

import javax.swing.JOptionPane;

import ui.GameButton;
import ui.Piece;

public class GameButtonClicker {
	
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

	public static void click( GameButton button, Controller cont ){
		if( button.getReady() == false ){
			if( button.getVal()!= '~' ){
				int x = 0;
				for( int index = 0; index < 13; index ++ )
					if( cont.charIndexAry[index] == p.getVal() )
						x = index;
				Controller.piecesAry [ x ] ++;
			}
			if( cont.getSelectedPieceOpt() != 0 ){
				GameButtonClicker.alterButton(button, 2, Controller.charIndexAry[cont.getSelectedPieceOpt()], "BLUE" );
				
				button.setPiece( new Piece( 2, Controller.charIndexAry[cont.getSelectedPieceOpt()] , button.getXLocal(), button.getYLocal(), "BLUE", cont ) );
			} else{
				button.setPiece( new Piece( 3, '~', button.getXLocal(), button.getYLocal(), "NONE", cont ) );
			}
			int targetType = cont.getSelectedPieceOpt();
			if( targetType!= 0 )
				Controller.piecesAry[ targetType ]--;
			Controller.optButtonAry[ targetType ].clicked();
			cont.testReady();
			System.out.println( "PIECE NOT READY" );
		}else if(cont.isReady()){
			if( p.getVal() != 'X' ){
				if( cont.selectionMade == false ){
					if( p.getVal() == '~' || p.getVal() == 'B' || p.getVal() == 'F' )
						return;
					if( cont.redTurn && button.getPiece().getPlayer().equals( "RED" ) )
						cont.setSelectedButton( button );
					else if(!cont.redTurn && button.getPiece().getPlayer().equals( "BLUE" ) )
						cont.setSelectedButton( button );
				}else{
					// same button clicked twice removes it from selection
					if( button.getXLocal()==cont.selectedButton.getXLocal() && button.getYLocal()==cont.getSelectedButton().getYLocal() )
						cont.clearSelectedButton();
					// highlighted in white
					else if( button.moveToFromSelected )
						setUpBattle();
					// same color piece as already selected
					else if( cont.getSelectedButton().getPiece().getPlayer().equals( button.getPiece().getPlayer() ) )
						// but not a bomb or flag
						if( button.getPiece().getVal() == 'B' || button.getPiece().getVal() == 'F' )
							return;
						else
							//set selected button as a different one
							cont.setSelectedButton( button );
				}
			}
		}else{
			System.out.println( "GAME NOT READY" );
		}
	}
	
	private void waitTime( long time ){
		long start = System.currentTimeMillis();
		while( System.currentTimeMillis() - start < time ){
			;
		}
	}
	
	public void setUpBattle(){
		GameButton attacker = cont.getSelectedButton();
		attacker.repaint();
		GameButton defender = this;
		this.getPiece().visStatus();
		this.repaint();
		commenceBattle(attacker, defender ); 
	}
	
	public void commenceBattle( GameButton attacker, GameButton defender ){
		waitTime( 10 );
	
		String result = attacker.getPiece().battle( defender.getPiece() );
		
		if( result.equals( "INVALID" ) ){
			;
		} else if( result.equals( "NEITHER" ) ){
			attacker.setPiece( new Piece( 4, '~', attacker.getXLocal(), attacker.getYLocal(), "BLACK", cont ) );
			defender.setPiece( new Piece( 4, '~', defender.getXLocal(), defender.getYLocal(), "BLACK", cont ) );
		} else if( result.equals( "WIN" ) ){
			JOptionPane.showMessageDialog( this, "GameOver!" );
			System.exit( 0 );
		} else {
			if( result.equals( attacker.getPiece().getPlayer() ) ){
				defender.setPiece( attacker.getPiece() );
			}else{
			}
			attacker.setPiece( new Piece( 4, '~', attacker.getXLocal(), attacker.getYLocal(), "BLACK", cont ) );

		}
		attacker.repaint();
		defender.repaint();
		cont.clearSelectedButton();
		cont.switchTurns();
		System.out.println( cont.getGameBoard() );

	}
}
