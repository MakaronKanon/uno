package GameModel;

import CardModel.CardDeck;
import CardModel.UnoCard;


public class Dealer {
	
	private CardDeck cardDeck = CardDeck.createDefaultUnoDeck();
	private final static int startCardsCount = 8;

	public Dealer() {
		shuffle();
	}

	//Shuffle cards
	public void shuffle(){
		cardDeck.shuffle();
	}
	
	//Spread cards to players - 8 each
	public void spreadOut(Player[] players){
		for(int i=1;i<=startCardsCount;i++){
			for(Player p : players){
				p.obtainCard(cardDeck.popTopCard());
			}
		}		
	}
	
	public UnoCard getCard(){
		return cardDeck.popTopCard();
	}

	/**
	 * @return true if there are atleast 1 card in the deck.
	 */
	public boolean hasCards() {
		return cardDeck.hasCards();
	}

	/**
	 * @return how many cards there are still in the deck.
	 */
	public int cardsLeftCount() {
		return cardDeck.cardsLeftCount();
	}
}
