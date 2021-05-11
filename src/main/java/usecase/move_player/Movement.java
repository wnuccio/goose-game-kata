package usecase.move_player;

import java.util.List;

import static java.util.Arrays.asList;

public interface Movement {
    String player();

    int firstDice();

    int secondDice();

    int startPosition();

    int finalPosition();

    boolean isVictory();

    int intermediatePosition();

    MovementType type();

    default boolean endsOnGoose() {
        List<Integer> positionsWithGoose = asList(5, 9, 14, 18, 23, 27);
        return positionsWithGoose.contains(finalPosition());
    }

    default int diceTotal() {
        return firstDice() + secondDice();
    }

    Movement previousMovement();
}
