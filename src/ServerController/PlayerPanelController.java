package ServerController;

import CardModel.UnoCard;
import GameModel.Game;
import GameModel.Player;
import View.CardView;
import View.PlayerPanel;

import java.util.ArrayList;
import java.util.List;

public class PlayerPanelController {

    private PlayerPanel playerPanel;
    private Controller controller;

    private Player player;
    private Game game;

    // Unsure if we want this, have it while refactoring. Should atleast rename it.
//    private Server server;

    public PlayerPanelController(Player player, Controller controller, Game game) {
        this.player = player;
        this.controller = controller;
        this.game = game;


    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

//    public void setServer(Server server) {
//        this.server = server;
//    }

    public String getPlayerName() {
        return player.getName();
    }

    public int getPlayerCardsCount() {
        return player.getTotalCards();
    }



    // In future we might want to convert model cards to unoviews. Or do that in the view.
    public List<CardView> getPlayerCards() {
        List<CardView> cardViews = new ArrayList<>();
        for (UnoCard card : player.getAllCards()) {
            CardView cardView = new CardView(card);
            UNOCardController unoCardController = new UNOCardController(game, player, controller);
            unoCardController.setCardView(cardView);
            cardView.setUnoCardController(unoCardController);
            cardViews.add(cardView);
        }
        return cardViews;
    }



    public void drawBtnClicked() {
        if (player.isMyTurn()) {
            if (game.canPlay()) {
                game.requestCard();
            }
            System.out.println("Draw button clicked");
        }

    }

    public void sayUnoBtnClicked() {
        game.playerSayUno(player);

    }
}
