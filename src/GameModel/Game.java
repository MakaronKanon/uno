package GameModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import CardModel.ActionCard;
import CardModel.SpecialCards.Draw2xCard;
import CardModel.SpecialCards.Draw4xCard;
import CardModel.SpecialCards.ReverseActionCard;
import CardModel.SpecialCards.SkipActionCard;
import CardModel.UnoCard;
import CardModel.WildCard;

public class Game {

	private Player[] players;
	private boolean isOver;

	private TurnManager turnManager = new TurnManager();
	
	private Dealer dealer;
	private Stack<UnoCard> playedCards = new Stack<>();

	public Stack<UnoCard> playedCards() {
		return playedCards;
	}

	public UnoCard getTopCard() {
		return playedCards.peek();
	}

	//todo: we can only create games with two players, we want to be able to be mroe players!
	// This forces someone else to create Players, but that's probably good
	// maybe one can have a gameStarter class that does so. For now main handles it.
	public static Game createGame2Players(Player player1, Player player2) {
		return new Game(player1, player2);
	}


	// We can pass list directly, but since we only support two players we take two separate players and then
	// make a collection of them
	private Game(Player player1, Player player2){
		player1.setGame(this);
		player2.setGame(this);

		//todo: We should be satisfied we only having turnManager not also Player array
		players = new Player[]{player1, player2};

		turnManager.addPlayer(player2); // want player2 to start, so add it first.
		//todo: we should want player1 to start because that's more logical.
		turnManager.addPlayer(player1);

		//Create Dealer
		dealer = new Dealer();

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
				    for (GameListener listener : gameListeners) {
				        listener.forgotToSayUno(p.getName());
                    }
					p.obtainCard(getCard());
					p.obtainCard(getCard());
				}else if(p.getTotalCards()>2){
					p.setSaidUNOFalse();
				}
			}			
		}
	}

	// Should be called when player has drawn 3 cards and still can't play.
	public void cantPlayGoNext() {
	    switchTurn();
    }
	
	//give player a card
	public void drawCard() {
		UnoCard topCard = getTopCard();
		boolean canPlay = false;

        Player currentPlayer = turnManager.currentPlayer();
        UnoCard newCard = getCard();
        currentPlayer.obtainCard(newCard);
        canPlay = canPlay(topCard, newCard);

//		currentPlayer
//		for (Player p : players) {
//			if (p.isMyTurn()) {
//				p.obtainCard(newCard);
//				canPlay = canPlay(topCard, newCard);
//				break;
//			}
//		}

//		if (!canPlay) // todo: come up with better way to switch turn
//			switchTurn();

        for (GameListener gameListener : gameListeners) {
            gameListener.cardDrawn();
        }
	}

	public void switchTurn() {
	    turnManager.nextTurn();
        Player currentPlayer = currentPlayer();
        for (GameListener gameListener : gameListeners) {
            gameListener.newTurn(currentPlayer.getName());
        }
	}
	
	//Draw cards x times
	public void drawPlus(int times) {
	    Player nextPlayer = turnManager.nextPlayer();
	    for (int i = 0; i < times; i++) {
	        nextPlayer.obtainCard(getCard());
        }
	}

	// Returns current player
	private Player currentPlayer() {
	    return turnManager.currentPlayer();
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
//		    turnManager.reverseOrder(); //todo use this
			switchTurn();
		else if (actionCard instanceof  SkipActionCard)
//		    turnManager.skipNextTurn(); // todo use this
			switchTurn();
        else if (actionCard instanceof Draw4xCard)
            drawPlus(4);
	}

	//check player's turn, bad way of checking whose turn it is
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

				playedCards().add(clickedCard);

				removePlayedCard(clickedCard);



				// function cards ??
                if (clickedCard instanceof ActionCard) {
                    performAction(clickedCard);

                }

				for (GameListener listener : gameListeners) {
					listener.cardPlayed(clickedCard);
				}

				checkResults();
				if (!isOver()) {
				    switchTurn();
                }
//                switchTurn();
			} else {
				throw new InvalidMoveException();

			}
		}
	}

	//Check if the game is over
	private void checkResults() {
		if (isOver()) {
			for (GameListener gameListener : gameListeners) {
				gameListener.gameOverCallback();
			}
		}
	}
}