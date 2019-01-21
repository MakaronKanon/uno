package Interfaces;

import java.awt.Color;

import View.InfoPanel;


public class GameConstants {

	public static Color[] UNO_COLORS = {UnoColors.RED, UnoColors.BLUE, UnoColors.GREEN, UnoColors.YELLOW};

	public enum GameMode {vsPC, twoPlayer}
	
	public static InfoPanel infoPanel = new InfoPanel(); // todo this is worse than the other constans here..
}
