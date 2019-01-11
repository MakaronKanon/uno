package View;


import javax.swing.JFrame;
import Interfaces.GameConstants;
import ServerController.MyButtonListener;
import ServerController.MyCardListener;
import ServerController.Server;



public class MainFrame extends JFrame {
	
	private Session mainPanel;
	private Server server;
	
	public MainFrame(){	

		MyCardListener CARDLISTENER = new MyCardListener();
		MyButtonListener BUTTONLISTENER = new MyButtonListener();
		server = new Server(BUTTONLISTENER, CARDLISTENER);

		CARDLISTENER.setServer(server);
		BUTTONLISTENER.setServer(server);
		
		mainPanel = server.getSession();
		add(mainPanel);
	}
}
