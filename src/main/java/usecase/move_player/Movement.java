package usecase.move_player;

public interface Movement {
    String player();

    int firstDice();

    int secondDice();

    int startPosition();

    int finalPosition();

    boolean isVictory();

    int intermediatePosition();

    MovementType type();
}
