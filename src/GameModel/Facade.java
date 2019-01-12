package GameModel;

import CardModel.ModelUnoCard;

/**
 * This is a facade to make the controller and view get unaffected by the bad code in model.
 */
public class Facade {

    // Should have server, and game references

    public void playCard(Player player, ModelUnoCard modelUnoCard) throws GameIsOverException, NotYourTurnException {

    }

    public void drawCard(Player player) throws GameIsOverException, NotYourTurnException {

    }
}
