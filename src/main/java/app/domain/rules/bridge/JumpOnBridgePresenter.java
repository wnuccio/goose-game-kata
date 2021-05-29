package app.domain.rules.bridge;

import app.domain.player.PlayerOnTurn;
import app.domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class JumpOnBridgePresenter {
    public void present(StringBuilderPresenter presenter, PlayerOnTurn playerOnTurn) {
        String playerJumps = format(". %s jumps to 12", playerOnTurn.name());

        presenter.append(playerJumps);
    }
}
