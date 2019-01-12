package GameModel;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.sound.midi.Receiver;

import CardModel.ModelUnoCard;
import CardModel.WildCard;
import Interfaces.GameConstants;
import ServerController.Server;

import static Interfaces.UNOConstants.CardType.WILD;

public class PC extends Player {

	private Server server;

	public void setServer(Server server) {
		this.server = server;
	}

	public PC() {
		super("PC");
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

				server.playThisCardIfPossible(card);
				done = true;
				break;
			}
		}

		// if no card was found, play wild card
		if (!done) {
			for (ModelUnoCard card : getAllCards()) {
				if (card.getType() == WILD) {
					server.playThisCardIfPossible(card);
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
}
