package usecase.move_player;

public abstract class RepeatedMovement implements Movement {
    protected SimpleMovement previousMovement;
    private MovementType type;

    public RepeatedMovement(SimpleMovement previousMovement, MovementType type) {
        this.previousMovement = previousMovement;
        this.type = type;
    }

    @Override
    public MovementType type() {
        return type;
    }

    public String player() {
        return previousMovement.player();
    }

    public int firstDice() {
        return previousMovement.firstDice();
    }

    public int secondDice() {
        return previousMovement.secondDice();
    }

    public int startPosition() {
        return previousMovement.startPosition();
    }

    public boolean isBouncing() {
        return false;
    }

    public boolean isVictory() {
        return false;
    }

}
