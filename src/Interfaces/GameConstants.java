package Interfaces;

import java.awt.Color;

import javax.swing.JTextArea;

import View.InfoPanel;


public interface GameConstants extends UNOConstants {
	
	int TOTAL_CARDS = 108;
//	int FIRSTHAND = 8;
	
	Color[] UNO_COLORS = {RED, BLUE, GREEN, YELLOW};
	Color WILD_CARDCOLOR = BLACK;
	
	int[] UNO_NUMBERS =  {0,1,2,3,4,5,6,7,8,9};

	enum GameMode {vsPC, twoPlayer}
	
	InfoPanel infoPanel = new InfoPanel(); // todo this is worse than the other constans here..
}
