package usecase.move_player;

import domain.Position;

public interface Movement {
    int startPosition();

    int finalPosition();

    boolean hasPreviousMovement();

    Movement previousMovement();

    void present(MovementPresenter movementPresenter);

    default boolean endsOnGoose() {
        return Position.of(finalPosition()).hasTheGoose();
    }

    default boolean isVictory() {
        return Position.of(finalPosition()).isWin();
    }

    default boolean isOverTheVictory() {
         return Position.of(finalPosition()).isOverTheVictory();
    }
}
