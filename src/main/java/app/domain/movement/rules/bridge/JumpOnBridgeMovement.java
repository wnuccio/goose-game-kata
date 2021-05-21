package app.domain.movement.rules.bridge;

import app.domain.movement.MovementPresenter;
import app.domain.movement.PresentableMovement;

import static app.domain.player.Position.BRIDGE;
import static app.domain.player.Position.BRIDGE_TARGET;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement() { super(BRIDGE, BRIDGE_TARGET); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentJumpOnBridge(this); }
}