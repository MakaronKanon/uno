package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import Controller.UNOCardController;
import Model.CardModel.UnoCard;
import Model.CardModel.WildCard;


public class TablePanel extends JPanel {
	
	private JPanel table;
	
	public TablePanel(UnoCard firstCard, InfoPanel infoPanel){
		setOpaque(false);
		setLayout(new GridBagLayout());
		
		table = new JPanel();
		table.setBackground(new Color(64,64,64));
		
		setTable(firstCard);
		setComponents(infoPanel);
	}
	
	private void setTable(UnoCard topCard){
		
		table.setPreferredSize(new Dimension(500,200));
		table.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;


		// Code should not be here, should be instantiated before this object
		UNOCardController controller = new UNOCardController(null, null);
		controller.setCardActive(false);
		CardView cardView = new CardView(topCard, controller);
		controller.setCardView(cardView);
		//cardView.disableMouseListener();

		table.add(cardView, c);
	}
	
	private void setComponents(InfoPanel infoPanel) {
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 130, 0, 45);
		add(table,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 1, 0, 1);
		add(infoPanel, c);	
	}

	public void setPlayedCard(UnoCard playedCard){
		table.removeAll();
		setTable(playedCard);
		
		setBackgroundColor(playedCard);
	}
	
	public void setBackgroundColor(UnoCard playedCard){
		Color background;
		if(playedCard instanceof WildCard)
			background = ((WildCard) playedCard).getWildColor();
		else
			background = playedCard.getColor();
		
		table.setBackground(background);
	}
}
