package CardModel;

import CardModel.SpecialCards.Draw2xCard;
import CardModel.SpecialCards.Draw4xCard;
import CardModel.SpecialCards.ReverseActionCard;
import CardModel.SpecialCards.SkipActionCard;

import java.awt.*;


public class CardFactory {

    public static UnoCard createDraw2xCard(Color cardColor) {
        return new Draw2xCard(cardColor);
    }

    public static UnoCard createDraw4xCard() {
        return new Draw4xCard();
    }

    public static UnoCard createReverseActionCard(Color cardColor) {
        char symbol = 8634;
        return new ReverseActionCard(cardColor,
                ((Character)(symbol)).toString());
    }

    public static UnoCard createSkipActionCard(Color cardColor) {
        char symbol = (char) Integer.parseInt("2718",16);
        return new SkipActionCard(cardColor,
                ((Character)(symbol)).toString()); // to get correct symbol
    }

    public static UnoCard createWildCard() {
        return new WildCard("W");
    }

    public static UnoCard createNumberCard(Color cardColor, String cardValue) {
        return new UnoCard(cardColor, cardValue);
    }

}
