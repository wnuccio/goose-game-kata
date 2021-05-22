package _2_domain.movement;

import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

public interface Movement {
    Position startPosition();

    Position finalPosition();

    void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView);
}
