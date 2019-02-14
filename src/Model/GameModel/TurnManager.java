package Model.GameModel;

import java.util.ArrayList;
import java.util.List;

public class TurnManager {

    private List<Player> players = new ArrayList<>();

    private int currentPlayerIndex = 0;

    private boolean skipNextTurn = false;

    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Starts the turns, should only be called once at start.
     */
    public void startTurn() {
        currentPlayer().yourTurnStarted();
    }

    public void nextTurn() {
        currentPlayer().yourTurnEnded();
        goToNextPlayer();
        currentPlayer().yourTurnStarted();
    }

    private void goToNextPlayer() {
        currentPlayerIndex++;
        if (skipNextTurn) {
            currentPlayerIndex++;
            skipNextTurn = false;
        }
        currentPlayerIndex = currentPlayerIndex % playersCount();
    }

    public Player currentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int playersCount() {
        return players.size();
    }

    public boolean isMyTurn(Player player) {
        return currentPlayer().equals(player);
    }

    /**
     * @return the player of nextPlayer.
     * Even if we skip nextPlayer he will get returned.
     */
    public Player nextPlayer() {
        return players.get((currentPlayerIndex + 1) % playersCount());
    }

    /**
     * Enables a flag that indicates that the next players turn will be skipped
     */
    public void skipNextTurn() {
        skipNextTurn = true;
    }
}
