package ai;

import java.awt.Color;
import java.util.*;

import logic.Controller;

import ui.GameButton;

public class AiBeta {
	
	private static ArrayList<GameButton>[][] attackablePieces;
	private static GameButton[][] buttonMatrix;
	private static double [][] attacks; 
	private static Object [][] HOLYFUCKMAP = new Object[ 10 ][ 10 ];
	
	public AiBeta(){
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				int [] tempAry = new int[ ]{ 1, 1, 2, 3, 4, 4, 4, 5, 8, 6, 1, 1};
				Map<Boolean, int[]> temp = new HashMap< Boolean, int[] >();
				temp.put( false, tempAry );
				HOLYFUCKMAP[ x ][ y ] = temp;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void makeMove1(){
		attacks = new double[ 10 ][ 10 ];
		attackablePieces = new ArrayList[ 10 ][ 10 ];
		buttonMatrix = Controller.getButtonMatrix();
		for( int x=0; x<10; x++ ){
			for( int y=0; y<10; y++ ){
				GameButton button = buttonMatrix[ x ][ y ];
				if( button.getPlayerColor() == Color.RED)
					attackablePieces[ x ][ y ] = ProbabilityCalculator.findTargets( button, false );
				if( attackablePieces[ x ][ y ]!= null && !attackablePieces[ x ][ y ].isEmpty() ){
					double battleStats = 0.0;
					battleStats += 
				}
			}
		}
		
		
		/*
		for( int x=0; x<10; x++ )
			System.out.println( Arrays.toString(attackablePieces[ x ] ) );
		*/
	}
}
