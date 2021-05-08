package usecase.move_player;

import static usecase.move_player.SimpleMovement.BRIDGE_TARGET;

public class JumpOnBridgeMovement implements Movement {
    private SimpleMovement previousMovement;

    public JumpOnBridgeMovement(SimpleMovement previousMovement) {
        this.previousMovement = previousMovement;
    }

    @Override
    public String player() {
        return previousMovement.player();
    }

    @Override
    public int firstDice() {
        return previousMovement.firstDice();
    }

    @Override
    public int secondDice() {
        return previousMovement.secondDice();
    }

    @Override
    public int fromPosition() {
        return previousMovement.fromPosition();
    }

    @Override
    public int toPosition() {
        return BRIDGE_TARGET;
    }

    @Override
    public boolean isBouncing() {
        return false;
    }

    @Override
    public boolean isVictory() {
        return false;
    }

    @Override
    public boolean isToBridge() {
        return false;
    }

    @Override
    public boolean isJumpOnBridge() {
        return true;
    }

    @Override
    public int intermediatePosition() {
        return previousMovement.toPosition();
    }
}