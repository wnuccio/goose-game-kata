package app.domain.rules.bridge;

import app.domain.movement.PlayerTurn;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class JumpOnBridgePresenter {
    public void present(StringBuilderPresenter presenter, PlayerTurn playerTurn) {
        String playerJumps = format(". %s jumps to 12", playerTurn.player());

        presenter.append(playerJumps);
    }
}
