package usecase.move_player;

public class ComputeMovementWithDice {
    private ComputeMovement computeMovement;
    private Dice dice;

    public ComputeMovementWithDice(ComputeMovement computeMovement, Dice dice) {
        this.computeMovement = computeMovement;
        this.dice = dice;
    }

    public Movement doComputationFor(String player) {
        Dice dice = this.dice.roll();
        return computeMovement.doComputationFor(player, dice.first(), dice.second());
    }
}
