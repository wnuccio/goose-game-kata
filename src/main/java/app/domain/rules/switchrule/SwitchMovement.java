package app.domain.rules.switchrule;

import app.domain.movement.PlayerOnTurn;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;
import app.domain.presenter.StringBuilderPresenter;

public class SwitchMovement extends PresentableMovement {
    private final String switchedPlayer;

    public SwitchMovement(String switchedPlayer, Position startPosition, Position finalPosition) {
        super(startPosition, finalPosition);
        this.switchedPlayer = switchedPlayer;
    }

    public String switchedPlayer() {
        return switchedPlayer;
    }

    @Override
    public void present(PlayerOnTurn playerOnTurn, StringBuilderPresenter presenter) {
        new SwitchPlayersPresenter().present(this, presenter);
    }
}
