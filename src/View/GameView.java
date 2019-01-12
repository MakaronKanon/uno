package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import CardModel.ModelUnoCard;
import GameModel.Dealer;
import GameModel.Game;
import GameModel.Player;
import ServerController.Controller;
import ServerController.PlayerPanelController;

public class GameView extends JPanel {
	private PlayerPanel player1panel;
	private PlayerPanel player2panel;
	private TablePanel table;	
	
	private Game game;
	private Controller controller;

	public GameView(Game newGame, ModelUnoCard firstCard, Controller controller){
		setPreferredSize(new Dimension(960,720));
		setBackground(new Color(30,36,40));
		setLayout(new BorderLayout());
		
		game = newGame;

		this.controller = controller;
		setPlayers(game, controller);

	}
	
	private void setPlayers(Game game, Controller controller) {
		Player player1 = game.getPlayers()[0];
		Player player2 = game.getPlayers()[1];

		PlayerPanelController playerPanelController = new PlayerPanelController(player1, controller, game);
		PlayerPanelController playerPanelController2 = new PlayerPanelController(player2, controller, game);
		player1panel = new PlayerPanel(playerPanelController);
		player2panel = new PlayerPanel(playerPanelController2);
		playerPanelController.setPlayerPanel(player1panel);
		playerPanelController2.setPlayerPanel(player2panel);


		table = new TablePanel(game.getTopCard());
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
	
	public void updatePanel(ModelUnoCard playedCard){

		table.setPlayedCard(playedCard);
		refreshPanel();
	}	

}
