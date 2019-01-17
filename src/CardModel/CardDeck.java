package CardModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static Interfaces.GameConstants.*;

/**
 * This class represents a CardDeck, a collection of UnoCards
 * todo: If this is a collection what do we gain if it implements collection or something like that?
 * todo: should atleast use iterator-pattern, maybe we can make this generic class not only for UnoCards.
 */
public class CardDeck{

	private List<UnoCard> cards;

    /**
     * @return a CardDeck containing no Cards.
     */
	public static CardDeck createEmptyDeck() {
		return new CardDeck(new ArrayList<>());
	}

    /**
     * @return a CardDeck with standard UnoCards-set.
     */
	public static CardDeck createDefaultUnoDeck() {
		return new CardDeck(defaultUnoCards());
	}

	private CardDeck(List<UnoCard> cards){
		this.cards = cards;
	}

	//Returns a list with 108 cards.
	private static List<UnoCard> defaultUnoCards() {
        List<UnoCard> cards = new ArrayList<>();
        addColoredCards(cards);
        addWilds(cards);
		return cards;
	}

	private static void addCardsWithColor(Color color, List<UnoCard> cards) {
        addNormalCardsWithColor(color, cards);
        addActionCardsWithColor(color, cards);
    }

    // Adds wildCards to the list passed as argument. Four of each type.
    private static void addWilds(List<UnoCard> cards) {
        for(String type : WildTypes){
            for(int i=0;i<4;i++){
                cards.add(new WildCard(type));
            }
        }
    }

    private static void addNormalCardsWithColor(Color color, List<UnoCard> cards) {
        //Create 76 NumberCards --> doubles except 0s.
        for(int num : UNO_NUMBERS){
            for (int i = 0; i < 2; i++) {
                cards.add(CardFactory.createNumberCard(color, Integer.toString(num)));
                if (num == 0) {
                    break; // Only add one with num 0
                }
            }
        }
    }

    private static void addActionCardsWithColor(Color color, List<UnoCard> cards) {
        //Create 24 ActionCards --> everything twice
        for(String type : ActionTypes){
            for(int i=0;i<2;i++)
                cards.add(CardFactory.createActionCard(color, type));
        }
    }

    // Adds all the colored cards to the list passed.
	private static void addColoredCards(List<UnoCard> cards) {
        for(Color color:UNO_COLORS){
            addCardsWithColor(color, cards);
        }
    }

    // One can argue that logically shuffle should be dealers responsibility,
    // but placing it here we can hide the list. Just have shuffle method in dealer that delegates
    // here (to better follow Law Of Demeter, as well as logical that dealer should have that method)
    public void shuffle() {

    }

	//todo: use iterator instead.
	public List<UnoCard> getCards(){
		return cards;
	}
}
