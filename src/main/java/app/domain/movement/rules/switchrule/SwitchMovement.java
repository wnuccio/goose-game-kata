package app.domain.movement.rules.switchrule;

import app.domain.movement.MovementPresenter;
import app.domain.movement.PresentableMovement;
import app.domain.player.Position;

public class SwitchMovement extends PresentableMovement {
    private final String switchedPlayer;

    public SwitchMovement(String switchedPlayer, Position startPosition, Position finalPosition) {
        super(startPosition, finalPosition);
        this.switchedPlayer = switchedPlayer;
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        movementPresenter.presentSwitchMovement(this);
    }

    public String switchedPlayer() {
        return switchedPlayer;
    }
}
