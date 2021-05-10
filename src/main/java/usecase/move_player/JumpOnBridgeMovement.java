package usecase.move_player;

import static domain.Position.BRIDGE;
import static domain.Position.BRIDGE_TARGET;

public class JumpOnBridgeMovement extends RepeatedMovement implements Movement {

    public JumpOnBridgeMovement(SimpleMovement previousMovement) {
        super(previousMovement, MovementType.JUMP_ON_BRIDGE);
    }

    @Override
    public int finalPosition() {
        return BRIDGE_TARGET;
    }

    @Override
    public int intermediatePosition() {
        return BRIDGE;
    }
}