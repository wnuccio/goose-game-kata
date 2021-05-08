package usecase.move_player;

public class RepeatOnGooseMovement implements Movement {
    private SimpleMovement previousMovement;

    public RepeatOnGooseMovement(SimpleMovement previousMovement) {
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
        return previousMovement.fromPosition;
    }

    @Override
    public int toPosition() {
        return previousMovement.toPosition() + previousMovement.firstDice() + previousMovement.secondDice();
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
    public boolean endsOnBridge() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isJumpOnBridge() {
        return false;
    }

    @Override
    public int intermediatePosition() {
        return previousMovement.toPosition();
    }

    @Override
    public boolean isRepeatOnGoose() {
        return true;
    }

    @Override
    public boolean endsOnGoose() {
        throw new UnsupportedOperationException();
    }
}
