package View;


import javax.swing.JFrame;
import Interfaces.GameConstants;
import ServerController.Server;

import static Interfaces.GameConstants.BUTTONLISTENER;
import static Interfaces.GameConstants.CARDLISTENER;


public class MainFrame extends JFrame {
	
	private Session mainPanel;
	private Server server;
	
	public MainFrame(){	
		server = new Server();
		CARDLISTENER.setServer(server);
		BUTTONLISTENER.setServer(server);
		
		mainPanel = server.getSession();
		add(mainPanel);
	}
}
