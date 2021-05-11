package usecase.move_player;

public class RepeatedMovement implements Movement {
    protected Movement previousMovement;
    private MovementType type;
    private int intermediatePosition;
    private int finalPosition;

    public RepeatedMovement(Movement previousMovement, MovementType type, int intermediatePosition, int finalPosition) {
        this.previousMovement = previousMovement;
        this.type = type;
        this.intermediatePosition = intermediatePosition;
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

    public boolean isVictory() {
        return false;
    }

    @Override
    public int intermediatePosition() {
        return intermediatePosition;
    }

    @Override
    public int finalPosition() {
        return finalPosition;
    }

    @Override
    public Movement previousMovement() {
        return previousMovement;
    }
}
