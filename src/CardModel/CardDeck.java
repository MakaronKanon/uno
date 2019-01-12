package CardModel;

import java.awt.Color;
import java.util.LinkedList;

import ServerController.UNOCardController;

import static Interfaces.GameConstants.*;

/**
 * This Class contains standard 108-Card stack
 */
public class CardDeck{
	
	private final LinkedList<ModelUnoCard> numberCards;
	private final LinkedList<ModelUnoCard> actionCards;
	private final LinkedList<WildCard> wildCards;
	
	private LinkedList<ModelUnoCard> UNOcards;
	
	public CardDeck(){
		
		//Initialize Cards
		numberCards = new LinkedList<ModelUnoCard>();
		actionCards = new LinkedList<ModelUnoCard>();
		wildCards = new LinkedList<WildCard>();
		
		UNOcards = new LinkedList<ModelUnoCard>();
		
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
	
	public LinkedList<ModelUnoCard> getCards(){
		return UNOcards;
	}
}
