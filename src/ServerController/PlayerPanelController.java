package ServerController;

import GameModel.Player;
import View.PlayerPanel;
import View.UNOCard;

import java.util.List;

public class PlayerPanelController {

    private PlayerPanel playerPanel;

    private Player player;

    // Unsure if we want this, have it while refactoring. Should atleast rename it.
    private Server server;

    public PlayerPanelController(Player player) {
        this.player = player;
    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerCardsCount() {
        return player.getTotalCards();
    }

    // In future we might want to convert model cards to unoviews. Or do that in the view.
    public List<UNOCard> getPlayerCards() {
        return player.getAllCards();
    }



    public void drawBtnClicked() {
        if (player.isMyTurn()) {
            if (server.canPlay) {
                server.requestCard();
            }
            System.out.println("Draw button clicked");
        }

    }

    public void sayUnoBtnClicked() {
        if (player.isMyTurn()) {
            if (server.canPlay){
                server.submitSaidUNO();
            }
            System.out.println("SayUno button clicked");
        }

    }
}