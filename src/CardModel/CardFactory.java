package CardModel;

import Interfaces.UNOConstants;

import java.awt.*;


public class CardFactory {

    public static UnoCard createActionCard(Color cardColor, String cardValue) {
        return new UnoCard(cardColor, UNOConstants.CardType.ACTION, cardValue);
    }

    public static UnoCard createNumberCard(Color cardColor, String cardValue) {
        return new UnoCard(cardColor, UNOConstants.CardType.NUMBER, cardValue);
    }

}
