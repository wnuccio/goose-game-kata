package usecase.move_player;

public class RepeatOnGooseMovement implements Movement {
    private SimpleMovement previousMovement;

    public RepeatOnGooseMovement(SimpleMovement previousMovement) {
        this.previousMovement = previousMovement;
    }

    @Override
    public String player() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int firstDice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int secondDice() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int fromPosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int toPosition() {
        return previousMovement.toPosition() + previousMovement.firstDice() + previousMovement.secondDice();
    }

    @Override
    public boolean isBouncing() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isVictory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean endsOnBridge() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isJumpOnBridge() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int intermediatePosition() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMoveAgainOnGoose() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean endsOnGoose() {
        throw new UnsupportedOperationException();
    }
}
