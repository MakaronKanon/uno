package CardModel;
import java.awt.Color;

import View.UNOCard;

import static Interfaces.UNOConstants.NUMBERS;

public class NumberCard extends UNOCard {

	public NumberCard(){
	}
	
	public NumberCard(Color cardColor, String cardValue){
		super(cardColor, NUMBERS, cardValue);		
	}

}
