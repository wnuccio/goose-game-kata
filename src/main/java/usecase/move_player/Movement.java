package usecase.move_player;

public interface Movement {
    String player();

    int firstDice();

    int secondDice();

    int startPosition();

    int finalPosition();

    boolean isBouncing();

    boolean isVictory();

    boolean isJumpOnBridge();

    int intermediatePosition();

    boolean isRepeatOnGoose();
}
