package GameModel;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.sound.midi.Receiver;

import CardModel.ModelUnoCard;
import CardModel.WildCard;
import Interfaces.GameConstants;

import static Interfaces.UNOConstants.CardType.WILD;

public class PC extends Player {

//	private Server server;



//	public void setServer(Server server) {
//		this.server = server;
//	}

    private Game game;

	public PC(Game game) {
		super("PC");
		this.game = game;
		super.setCards();
//		this.server = server;
	}


	
	//PC plays a card
	public boolean play(ModelUnoCard topCard) {

		boolean done = false;

		Color color = topCard.getColor();
		String value = topCard.getValue();
		
		if(topCard.getType()==WILD){
			color = ((WildCard) topCard).getWildColor();			
		}

		for (ModelUnoCard card : getAllCards()) {

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
			for (ModelUnoCard card : getAllCards()) {
				if (card.getType() == WILD) {
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
        System.out.println("PC turn started");
    }

    @Override
    public void yourTurnEnded() {
	    super.yourTurnEnded();
        System.out.println("Pc turn ended");
    }
}
