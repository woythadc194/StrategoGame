package ui;

import java.util.ArrayList;

import javax.swing.JFrame;


public class Controller {

	public static  GameBoard gb;
	public static SelectionPanel sp;
	public static JFrame frame;
	public static ArrayList<ArrayList<GameButton>> buttonMatrix;
	public static int [] piecesAry;
	private static int selectedPieceOpt;
	public static PieceOptButton [] optButtonAry;

	public GameButton selectedButton;
	public boolean selectionMade;
	public boolean redTurn;
	
	
	public Controller(){
		this.redTurn = false;
		this.setSelectedPieceOpt( 13 );
	}
	
	public void makePiecesAry(){
		piecesAry = new int[13];
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
		redTurn = !redTurn;
	}
	
	public void setOptButtonAry( PieceOptButton[] optButtonAry ){
		Controller.optButtonAry = optButtonAry;
	}
	
	public void setSelectedPieceOpt( int opt){
		Controller.selectedPieceOpt = opt;
	}
	
	public void resetSelectedPieceOpt(){
		Controller.selectedPieceOpt = 13;
	}
	
	public int getSelectedPieceOpt(){
		return Controller.selectedPieceOpt;
	}
	
	
	public boolean isReady(){
		for( int y=0; y<10; y++ )
			for( int x=0; x<10; x++ )
				if( buttonMatrix.get( x ).get( y ).isReady() == false )
					return false;
		return true;
	}
	
	public void highlightMoveable(){
		if( selectedButton.getPiece().getVal() == '9' ){
			for( int x=selectedButton.getXLocal()+1; x<10; x++ ){
				GameButton b = buttonMatrix.get( x ).get( selectedButton.getYLocal() );
				if( b.getPiece().getPlayer().equals( selectedButton.getPiece().getPlayer() ) )
					break;
				else{
					if( b.getPiece().getVal()!='X' )
						b.setMovable( true );
					if( b.getPiece().getVal()!= '~' )
						break;
				}
			}
			for( int x=selectedButton.getXLocal()-1; x>=0; x-- ){
				GameButton b = buttonMatrix.get( x ).get( selectedButton.getYLocal() );
				if( b.getPiece().getPlayer().equals( selectedButton.getPiece().getPlayer() ) )
					break;
				else{
					if( b.getPiece().getVal()!='X' )
						b.setMovable( true );
					if( b.getPiece().getVal()!= '~' )
						break;
				}
			}
			for( int y=selectedButton.getYLocal()+1; y<10; y++ ){
				GameButton b = buttonMatrix.get( selectedButton.getXLocal() ).get( y );
				if( b.getPiece().getPlayer().equals( selectedButton.getPiece().getPlayer() ) )
					break;
				else{
					if( b.getPiece().getVal()!='X' )
						b.setMovable( true );
					if( b.getPiece().getVal()!= '~' )
						break;
				}
			}
			for( int y=selectedButton.getYLocal()-1; y>=0; y-- ){
				GameButton b = buttonMatrix.get( selectedButton.getXLocal() ).get( y );
				if( b.getPiece().getPlayer().equals( selectedButton.getPiece().getPlayer() ) )
					break;
				else{
					if( b.getPiece().getVal()!='X' )
						b.setMovable( true );
					if( b.getPiece().getVal()!= '~' )
						break;
				}
			}
		} else {

			for( int y=0; y<10; y++ ){
				for( int x=0; x<10; x++ ){
					GameButton b = buttonMatrix.get( x ).get( y );
					if(  !b.getPiece().getPlayer().equals( selectedButton.getPiece().getPlayer() ) && ( b.getPiece().getVal()!='X' ) ){				
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
	
	public void setSelectedButton( GameButton b ){
		clearSelectedButton();
		this.selectionMade = true;
		this.selectedButton = b;
		selectedButton.setMovable( true );
		highlightMoveable();
	}
	
	public void clearSelectedButton(){
		this.selectionMade = false;
		this.selectedButton = null;
		for( ArrayList<GameButton> list : buttonMatrix )
			for( GameButton b : list )
				b.setMovable( false );
	}
	
	public GameButton getSelectedButton(){
		return this.selectedButton;
	}
	
	public GameBoard getGameBoard(){
		return Controller.gb;
	}
	
	public SelectionPanel getSelectionPanel(){
		return Controller.sp;
	}
	
	public JFrame getFrame(){
		return Controller.frame;
	}
	
	public ArrayList<ArrayList<GameButton>> getButtonMatrix(){
		return buttonMatrix;
	}
	
	public void addButtonMatrix( ArrayList<ArrayList<GameButton>> buttonMatrix){
		Controller.buttonMatrix = buttonMatrix;
	}
	
	public void addFrame( JFrame frame ){
		Controller.frame = frame;
	}
	
	public void addGameBoard( GameBoard gb ){
		Controller.gb = gb;
	}
	
	public void addSelectionPanel( SelectionPanel sp ){
		Controller.sp = sp;
	}
}