package _1_actions.player.move.rules.bouncing;

import _2_domain.player.Position;
import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class BouncingPresenter {
    public void present(Position finalPosition, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                playerTurnView.player(),
                playerTurnView.player(),
                presenter.positionName(finalPosition));

        presenter.append(playerBounces);
    }
}
