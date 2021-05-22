package _1_actions.player.move.rules.switchrule;

import _1_actions.player.move.presenter.MovementPresenter;
import _1_actions.player.move.presenter.PresentableMovement;
import _2_domain.Position;

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
