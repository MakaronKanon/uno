package ServerController;

import CardModel.UnoCard;
import CardModel.WildCard;
import GameModel.*;
import View.GameView;
import View.InfoPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Interfaces.GameConstants.UNO_COLORS;
import static Interfaces.GameConstants.infoPanel;

/**
 * This is the main controller, it listens from Game for events, then delegates to a specific controller.
 */
public class Controller implements GameListener {

    private InfoPanel infoPanel;
    private Facade facade;
    private GameView gameView;

    public Controller(InfoPanel infoPanel, Facade facade) {
        this.infoPanel = infoPanel;
        this.facade = facade;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void playCard(Player player, UnoCard unoCard) {

        if (unoCard instanceof WildCard) {
            // Need to select color.

            WildCard wildCard = (WildCard) unoCard;

            String[] colors = {"RED", "BLUE", "GREEN", "YELLOW"};

            String chosenColor = (String) JOptionPane.showInputDialog(null,
                    "Choose a color", "Wild Card Color",
                    JOptionPane.DEFAULT_OPTION, null, colors, null);

            wildCard.useWildColor(UNO_COLORS[Arrays.asList(colors).indexOf(chosenColor)]);
        }

        try {
            facade.playCard(player, unoCard);
//            game.playThisCardIfPossible(unoCard);
        } catch (GameIsOverException e) {
//            e.printStackTrace();
            infoPanel.setError("Game is over!");
        } catch (NotYourTurnException e) {
            infoPanel.setError("It's not your turn");
            infoPanel.repaint();
        } catch (InvalidMoveException e) {
            infoPanel.setError("invalid move");
            infoPanel.repaint();
        }
//        facade.playCard(player, unoCard);
    }

    public void drawCard(Player player) {
//        facade.drawCard(player);
    }

    @Override
    public void gameOverCallback() {
        infoPanel.updateText("GAME OVER");

    }

    @Override
    public void cardPlayed(UnoCard unoCard) {
        gameView.updatePanel(unoCard);
    }

    @Override
    public void cardDrawn() {
        gameView.refreshPanel();
    }

    @Override
    public void newTurn(String newPlayerName) {
        infoPanel.updateText(newPlayerName + "'s Turn");

        int remainingCards = facade.getRemainingCardsCount();
        int[] playedCards = facade.getPlayedCards();
        infoPanel.setDetail(playedCards, remainingCards);
        infoPanel.repaint();
    }

    @Override
    public void forgotToSayUno(String name) {
        infoPanel.setError(name + " Forgot to say UNO");
    }


}
