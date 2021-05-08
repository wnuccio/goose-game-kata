package usecase.move_player;

public class JumpOnBridgeMovement implements Movement {
    private Movement previousMovement;

    public JumpOnBridgeMovement(Movement previousMovement) {
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
        return 12;
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
}
