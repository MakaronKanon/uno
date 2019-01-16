package CardModel;

import java.awt.Color;
import java.util.LinkedList;

import static Interfaces.GameConstants.*;

/**
 * This Class contains standard 108-Card stack
 */
public class CardDeck{
	
	private final LinkedList<UnoCard> numberCards;
	private final LinkedList<UnoCard> actionCards;
	private final LinkedList<WildCard> wildCards;
	
	private LinkedList<UnoCard> UNOcards;
	
	public CardDeck(){
		
		//Initialize Cards
		numberCards = new LinkedList<UnoCard>();
		actionCards = new LinkedList<UnoCard>();
		wildCards = new LinkedList<WildCard>();
		
		UNOcards = new LinkedList<UnoCard>();
		
		addCards();
	}
	
	
	//Create 108 cards for this CardDeck
	private void addCards() {
		for(Color color:UNO_COLORS){
			
			//Create 76 NumberCards --> doubles except 0s.
			for(int num : UNO_NUMBERS){
				int i=0;
				do{
					UNOcards.add(CardFactory.createNumberCard(color, Integer.toString(num)));
					i++;
				}while(num!=0 && i<2);
			}
			
			//Create 24 ActionCards --> everything twice
			for(String type : ActionTypes){
				for(int i=0;i<2;i++)
					UNOcards.add(CardFactory.createActionCard(color, type));
			}					
		}		
		
		for(String type : WildTypes){
			for(int i=0;i<4;i++){
				UNOcards.add(new WildCard(type));
			}
		}
		
	}
	
	public LinkedList<UnoCard> getCards(){
		return UNOcards;
	}
}
