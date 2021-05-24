package app.domain.rules.bouncing;

import app.domain.movement.PlayerTurnView;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class BouncingPresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                playerTurnView.player(),
                playerTurnView.player(),
                finalPosition.name());

        presenter.append(playerBounces);
    }
}
