package GameModel;

import java.util.LinkedList;

import CardModel.UnoCard;


public class Player {

	private Game game;
	private String name;
	private boolean isMyTurn = false;
	private boolean saidUNO = false;
	private LinkedList<UnoCard> myCards = new LinkedList<>();
	
	private int playedCards = 0;
	private int drawnCardsThisRound = 0;

	public Player(String player){
		name = player;
	}

	public String getName(){
		return this.name;
	}

	public void obtainCard(UnoCard card){
		myCards.add(card);
	}

	// Sets game reference
	public void setGame(Game game) {
		this.game = game;
	}
	
	public LinkedList<UnoCard> getAllCards(){
		return myCards;
	}
	
	public int getTotalCards(){
		return myCards.size();
	}
	
	public boolean hasCard(UnoCard thisCard){
		return myCards.contains(thisCard);		
	}
	
	public void removeCard(UnoCard thisCard){
		myCards.remove(thisCard);
		playedCards++;
	}
	
	public int totalPlayedCards(){
		return playedCards;
	}
	
	public void toggleTurn(){
		isMyTurn = (isMyTurn) ? false : true;
	}

	/**
	 * Method called when it's this player's turn.
	 */
	public void yourTurnStarted() {
		isMyTurn = true;
		System.out.println("A players turn started");
		drawnCardsThisRound = 0;
	}

	/**
	 * Method called when this player's turn ended.
	 */
	public void yourTurnEnded() {
		isMyTurn = false;
		System.out.println("A players turn ended");
	}
	
	public boolean isMyTurn(){
		return isMyTurn;
	}
	
	public boolean hasCards(){
		return (myCards.isEmpty()) ? false : true;
	}
	
	public boolean getSaidUNO(){
		return saidUNO;
	}

	public void sayUno() {
		if (isMyTurn) {
			//todo punish if said uno while having more than two cards (give them +2)
			System.out.println(name + " said uno");
			saidUNO = true;
		} // todo throw if tried to say uno but not your turn so ui can show the user
	}

//	public void saysUNO(){
//		saidUNO = true;
//	}
	
	public void setSaidUNOFalse(){
		saidUNO = false;
	}
	
	public void setCards(){
		myCards = new LinkedList<UnoCard>();
	}

	public void playCard(UnoCard unoCard) {
		if (!isMyTurn) {
			// throw not my turn exception : maybe tho this is not the player's responsibility
			return;
		}
		if (!hasCard(unoCard)) {
			// throw down have card exception
			return;
		}
	}

	public void drawCard() {
		game.drawCard();
//		obtainCard(game.getCard());
		drawnCardsThisRound++;
		if (drawnCardsThisRound == 3 && !canPlay()) {
			game.cantPlayGoNext();
		}
//		game.getCard();
//		game.requestCard(); // Add cardsDrawn this round
	}

	private boolean canPlay() {
		for (UnoCard card : myCards) {
			if (game.isValidMove(card)) {
				return true;
			}
		}
		return false;
	}
}
