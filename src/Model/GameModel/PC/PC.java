package Model.GameModel.PC;

import java.awt.Color;
import java.util.Random;

import Model.CardModel.UnoCard;
import Model.CardModel.WildCard;
import Model.GameModel.Game;
import Model.GameModel.Player;

import static GameSettings.GameConstants.UNO_COLORS;

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

	private void playRound() {
        if (isMyTurn()) {
            for (int i = 0; i < 3; i++) {
                try {
                    play();
                    return; // When this is reached it has already played the card
                } catch (PCCantPlayException e) {
                    game.currentPlayerDrawCard();
                    e.printStackTrace();
                }
            }

            // Try to play card one last time
            try {
                 play();
            } catch (PCCantPlayException e) {
                game.cantPlayGoNext();
                return;
            }
        }

        // After we have played card check if we need to say uno
        System.out.println("After play round PC");
        //todo: bug, this code does not run since we play the card it keeps going from there
        // so this is just added to stack-frame, and it keeps growing
        if(getTotalCards()==1 || getTotalCards()==2)
            sayUno();
    }

    /**
     * PC attempts to play one card.
     * @throws PCCantPlayException, if PC can't play any card.
     */
	private void play() throws PCCantPlayException {
        UnoCard topCard = game.getTopCard();

		Color color = topCard.getColor();
		String value = topCard.getValue();
		
		if(topCard instanceof WildCard){
			color = ((WildCard) topCard).getWildColor();			
		}

		for (UnoCard card : getAllCards()) {
			if (card.getColor().equals(color) || card.getValue().equals(value)) {
                playThisCard(card);
                return;
			}
		}

		// if no card was found, play wild card
        for (UnoCard card : getAllCards()) {
            if (card instanceof WildCard) {
                WildCard wildCard = (WildCard)card;

                int random = new Random().nextInt() % 4;
                wildCard.useWildColor(UNO_COLORS[Math.abs(random)]);
                playThisCard(card);

                return;
            }
        }

		// If we havn't returned yet it hasn't played card because it can't.
        throw new PCCantPlayException();
	}

	private void playThisCard(UnoCard card) {
        try {
            game.playThisCardIfPossible(card);
        } catch (Exception e) {
            // Should not be called since we have checked for prerequisites
            e.printStackTrace();
        }
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
