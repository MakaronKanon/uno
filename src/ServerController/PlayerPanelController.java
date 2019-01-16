package ServerController;

import CardModel.UnoCard;
import GameModel.Facade;
import GameModel.Game;
import GameModel.Player;
import View.CardView;
import View.PlayerPanel;

import java.util.ArrayList;
import java.util.List;

//todo: I made a decision that, sub-controllers should have direct reference to facade
// so we can reduce coupling between controllers.
public class PlayerPanelController {

    private PlayerPanel playerPanel;
    private Controller controller;

    private Player player;
    private Facade facade;

    public PlayerPanelController(Player player, Controller controller, Facade facade) {
        this.player = player;
        this.controller = controller;
        this.facade = facade;
    }

    public void setPlayerPanel(PlayerPanel playerPanel) {
        this.playerPanel = playerPanel;
    }

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
            UNOCardController unoCardController = new UNOCardController(player, controller);
            unoCardController.setCardView(cardView);
            cardView.setUnoCardController(unoCardController);
            cardViews.add(cardView);
        }
        return cardViews;
    }

    public void drawBtnClicked() {
        // I made a decision that controller shouldn't have logic
        // for checking if it's its turn. Instead just delegate to facade and
        // todo: maybe catch exception.
        facade.drawCard(player);
    }

    public void sayUnoBtnClicked() {
        facade.sayUno(player);

    }
}
