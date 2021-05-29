package app.domain.rules.bouncing;

import app.domain.player.PlayerOnTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class BouncingPresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerOnTurn playerOnTurn) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                playerOnTurn.name(),
                playerOnTurn.name(),
                finalPosition.name());

        presenter.append(playerBounces);
    }
}
