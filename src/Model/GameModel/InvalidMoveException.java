package Model.GameModel;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("Invalid move");
    }
}
