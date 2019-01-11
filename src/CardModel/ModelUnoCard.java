package CardModel;

import Interfaces.UNOConstants;
import View.UNOCard;

import java.awt.*;

import static Interfaces.UNOConstants.CardType;

public class ModelUnoCard extends UNOCard { // todo temporarily extends UNOCard in transition

    private Color color;
    private String value; // could be x, ?, 1, 2, 13 etc.
    private CardType type;


    public ModelUnoCard(Color cardColor, UNOConstants.CardType cardType, String cardValue) {
        super(cardColor, cardType, cardValue);
        this.color = cardColor;
        this.value = cardValue;
        this.type = cardType;
    }

    public Color getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    public CardType getType() {
        return type;
    }
}
