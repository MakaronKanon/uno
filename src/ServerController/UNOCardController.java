package ServerController;

import GameModel.Game;
import GameModel.Player;
import View.CardView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UNOCardController {


    private Player player;
    private Controller controller;

    private CardView cardView;

    public UNOCardController(Player player, Controller controller) {
        this.player = player;
        this.controller = controller;
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public void cardHovered(MouseEvent e) {
        Point p = cardView.getLocation();
        p.y -=20;
        cardView.setLocation(p);
    }

    public void cardStopHovered(MouseEvent e) {
        Point p = cardView.getLocation();
        p.y +=20;
        cardView.setLocation(p);
    }

    public void cardClicked(MouseEvent e) {

        controller.playCard(player, cardView.getModelUnoCard());
    }

}
