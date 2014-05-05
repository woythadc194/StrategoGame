package ai;

import java.awt.Color;
import java.util.*;

import logic.Battle;
import logic.Controller;

import ui.GameButton;

public class AiBeta {
	
	private static ArrayList<GameButton>[][] attackablePieces;
	private static GameButton[][] buttonMatrix;
	private static int [][] distances;
	private static ArrayList<GameButton> [][] paths;
	private static int [][] chance; 
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
	
	public static Map<Boolean, int[]> [][] getHOLYFUCKMAP(){
		return AiBeta.HOLYFUCKMAP;
	}
	
	@SuppressWarnings("unchecked")
	public void makeMove1(){
		chance = new int[ 10 ][ 10 ];
		attackablePieces = new ArrayList[ 10 ][ 10 ];
		paths = new ArrayList[ 10 ][ 10 ];
		distances = new int[ 10 ][ 10 ];
		buttonMatrix = Controller.getButtonMatrix();
		
		//Set up chances
		for( int x=0; x<10; x++ )
			for( int y=0; y<10; y++ ){
				GameButton attacker = buttonMatrix[ x ][ y ];
				if( attacker.getPlayerColor() == Color.RED)
					attackablePieces[ x ][ y ] = ProbabilityCalculator.findTargets( attacker, false );
				if( attackablePieces[ x ][ y ]!= null && !attackablePieces[ x ][ y ].isEmpty() ){
					int attackWins = 0;
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
					chance[ x ][ y ] = (attackWins*100/numAttacks);
				}
			}

		//Set up Distances
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chance[x][y] != 0.0 ){
					distances[ x ][ y ] = getAttackDistance( x, y );
				}
			}
		}
		
		//Set up Paths
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chance[x][y] != 0.0 ){
					paths[ x ][ y ] = getPathToVictim( x, y );
				}
			}
		}
		
		printStats();
	}
	
	private ArrayList<GameButton> getPathToVictim( int x, int y ){
		GameButton button = buttonMatrix[ x ][ y ];
		ArrayList<ArrayList<GameButton>> possiblePaths = new ArrayList<ArrayList<GameButton>>();
		ArrayList<GameButton> seen = new ArrayList<GameButton>();
		
		possiblePaths.add( new ArrayList<GameButton>() );
		possiblePaths.get( 0 ).add( button );
		seen.add( button );
		
		return getPath( seen, possiblePaths );
	}

	private ArrayList<GameButton> getPath( ArrayList<GameButton> seen, ArrayList<ArrayList<GameButton>> possiblePaths ){
		System.out.println( possiblePaths );
		ArrayList<ArrayList<GameButton>> newPossiblePaths = new ArrayList<ArrayList<GameButton>>();
		//Go through all possible paths so far
		for( int i=0; i<possiblePaths.size(); i++ ){
			ArrayList<GameButton> list = possiblePaths.get( i );
			int endIndex = list.size()-1;
			/*
			 * for every list, take the end button and find its 4 directions
			 * 
			 * if one of the directions has already been seen,  ignore it. 
			 * else add a new path with it attached at the end
			 */
			GameButton current = list.get( endIndex );
			GameButton next = null;
			for( int k=0; k<4; k++ ){
				int x, y;
				if( k==0 ){ 	 x=current.x()+1; y=current.y(); }
				else if( k==1 ){ x=current.x()-1; y=current.y(); }
				else if( k==2 ){ x=current.x(); y=current.y()+1; }
				else{ 			 x=current.x(); y=current.y()-1; }
				
				try{ 
					next = buttonMatrix[ x ][ y ];
					System.out.println( current + " " + next );
					// if next isnt black and isnt the color of start node
					if( ( next.getPlayerColor() != Color.BLACK ) && ( next.getPlayerColor()!=list.get( 0 ).getPlayerColor()) ){
						//and not in already seen
						if( !seen.contains( next ) ){
							seen.add( next );
							//make copy of current list
							ArrayList<GameButton> newList = new ArrayList<GameButton>();
							for( int j=0; i<list.size(); j++ )
								newList.add( list.get( j ) );
							//add next to the new copy
							newList.add( next );
							if( next.getPlayerColor() == list.get( 0 ).getOpponetColor() )
								return newList;
							//add new list to the new possible paths
							newPossiblePaths.add( newList );
						}
					}
				} catch( Exception e ){}		
			}
		}
		possiblePaths = null;
		return getPath( seen, newPossiblePaths );
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
	
	private void printStats(){
		printChances();
		printDistances();
		printPaths();
	}
	
	private void printChances(){
		System.out.println( "CHANCES" );
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				String s = "" + chance[ y ][ x ];
				if( s.length() != 2 )
					s = " " + s;
				System.out.print( s + " " );
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private void printDistances(){
		System.out.println( "DISTANCES" );
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				String s = "" + distances[ y ][ x ];
				if( s.length() != 2 )
					s = " " + s;
				System.out.print( s + " " );
			}
			System.out.println();
		}		
		System.out.println();
	}
	
	private void printPaths(){
		
		System.out.println( "PATHS" );
		for( int x=0; x<10; x++ )
			for( int y=0; y<10; y++ )
				if( distances[ y ][ x ]!= 0 )
					System.out.print( paths[ y ][ x ] + "\n" );
	}
}



