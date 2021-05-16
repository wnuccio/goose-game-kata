package usecase.move_player;

import domain.Position;

public interface Movement {
    Position startPosition();

    Position finalPosition();

    void present(MovementPresenter movementPresenter);

    default boolean endsOnGoose() {
        return finalPosition().hasTheGoose();
    }

    default boolean isVictory() {
        return finalPosition().isWin();
    }

    default boolean isOverTheVictory() {
         return finalPosition().isOverTheVictory();
    }

    default Position bouncedPosition() {
        return finalPosition().bounced();
    }
}
