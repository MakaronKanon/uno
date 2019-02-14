package GameModel;

import java.awt.Color;
import java.util.Random;

import CardModel.UnoCard;
import CardModel.WildCard;

import static Interfaces.GameConstants.UNO_COLORS;

public class PC extends Player {

    private Game game;

	public PC() {
		super("PC");
		super.setCards();
	}

    // Sets game reference, we get it overriding this method
    // but we cant forget to call super so it also gets a reference
    @Override
    public void setGame(Game game) {
	    super.setGame(game);
        this.game = game;
    }

	void playRound() {
        UnoCard topCard = game.getTopCard();
        if (isMyTurn()) {
            for (int i = 0; i < 3; i++) {
                boolean done = play(topCard);
                if (!done) {
                    game.drawCard();
                } else {
                    return;
                }
            }
            boolean done = play(topCard); // Maybe we can play after we have drawn our third card.
            if (!done) {
                game.cantPlayGoNext();
            }
        }
    }
	
	//PC plays a card
	public boolean play(UnoCard topCard) {

		boolean done = false;

		Color color = topCard.getColor();
		String value = topCard.getValue();
		
		if(topCard instanceof WildCard){
			color = ((WildCard) topCard).getWildColor();			
		}

		for (UnoCard card : getAllCards()) {

			if (card.getColor().equals(color) || card.getValue().equals(value)) {

                try {
                    game.playThisCardIfPossible(card);
                } catch (GameIsOverException e) {
                    e.printStackTrace();
                } catch (NotYourTurnException e) {
                    e.printStackTrace();
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                }
                done = true;
				break;
			}
		}

		// if no card was found, play wild card
		if (!done) {
			for (UnoCard card : getAllCards()) {
				if (card instanceof WildCard) {
				    WildCard wildCard = (WildCard)card;

                    int random = new Random().nextInt() % 4;
                    wildCard.useWildColor(UNO_COLORS[Math.abs(random)]);

                    try {
                        game.playThisCardIfPossible(card);
                    } catch (GameIsOverException e) {
                        e.printStackTrace();
                    } catch (NotYourTurnException e) {
                        e.printStackTrace();
                    } catch (InvalidMoveException e) {
                        e.printStackTrace();
                    }
//					playCard(card);
					
					done = true;
					break;
				}
			}
		}

		if(getTotalCards()==1 || getTotalCards()==2)
			sayUno(); //todo bug, pc does not say uno because its not his turn

		
		return done;
	}

    @Override
    public void yourTurnStarted() {
	    super.yourTurnStarted();
        System.out.println("!!!! PC turn started");
        playRound();

//        playRound();
    }

    @Override
    public void yourTurnEnded() {
	    super.yourTurnEnded();
        System.out.println("Pc turn ended");
    }
}
