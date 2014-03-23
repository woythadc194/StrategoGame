/**
  * @author Dylan C. Woythal
  */

package logic;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ui.GameBoard;
import ui.GameButton;
import ui.PieceOptButton;
import ui.SelectionPanel;
import ui.SetupPane;


public class Controller {

	private static GameBoard gb;
	private static JFrame frame;
	private static ArrayList<ArrayList<GameButton>> buttonMatrix;
	private static int [] piecesAry;
	private static int selectedPieceOpt;
	private static PieceOptButton [] optButtonAry;
	private static char [] charIndexAry;
	private static SelectionPanel sp;
	private static GameButton selectedButton;
	private static boolean selectionMade;
	private static boolean redTurn;
	private static boolean ifNumPlayersSelected;
	private static SetupLogic SULogic;
	
	
	public Controller( JFrame frame, GameBoard gb ){
		
		Controller.setRedTurn(false);
		Controller.setSelectedPieceOpt( 0 );
		Controller.setFrame( frame );
		Controller.setGameBoard( gb );
		Controller.setGameButtonMatrix( ControllerMaker.getGameButtonMatrix( this ) );
		Controller.setPiecesAry(ControllerMaker.makeNumPiecesAry() );
		Controller.setIfNumPlayersSelected( false );
		Controller.setCharIndexAry( ControllerMaker.makeCharIndexAry() );
		
		constructSetupPane();
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
	
	public void switchTurns(){
		setRedTurn(!getRedTurn());
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
	
	public void testReady(){
		for( int y=0; y<10; y++ ){
			if( y == 4 )
				y = 6;
			for( int x=0; x<10; x++ ){
				char c = buttonMatrix.get( x ).get( y ).getVal();
				if( ( c=='~' ) || ( c=='X') ){
					
					return;
				}
			}
		}
		askIfReady();
	}
	
	private void askIfReady(){
		int reply = JOptionPane.showConfirmDialog(null, "Pieces all set?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
        	setAllReady();
        }
    }
	
	private void setAllReady(){
        for( int y=0; y<10; y++ )
			for( int x=0; x<10; x++ )
				buttonMatrix.get( x ).get( y ).setReady( true );
        Controller.sp.dispose();
	}
	
	public int getSelectedPieceOpt(){
		return Controller.selectedPieceOpt;
	}
	
	
	public boolean isReady(){
		for( int y=0; y<10; y++ )
			for( int x=0; x<10; x++ )
				if( buttonMatrix.get( x ).get( y ).getReady() == false )
					return false;
		return true;
	}
	
	public static void highlightMoveable(){
		if( selectedButton.getVal() == '9' ){
			for( int x=selectedButton.getXLocal()+1; x<10; x++ ){
				GameButton b = buttonMatrix.get( x ).get( selectedButton.getYLocal() );
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
			for( int x=selectedButton.getXLocal()-1; x>=0; x-- ){
				GameButton b = buttonMatrix.get( x ).get( selectedButton.getYLocal() );
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
			for( int y=selectedButton.getYLocal()+1; y<10; y++ ){
				GameButton b = buttonMatrix.get( selectedButton.getXLocal() ).get( y );
				if( b.getPlayerColor().equals( selectedButton.getPlayerColor() ) )
					break;
				else{
					if( b.getVal()!='X' )
						b.setMovable( true );
					if( b.getVal()!= '~' )
						break;
				}
			}
			for( int y=selectedButton.getYLocal()-1; y>=0; y-- ){
				GameButton b = buttonMatrix.get( selectedButton.getXLocal() ).get( y );
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
					GameButton b = buttonMatrix.get( x ).get( y );
					if(  !b.getPlayerColor().equals( selectedButton.getPlayerColor() ) && ( b.getVal()!='X' ) ){				
						if( ( b.getXLocal()==selectedButton.getXLocal()-1 ) && ( b.getYLocal()==selectedButton.getYLocal() ) )
							b.setMovable( true );
						if( ( b.getXLocal()==selectedButton.getXLocal()+1 ) && ( b.getYLocal()==selectedButton.getYLocal() ) )
							b.setMovable( true );
						if( ( b.getXLocal()==selectedButton.getXLocal() ) && ( b.getYLocal()==selectedButton.getYLocal()-1 ) )
							b.setMovable( true );
						if( ( b.getXLocal()==selectedButton.getXLocal() ) && ( b.getYLocal()==selectedButton.getYLocal()+1 ) )
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
		for( ArrayList<GameButton> list : buttonMatrix )
			for( GameButton b : list )
				b.setMovable( false );
	}
	
	public static GameButton getSelectedButton(){
		return Controller.selectedButton;
	}
	
	public GameBoard getGameBoard(){
		return Controller.gb;
	}
	
	public JFrame getFrame(){
		return Controller.frame;
	}
	
	public ArrayList<ArrayList<GameButton>> getButtonMatrix(){
		return buttonMatrix;
	}
	
	public static void setGameButtonMatrix( ArrayList<ArrayList<GameButton>> buttonMatrix){
		Controller.buttonMatrix = buttonMatrix;
	}
	
	public static void setFrame( JFrame frame ){
		Controller.frame = frame;
	}
	
	public static void setGameBoard( GameBoard gb ){
		Controller.gb = gb;
	}
	
	public void setSelectionPanel( SelectionPanel sp ){
		Controller.sp = sp;
	}

	public boolean getSelectionMade() {
		return selectionMade;
	}

	public static void setSelectionMade(boolean selectionMade) {
		Controller.selectionMade = selectionMade;
	}

	public static boolean getRedTurn() {
		return redTurn;
	}

	public static void setRedTurn(boolean redTurn) {
		Controller.redTurn = redTurn;
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
}