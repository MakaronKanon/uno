package View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static GameSettings.GameConstants.UNO_COLORS;

/**
 * This class will be called when the player needs to select the color of a WildCard.
 * It will display using Java Swings showInputDialogue
 */
public class SelectWildCardView {

    /**
     * Prompts the user to select a uno-color.
     * @return the color that was selected.
     */
    public Color selectWildColor() {
        String[] colors = {"RED", "BLUE", "GREEN", "YELLOW"};

        String chosenColor = (String) JOptionPane.showInputDialog(null,
                "Choose a color", "Wild Card Color",
                JOptionPane.DEFAULT_OPTION, null, colors, null);

        return UNO_COLORS[Arrays.asList(colors).indexOf(chosenColor)];
    }
}
