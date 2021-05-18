package goosegame.usecase.move_player;

import goosegame.domain.Position;

public class JumpOnBridgeMovement extends PresentableMovement {

    public JumpOnBridgeMovement(Position startPosition, Position finalPosition) { super(startPosition, finalPosition); }

    @Override
    public void present(MovementPresenter movementPresenter) { movementPresenter.presentJumpOnBridge(this); }
}