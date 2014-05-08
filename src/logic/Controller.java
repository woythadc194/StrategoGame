/**
  * @author Dylan C. Woythal
  */

package logic;

import java.awt.Color;

import javax.swing.JFrame;

import ai.AiBeta;
import ai.ProbabilityCalculator;

import ui.RandomPane;
import ui.Board;
import ui.GameButton;
import ui.PieceOptButton;
import ui.SelectionPanel;
import ui.SetupPane;


public class Controller {

	private static Board gb;
	private static JFrame frame;
	private static int [] piecesAry;
	private static int selectedPieceOpt;
	private static int numPlayers;
	private static char [] charIndexAry;
	private static boolean selectionMade;
	private static boolean ifNumPlayersSelected;
	private static Color playerTurn;
	private static AiBeta AI;
	private static SetupLogic SULogic;
	private static GameButton selectedButton;
	private static PieceOptButton [] optButtonAry;
	private static GameButton[][] buttonMatrix;
	private static int numTurns = 0;
	
	@SuppressWarnings("unused")
	private static SelectionPanel sp;
	
	
	public Controller( JFrame frame, Board gb ){
		
		Controller.setTurn( Color.BLUE );
		Controller.setSelectedPieceOpt( 0 );
		Controller.setFrame( frame );
		Controller.setGameBoard( gb );
		Controller.setGameButtonMatrix( ControllerMaker.getGameButtonMatrix( this ) );
		Controller.setPiecesAry(ControllerMaker.makeNumPiecesAry() );
		Controller.setIfNumPlayersSelected( false );
		Controller.setCharIndexAry( ControllerMaker.makeCharIndexAry() );
		Controller.AI = new AiBeta();
		constructSetupPane();
	}
	
	public void constructRandomPane(){
		new RandomPane( this );
	}
	
	public static boolean playerNotReady( int yMin, int yMax ){
		return true;
	}
	
	public void constructSetupPane(){
		new SetupPane( this );
	}

	public static void setPiecesAry( int [] piecesAry ){
		Controller.piecesAry = piecesAry;
	}
	
	public static void setCharIndexAry( char [] charIndexAry ){
		Controller.charIndexAry = charIndexAry;
	}
	
	public static char [] getCharIndexAry(){
		return Controller.charIndexAry;
	}
	
	public static PieceOptButton[] getOptButtonAry(){
		return Controller.optButtonAry;
	}
	
	public static void switchTurns(){
		numTurns++;
		System.out.println( "TURN NUMBER: " + numTurns );
		setTurn( getPlayerTurn() == Color.RED ? Color.BLUE : Color.RED );
		ProbabilityCalculator pc = new ProbabilityCalculator();
		ProbabilityCalculator.clearTargets();
		pc.getAllBattleStats( getPlayerTurn() );
		
		
		if( getPlayerTurn() == Color.RED )
			AI.makeMove1();
		//TODO
		
	}
	
	public static int[] getPiecesAry(){
		return piecesAry;
	}
	
	public void setOptButtonAry( PieceOptButton[] optButtonAry ){
		Controller.optButtonAry = optButtonAry;
	}
	
	public static void setSelectedPieceOpt( int opt){
		Controller.selectedPieceOpt = opt;
	}
	
	public boolean testPlayrReady(){
		for( int y=SetupLogic.getYMin(); y<SetupLogic.getYMax(); y++ ){
			for( int x=0; x<10; x++ ){
				char c = buttonMatrix[ x ][ y ].getVal();
				if( ( c=='~' ) || ( c=='X') ){
					return false;
				}
			}
		}
		return true;
	}
	
	public void setAllReady(){
        for( int y=SetupLogic.getYMin(); y<SetupLogic.getYMax(); y++ )
			for( int x=0; x<10; x++ )
				buttonMatrix[ x ][ y ].setReady( true );

        if( SetupLogic.getCurrentPlayer() == Color.RED )
        	SetupLogic.setHumanPlayers( 1 );
	}
	
	public static boolean isReady(){
		for( int y=0; y<10; y++ )
			for( int x=0; x<10; x++ )
				if( buttonMatrix[ x ][ y ].getReady() == false )
					return false;
		return true;
	}

