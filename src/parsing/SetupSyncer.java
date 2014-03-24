package parsing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SetupSyncer {

	public static String getSyncSetups() throws FileNotFoundException{
		File file = new File(System.getProperty("user.dir") + "/src/parsing/Setups.txt");
		Scanner sc = new Scanner( file );
		String newFileString = "";
		Scanner blockSc = new Scanner ( "" );

		String block = "";
		while( sc.hasNextLine() ){
			String line = sc.nextLine();
			if( !line.equals( "" ) ){
				block += line + "\n";
			}else{
				blockSc = new Scanner( block );
				boolean is10High = test10High( blockSc );
				blockSc = new Scanner( block );
				if( is10High ) {
					while( blockSc.hasNextLine() ){
						String blockLine = blockSc.nextLine();
						Scanner lineSc = new Scanner( blockLine );
						while( lineSc.hasNext() ){
							newFileString += getNewChar( lineSc.next().charAt( 0 ) ) + " ";
						}
						newFileString += "\n";
						lineSc.close();
					}
				} else {
					while( blockSc.hasNextLine() ){
						String blockLine = blockSc.nextLine();
						newFileString += blockLine + "\n";
					}
					newFileString += "\n";
				}
				newFileString += "\n";
				block = "";
			}
		}
		
		
		sc.close();
		blockSc.close();

		return newFileString;
	}
	
	public static boolean test10High( Scanner blockSc ){
		while( blockSc.hasNext() ){
			String next = blockSc.next();
			if( next.equals( "10" ) )
				return true;
		}
		return false;
	}
	
	public static char getNewChar( char oldChar ){
		if( oldChar == 'B' )
			return 'B';
		if( oldChar == 'F' )
			return 'F';
		if( oldChar == 'S' )
			return 'S';
		if( oldChar == '1' )
			return '1';
		if( oldChar == '9' )
			return '2';
		if( oldChar == '8' )
			return '3';
		if( oldChar == '7' )
			return '4';
		if( oldChar == '6' )
			return '5';
		if( oldChar == '5' )
			return '6';
		if( oldChar == '4' )
			return '7';
		if( oldChar == '3' )
			return '8';
		if( oldChar == '2' )
			return '9';
		if( oldChar == 'b' )
			return 'B';
		if( oldChar == 'f' )
			return 'F';
		if( oldChar == 's' )
			return 'S';
		return 'X';
	}
}
