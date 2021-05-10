package usecase.move_player;

public class RepeatOnGooseMovement extends RepeatedMovement {
    private SimpleMovement previousMovement;

    public RepeatOnGooseMovement(SimpleMovement previousMovement) {
        super(previousMovement, MovementType.REPEAT_ON_GOOSE);
        this.previousMovement = previousMovement;
    }

    @Override
    public int finalPosition() {
        return previousMovement.finalPosition() + previousMovement.firstDice() + previousMovement.secondDice();
    }

    @Override
    public int intermediatePosition() {
        return previousMovement.finalPosition();
    }

}
