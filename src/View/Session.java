package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import GameModel.Dealer;
import GameModel.Game;
import GameModel.Player;
import ServerController.MyButtonListener;
import ServerController.MyCardListener;
import ServerController.PlayerPanelController;
import ServerController.Server;

public class Session extends JPanel {
	private PlayerPanel player1panel;
	private PlayerPanel player2panel;
	private TablePanel table;	
	
	private Game game;
	
	public Session(Game newGame, UNOCard firstCard, MyButtonListener BUTTONLISTENER, Server server){
		setPreferredSize(new Dimension(960,720));
		setBackground(new Color(30,36,40));
		setLayout(new BorderLayout());
		
		game = newGame;
		
		setPlayers(BUTTONLISTENER, server);
		table = new TablePanel(firstCard);
		player1panel.setOpaque(false);
		player2panel.setOpaque(false);
		
		add(player1panel,BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
		add(player2panel, BorderLayout.SOUTH);
	}
	
	private void setPlayers(MyButtonListener BUTTONLISTENER, Server server) {
		Player player1 = game.getPlayers()[0];
		Player player2 = game.getPlayers()[1];

		PlayerPanelController playerPanelController = new PlayerPanelController(player1);
		PlayerPanelController playerPanelController2 = new PlayerPanelController(player2);
		player1panel = new PlayerPanel(player1, BUTTONLISTENER, playerPanelController);
		player2panel = new PlayerPanel(player2, BUTTONLISTENER, playerPanelController2);
		playerPanelController.setPlayerPanel(player1panel);
		playerPanelController2.setPlayerPanel(player2panel);
		playerPanelController.setServer(server);
		playerPanelController2.setServer(server);
	}
	
	public void refreshPanel(){
		player1panel.setCards();
		player2panel.setCards();
		
		table.revalidate();		
		revalidate();
	}
	
	public void updatePanel(UNOCard playedCard){
		table.setPlayedCard(playedCard);
		refreshPanel();
	}	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
