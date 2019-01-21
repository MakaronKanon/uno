package CardModel.SpecialCards;

import CardModel.ActionCard;
import CardModel.UnoCard;
import Interfaces.UNOConstants;

import java.awt.*;

public class ReverseActionCard extends UnoCard implements ActionCard {
    public ReverseActionCard(Color cardColor, String cardValue) {
        super(cardColor, cardValue);
    }
}
