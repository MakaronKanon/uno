package CardModel;

import java.awt.Color;

import static Interfaces.UNOConstants.BLACK;
import static Interfaces.UNOConstants.WILD;

public class WildCard extends ModelUnoCard {
	
	private Color chosenColor;

	
	public WildCard(String cardValue){
		super(BLACK, WILD, cardValue);		
	}
	
	public void useWildColor(Color wildColor){
		chosenColor = wildColor;
	}
	
	public Color getWildColor(){
		return chosenColor;
	}

}
