package Controller;

import Model.CardModel.UnoCard;
import Model.GameModel.Player;
import View.CardView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Every UNOCardController needs one UNOCard vice, versa.
 */
public class UNOCardController {


    private Player player;
    private Controller controller;

    private CardView cardView;

    private boolean cardEnabled = true;


    public UNOCardController(Player player, Controller controller) {
        this.player = player;
        this.controller = controller;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public void cardHovered(MouseEvent e) {
        if (!cardEnabled)
            return;
        Point p = cardView.getLocation();
        p.y -=20;
        cardView.setLocation(p);
    }

    public void cardStopHovered(MouseEvent e) {
        if (!cardEnabled)
            return;

        Point p = cardView.getLocation();
        p.y +=20;
        cardView.setLocation(p);
    }

    public void cardClicked(MouseEvent e, UnoCard cardClicked) {
        if (!cardEnabled)
            return;

        controller.playCard(player, cardClicked);
    }

    /**
     * To make card not respond to mouse events, such that when player is played
     * @param cardEnabled, whether if its active or not
     */
    public void setCardActive(boolean cardEnabled) {
        this.cardEnabled = cardEnabled;
    }
}
