package GameModel;

import CardModel.CardDeck;
import ServerController.Server;
import View.UNOCard;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

import static Interfaces.GameConstants.FIRSTHAND;

public class Dealer {
	
	private CardDeck cardDeck;
	private Stack<UNOCard> CardStack;	
	
	public Dealer(Server server){
		this.cardDeck = new CardDeck(server);
	}
	
	//Shuffle cards
	public Stack<UNOCard> shuffle(){
		
		LinkedList<UNOCard> DeckOfCards = cardDeck.getCards();
		LinkedList<UNOCard> shuffledCards = new LinkedList<UNOCard>();
		
		while(!DeckOfCards.isEmpty()){
			int totalCards = DeckOfCards.size();
			
			Random random = new Random();
			int pos = (Math.abs(random.nextInt()))% totalCards;
			
			UNOCard randomCard = DeckOfCards.get(pos);
			DeckOfCards.remove(pos);
			shuffledCards.add(randomCard);
		}
		
		CardStack = new Stack<UNOCard>();
		for(UNOCard card : shuffledCards){
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
	
	public UNOCard getCard(){
		return CardStack.pop();
	}
}
