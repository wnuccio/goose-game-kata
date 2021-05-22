package _1_actions.player.move.rules.switchrule;

import _2_domain.player.Position;
import _2_domain.presenter.MovementPresenter;
import _2_domain.presenter.PresentableMovement;

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
