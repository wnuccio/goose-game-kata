package usecase.move_player;

public class JumpOnBridgeMovement extends SimpleMovement {
    private SimpleMovement previousMovement;

    public JumpOnBridgeMovement(SimpleMovement previousMovement) {
        super(previousMovement.player());
        this.previousMovement = previousMovement;
        Dice originalDice = previousMovement.dice;
        super.dice = originalDice.from(originalDice.first(), originalDice.second());
    }

    @Override
    public int fromPosition() {
        return previousMovement.fromPosition();
    }

    @Override
    public int toPosition() {
        return 12;
    }

    @Override
    public boolean isJumpOnBridge() {
        return true;
    }
}
