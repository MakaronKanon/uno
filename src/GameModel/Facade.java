package GameModel;

import CardModel.UnoCard;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a facade to make the controller and view get unaffected by the bad code in model.
 */
public class Facade {

    private Game game;

    /**
     * Creates a Facade that simplifies package
     * @param game it's the game object this Facade "wraps"
     */
    public Facade (Game game) {
        this.game = game;
    }

    //todo: since Facade is already tightly coupled to Game we could have a factory method,
    // which create a game as well.

    /**
     * Attempts to play card
     * @param player the player who plays the card.
     * @param unoCard the card that should be played.
     */
    /* todo: Make a decision whether we need both Player and UnoCard or just one of them.
        what should be in view? Both or just one?
    */
    // if you think about if we have player and unocard we would need to throw exception if
    // player don't own the card.
    public void playCard(Player player, UnoCard unoCard) throws GameIsOverException,
            NotYourTurnException, InvalidMoveException {
        // For now simply delegate to game.
        game.playThisCardIfPossible(unoCard);
    }


    /**
     * Attempt's for the player to draw card.
     * @param player the player that should draw the card.
     */
    public void drawCard(Player player) {
        //todo: this logic is just copied to here, might want to throw exceptions and make it more clean.
        // draw card
        if (player.isMyTurn()) {
            if (game.canPlay()) {
                game.requestCard();
            }
            System.out.println("Draw button clicked");
        }
    }

    /**
     * Attempt's to say uno for the player.
     * @param player the player who says uno.
     */
    public void sayUno(Player player) {
        //todo: if the player says uno and it's not his turn
        // he should get punished as well as getting a warning message
        // the punish logic could be in here, but it probably should be in game,
        // since other logic like that is there. Maybe the player has a callback for punish
        // so the view can display.
        game.playerSayUno(player);
    }

    /**
     * @return a List of all the players.
     */
    public List<Player> getPlayers() {

        //todo, we should make a defensive copy, but it probably should be in Game getPlayers as well.
        // temporarily we convert from array to list
        List<Player> players = new ArrayList<>();
        Player[] oldPlayers = game.getPlayers();
        players.add(oldPlayers[0]);
        players.add(oldPlayers[1]);
        return players;
    }

    /**
     * @return the last placed card
     */
    //todo: this is mainly needed to feed the view its first card to display
    // we should have a stream from a cardstack or something that updates the card on top.
    // A stream that we can fetch last placed card.
    public UnoCard getLastPlayedCard() {
        return game.getTopCard();
    }

    //todo: this is just copied from game, might want to rethink design
    public int getRemainingCardsCount() {
        return game.remainingCards();
    }

    //todo: this is just copied from game, might want to rethink design
    public int[] getPlayedCards() {
        return game.playedCardsSize();
    }
}