package usecase.move_player;

public abstract class RepeatedMovement implements Movement {
    protected SimpleMovement previousMovement;

    public RepeatedMovement(SimpleMovement previousMovement) {
        this.previousMovement = previousMovement;
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

    public boolean isJumpOnBridge() {
        return false;
    }

    public boolean isRepeatOnGoose() {
        throw new UnsupportedOperationException();
    }
}
