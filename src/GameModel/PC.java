package GameModel;

import java.awt.Color;

import CardModel.UnoCard;
import CardModel.WildCard;

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
