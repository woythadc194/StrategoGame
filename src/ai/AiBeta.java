package ai;

import java.awt.Color;
import java.util.*;

import logic.Battle;
import logic.Controller;

import ui.GameButton;

public class AiBeta {
	
	private static ArrayList<GameButton>[][] attackablePieces;
	private static GameButton[][] buttonMatrix;
	private static double [][] attackStats; 
	private static Map<Boolean, int[]> [][] HOLYFUCKMAP;
	
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
		buttonMatrix = Controller.getButtonMatrix();
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				GameButton attacker = buttonMatrix[ x ][ y ];
				if( attacker.getPlayerColor() == Color.RED)
					attackablePieces[ x ][ y ] = ProbabilityCalculator.findTargets( attacker, false );
				if( attackablePieces[ x ][ y ]!= null && !attackablePieces[ x ][ y ].isEmpty() ){
					double attackWins = 0.0;
					int numAttacks = 0;
					for( GameButton defender : attackablePieces[ x ][ y ] ){
						Map< Boolean, int[]> map = HOLYFUCKMAP[defender.x()][defender.y()];
						for( int i = 1; i< map.get(false).length; i++ ){
							for( int j=0; j< map.get(false)[i]; j++ ){
								numAttacks ++;
								char val = Controller.getCharIndexAry()[i];
								attackWins += (Battle.getResult(attacker, val ) == attacker.getPlayerColorString()) ? 1 : 0;
							}
						}
					}
						
					System.out.println(attackWins/numAttacks);
				}
			}
		}
		for( int x=0; x<10; x++ )
			System.out.println( Arrays.toString(attackablePieces[ x ] ) );
	}
	
	/*
	@SuppressWarnings("unchecked")
	public static void makeMove1(){
		attackStats = new double[ 10 ][ 10 ];
		attackablePieces = new ArrayList[ 10 ][ 10 ];
		buttonMatrix = Controller.getButtonMatrix();
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				GameButton attacker = buttonMatrix[ x ][ y ];
				if( attacker.getPlayerColor() == Color.RED)
					attackablePieces[ x ][ y ] = ProbabilityCalculator.findTargets( attacker, false );
				if( attackablePieces[ x ][ y ]!= null && !attackablePieces[ x ][ y ].isEmpty() ){
					double attackPercent = 0.0;
					for( GameButton defender : attackablePieces[ x ][ y ] )
						attackPercent += ( Battle.getResult(attacker, defender) == attacker.getPlayerColorString() )? 1 : 0;
					attackPercent /= attackablePieces[ x ][ y ].size();
					System.out.println(attackPercent);
				}
			}
		}
		for( int x=0; x<10; x++ )
			System.out.println( Arrays.toString(attackablePieces[ x ] ) );
	}
	*/
}
