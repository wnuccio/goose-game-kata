package _2_domain.rules.bouncing;

import _2_domain.movement.PlayerTurnView;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

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
