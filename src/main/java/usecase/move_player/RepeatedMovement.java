package usecase.move_player;

import boundary.output.MovementPresenter;

public class RepeatedMovement implements Movement {
    protected Movement previousMovement;
    private MovementType type;
    private int finalPosition;

    public RepeatedMovement(Movement previousMovement, MovementType type, int finalPosition) {
        this.previousMovement = previousMovement;
        this.type = type;
        this.finalPosition = finalPosition;
    }

    @Override
    public MovementType type() {
        return type;
    }

    public int diceTotal() {
        return previousMovement.diceTotal();
    }

    public int startPosition() {
        return previousMovement.startPosition();
    }

    @Override
    public int finalPosition() {
        return finalPosition;
    }

    @Override
    public Movement previousMovement() {
        return previousMovement;
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        switch(type) {
            case BOUNCING: movementPresenter.presentBouncing(this); break;
            case JUMP_ON_BRIDGE: movementPresenter.presentJumpOnBridge(this); break;
            case REPEAT_ON_GOOSE: movementPresenter.presentRepeatOnGoose(this); break;
            default: break;
        }
    }
}
