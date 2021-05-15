package usecase.move_player;


public class FurtherMovement implements Movement {
    protected Movement previousMovement;
    private MovementType type;
    private int finalPosition;

    public FurtherMovement(Movement previousMovement, MovementType type, int finalPosition) {
        this.previousMovement = previousMovement;
        this.type = type;
        this.finalPosition = finalPosition;
    }

    public int startPosition() {
        return previousMovement.startPosition();
    }

    @Override
    public int finalPosition() {
        return finalPosition;
    }

    @Override
    public boolean hasPreviousMovement() {
        return true;
    }

    @Override
    public Movement previousMovement() {
        return previousMovement;
    }

    @Override
    public void present(MovementPresenter movementPresenter) {
        type.presentMovement(this, movementPresenter);
    }
}
