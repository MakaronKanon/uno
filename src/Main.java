import javax.swing.*;

import ServerController.Server;
import View.MainFrame;

public class Main {
	
	public static void main(String[] args) {

	    //todo unsure if this invokeLater is needed
		//Create Frame and invoke it.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Server server = new Server();

				JFrame frame = new MainFrame(server.getSession());
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocation(200, 100);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
