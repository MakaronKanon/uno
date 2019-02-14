package Model.GameModel;

import Model.CardModel.UnoCard;

public interface GameListener {
    void gameOverCallback();

    void cardPlayed(UnoCard unoCard);

    void cardDrawn();

    void newTurn(String newPlayerName);

    void forgotToSayUno(String name);
}
