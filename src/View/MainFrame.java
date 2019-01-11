package View;


import javax.swing.JFrame;
import Interfaces.GameConstants;
import ServerController.Server;



public class MainFrame extends JFrame {
	
	private Session mainPanel;
	private Server server;
	
	public MainFrame(){	

		server = new Server();

		mainPanel = server.getSession();
		add(mainPanel);
	}
}
