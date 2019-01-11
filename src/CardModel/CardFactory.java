package CardModel;

import java.awt.*;

import static Interfaces.UNOConstants.ACTION;
import static Interfaces.UNOConstants.NUMBERS;

public class CardFactory {

    public static ModelUnoCard createActionCard(Color cardColor, String cardValue) {
        return new ModelUnoCard(cardColor, ACTION, cardValue);
    }

    public static ModelUnoCard createNumberCard(Color cardColor, String cardValue) {
        return new ModelUnoCard(cardColor, NUMBERS, cardValue);
    }

}
