package ServerController;

import GameModel.Game;
import GameModel.GameIsOverException;
import GameModel.Player;
import View.UNOCard;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UNOCardController {


    // this is temporary during refactoring
//    private MyCardListener myCardListener;
//    private Server server;

    private Game game;

    private Player player;
    private Controller controller;

    private UNOCard unoCard;

    public UNOCardController(Game game, Player player, Controller controller) {
//        this.server = server;
        this.game = game;
        this.player = player;
        this.controller = controller;
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

        controller.playCard(player, unoCard.getModelUnoCard());

//        unoCard.getModelUnoCard();
//        // Send playCard event to player/model
//        player.playCard(unoCard.getModelUnoCard());
//        try {
//            server.playThisCardIfPossible(unoCard.getModelUnoCard()); // todo catch if cant play card and display error
//        } catch (GameIsOverException e1) {
//            e1.printStackTrace();
//
//        }

    }

}
