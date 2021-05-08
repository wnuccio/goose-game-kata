package usecase.move_player;

public interface Movement {
    String player();

    int firstDice();

    int secondDice();

    int fromPosition();

    int toPosition();

    boolean isBouncing();

    boolean isVictory();

    boolean endsOnBridge();

    boolean isJumpOnBridge();

    int intermediatePosition();

    boolean isRepeatOnGoose();

    boolean endsOnGoose();
}
