package GameModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

import CardModel.*;
import CardModel.SpecialCards.Draw2xCard;
import CardModel.SpecialCards.Draw4xCard;
import CardModel.SpecialCards.ReverseActionCard;
import CardModel.SpecialCards.SkipActionCard;
//import ServerController.Server;

import static Interfaces.GameConstants.*;
import static Interfaces.GameConstants.GameMode.vsPC;
import static Interfaces.GameConstants.infoPanel;

public class Game {

	private Player[] players;
	private boolean isOver;
	private GameMode gamemode;

	private TurnManager turnManager = new TurnManager();
	
	private PC pc;
	private Dealer dealer;
//	private Stack<UnoCard> cardStack;
	private Stack<UnoCard> playedCards = new Stack<>();



	public Stack<UnoCard> playedCards() {
		return playedCards;
	}

	public UnoCard getTopCard() {
		return playedCards.peek();
	}

//	public void setServer(Server server) {
//		pc.setServer(server);
//	}


	public Game(GameMode mode){
		
		gamemode = mode;

		
		//Create players //todo this needs to be move to view, can have factory methods for the different modes
		String name = (gamemode== GameMode.twoPlayer) ? JOptionPane.showInputDialog("Player 1") : "PC";
		String name2 = JOptionPane.showInputDialog("Player 2");
		
		if(gamemode== GameMode.vsPC)
			pc = new PC(this);
		
		Player player1 = (gamemode== GameMode.vsPC) ? pc : new Player(name);
		Player player2 = new Player(name2);		
		player2.toggleTurn();				//Initially, player2's turn		
			
		players = new Player[]{player1, player2};
		turnManager.addPlayer(player2); // want player2 to start, so add it first.
		turnManager.addPlayer(player1);

		//Create Dealer
		dealer = new Dealer();
//		cardStack = dealer.shuffle();

		// First Card
		UnoCard firstCard = getCard();
		playedCards().add(firstCard);


		dealer.spreadOut(players);
		
		isOver = false;
		// Start the turn.
		turnManager.startTurn();

	}

	public Player[] getPlayers() {
		return players;
	}

	public UnoCard getCard() {
		return dealer.getCard();
	}
	
	public void removePlayedCard(UnoCard playedCard) {

		for (Player p : players) {
			if (p.hasCard(playedCard)){
				p.removeCard(playedCard);
				
				if (p.getTotalCards() == 1 && !p.getSaidUNO()) {
					infoPanel.setError(p.getName() + " Forgot to say UNO");
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}else if(p.getTotalCards()>2){
					p.setSaidUNOFalse();
				}
			}			
		}
	}
	
	//give player a card
	public void drawCard() {
		UnoCard topCard = getTopCard();
		boolean canPlay = false;

		for (Player p : players) {
			if (p.isMyTurn()) {
				UnoCard newCard = getCard();
				p.obtainCard(newCard);
				canPlay = canPlay(topCard, newCard);
				break;
			}
		}

		if (!canPlay)
			switchTurn();
	}

	public void switchTurn() {
		for (Player p : players) {
			p.toggleTurn();
		}
		whoseTurn();
	}
	
	//Draw cards x times
	public void drawPlus(int times) {
		for (Player p : players) {
			if (!p.isMyTurn()) {
				for (int i = 1; i <= times; i++)
					p.obtainCard(getCard());
			}
		}
	}
	
	//response whose turn it is
	public void whoseTurn() {

		for (Player p : players) {
			if (p.isMyTurn()){
				infoPanel.updateText(p.getName() + "'s Turn");
				System.out.println(p.getName() + "'s Turn");
			}
		}
		infoPanel.setDetail(playedCardsSize(), remainingCards());
		infoPanel.repaint();
	}
	
	//return if the game is over
	public boolean isOver() {
		
		if(!dealer.hasCards()){
			isOver= true;
			return isOver;
		}
		
		for (Player p : players) {
			if (!p.hasCards()) {
				isOver = true;
				break;
			}
		}
		
		return isOver;
	}

	public int remainingCards() {
	    return dealer.cardsLeftCount();
//		return cardStack.size();
	}

	public int[] playedCardsSize() {
		int[] nr = new int[2];
		int i = 0;
		for (Player p : players) {
			nr[i] = p.totalPlayedCards();
			i++;
		}
		return nr;
	}

