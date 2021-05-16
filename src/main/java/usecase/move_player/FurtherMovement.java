package usecase.move_player;


import domain.Position;

public class FurtherMovement implements Movement {
    protected Movement previousMovement;
    private final MovementType type;
    private final int finalPosition;

    public FurtherMovement(Movement previousMovement, MovementType type, int finalPosition) {
        this.previousMovement = previousMovement;
        this.type = type;
        this.finalPosition = finalPosition;
    }

    @Override
    public Position startPosition() {
        return previousMovement.startPosition();
    }

    @Override
    public Position finalPosition() {
        return Position.of(finalPosition);
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
