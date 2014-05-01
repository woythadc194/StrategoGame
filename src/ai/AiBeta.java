package ai;

import java.awt.Color;
import java.util.*;

import logic.Battle;
import logic.Controller;

import ui.GameButton;

public class AiBeta {
	
	private static ArrayList<GameButton>[][] attackablePieces;
	private static GameButton[][] buttonMatrix;
	private static int [][] attackDistances;
	private static double [][] attackStats; 
	private static Map<Boolean, int[]> [][] HOLYFUCKMAP;
	
	@SuppressWarnings("unchecked")
	public AiBeta(){
		HOLYFUCKMAP = new HashMap[ 10 ][ 10 ];
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				int [] tempAry = new int[ ]{ 0, 1, 1, 2, 3, 4, 4, 4, 5, 8, 6, 1, 1};
				Map<Boolean, int[]> temp = new HashMap< Boolean, int[] >();
				temp.put( false, tempAry );
				HOLYFUCKMAP[ x ][ y ] = temp;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void makeMove1(){
		attackStats = new double[ 10 ][ 10 ];
		attackablePieces = new ArrayList[ 10 ][ 10 ];
		attackDistances = new int[ 10 ][ 10 ];
		buttonMatrix = Controller.getButtonMatrix();
		
		for( int x=0; x<10; x++ )
			for( int y=0; y<10; y++ ){
				GameButton attacker = buttonMatrix[ x ][ y ];
				if( attacker.getPlayerColor() == Color.RED)
					attackablePieces[ x ][ y ] = ProbabilityCalculator.findTargets( attacker, false );
				if( attackablePieces[ x ][ y ]!= null && !attackablePieces[ x ][ y ].isEmpty() ){
					double attackWins = 0.0;
					int numAttacks = 0;
					for( GameButton defender : attackablePieces[ x ][ y ] ){
						Map< Boolean, int[]> map = HOLYFUCKMAP[defender.x()][defender.y()];
						for( int i = 1; i< map.get(false).length; i++ )
							for( int j=0; j< map.get(false)[i]; j++ ){
								numAttacks ++;
								char val = Controller.getCharIndexAry()[i];
								attackWins += (Battle.getResult(attacker, val ) == attacker.getPlayerColorString()) ? 1 : 0;
							}
					}
					attackStats[ x ][ y ] = (attackWins/numAttacks*100);
				}
			}

		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( attackStats[x][y] != 0.0 ){
					attackDistances[ x ][ y ] = getAttackDistance( x, y );
				}
			}
		}

		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				String s = "" + (int)attackStats[ y ][ x ];
				if( s.length() != 2 )
					s = "0" + s;
				System.out.print( s + " " );
			}
			System.out.println();
		}
		System.out.println();
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ )
				System.out.print( attackDistances[ y ][ x ] + " ");
			System.out.println();
		}
		
	}
	
	public int getAttackDistance( int x, int y ){
		GameButton startButton = buttonMatrix[ x ][ y ];
		ArrayList<GameButton> expansionA = new ArrayList<GameButton>();
		expansionA.add( startButton );
		ArrayList<GameButton> expansionB = new ArrayList<GameButton>();
		int steps = 0;
		steps = findStepsToVictim( steps, startButton.getPlayerColor(), expansionA, expansionB );
		
		
		return steps;
	}
	
	public int findStepsToVictim( int steps, Color startColor, ArrayList<GameButton> expansionA, ArrayList<GameButton> expansionB ){
		for( GameButton button : expansionA ){
			if( (button.getPlayerColor() != startColor) && (button.getVal()!= '~') && (button.getVal()!='X') ){
				return steps;
			} else {
				GameButton b = null;
				try{ 
					b = buttonMatrix[ button.x()+1 ][ button.y() ];
					if( b.getPlayerColor() != Color.BLACK && b.getPlayerColor() != startColor )
						expansionB.add( b );
				} catch( Exception e ){}
				
				try{ 
					b = buttonMatrix[ button.x()-1 ][ button.y() ];
					if( b.getPlayerColor() != Color.BLACK && b.getPlayerColor() != startColor )
						expansionB.add( b );
				} catch( Exception e ){}

				try{ 
					b = buttonMatrix[ button.x() ][ button.y()+1 ];
					if( b.getPlayerColor() != Color.BLACK && b.getPlayerColor() != startColor )
						expansionB.add( b );
				} catch( Exception e ){}
				
				try{ 
					b = buttonMatrix[ button.x() ][ button.y()-1 ];
					if( b.getPlayerColor() != Color.BLACK && b.getPlayerColor() != startColor )
						expansionB.add( b );
				} catch( Exception e ){}
			}
		}
		steps ++;
		expansionA = expansionB;
		expansionB = new ArrayList<GameButton>();
		return findStepsToVictim( steps, startColor, expansionA, expansionB );
				
	}
}



