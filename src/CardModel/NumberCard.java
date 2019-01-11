package CardModel;
import java.awt.Color;

import Interfaces.UNOConstants;
import View.UNOCard;

import static javax.swing.text.html.parser.DTDConstants.NUMBERS;

@Deprecated
public class NumberCard extends UNOCard {

	
	public NumberCard(Color cardColor, String cardValue){
		super(cardColor, UNOConstants.CardType.NUMBER, cardValue);
	}

}
