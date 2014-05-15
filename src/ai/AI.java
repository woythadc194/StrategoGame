package ai;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import logic.Battle;
import logic.Controller;
import logic.GameButtonLogic;
import ui.Board;
import ui.GameButton;
import ui.SpacerButton;

public class AI {

	private static ArrayList<GameButton>[][] attackablePieces;
	private static GameButton[][] buttonMatrix;
	private static int [][] distances;
	private static ArrayList<GameButton> [][] paths;
	private static int [][] chances; 

	private static int [] possibleOpponentPieces;
	private static int [] originalOpponentPieces;
	private static char [][] foundOpponentPieces;
	private static boolean [][] movedAry;


	public AI(){

		buttonMatrix = Controller.getButtonMatrix();
		possibleOpponentPieces = new int[ ]{ 0, 1, 1, 2, 3, 4, 4, 4, 5, 8, 6, 1, 1};
		originalOpponentPieces = new int[ ]{ 0, 1, 1, 2, 3, 4, 4, 4, 5, 8, 6, 1, 1};
		foundOpponentPieces = new char[ 10 ][ 10 ];
		movedAry = new boolean[ 10 ][ 10 ];

		for( int x=0; x<10; x++ ){

			for( int y=0; y<10; y++ ){

				foundOpponentPieces[ x ][ y ] = 'X';
				movedAry[ x ][ y ] = false;

			}
		}
	}

	public static void updateStats( GameButton attacker, boolean nowShowingAtk, boolean alreadyShowingAtk, 
			GameButton defender, boolean nowShowingDef, boolean alreadyShowingDef, String result ){
		if( result.equals( "INVALID" ) )
			return;

		if( attacker.getPlayerColor() == Color.BLUE ){

			if( !alreadyShowingAtk )
				if( nowShowingAtk )
					possibleOpponentPieces[ Controller.getCharIndex( attacker.getVal() ) ] --;

			if( result.equals( "NEITHER" ) ){				// blue attacked and stalemate

				foundOpponentPieces[ attacker.x() ][ attacker.y() ] = 'X';
				movedAry[ attacker.x() ][ attacker.y() ] = false;				
				Board.getGraveyard().add( new SpacerButton( new Dimension( 40, 40 ), defender.getVal() ) );
				Board.getGraveyard().repaint();

			} else if( result.equals( "BLUE" ) ){ 			// blue attacked and won
				if( defender.getVal() != '~' ){				// not moving to empty space
					if( Math.abs( attacker.y() - defender.y() ) > 1 || Math.abs( attacker.x() - defender.x() ) > 1 ) //changes if 9 double moved
						foundOpponentPieces[ defender.x() ][ defender.y() ] = attacker.getVal();
					else 																			// else keep hidden
						foundOpponentPieces[ defender.x() ][ defender.y() ] = 'X';
					Board.getGraveyard().add( new SpacerButton( new Dimension( 40, 40 ), defender.getVal() ) );
					Board.getGraveyard().repaint();

				}else{
					foundOpponentPieces[ defender.x() ][ defender.y() ] = 'X';
				}
				movedAry[ defender.x() ][defender.y() ] = true;


			} else {  //blue attacked but lost

				foundOpponentPieces[ attacker.x() ][ attacker.y() ] = 'X';
				movedAry[ attacker.x() ][ attacker.y() ] = false;

			}
		} // end attack == blue


		if( defender.getPlayerColor() == Color.BLUE ){
			if( !alreadyShowingDef )
				if( nowShowingDef )
					possibleOpponentPieces[ Controller.getCharIndex( defender.getVal() ) ]--;

			if( result.equals( "NEITHER" ) ){ //red attacked and stalemate

				foundOpponentPieces[ defender.x() ][ defender.y() ] = 'X';
				movedAry[ defender.x() ][ defender.y() ] = false;
				Board.getGraveyard().add( new SpacerButton( new Dimension( 40, 40 ), defender.getVal() ) );
				Board.getGraveyard().repaint();

			} else if( result.equals( "RED" ) ){ //red attacked and won

				foundOpponentPieces[ defender.x() ][ defender.y() ] = 'X';
				movedAry[ defender.x() ][ defender.y() ] = false;	

			} else{ //red attacked and lost

				foundOpponentPieces[ defender.x() ][ defender.y() ] = defender.getVal();
				movedAry[ defender.x() ][ defender.y() ] = false;
				Board.getGraveyard().add( new SpacerButton( new Dimension( 40, 40 ), attacker.getVal() ) );
				Board.getGraveyard().repaint();

			}
		}

	} // end updateStats

