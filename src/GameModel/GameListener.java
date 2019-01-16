package GameModel;

import CardModel.UnoCard;

public interface GameListener {
    void gameOverCallback();

    void cardPlayed(UnoCard unoCard);

    void cardDrawn();
}
