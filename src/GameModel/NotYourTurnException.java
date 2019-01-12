package GameModel;

public class NotYourTurnException extends Exception {
    public NotYourTurnException() {
        super("Its not your turn");
    }
}
