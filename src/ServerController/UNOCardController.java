package ServerController;

import View.UNOCard;

import java.awt.*;
import java.awt.event.MouseEvent;

public class UNOCardController {


    // this is temporary during refactoring
    private MyCardListener myCardListener;

    private UNOCard unoCard;

    public UNOCardController(MyCardListener myCardListener) {
        this.myCardListener = myCardListener;
    }

    public void setUnoCard(UNOCard unoCard) {
        this.unoCard = unoCard;
    }

    public void cardHovered(MouseEvent e) {
        Point p = unoCard.getLocation();
        p.y -=20;
        unoCard.setLocation(p);

//        myCardListener.mouseEntered(e); // these are wrappers for now, e is also temporary
    }

    public void cardStopHovered(MouseEvent e) {
        Point p = unoCard.getLocation();
        p.y +=20;
        unoCard.setLocation(p);
//        myCardListener.mouseExited(e);

    }

    public void cardClicked(MouseEvent e) {
        myCardListener.mousePressed(e);
    }

}
