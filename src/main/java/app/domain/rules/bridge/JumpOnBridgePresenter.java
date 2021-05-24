package app.domain.rules.bridge;

import app.domain.movement.PlayerTurnView;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class JumpOnBridgePresenter {
    public void present(StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String playerJumps = format(". %s jumps to 12", playerTurnView.player());

        presenter.append(playerJumps);
    }
}
