package logic;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ui.GameBoard;
import ui.GameButton;
import ui.PieceOptButton;
import ui.SelectionPanel;


public class Controller {

	private static  GameBoard gb;
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
	
	
	public Controller(){
		Controller.setRedTurn(false);
		this.setSelectedPieceOpt( 0 );
	}
	
	public static char [] getCharIndexAry(){
		return Controller.charIndexAry;
	}
	
	public static PieceOptButton[] getOptButtonAry(){
		return Controller.optButtonAry;
	}
	
	public void makeNumPiecesAry(){
		piecesAry = new int[13];
		piecesAry[0] = 1;
		piecesAry[1] = 1;
		piecesAry[2] = 1;
		piecesAry[3] = 2;
		piecesAry[4] = 3;
		piecesAry[5] = 4;
		piecesAry[6] = 4;
		piecesAry[7] = 4;
		piecesAry[8] = 5;
		piecesAry[9] = 8;
		piecesAry[10] = 6;
		piecesAry[11] = 1;
		piecesAry[12] = 1;
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
	
	public void setSelectedPieceOpt( int opt){
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
				if( b.getPlayer().equals( selectedButton.getPlayer() ) )
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
				if( b.getPlayer().equals( selectedButton.getPlayer() ) )
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
				if( b.getPlayer().equals( selectedButton.getPlayer() ) )
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
				if( b.getPlayer().equals( selectedButton.getPlayer() ) )
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
					if(  !b.getPlayer().equals( selectedButton.getPlayer() ) && ( b.getVal()!='X' ) ){				
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
	
	public void setGameButtonMatrix( ArrayList<ArrayList<GameButton>> buttonMatrix){
		Controller.buttonMatrix = buttonMatrix;
	}
	
	public void setFrame( JFrame frame ){
		Controller.frame = frame;
	}
	
	public void setGameBoard( GameBoard gb ){
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
}