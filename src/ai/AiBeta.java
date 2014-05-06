package ai;

import java.awt.Color;
import java.util.*;

import logic.Battle;
import logic.Controller;
import logic.GameButtonLogic;

import ui.GameButton;

public class AiBeta {
	
	private static ArrayList<GameButton>[][] attackablePieces;
	private static GameButton[][] buttonMatrix;
	private static int [][] distances;
	private static ArrayList<GameButton> [][] paths;
	private static int [][] chance; 
	private static Map<Boolean, int[]> [][] HOLYFUCKMAPMATRIX;
	
	@SuppressWarnings("unchecked")
	public AiBeta(){
		HOLYFUCKMAPMATRIX = new HashMap[ 10 ][ 10 ];
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				int [] tempAry = new int[ ]{ 0, 1, 1, 2, 3, 4, 4, 4, 5, 8, 6, 1, 1};
				Map<Boolean, int[]> temp = new HashMap< Boolean, int[] >();
				temp.put( false, tempAry );
				HOLYFUCKMAPMATRIX[ x ][ y ] = temp;
			}
		}
	}
	
	public static void updateHOLYFUCKMAP( GameButton button, boolean AIPiece, boolean moved, boolean showing ){
		//if AI piece
		if( !AIPiece ){
			/*
			 * remove bombs and flags from possible pieces 
			 * also, set the key of the map to true 
			 */
			if( moved ){
				for( boolean key : HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].keySet() ){
					Map<Boolean, int[]> temp = new HashMap< Boolean, int[] >();
					temp.put( true, HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].get( key ) );
					HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ] = temp;
					System.out.println( HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].containsKey( false ) );								//DEBUG
					HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].get( true )[10] = 0;
					HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].get( true )[11] = 0;
				}
			}
			if( showing ){
				int index = Controller.getCharIndex( button.getVal() );
				for( int x=0; x<10; x++ )
					for( int y=0; y<10; y ++ )
						for( boolean key : HOLYFUCKMAPMATRIX[ x ][ y ].keySet() ){
							if( getPossiblePieceCount( x, y, key ) > 1 )
								HOLYFUCKMAPMATRIX[ x ][ y ].get( key )[ index ]--;
							else if( getPossiblePieceCount( x, y, key ) == 1 )											//DEBUG
								System.out.println( "Discovered Piece " + button );										//DEBUG
							else if( getPossiblePieceCount( x, y, key ) < 1 )											//DEBUG
								System.out.println( "ERROR " + button );												//DEBUG
						}
				for( boolean key : HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].keySet() ){
					int [] temp = new int [ 13 ];
					temp[ index ] = 1;
					HOLYFUCKMAPMATRIX[ button.x() ][ button.y() ].put( key, temp );
				}
			}
		}// if( !AIPiece )
	}
	
	private static int getPossiblePieceCount( int x, int y, boolean key ){
		int num = 0;
		for( int i=0; i< HOLYFUCKMAPMATRIX[ x ][ y ].get( key ).length; i++ )
			num += HOLYFUCKMAPMATRIX[ x ][ y ].get( key )[ i ];
		return num;
		
	}
	
	public static Map<Boolean, int[]> [][] getHOLYFUCKMAP(){
		return AiBeta.HOLYFUCKMAPMATRIX;
	}
	
	@SuppressWarnings("unchecked")
	public void makeMove1(){
		try{ Thread.sleep(1000); } catch( Exception e ){}

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
						Map< Boolean, int[]> map = HOLYFUCKMAPMATRIX[defender.x()][defender.y()];
						for( boolean key : map.keySet() ){
							System.out.println( map );																	//DEBUG
							for( int i=1; i< map.get( key ).length; i++ )
								for( int j=0; j< map.get( key )[i]; j++ ){
									numAttacks ++;
									char val = Controller.getCharIndexAry()[i];
									attackWins += (Battle.getResult(attacker, val ) == attacker.getPlayerColorString()) ? 1 : 0;
								}
						}
					}
					chance[ x ][ y ] = (attackWins*100/numAttacks);
				}
			}
		
		//Set up Paths
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chance[x][y] != 0 ){
					paths[ x ][ y ] = getPathToVictim( x, y );
				}
			}
		}
		
		//Set up Distances
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chance[x][y] != 0 ){
					distances[ x ][ y ] = paths[ x ][ y ].size()-1;
				}
			}
		}
		
//		printStats();
		
		GameButton startButton = getButton();
		GameButton endButton = paths[ startButton.x() ][ startButton.y() ].get( 1 );
		GameButtonLogic.clicked( startButton );
		GameButtonLogic.clicked( endButton );
	}

	private GameButton getButton(){
		GameButton choice = null;
		double weight = 0.0;

		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chance[x][y] != 0 ){
					double currentWeight = (double)chance[ x ][ y ] / (double)distances[ x ][ y ];
					if( currentWeight > weight ){
						weight = currentWeight;
						choice = buttonMatrix[ x ][ y ];
					}
				}
			}
		}
		return choice;
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
		ArrayList<ArrayList<GameButton>> newPossiblePaths = new ArrayList<ArrayList<GameButton>>();
		//Go through all possible paths so far
		for( int i=0; i<possiblePaths.size(); i++ ){
			ArrayList<GameButton> list = possiblePaths.get( i );
			/*
			 * for every list, take the end button and find its 4 directions
			 * 
			 * if one of the directions has already been seen,  ignore it. 
			 * else add a new path with it attached at the end
			 */
			GameButton current = list.get( list.size()-1 );
			GameButton next = null;
			for( int k=0; k<4; k++ ){
				int x, y;
				if( k==0 ){ 	 x=current.x()+1; y=current.y(); }
				else if( k==1 ){ x=current.x()-1; y=current.y(); }
				else if( k==2 ){ x=current.x(); y=current.y()+1; }
				else{ 			 x=current.x(); y=current.y()-1; }
				
				try{ 
					next = buttonMatrix[ x ][ y ];
					// if next isnt black and isnt the color of start node
					if( ( next.getPlayerColor() != Color.BLACK ) && ( next.getPlayerColor()!=list.get( 0 ).getPlayerColor()) ){
						//and not in already seen
						if( !seen.contains( next ) ){
							seen.add( next );
							//make copy of current list
							ArrayList<GameButton> newList = new ArrayList<GameButton>();
							for( int j=0; j<list.size(); j++ )
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
	
	@SuppressWarnings("unused")
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
				if( distances[ x ][ y ]!= 0 ){
					ArrayList<GameButton> list = paths[ x ][ y ];
					for( int i=0; i<list.size(); i++ ){
						GameButton b = list.get( i );
						System.out.print( "(" + b.x() + "," + b.y() + ") " );
					}
					System.out.println();
				}
	}
}