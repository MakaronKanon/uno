package ServerController;

import CardModel.ModelUnoCard;
import CardModel.WildCard;
import GameModel.*;
import Interfaces.UNOConstants;
import View.GameView;
import View.InfoPanel;

import javax.swing.*;
import java.util.ArrayList;

import static Interfaces.GameConstants.UNO_COLORS;
import static Interfaces.GameConstants.infoPanel;

/**
 * This is the main controller, it listens from Game for events, then delegates to a specific controller.
 */
public class Controller implements GameListener {

    private InfoPanel infoPanel;
    private Facade facade;
    private GameView gameView;


    private Game game; // Game should probably be removed by facade

    public Controller(InfoPanel infoPanel, Facade facade, Game game) {
        this.infoPanel = infoPanel;
        this.facade = facade;
        this.game = game;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void playCard(Player player, ModelUnoCard modelUnoCard) {

        if (modelUnoCard.getType() == UNOConstants.CardType.WILD) {
            // Need to select color.

            WildCard wildCard = (WildCard) modelUnoCard;

            ArrayList<String> colors = new ArrayList<String>();
            colors.add("RED");
            colors.add("BLUE");
            colors.add("GREEN");
            colors.add("YELLOW");

            String chosenColor = (String) JOptionPane.showInputDialog(null,
                    "Choose a color", "Wild Card Color",
                    JOptionPane.DEFAULT_OPTION, null, colors.toArray(), null);

            wildCard.useWildColor(UNO_COLORS[colors.indexOf(chosenColor)]);

        }

        try {
            game.playThisCardIfPossible(modelUnoCard);
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
//        facade.playCard(player, modelUnoCard);
    }

    public void drawCard(Player player) {
//        facade.drawCard(player);
    }

    @Override
    public void gameOverCallback() {
        infoPanel.updateText("GAME OVER");

    }

    @Override
    public void cardPlayed(ModelUnoCard unoCard) {
        gameView.updatePanel(unoCard);
        //				gameView.updatePanel(clickedCard);

    }

    @Override
    public void cardDrawn() {
        gameView.refreshPanel();

    }
}
