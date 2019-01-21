package CardModel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Interfaces.GameConstants.*;

/**
 * This class represents a CardDeck, a collection of UnoCards
 * todo: If this is a collection what do we gain if it implements collection or something like that?
 * todo: should atleast use iterator-pattern, maybe we can make this generic class not only for UnoCards.
 * todo: might make more sense to have Stack.
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
	    for (int i = 0; i < 4; i++) {
	        cards.add(CardFactory.createDraw4xCard());
	        cards.add(CardFactory.createWildCard());
        }
    }

    private static void addNormalCardsWithColor(Color color, List<UnoCard> cards) {
        //Create 76 NumberCards --> doubles except 0s.
        for(int i = 0; i < 10; i++){
            for (int j = 0; j < 2; j++) {
                cards.add(CardFactory.createNumberCard(color, Integer.toString(i)));
                if (i == 0) {
                    break; // Only add one with num 0
                }
            }
        }
    }

    private static void addActionCardsWithColor(Color color, List<UnoCard> cards) {
        for(int i=0;i<2;i++) {
            cards.add(CardFactory.createDraw2xCard(color));
            cards.add(CardFactory.createReverseActionCard(color));
            cards.add(CardFactory.createSkipActionCard(color));
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
	    int cardSize = cards.size();
	    for (int i = 0; i < cardSize; i++) {
	        swapCards(i, new Random().nextInt(cardSize));
        }
    }

    private void swapCards(int oldIndex, int newIndex) {
        UnoCard tempCard = cards.get(oldIndex);
        cards.set(oldIndex, cards.get(newIndex));
        cards.set(newIndex, tempCard);
    }

    /**
     * CARE: Alters state and returns value.
     * @return the topCard, then it deletes it from the list
     */
    public UnoCard popTopCard() {
        UnoCard topCard = cards.get(0);
        cards.remove(0);
	    return topCard;
    }

    /**
     * @return the topCard.
     */
    public UnoCard seeTopCard() {
	    return cards.get(0);
    }


    /**
     * @return true if there are at least 1 card in deck.
     */
    public boolean hasCards() {
        return cards.size() > 0;
    }

    /**
     *
     * @return how many cards there are still in the deck.
     */
    public int cardsLeftCount() {
        return cards.size();
    }
}
