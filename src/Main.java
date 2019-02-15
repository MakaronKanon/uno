import javax.swing.*;

import Model.GameModel.Facade;
import Model.GameModel.Game;
import Model.GameModel.PC.PC;
import Model.GameModel.Player;
import GameSettings.GameConstants;
import Controller.Controller;
import View.GameView;
import View.MainFrame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static GameSettings.GameConstants.GameMode.vsPC;

public class Main {


	public static void main(String[] args) {

		// Before starting game, let the user choice options.

		GameConstants.GameMode gameMode = requestMode();

	    //todo unsure if this invokeLater is needed
		//Create Frame and invoke it.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				//Game game = Game.transCreateGame(gameMode);
				List<Player> players = createPlayersToPlay(gameMode);
				Game game = Game.createGame2Players(players.get(0), players.get(1));

				Facade facade = new Facade(game);
				Controller controller = new Controller(facade);
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

	// todo: this setting should probably not be in main, it should not handle details like PC
	// Returns a list of the players that should play, exactly 2 players
	private static List<Player> createPlayersToPlay(GameConstants.GameMode gameMode) {
		PC pc = null;

		String name = (gameMode == GameConstants.GameMode.twoPlayer) ? JOptionPane.showInputDialog("Player 1") : "PC";
		String name2 = JOptionPane.showInputDialog("Player 2");

		if(gameMode == GameConstants.GameMode.vsPC)
			pc = new PC();

		Player player1 = (gameMode == GameConstants.GameMode.vsPC) ? pc : new Player(name);
		Player player2 = new Player(name2);
		java.util.List<Player> players = new ArrayList<>();
		players.add(player1);
		players.add(player2);
		return players;
	}
}
