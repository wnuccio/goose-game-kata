package usecase.move_player;

import static usecase.move_player.SimpleMovement.BRIDGE_TARGET;

public class JumpOnBridgeMovement extends RepeatedMovement implements Movement {

    public JumpOnBridgeMovement(SimpleMovement previousMovement) {
        super(previousMovement);
    }

    @Override
    public int finalPosition() {
        return BRIDGE_TARGET;
    }

    @Override
    public boolean isJumpOnBridge() {
        return true;
    }

    @Override
    public int intermediatePosition() {
        return previousMovement.finalPosition();
    }
}