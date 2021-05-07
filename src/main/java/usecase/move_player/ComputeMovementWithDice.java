package usecase.move_player;

public class ComputeMovementWithDice {
    private ComputeMovement computeMovement;
    private DiceThrower diceThrower;

    public ComputeMovementWithDice(ComputeMovement computeMovement, DiceThrower diceThrower) {
        this.computeMovement = computeMovement;
        this.diceThrower = diceThrower;
    }

    public Movement doComputationFor(String player) {
        Dice dice = diceThrower.throwDice();
        return computeMovement.doComputationFor(player, dice.first(), dice.second());
    }
}
