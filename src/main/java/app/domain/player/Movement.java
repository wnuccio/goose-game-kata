package app.domain.player;

import app.domain.presenter.StringBuilderPresenter;

public interface Movement {
    Position startPosition();

    Position finalPosition();

    void present(PlayerOnTurn playerOnTurn, StringBuilderPresenter presenter);
}
