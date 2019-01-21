package CardModel;

import Interfaces.UNOConstants;

import java.awt.*;


public class UnoCard {

    private Color color;
    private String value; // could be x, ?, 1, 2, 13 etc.

    public UnoCard(Color cardColor, String cardValue) {
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
