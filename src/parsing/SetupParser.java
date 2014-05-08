package parsing;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import logic.GameButtonLogic;

import ui.GameButton;

public class SetupParser {
	
	public static ArrayList<ArrayList<GameButton>> getAIList( ) throws FileNotFoundException{
		
		String setupList = SetupSyncer.getSyncSetups();
		Scanner sc = new Scanner( setupList );
		Random rand = new Random();
		int numSetups = getSetups( sc );
		String [] setups = new String[numSetups];
		sc = new Scanner( setupList );
		for( int index = 0; index<numSetups; index ++ ){
			String setup = sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine();
			sc.nextLine();
			setups[ index ] = setup;
		}
		sc.close();
		return getAITarget( setups[rand.nextInt( numSetups )] );
	}

	public static ArrayList<ArrayList<GameButton>> getAITarget( String setup ) throws FileNotFoundException{
		
		ArrayList<ArrayList<GameButton>> AIList = new ArrayList<ArrayList<GameButton>>();
		Scanner sc = new Scanner( setup );
		for( int y=0; y<4; y++ ){
			ArrayList<GameButton> gbList = new ArrayList<GameButton> ();
			for( int x=0; x<10; x++ ){
				GameButton button = new GameButton( 40, x, y );		
				GameButtonLogic.alterButton(button, 1, sc.next().charAt(0), Color.RED );										//VISIBILITY
				gbList.add( button );
			}
			AIList.add( gbList );
		}
		sc.close();
		return AIList;
	}
	
	public static ArrayList<ArrayList<GameButton>> getHumanList( ) throws FileNotFoundException{
		
		String setupList = SetupSyncer.getSyncSetups();
		Scanner sc = new Scanner( setupList );
		Random rand = new Random();
		int numSetups = getSetups( sc );
		String [] setups = new String[numSetups];
		sc = new Scanner( setupList );
		for( int index = 0; index<numSetups; index ++ ){
			String setup = sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine();
			sc.nextLine();
			setups[ index ] = setup;
		}
		sc.close();
		return getHumanTarget( setups[rand.nextInt( numSetups )] );
	}
	
	public static ArrayList<ArrayList<GameButton>> getHumanTarget( String setup ) throws FileNotFoundException{
		
		ArrayList<ArrayList<GameButton>> AIList = new ArrayList<ArrayList<GameButton>>();
		Scanner sc = new Scanner( setup );
		for( int y=6; y<10; y++ ){
			ArrayList<GameButton> gbList = new ArrayList<GameButton> ();
			for( int x=0; x<10; x++ ){
				GameButton button = new GameButton( 40, x, y );		
				GameButtonLogic.alterButton(button, 2, sc.next().charAt(0), Color.BLUE );										//VISIBILITY
				gbList.add( button );
			}
			AIList.add( gbList );
		}
		sc.close();
		return AIList;
	}
	
	private static int getSetups( Scanner sc ){
		int numSetups = 0;
		while( sc.hasNextLine() ){
			String line = sc.nextLine();
			if( !line.equals("") )
				numSetups ++;
		}
		return numSetups/4;
	}
}
