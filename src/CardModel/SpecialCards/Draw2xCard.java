package CardModel.SpecialCards;

import CardModel.ActionCard;
import CardModel.UnoCard;
import Interfaces.UNOConstants;

import java.awt.*;

public class Draw2xCard extends UnoCard implements ActionCard {

    //todo: constructor is supposed to get rid of
    public Draw2xCard(Color cardColor, String cardValue) {
        super(cardColor, cardValue);
    }
}
