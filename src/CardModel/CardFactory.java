package CardModel;

import CardModel.SpecialCards.Draw2xCard;
import CardModel.SpecialCards.Draw4xCard;
import CardModel.SpecialCards.ReverseActionCard;
import CardModel.SpecialCards.SkipActionCard;
import Interfaces.UNOConstants;

import java.awt.*;


public class CardFactory {

    public static UnoCard createDraw2xCard(Color cardColor) {
        return new Draw2xCard(cardColor, UNOConstants.CardType.ACTION, UNOConstants.DRAW2PLUS);
    }

    public static UnoCard createDraw4xCard() {
        return new Draw4xCard(UNOConstants.W_DRAW4PLUS);
    }

    public static UnoCard createReverseActionCard(Color cardColor) {
        return new ReverseActionCard(cardColor, UNOConstants.CardType.ACTION, UNOConstants.REVERSE);
    }

    public static UnoCard createSkipActionCard(Color cardColor) {
        return new SkipActionCard(cardColor, UNOConstants.CardType.ACTION, UNOConstants.SKIP);
    }

    public static UnoCard createWildCard() {
        return new WildCard(UNOConstants.W_COLORPICKER);
    }

//    public static UnoCard createActionCard(Color cardColor, String cardValue) {
//        return new UnoCard(cardColor, UNOConstants.CardType.ACTION, cardValue);
//    }

    public static UnoCard createNumberCard(Color cardColor, String cardValue) {
        return new UnoCard(cardColor, UNOConstants.CardType.NUMBER, cardValue);
    }

}
