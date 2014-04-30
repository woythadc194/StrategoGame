package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ui.GameButton;
import logic.Controller;

public class AiBeta {
	
	private static ArrayList<ArrayList<GameButton>> buttonMatrix;
	private static ArrayList<ArrayList<Map<Boolean, ArrayList<Integer>>>> HOLYFUCKMAP = new ArrayList<ArrayList<Map<Boolean, ArrayList<Integer>>>>();  
	
	public AiBeta(){
		
	}
	public static void makeMove1(){
		buttonMatrix = Controller.getButtonMatrix();
		Map<GameButton, Double> percentMap = new HashMap<GameButton, Double>();
		for( ArrayList<GameButton> list : buttonMatrix ){
			for( GameButton button : list ){
				
			}
		}
	}
}
