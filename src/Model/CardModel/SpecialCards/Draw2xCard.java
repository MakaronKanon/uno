package Model.CardModel.SpecialCards;

import Model.CardModel.ActionCard;
import Model.CardModel.UnoCard;

import java.awt.*;

public class Draw2xCard extends UnoCard implements ActionCard {

    //todo: constructor is supposed to get rid of
    public Draw2xCard(Color cardColor) {
        super(cardColor, "2+");
    }
}
