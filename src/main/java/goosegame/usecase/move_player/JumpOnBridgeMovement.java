package goosegame.usecase.move_player;

import static goosegame.domain.Position.BRIDGE;
import static goosegame.domain.Position.BRIDGE_TARGET;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement() { super(BRIDGE, BRIDGE_TARGET); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentJumpOnBridge(this); }
}