package CardModel.SpecialCards;

import CardModel.ActionCard;
import CardModel.UnoCard;
import Interfaces.UNOConstants;

import java.awt.*;

public class SkipActionCard extends UnoCard implements ActionCard {
    public SkipActionCard(Color cardColor, UNOConstants.CardType cardType, String cardValue) {
        super(cardColor, cardType, cardValue);
    }
}
