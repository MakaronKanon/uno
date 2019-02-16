package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.*;

import Model.CardModel.UnoCard;
import Model.GameModel.Facade;
import Model.GameModel.Player;
import Controller.Controller;
import Controller.PlayerPanelController;
import Model.Tuple.Tuple;

public class GameView extends JPanel {
	private PlayerPanel player1panel;
	private PlayerPanel player2panel;
	private TablePanel table;	
	
	private Controller controller;

	private InfoPanel infoPanel; // Maybe it should be elsewhere? tablepanel?
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public GameView(UnoCard firstCard, Controller controller, Facade facade){
		setPreferredSize(new Dimension(960,720));
		setBackground(new Color(30,36,40));
		setLayout(new BorderLayout());

		this.infoPanel = new InfoPanel();

		this.controller = controller;
		setPlayers(controller, facade, infoPanel);

	}
	
	private void setPlayers(Controller controller, Facade facade, InfoPanel infoPanel) {
		List<Player> players = facade.getPlayers();
		Player player1 = players.get(0);
		Player player2 = players.get(1);

		//todo: fix this maybe, do we have a double dependency?
		PlayerPanelController playerPanelController = new PlayerPanelController(player1, controller, facade);
		PlayerPanelController playerPanelController2 = new PlayerPanelController(player2, controller, facade);
		player1panel = new PlayerPanel(playerPanelController);
		player2panel = new PlayerPanel(playerPanelController2);
		playerPanelController.setPlayerPanel(player1panel);
		playerPanelController2.setPlayerPanel(player2panel);


		table = new TablePanel(facade.getLastPlayedCard(), infoPanel);
		player1panel.setOpaque(false);
		player2panel.setOpaque(false);

		add(player1panel,BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
		add(player2panel, BorderLayout.SOUTH);

	}
	
	public void refreshPanel(){
		player1panel.setCards();
		player2panel.setCards();
		
		table.revalidate();		
		revalidate();
	}
	
	public void updatePanel(UnoCard playedCard){

		table.setPlayedCard(playedCard);
		refreshPanel();
	}

	/**
	 * Displays an error to the screen, in InfoPanel.
	 * @param errorMsg is a String with the error message.
	 */
	public void displayError(String errorMsg) {
		infoPanel.setError(errorMsg);
	}

	/**
	 * Displays an text to the screen, in InfoPanel.
	 * @param gameOver is the String to display.
	 */
	public void updateText(String gameOver) {
		infoPanel.updateText(gameOver);
	}

	/**
	 * Updates playedCards and remainingCards
	 * @param playedCards array, first element is player1 played cards, etc
	 * @param remainingCards how many cards remaining
	 */
	//todo: the structure of using this method is little bad, should it update automatically and
	//todo: playedCards is really cryptic
	public void updateDetail(Tuple<Integer, Integer> playedCards, int remainingCards) {
		infoPanel.setDetail(playedCards, remainingCards);
	}
}
