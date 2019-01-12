package GameModel;

import java.util.LinkedList;

import CardModel.ModelUnoCard;


public class Player {
	
	private String name;
	private boolean isMyTurn = false;
	private boolean saidUNO = false;
	private LinkedList<ModelUnoCard> myCards = new LinkedList<>();
	
	private int playedCards = 0;

	public Player(String player){
		name = player;
	}

	public String getName(){
		return this.name;
	}

	public void obtainCard(ModelUnoCard card){
		myCards.add(card);
	}
	
	public LinkedList<ModelUnoCard> getAllCards(){
		return myCards;
	}
	
	public int getTotalCards(){
		return myCards.size();
	}
	
	public boolean hasCard(ModelUnoCard thisCard){
		return myCards.contains(thisCard);		
	}
	
	public void removeCard(ModelUnoCard thisCard){
		myCards.remove(thisCard);
		playedCards++;
	}
	
	public int totalPlayedCards(){
		return playedCards;
	}
	
	public void toggleTurn(){
		isMyTurn = (isMyTurn) ? false : true;
	}
	
	public boolean isMyTurn(){
		return isMyTurn;
	}
	
	public boolean hasCards(){
		return (myCards.isEmpty()) ? false : true;
	}
	
	public boolean getSaidUNO(){
		return saidUNO;
	}

	public void sayUno() {
		if (isMyTurn) {
			//todo punish if said uno while having more than two cards (give them +2)
			System.out.println(name + " said uno");
			saidUNO = true;
		} // todo throw if tried to say uno but not your turn so ui can show the user
	}

//	public void saysUNO(){
//		saidUNO = true;
//	}
	
	public void setSaidUNOFalse(){
		saidUNO = false;
	}
	
	public void setCards(){
		myCards = new LinkedList<ModelUnoCard>();
	}

	public void playCard(ModelUnoCard modelUnoCard) {
		if (!isMyTurn) {
			// throw not my turn exception : maybe tho this is not the player's responsibility
			return;
		}
		if (!hasCard(modelUnoCard)) {
			// throw down have card exception
			return;
		}
	}
}
