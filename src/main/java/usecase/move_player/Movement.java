package usecase.move_player;

import domain.Position;

public interface Movement {
    int startPosition();

    int finalPosition();

    int diceTotal();

    boolean hasPreviousMovement();

    Movement previousMovement();

    void present(MovementPresenter movementPresenter);

    default boolean endsOnGoose() {
        return Position.of(finalPosition()).hasTheGoose();
    }
}
