package _2_domain;

import _1_actions.player.move.presenter.MovementPresenter;

public interface Movement {
    Position startPosition();

    Position finalPosition();

    void present(MovementPresenter movementPresenter);

    default boolean isVictory() {
        return finalPosition().isWin();
    }

}
