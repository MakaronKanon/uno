package ServerController;

import CardModel.UnoCard;
import GameModel.Facade;
import GameModel.Player;
import View.CardView;
import View.PlayerPanel;

import java.util.ArrayList;
import java.util.List;


/**
 * Controller class for PlayerPanel
 *
 * I made a decision that sub-controllers like this should have a direct reference to facade
 * instead of delegating everything to a 'main'-controller. This reduces coupling between controllers.
 */
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

    /**
     * @return list of the playerCards
     * Converts from Model-Cards to View-Cards.
     */
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

    /**
     * Should be called when player clicked the drawCard-button
     */
    public void drawBtnClicked() {
        // todo: maybe catch exception. such as notYourTurn / gameOver
        facade.drawCard(player);
    }

    /**
     * Should be called when player clicked the sayUno-button
     */
    public void sayUnoBtnClicked() {
        //todo: same todo as drawBtnClicked
        facade.sayUno(player);
    }
}
