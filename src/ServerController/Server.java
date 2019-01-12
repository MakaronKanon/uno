package ServerController;

import GameModel.*;
import View.GameView;

import static Interfaces.GameConstants.GameMode;

public class Server {
	private Game game;
	private GameMode mode;

	public void setGame(Game game) {
		this.game = game;
		game.whoseTurn();

	}

	public Game getGame() {
		return game;
	}

	public Server(GameMode gameMode, GameView gameView) {
		this.mode = gameMode;
	}




}
