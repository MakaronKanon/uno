package CardModel;
import java.awt.Color;

import View.UNOCard;

import static Interfaces.UNOConstants.ACTION;

@Deprecated
public class ActionCard extends UNOCard{

	public ActionCard(Color cardColor, String cardValue){
		super(cardColor,ACTION, cardValue);
	}
}
