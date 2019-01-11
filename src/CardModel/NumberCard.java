package CardModel;
import java.awt.Color;

import View.UNOCard;

import static Interfaces.UNOConstants.NUMBERS;

@Deprecated
public class NumberCard extends UNOCard {

	
	public NumberCard(Color cardColor, String cardValue){
		super(cardColor, NUMBERS, cardValue);		
	}

}
