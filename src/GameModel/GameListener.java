package GameModel;

import CardModel.ModelUnoCard;

public interface GameListener {
    void gameOverCallback();

    void cardPlayed(ModelUnoCard unoCard);

    void cardDrawn();
}
