package View;
import Model.Tuple.Tuple;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	
	private String error;
	private String text;
	private int panelCenter;
	
	private int you = 0;
	private int pc = 0;
	private int rest = 0;

	// Font to displays text
    private Font adjustedFont = new Font("Calibri", Font.PLAIN,	25);
    // FontMetrics to display text
    private FontMetrics fm = this.getFontMetrics(adjustedFont);


    public InfoPanel(){
		setPreferredSize(new Dimension(275,200));
		setOpaque(false);
		error = "";
		text = "Game Started";
		
		updateText(text);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		panelCenter = getWidth()/2;
		
		printMessage(g);
		printError(g);
		printDetail(g);
	}

	private void printError(Graphics g) {
		if(!error.isEmpty()){

			//Determine the width of the word to position
			int xPos = panelCenter - fm.stringWidth(error) / 2;
			
			g.setFont(adjustedFont);
			g.setColor(Color.red);
			g.drawString(error, xPos, 35);
			
			error = "";
		}
	}

	private void printMessage(Graphics g) {
		//Determine the width of the word to position
		int xPos = panelCenter - fm.stringWidth(text) / 2;
		
		g.setFont(adjustedFont);
		g.setColor(new Color(228,108,10));
		g.drawString(text, xPos, 75);		
	}
	
	private void printDetail(Graphics g){
		g.setColor(new Color(127,127,127));
		
		//Determine the width of the word to position
		String text = "Played Cards";
		int xPos = panelCenter - fm.stringWidth(text) / 2;
		
		g.setFont(adjustedFont);
		g.drawString(text, xPos, 120);
		
		text = "Remaining: " + rest;
		xPos = panelCenter - fm.stringWidth(text) / 2;
		g.drawString(text, xPos, 180);
		
		//Details
        Font smallPlainFont = new Font("Calibri", Font.PLAIN,	20);

		g.setFont(smallPlainFont);
		fm = this.getFontMetrics(smallPlainFont);
		
		text = "You : "+you + "  PC : " + pc;
		xPos = panelCenter - fm.stringWidth(text) / 2;
		g.drawString(text, xPos, 140);
	}

	public void updateText(String newText) {
		text = newText;
		repaint();
	}
	
	public void setError(String errorMgs){
		error = errorMgs;
		repaint();
	}
	
	public void setDetail(Tuple<Integer, Integer> playedCards, int remainingCards){
		you = playedCards.getRight();
		pc = playedCards.getLeft();
		rest = remainingCards;
		repaint();
	}
}
