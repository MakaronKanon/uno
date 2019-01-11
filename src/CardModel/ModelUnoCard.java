package CardModel;

import View.UNOCard;

import java.awt.*;

public class ModelUnoCard extends UNOCard { // todo temporarily extends UNOCard in transition

    private Color color;
    private String value; // could be x, ?, 1, 2, 13 etc.


    public ModelUnoCard(Color cardColor, int cardType, String cardValue) {
        super(cardColor, cardType, cardValue);
        this.color = cardColor;
        this.value = cardValue;
    }

    public Color getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }
}
