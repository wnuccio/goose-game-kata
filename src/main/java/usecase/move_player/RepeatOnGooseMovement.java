package usecase.move_player;

public class RepeatOnGooseMovement extends RepeatedMovement {
    private SimpleMovement previousMovement;

    public RepeatOnGooseMovement(SimpleMovement previousMovement) {
        super(previousMovement);
        this.previousMovement = previousMovement;
    }

    @Override
    public int toPosition() {
        return previousMovement.toPosition() + previousMovement.firstDice() + previousMovement.secondDice();
    }

    @Override
    public int intermediatePosition() {
        return previousMovement.toPosition();
    }

    @Override
    public boolean isRepeatOnGoose() {
        return true;
    }
}
