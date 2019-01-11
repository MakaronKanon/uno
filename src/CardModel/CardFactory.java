package CardModel;

import Interfaces.UNOConstants;

import java.awt.*;


public class CardFactory {

    public static ModelUnoCard createActionCard(Color cardColor, String cardValue) {
        return new ModelUnoCard(cardColor, UNOConstants.CardType.ACTION, cardValue);
    }

    public static ModelUnoCard createNumberCard(Color cardColor, String cardValue) {
        return new ModelUnoCard(cardColor, UNOConstants.CardType.NUMBER, cardValue);
    }

}
