package usecase.move_player;

import boundary.output.MovementPresenter;

import java.util.List;

import static java.util.Arrays.asList;

public interface Movement {
    int startPosition();

    int finalPosition();

    default boolean endsOnGoose() {
        List<Integer> positionsWithGoose = asList(5, 9, 14, 18, 23, 27);
        return positionsWithGoose.contains(finalPosition());
    }

    int diceTotal();

    boolean hasPreviousMovement();

    Movement previousMovement();

    void present(MovementPresenter movementPresenter);
}
