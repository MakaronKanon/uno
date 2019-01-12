package ServerController;

import GameModel.Player;
import View.UNOCard;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UNOCardController {


    // this is temporary during refactoring
//    private MyCardListener myCardListener;
    private Server server;

    private Player player;

    private UNOCard unoCard;

    public UNOCardController(Server server, Player player) {
        this.server = server;
        this.player = player;
    }

    public void setUnoCard(UNOCard unoCard) {
        this.unoCard = unoCard;
    }

    public void cardHovered(MouseEvent e) {
        Point p = unoCard.getLocation();
        p.y -=20;
        unoCard.setLocation(p);
    }

    public void cardStopHovered(MouseEvent e) {
        Point p = unoCard.getLocation();
        p.y +=20;
        unoCard.setLocation(p);
    }

    public void cardClicked(MouseEvent e) {

//        unoCard.getModelUnoCard();
        // Send playCard event to player/model
        player.playCard(unoCard.getModelUnoCard());
        server.playThisCardIfPossible(unoCard.getModelUnoCard()); // todo catch if cant play card and display error

    }

}
