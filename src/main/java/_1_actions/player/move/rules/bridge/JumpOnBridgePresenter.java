package _1_actions.player.move.rules.bridge;

import _2_domain.movement.PlayerTurnView;
import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class JumpOnBridgePresenter {
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerJumps = format(". %s jumps to 12", playerTurnView.player());

        presenter.append(playerJumps);
    }
}
