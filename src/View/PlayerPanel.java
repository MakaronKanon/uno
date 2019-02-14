package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Controller.PlayerPanelController;

public class PlayerPanel extends JPanel {

	private Box myLayout;
	private JLayeredPane cardHolder;
	private Box controlPanel;

	private JButton draw;
	private JButton sayUNO;
	private JLabel nameLbl;

	private PlayerPanelController playerPanelController;


	// Constructor
	public PlayerPanel(PlayerPanelController playerPanelController) {
		this.playerPanelController = playerPanelController;

		myLayout = Box.createHorizontalBox();
		cardHolder = new JLayeredPane();
		cardHolder.setPreferredSize(new Dimension(600, 175));

		// Set
		setCards();
		setControlPanel(playerPanelController.getPlayerName());

		myLayout.add(cardHolder);
		myLayout.add(Box.createHorizontalStrut(40));
		myLayout.add(controlPanel);
		add(myLayout);


		draw.addActionListener(e -> playerPanelController.drawBtnClicked());
		sayUNO.addActionListener(e -> playerPanelController.sayUnoBtnClicked());
	}

	// This class removes all cards, then re-adds the new version of them, so it gets updated.
	public void setCards() {
		cardHolder.removeAll();

		// Origin point at the center
		Point origin = getPoint(cardHolder.getWidth(), playerPanelController.getPlayerCardsCount());
		int offset = calculateOffset(cardHolder.getWidth(),
				playerPanelController.getPlayerCardsCount());

		int i = 0;
		for (CardView card : playerPanelController.getPlayerCards()) {

			card.setBounds(origin.x, origin.y, card.getSize().width,
					card.getSize().height);
			cardHolder.add(card, i++);
			cardHolder.moveToFront(card);
			origin.x += offset;
		}
		repaint();
	}

	private void setControlPanel(String playerName) {
		draw = new JButton("Draw");
		sayUNO = new JButton("Say UNO");
		nameLbl = new JLabel(playerName);

		// style
		draw.setBackground(new Color(79, 129, 189));
		draw.setFont(new Font("Arial", Font.BOLD, 20));
		draw.setFocusable(false);

		sayUNO.setBackground(new Color(149, 55, 53));
		sayUNO.setFont(new Font("Arial", Font.BOLD, 20));
		sayUNO.setFocusable(false);

		nameLbl.setForeground(Color.WHITE);
		nameLbl.setFont(new Font("Arial", Font.BOLD, 15));

		controlPanel = Box.createVerticalBox();
		controlPanel.add(nameLbl);
		controlPanel.add(draw);
		controlPanel.add(Box.createVerticalStrut(15));
		controlPanel.add(sayUNO);
	}

	private int calculateOffset(int width, int totalCards) {
		int offset = 71;
		if (totalCards <= 8) {
			return offset;
		} else {
			double o = (width - 100) / (totalCards - 1);
			return (int) o;
		}
	}

	private Point getPoint(int width, int totalCards) {
		Point p = new Point(0, 20);
		if (totalCards >= 8) {
			return p;
		} else {
			p.x = (width - calculateOffset(width, totalCards) * totalCards) / 2;
			return p;
		}
	}

}