	@SuppressWarnings("unchecked")
	public void makeMove1(){
		System.out.println( "AI MAKING MOVE" );																					//DEBUG
		try{ Thread.sleep(100); } catch( Exception e ){}

		chances = new int[ 10 ][ 10 ];
		attackablePieces = new ArrayList[ 10 ][ 10 ];
		paths = new ArrayList[ 10 ][ 10 ];
		distances = new int[ 10 ][ 10 ];

		//Set up chances
		for( int x=0; x<10; x++ )

			for( int y=0; y<10; y++ ){

				GameButton attacker = buttonMatrix[ x ][ y ];

				if( attacker.getPlayerColor() == Color.RED && attacker.getVal()!= 'B' && attacker.getVal()!= 'F' )

					attackablePieces[ x ][ y ] = ProbabilityCalculator.findTargets( attacker, false );

				if( attackablePieces[ x ][ y ]!= null && !attackablePieces[ x ][ y ].isEmpty() ){

					int attackWins = 0;
					int numAttacks = 0;

					for( GameButton defender : attackablePieces[ x ][ y ] ){

						if( defender.getVisibility() != 3 ){

							for( int i=0; i<possibleOpponentPieces.length; i++ )

								if( movedAry[ x ][ y ] && ( i==11 || i==12 ) ){

									;

								}else{

									numAttacks ++;
									char val = Controller.getCharIndexAry()[i];
									attackWins += (Battle.getResult(attacker, val ) == attacker.getPlayerColorString()) ? 1 : 0;

								}

						} else { // visible piece

							numAttacks++;
						char val = defender.getVal();
						attackWins += ( Battle.getResult(attacker, val ) == defender.getPlayerColorString() ) ? 0 : 1;

						}
					}
					chances[ x ][ y ] = (attackWins*100/numAttacks);
				}
			}
		//		System.out.println( "Maded Chances" ); //DEBUG

		//Set up Paths
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chances[x][y] != 0 ){
					paths[ x ][ y ] = getPathToVictim( x, y );
				}
			}
		}
		//		System.out.println( "Made Paths" ); //DEBUG

		//Set up Distances
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chances[x][y] != 0 ){
					distances[ x ][ y ] = paths[ x ][ y ].size()-1;
				}
			}
		}
		//		System.out.println( "Made Distances" );

		//		printStats();

		GameButton startButton = getButton();
		GameButton endButton = getButtonVictim( startButton );

		System.out.println( "Chosing button " + startButton );
		try{ Thread.sleep( 200 ); } catch (Exception e ){}
		GameButtonLogic.clicked( startButton );

		System.out.println( "Clicking space to move to " + endButton );
		try{ Thread.sleep( 200 ); } catch (Exception e ){}
		GameButtonLogic.clicked( endButton );
	}

	private GameButton getButton(){
		GameButton choice = null;
		double weight = 0.0;

		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( chances[x][y] != 0 ){
					double currentWeight = (double)chances[ x ][ y ] / (double)distances[ x ][ y ];
					if( currentWeight > weight ){
						weight = currentWeight;
						choice = buttonMatrix[ x ][ y ];
					}
				}
			}
		}
		return choice;
	}

	private GameButton getButtonVictim( GameButton startButton ){
		return paths[ startButton.x() ][ startButton.y() ].get( 1 );

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
		if( possiblePaths.size() == 0 ){
			int x = seen.get( 0 ).x();
			int y = seen.get( 0 ).y();
			chances[ x ][ y ] = 0;
			distances[ x ][ y ] = 0;
			return null;
		}
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
					boolean winning = true;
					next = buttonMatrix[ x ][ y ];
					// if next isnt black and isnt the color of start node
					if( ( next.getPlayerColor() != Color.BLACK ) && ( next.getPlayerColor()!=list.get( 0 ).getPlayerColor()) ){
						//and not in already seen
						if( !seen.contains( next ) ){
							seen.add( next );
							if( next.getPlayerColor() == Color.BLUE ) 
								if ( ( next.getVisibility() == 3 ) && ( Battle.getResult( list.get( 0 ), next ).equals( "BLUE" ) ) ){
									winning = false;
									//									System.out.println( "Lost obvious battle " + k );													//DEBUG
								}
							//make copy of current list
							ArrayList<GameButton> newList = new ArrayList<GameButton>();
							for( int j=0; j<list.size(); j++ )
								newList.add( list.get( j ) );
							//add next to the new copy
							if( winning ){
								newList.add( next );
								if( next.getPlayerColor() == list.get( 0 ).getOpponetColor() )
									return newList;
							}
							//add new list to the new possible paths
							newPossiblePaths.add( newList );
						}
					}
				} catch( Exception e ){}
			}
		}
		//		System.out.println( newPossiblePaths );																					//DEBUG
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
				String s = "" + chances[ y ][ x ];
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

	public static void printPossibleOpponentPieces(){
		System.out.println( "*Possible Opponent Pieces*" );
		System.out.print( "[ (" + Controller.getCharIndexAry()[ 0 ] + ": "  + possibleOpponentPieces[ 0 ] + "/" + originalOpponentPieces[ 0 ] + " ) " );
		for( int index=1; index<possibleOpponentPieces.length; index++ )
			System.out.print( "(" + Controller.getCharIndexAry()[ index ] + ": "  + possibleOpponentPieces[ index ] + "/" + originalOpponentPieces[ index ] + " ) " );
		System.out.println( "]\n" );
	}

	public static void printFoundOpponentPieces(){

		System.out.println( "*Found Opponent Pieces*" );
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( buttonMatrix[ y ][ x ].getPlayerColor() == Color.RED )
					System.out.print( "0 " );
				else if( buttonMatrix[ y ][ x ].getPlayerColor() == Color.BLUE )
					if( foundOpponentPieces[ y ][ x ] == 'X' )
						System.out.print( "# ");
					else
						System.out.print( foundOpponentPieces[ y ][ x ] + " " );
				else 
					System.out.print( buttonMatrix[ y ][ x ].getVal() + " " );
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printMovedAry(){
		System.out.println( "*Moved Array*" );
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				if( buttonMatrix[ y ][ x ].getPlayerColor() == Color.RED )
					System.out.print( "0 " );
				else if( buttonMatrix[ y ][ x ].getPlayerColor() == Color.BLUE )
					if( movedAry[ y ][ x ] )
						System.out.print( "T " );
					else 
						System.out.print( "F " );
				else 
					System.out.print( buttonMatrix[ y ][ x ].getVal() + " " );
			}
			System.out.println();
		}
		System.out.println();
	}
}

