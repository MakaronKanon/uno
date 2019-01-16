package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.*;

import CardModel.UnoCard;
import GameModel.Facade;
import GameModel.Player;
import ServerController.Controller;
import ServerController.PlayerPanelController;

public class GameView extends JPanel {
	private PlayerPanel player1panel;
	private PlayerPanel player2panel;
	private TablePanel table;	
	
	private Controller controller;

	public GameView(UnoCard firstCard, Controller controller, Facade facade){
		setPreferredSize(new Dimension(960,720));
		setBackground(new Color(30,36,40));
		setLayout(new BorderLayout());

		this.controller = controller;
		setPlayers(controller, facade);

	}
	
	private void setPlayers(Controller controller, Facade facade) {
		List<Player> players = facade.getPlayers();
		Player player1 = players.get(0);
		Player player2 = players.get(1);

		PlayerPanelController playerPanelController = new PlayerPanelController(player1, controller, facade);
		PlayerPanelController playerPanelController2 = new PlayerPanelController(player2, controller, facade);
		player1panel = new PlayerPanel(playerPanelController);
		player2panel = new PlayerPanel(playerPanelController2);
		playerPanelController.setPlayerPanel(player1panel);
		playerPanelController2.setPlayerPanel(player2panel);


		table = new TablePanel(facade.getLastPlayedCard());
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

}
