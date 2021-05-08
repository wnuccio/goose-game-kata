package usecase.move_player;

public interface Movement {
    String player();

    int firstDice();

    int secondDice();

    int fromPosition();

    int toPosition();

    boolean isBouncing();

    boolean isVictory();

    boolean isToBridge();

    boolean isJumpOnBridge();

    int intermediatePosition();
}
