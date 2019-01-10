package CardModel;

import View.UNOCard;

import java.awt.*;

import static Interfaces.UNOConstants.ACTION;
import static Interfaces.UNOConstants.NUMBERS;

public class CardFactory {

    public static UNOCard createActionCard(Color cardColor, String cardValue) {
        return new UNOCard(cardColor, ACTION, cardValue);
    }

    public static UNOCard createNumberCard(Color cardColor, String cardValue) {
        return new UNOCard(cardColor, NUMBERS, cardValue);
    }

}
