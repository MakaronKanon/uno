package View;


import javax.swing.JFrame;
import Interfaces.GameConstants;
import ServerController.Server;



public class MainFrame extends JFrame {
	

	public MainFrame(Session mainPanel){
		add(mainPanel);
	}
}
