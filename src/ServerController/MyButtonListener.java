package ServerController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Deprecated
public class MyButtonListener {
		
	Server myServer;
	
	public void setServer(Server server){
		myServer = server;
	}
	
	public void drawCard() {
		if(myServer.canPlay)
			myServer.requestCard();	
	}
	
	public void sayUNO() {
		if(myServer.canPlay)
			myServer.submitSaidUNO();
	}
	
}
