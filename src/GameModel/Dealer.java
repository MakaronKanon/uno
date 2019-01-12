package GameModel;

import CardModel.CardDeck;
import CardModel.ModelUnoCard;
import CardModel.WildCard;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import static Interfaces.GameConstants.FIRSTHAND;
import static Interfaces.GameConstants.UNO_COLORS;
import static Interfaces.UNOConstants.CardType.NUMBER;
import static Interfaces.UNOConstants.CardType.WILD;

public class Dealer {
	
	private CardDeck cardDeck;
	private Stack<ModelUnoCard> CardStack;
	
	public Dealer(){
		this.cardDeck = new CardDeck();
	}
	
	//Shuffle cards
	public Stack<ModelUnoCard> shuffle(){
		
		LinkedList<ModelUnoCard> DeckOfCards = cardDeck.getCards();
		LinkedList<ModelUnoCard> shuffledCards = new LinkedList<ModelUnoCard>();
		
		while(!DeckOfCards.isEmpty()){
			int totalCards = DeckOfCards.size();
			
			Random random = new Random();
			int pos = (Math.abs(random.nextInt()))% totalCards;

			ModelUnoCard randomCard = DeckOfCards.get(pos);
			if (shuffledCards.size() == 0 && randomCard.getType() != NUMBER) {
				continue; // Make sure first card is not a wild.
			}
			DeckOfCards.remove(pos);
			shuffledCards.add(randomCard);

		}
		
		CardStack = new Stack<ModelUnoCard>();
		for(ModelUnoCard card : shuffledCards){
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
	
	public ModelUnoCard getCard(){
		return CardStack.pop();
	}
}
