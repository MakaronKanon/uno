package Controller;

import Model.CardModel.UnoCard;
import Model.CardModel.WildCard;
import Model.GameModel.Facade;
import Model.GameModel.GameIsOverException;
import Model.GameModel.GameListener;
import Model.GameModel.InvalidMoveException;
import Model.GameModel.NotYourTurnException;
import Model.GameModel.Player;
import View.GameView;
import View.SelectWildCardView;

import java.awt.Color;

/**
 * This is the main controller, it listens from Game for events, then delegates to a specific controller.
 */
public class Controller implements GameListener {


    private Facade facade;
    private GameView gameView;

    public Controller(Facade facade) {
        this.facade = facade;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Method should be called when player attempts to play a card.
     * It calls the Model's playCard, if it is invalid move this method
     * displays error-messages to the player.
     * @param player, the player who plays the card
     * @param unoCard, the unoCard to play
     */
    public void playCard(Player player, UnoCard unoCard) {

        //todo: is there a way to design the card system to get rid of these instanceof checks?
        if (unoCard instanceof WildCard) {
            // Need to select color.
            WildCard wildCard = (WildCard) unoCard;
            Color selectedColor = new SelectWildCardView().selectWildColor();
            wildCard.useWildColor(selectedColor);
        }

        try {
            facade.playCard(player, unoCard);
        } catch (GameIsOverException e) {
            gameView.displayError("Game is over!");
        } catch (NotYourTurnException e) {
            gameView.displayError("It's not your turn");
        } catch (InvalidMoveException e) {
            gameView.displayError("Invalid move");
        }
    }

    @Override
    public void gameOverCallback() {
        gameView.updateText("GAME OVER");
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
        gameView.updateText(newPlayerName + "'s Turn");

        int remainingCards = facade.getRemainingCardsCount();
        int[] playedCards = facade.getPlayedCards();
        gameView.updateDetail(playedCards, remainingCards);
    }

    @Override
    public void forgotToSayUno(String name) {
        gameView.displayError(name + " forgot to say UNO");
    }

}