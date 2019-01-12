package ServerController;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import CardModel.ModelUnoCard;
import CardModel.WildCard;
import GameModel.Game;
import GameModel.Player;
import View.GameView;

import static Interfaces.GameConstants.*;
import static Interfaces.GameConstants.GameMode.vsPC;
import static Interfaces.UNOConstants.CardType.WILD;

public class Server {
	private Game game;
	private GameView gameView;
	private GameMode mode;

	public boolean canPlay() {
		return !game.isOver();
	}

	public void setGame(Game game) {
		this.game = game;
		game.whoseTurn();

	}

	public Server(GameMode gameMode, GameView gameView) {

		this.mode = gameMode;




//		playedCards.add(firstCard);
//		gameView = new GameView(game, firstCard, this);
		this.gameView = gameView;

//		canPlay = true;
	}

	//return if it's 2-Player's mode or PC-mode


	public void playThisCardIfPossible(ModelUnoCard modelUnoCard) {
		if (canPlay()) {
			playThisCard(modelUnoCard);
		}
	}
	
	//return Main Panel
	public GameView getGameView() {
		return this.gameView;
	}
	
	
	//request to play a card
	private void playThisCard(ModelUnoCard clickedCard) {


		// Check player's turn
		if (!isHisTurn(clickedCard)) {
			infoPanel.setError("It's not your turn");
			infoPanel.repaint();
		} else {

			// Card validation
			if (game.isValidMove(clickedCard)) {

//				clickedCard.removeMouseListener(CARDLISTENER);
//				clickedCard.disableMouseListener();
				game.playedCards().add(clickedCard);

				game.removePlayedCard(clickedCard);

				// function cards ??
				switch (clickedCard.getType()) {
				case ACTION:
					performAction(clickedCard);
					break;
				case WILD:
					performWild((WildCard) clickedCard);
					break;
				default:
					break;
				}

				game.switchTurn();
				gameView.updatePanel(clickedCard);
				checkResults();
			} else {
				infoPanel.setError("invalid move");
				infoPanel.repaint();
			}
			
		}
		
		
		
		if(mode== vsPC && canPlay()){
			if(game.isPCsTurn()){
				game.playPC();
			}
		}
	}

	//Check if the game is over
	private void checkResults() {

		if (game.isOver()) {
//			canPlay = false;
			infoPanel.updateText("GAME OVER");
		}
	}

	public void playerSayUno(Player player) {
		if (canPlay()) {
			player.sayUno();
		}
	}
	
	//check player's turn
	public boolean isHisTurn(ModelUnoCard clickedCard) {

		for (Player p : game.getPlayers()) {
			if (p.hasCard(clickedCard) && p.isMyTurn())
				return true;
		}
		return false;
	}



	// ActionCards
	private void performAction(ModelUnoCard actionCard) {

		// Draw2PLUS
		if (actionCard.getValue().equals(DRAW2PLUS))
			game.drawPlus(2);
		else if (actionCard.getValue().equals(REVERSE))
			game.switchTurn();
		else if (actionCard.getValue().equals(SKIP))
			game.switchTurn();
	}

	private void performWild(WildCard functionCard) {		

		//System.out.println(game.whoseTurn());
		if(mode==vsPC && game.isPCsTurn()){
			int random = new Random().nextInt() % 4;
			functionCard.useWildColor(UNO_COLORS[Math.abs(random)]);
		}
		else{
			
			ArrayList<String> colors = new ArrayList<String>();
			colors.add("RED");
			colors.add("BLUE");
			colors.add("GREEN");
			colors.add("YELLOW");
			
			String chosenColor = (String) JOptionPane.showInputDialog(null,
					"Choose a color", "Wild Card Color",
					JOptionPane.DEFAULT_OPTION, null, colors.toArray(), null);
	
			functionCard.useWildColor(UNO_COLORS[colors.indexOf(chosenColor)]);
		}
		
		if (functionCard.getValue().equals(W_DRAW4PLUS))
			game.drawPlus(4);
	}
	
	public void requestCard() {
		game.drawCard();
		
		if(mode==vsPC && canPlay()){
			if(game.isPCsTurn())
				game.playPC();
		}
		
		gameView.refreshPanel();
	}
}
