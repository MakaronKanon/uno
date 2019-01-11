package ServerController;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import CardModel.ModelUnoCard;
import CardModel.WildCard;
import GameModel.Game;
import GameModel.Player;
import View.Session;

import static Interfaces.GameConstants.*;
import static Interfaces.GameConstants.GameMode.vsPC;
import static Interfaces.UNOConstants.CardType.WILD;

public class Server {
	private Game game;
	private Session session;
	private Stack<ModelUnoCard> playedCards;
	public boolean canPlay;
	private GameMode mode;


	public Server(GameMode gameMode) {

		this.mode = gameMode;

		game = new Game(mode,this);
		playedCards = new Stack<ModelUnoCard>();

		// First Card
		ModelUnoCard firstCard = game.getCard();
		modifyFirstCard(firstCard);

		playedCards.add(firstCard);
		session = new Session(game, firstCard, this);

		game.whoseTurn();
		canPlay = true;
	}

	//return if it's 2-Player's mode or PC-mode


	public void playThisCardIfPossible(ModelUnoCard modelUnoCard) {
		if (canPlay) {
			playThisCard(modelUnoCard);
		}
	}
	
	//coustom settings for the first card
	private void modifyFirstCard(ModelUnoCard firstCard) {
//		firstCard.disableMouseListener();
//		firstCard.removeMouseListener(CARDLISTENER);
		if (firstCard.getType() == WILD) {
			int random = new Random().nextInt() % 4;
			try {
				((WildCard) firstCard).useWildColor(UNO_COLORS[Math.abs(random)]);
			} catch (Exception ex) {
				System.out.println("something wrong with modifyFirstcard");
			}
		}
	}
	
	//return Main Panel
	public Session getSession() {
		return this.session;
	}
	
	
	//request to play a card
	public void playThisCard(ModelUnoCard clickedCard) {

//		ModelUnoCard clickedCard = (ModelUnoCard) clickedCardUno; // todo this is temp

		// Check player's turn
		if (!isHisTurn(clickedCard)) {
			infoPanel.setError("It's not your turn");
			infoPanel.repaint();
		} else {

			// Card validation
			if (isValidMove(clickedCard)) {

//				clickedCard.removeMouseListener(CARDLISTENER);
//				clickedCard.disableMouseListener();
				playedCards.add(clickedCard);
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
				session.updatePanel(clickedCard);
				checkResults();
			} else {
				infoPanel.setError("invalid move");
				infoPanel.repaint();
			}
			
		}
		
		
		
		if(mode== vsPC && canPlay){
			if(game.isPCsTurn()){
				game.playPC(peekTopCard());
			}
		}
	}

	//Check if the game is over
	private void checkResults() {

		if (game.isOver()) {
			canPlay = false;
			infoPanel.updateText("GAME OVER");
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

	//check if it is a valid card
	public boolean isValidMove(ModelUnoCard playedCard) {
		ModelUnoCard topCard = peekTopCard();

		if (playedCard.getColor().equals(topCard.getColor())
				|| playedCard.getValue().equals(topCard.getValue())) {
			return true;
		}

		else if (playedCard.getType() == WILD) {
			return true;
		} else if (topCard.getType() == WILD) {
			Color color = ((WildCard) topCard).getWildColor();
			if (color.equals(playedCard.getColor()))
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
		game.drawCard(peekTopCard());
		
		if(mode==vsPC && canPlay){
			if(game.isPCsTurn())
				game.playPC(peekTopCard());
		}
		
		session.refreshPanel();
	}

	public ModelUnoCard peekTopCard() {
		return playedCards.peek();
	}

	public void submitSaidUNO() {
		game.setSaidUNO();
	}
}
