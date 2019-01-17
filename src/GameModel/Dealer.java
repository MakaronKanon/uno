package GameModel;

import CardModel.CardDeck;
import CardModel.UnoCard;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import static Interfaces.GameConstants.FIRSTHAND;
import static Interfaces.UNOConstants.CardType.NUMBER;

public class Dealer {
	
	private CardDeck cardDeck = CardDeck.createDefaultUnoDeck();
	private Stack<UnoCard> CardStack;

	//Shuffle cards
	public Stack<UnoCard> shuffle(){
		
		List<UnoCard> DeckOfCards = cardDeck.getCards();
		LinkedList<UnoCard> shuffledCards = new LinkedList<UnoCard>();
		
		while(!DeckOfCards.isEmpty()){
			int totalCards = DeckOfCards.size();
			
			Random random = new Random();
			int pos = (Math.abs(random.nextInt()))% totalCards;

			UnoCard randomCard = DeckOfCards.get(pos);
			if (shuffledCards.size() == 0 && randomCard.getType() != NUMBER) {
				continue; // Make sure first card is not a wild.
			}
			DeckOfCards.remove(pos);
			shuffledCards.add(randomCard);

		}
		
		CardStack = new Stack<UnoCard>();
		for(UnoCard card : shuffledCards){
			CardStack.add(card);
		}

		return CardStack;
	}
	
	//Spread cards to players - 8 each
	public void spreadOut(Player[] players){		
		
		for(int i=1;i<=FIRSTHAND;i++){
			for(Player p : players){
				p.obtainCard(CardStack.pop());
			}
		}		
	}
	
	public UnoCard getCard(){
		return CardStack.pop();
	}
}
