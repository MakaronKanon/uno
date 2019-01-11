package ServerController;

import View.UNOCard;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UNOCardController {


    // this is temporary during refactoring
//    private MyCardListener myCardListener;
    private Server server;

    private UNOCard unoCard;

    public void setServer(Server server) {
        this.server = server;
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

        // Send playCard event to player/model

        // todo remove almost all of this logic, especially this catching null.
        try{
            if(server.canPlay)
                server.playThisCard(unoCard);

        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
//        myCardListener.mousePressed(e);
    }

}
