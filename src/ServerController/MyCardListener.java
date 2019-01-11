package ServerController;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.omg.CORBA.Bounds;
import View.MainFrame;
import View.UNOCard;

public class MyCardListener extends MouseAdapter {
	
	UNOCard sourceCard;
	Server myServer;
	
	public void setServer(Server server){
		myServer = server;
	}
	
	public void mousePressed(MouseEvent e) {		
		sourceCard = (UNOCard) e.getSource();
		
		try{
			if(myServer.canPlay)
				myServer.playThisCard(sourceCard);			
			
		}catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}
}