	//Check if this card can be played
	private boolean canPlay(UnoCard topCard, UnoCard newCard) {

		// Color or value matches
		if (topCard.getColor().equals(newCard.getColor())
				|| topCard.getValue().equals(newCard.getValue()))
			return true;
		// if chosen wild card color matches
		else if (topCard instanceof WildCard)
			return ((WildCard) topCard).getWildColor().equals(newCard.getColor());

		// suppose the new card is a wild card
		else if (newCard instanceof WildCard)
			return true;

		// else
		return false;
	}

	public void setSaidUNO() {
//		for (Player p : players) {
//			if (p.isMyTurn()) {
//				if (p.getTotalCards() == 2) {
//					p.saysUNO();
//					infoPanel.setError(p.getName() + " said UNO");
//				}
//			}
//		}
	}

	
	public boolean isPCsTurn(){
		if(pc.isMyTurn()){
			return true;
		}
		return false;
	}

	//if it's PC's turn, play it for pc
	public void playPC() {
		UnoCard topCard = getTopCard();
		if (pc.isMyTurn()) {
			boolean done = pc.play(topCard);
			
			if(!done)
				drawCard();
		}
	}

	//check if it is a valid card
	public boolean isValidMove(UnoCard playedCard) {
		UnoCard topCard = getTopCard();

		if (playedCard.getColor().equals(topCard.getColor())
				|| playedCard.getValue().equals(topCard.getValue())) {
			return true;
		}

		else if (playedCard instanceof WildCard) {
			return true;
		} else if (topCard instanceof WildCard) {
			Color color = ((WildCard) topCard).getWildColor();
			if (color.equals(playedCard.getColor()))
				return true;
		}
		return false;
	}


	// all below is copy pasted from server
	public void performWild(WildCard functionCard) {

		//System.out.println(game.whoseTurn());
		if(gamemode==vsPC && isPCsTurn()){
			int random = new Random().nextInt() % 4;
			functionCard.useWildColor(UNO_COLORS[Math.abs(random)]);
		}

		if (functionCard instanceof Draw4xCard)
			drawPlus(4);
	}

	public boolean canPlay() {
		return !isOver();
	}

	public void playerSayUno(Player player) {
		if (canPlay()) {
			player.sayUno();
		}
	}

	// SpecialCards
	public void performAction(UnoCard actionCard) {

		// Draw2PLUS
		if (actionCard instanceof Draw2xCard)
			drawPlus(2);
		else if (actionCard instanceof ReverseActionCard)
			switchTurn();
		else if (actionCard instanceof  SkipActionCard)
			switchTurn();
	}

	//check player's turn
	public boolean isHisTurn(UnoCard clickedCard) {

		for (Player p : getPlayers()) {
			if (p.hasCard(clickedCard) && p.isMyTurn())
				return true;
		}
		return false;
	}

	private List<GameListener> gameListeners = new ArrayList<GameListener>();
	public void addGameListener(GameListener listener) {
		gameListeners.add(listener);
	}




	//return if it's 2-Player's mode or PC-mode


	public void playThisCardIfPossible(UnoCard unoCard) throws GameIsOverException, NotYourTurnException
			, InvalidMoveException {
		if (canPlay()) {
			playThisCard(unoCard);
		} else {
			throw new GameIsOverException();
		}
	}


	//request to play a card
	private void playThisCard(UnoCard clickedCard) throws NotYourTurnException, InvalidMoveException {


		// Check player's turn
		if (!isHisTurn(clickedCard)) {
			throw new NotYourTurnException();
		} else {

			// Card validation
			if (isValidMove(clickedCard)) {

//				clickedCard.removeMouseListener(CARDLISTENER);
//				clickedCard.disableMouseListener();
				playedCards().add(clickedCard);

				removePlayedCard(clickedCard);



				// function cards ??
                if (clickedCard instanceof ActionCard) {
                    performAction(clickedCard);

                }

                if (clickedCard instanceof  WildCard) {
                    performWild((WildCard) clickedCard);
                }

				switchTurn();

				for (GameListener listener : gameListeners) {
					listener.cardPlayed(clickedCard);
				}

//				gameView.updatePanel(clickedCard);
				checkResults();
			} else {
				throw new InvalidMoveException();

			}


			if(gamemode== vsPC && canPlay()){
				if(isPCsTurn()){
					playPC();
				}
			}
		}




	}

	//Check if the game is over
	private void checkResults() {

		if (isOver()) {
//			canPlay = false;
			for (GameListener gameListener : gameListeners) {
				gameListener.gameOverCallback();
			}
		}
	}

	public void requestCard() {
		drawCard();

		if(gamemode==vsPC && canPlay()){
			if(isPCsTurn())
				playPC();
		}

		for (GameListener gameListener : gameListeners) {
			gameListener.cardDrawn();
		}

	}



}
