package CardModel;
import java.awt.Color;

import View.UNOCard;

import static Interfaces.UNOConstants.ACTION;

public class ActionCard extends UNOCard{

	//Constructor
	public ActionCard(){
	}
	
	public ActionCard(Color cardColor, String cardValue){
		super(cardColor,ACTION, cardValue);		
	}	
}
