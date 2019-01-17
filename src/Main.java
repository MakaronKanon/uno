import javax.swing.*;

import GameModel.Facade;
import GameModel.Game;
import Interfaces.GameConstants;
import ServerController.Controller;
import View.GameView;
import View.MainFrame;

import java.awt.*;

import static Interfaces.GameConstants.GameMode.vsPC;
import static Interfaces.GameConstants.infoPanel;

public class Main {


	public static void main(String[] args) {

		// Before starting game, let the user choice options.

		GameConstants.GameMode gameMode = requestMode();

	    //todo unsure if this invokeLater is needed
		//Create Frame and invoke it.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Game game = new Game(gameMode);
				Facade facade = new Facade(game);
				Controller controller = new Controller(infoPanel, facade);
				GameView gameView = new GameView(game.getTopCard(), controller, facade);
				controller.setGameView(gameView);

				game.addGameListener(controller);


				JFrame frame = new MainFrame(gameView);
				frame.setBackground(Color.PINK);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setLocation(200, 100);
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	private static GameConstants.GameMode requestMode() {

		Object[] options = { "vs PC", "Manual", "Cancel" };

		int n = JOptionPane.showOptionDialog(null,
				"Choose a Game Mode to play", "Game Mode",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

		if (n == 2)
			System.exit(1);

		if (n == 0)
			return vsPC;
		else
			return GameConstants.GameMode.twoPlayer;
	}
}
