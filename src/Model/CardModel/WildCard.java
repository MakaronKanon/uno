package Model.CardModel;

import GameSettings.UnoColors;

import java.awt.Color;


// This is the colorPicker card.
public class WildCard extends UnoCard {
	
	private Color chosenColor;

	
	public WildCard(String cardValue){
		super(UnoColors.BLACK, cardValue);
	}
	
	public void useWildColor(Color wildColor){
		chosenColor = wildColor;
	}
	
	public Color getWildColor(){
		return chosenColor;
	}

}
