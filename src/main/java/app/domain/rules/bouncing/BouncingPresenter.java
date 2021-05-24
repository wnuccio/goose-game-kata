package app.domain.rules.bouncing;

import app.domain.movement.PlayerTurn;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class BouncingPresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerTurn playerTurn) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                playerTurn.player(),
                playerTurn.player(),
                finalPosition.name());

        presenter.append(playerBounces);
    }
}
