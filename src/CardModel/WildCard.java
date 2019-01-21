package CardModel;

import java.awt.Color;

import static Interfaces.UNOConstants.BLACK;

// This is the colorPicker card.
public class WildCard extends UnoCard {
	
	private Color chosenColor;

	
	public WildCard(String cardValue){
		super(BLACK, cardValue);
	}
	
	public void useWildColor(Color wildColor){
		chosenColor = wildColor;
	}
	
	public Color getWildColor(){
		return chosenColor;
	}

}
