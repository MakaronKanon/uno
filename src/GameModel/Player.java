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
	
	public void saysUNO(){
		saidUNO = true;
	}
	
	public void setSaidUNOFalse(){
		saidUNO = false;
	}
	
	public void setCards(){
		myCards = new LinkedList<ModelUnoCard>();
	}
}
