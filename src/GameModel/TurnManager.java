package GameModel;

import java.util.ArrayList;
import java.util.List;

public class TurnManager {

    private List<Player> players = new ArrayList<>();

    private int currentPlayerIndex = 0;

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

    public Player nextPlayer() {
        return players.get((currentPlayerIndex + 1) % playersCount());
    }
}
