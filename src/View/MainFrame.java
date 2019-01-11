package View;


import javax.swing.JFrame;
import Interfaces.GameConstants;
import ServerController.MyCardListener;
import ServerController.Server;



public class MainFrame extends JFrame {
	
	private Session mainPanel;
	private Server server;
	
	public MainFrame(){	

		MyCardListener CARDLISTENER = new MyCardListener();
		server = new Server(CARDLISTENER);

		CARDLISTENER.setServer(server);

		mainPanel = server.getSession();
		add(mainPanel);
	}
}