	public static int getCharIndex( char c ){
		for( int i=0; i<charIndexAry.length; i++ ){
			if( charIndexAry[ i ] == c )
				return i;
		}
		return 14; //ERROR
	}

	public static int getSelectedPieceOpt(){
		return Controller.selectedPieceOpt;
	}
	
	public static void highlightMoveable(){
		if( selectedButton.getVal() == '9' ){
			for( int x=selectedButton.x()+1; x<10; x++ ){
				GameButton b = buttonMatrix[ x ][ selectedButton.y() ];
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
			for( int x=selectedButton.x()-1; x>=0; x-- ){
				GameButton b = buttonMatrix[ x ][ selectedButton.y() ];
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
			for( int y=selectedButton.y()+1; y<10; y++ ){
				GameButton b = buttonMatrix[ selectedButton.x() ][ y ];
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
			for( int y=selectedButton.y()-1; y>=0; y-- ){
				GameButton b = buttonMatrix[ selectedButton.x() ][ y ];
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
		} else {

			for( int y=0; y<10; y++ ){
				for( int x=0; x<10; x++ ){
					GameButton b = buttonMatrix[ x ][ y ];
					if(  !b.getPlayerColor().equals( selectedButton.getPlayerColor() ) && ( b.getVal()!='X' ) ){				
						if( ( b.x()==selectedButton.x()-1 ) && ( b.y()==selectedButton.y() ) )
							b.setMovable( true );
						if( ( b.x()==selectedButton.x()+1 ) && ( b.y()==selectedButton.y() ) )
							b.setMovable( true );
						if( ( b.x()==selectedButton.x() ) && ( b.y()==selectedButton.y()-1 ) )
							b.setMovable( true );
						if( ( b.x()==selectedButton.x() ) && ( b.y()==selectedButton.y()+1 ) )
							b.setMovable( true );
					}
				}
			}
		}
	}
	
	public static void setSelectedButton( GameButton b ){
		clearSelectedButton();
		setSelectionMade(true);
		Controller.selectedButton = b;
		selectedButton.setMovable( true );
		highlightMoveable();
	}
	
	public static void clearSelectedButton(){
		setSelectionMade(false);
		Controller.selectedButton = null;
		for( int x=0; x<10; x++ )
			for( int y=0; y<10; y++ )
				buttonMatrix[ x ][ y ].setMovable( false );
	}
	
	public static GameButton getSelectedButton(){
		return Controller.selectedButton;
	}
	
	public Board getGameBoard(){
		return Controller.gb;
	}
	
	public JFrame getFrame(){
		return Controller.frame;
	}
	
	public static GameButton[][] getButtonMatrix(){
		return buttonMatrix;
	}
	
	public static void setGameButtonMatrix( GameButton[][] buttonMatrix){
		Controller.buttonMatrix = buttonMatrix;
	}
	
	public static void setFrame( JFrame frame ){
		Controller.frame = frame;
	}
	
	public static void setGameBoard( Board gb ){
		Controller.gb = gb;
	}
	
	public void setSelectionPanel( SelectionPanel sp ){
		Controller.sp = sp;
	}

	public static boolean getSelectionMade() {
		return selectionMade;
	}

	public static void setSelectionMade(boolean selectionMade) {
		Controller.selectionMade = selectionMade;
	}

	public static Color getPlayerTurn() {
		return playerTurn;
	}

	public static void setTurn( Color playerTurn ) {
		Controller.playerTurn = playerTurn;
	}

	public void setSULogic(SetupLogic SULogic) {
		Controller.SULogic = SULogic;
	}

	public static SetupLogic getSULogic() {
		return SULogic;
	}

	public static void setIfNumPlayersSelected( boolean b ){
		ifNumPlayersSelected = b;
	}
	
	public static boolean getIfNumPlayersSelected() {
		return ifNumPlayersSelected;
	}

	public static void setNumPlayers(int numPlayers) {
		Controller.numPlayers = numPlayers;
	}
	
	public static int getNumPlayers() {
		return Controller.numPlayers;
	}
}