package usecase.move_player;

import usecase.add_player.Players;

import static usecase.move_player.Movement.WIN_POSITION;

public class ComputeMovement {
    private Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }


    public Movement doComputationFor(String player, Dice dice) {
        MovementBuilder movement = Movement
                .of(player)
                .givenRoll(dice)
                .from(currentPositionOf(player))
                .to(computePosition(candidatePosition(player, dice)))
                .isBouncing(isBouncing(candidatePosition(player, dice)));

        return movement.end();
    }

    private int computePosition(int candidatePosition) {
        return isBouncing(candidatePosition) ?
                WIN_POSITION - (candidatePosition - WIN_POSITION) : candidatePosition;
    }

    private int candidatePosition(String player, Dice dice) {
        return currentPositionOf(player) + dice.total();
    }

    private boolean isBouncing(int candidatePosition) {
        return candidatePosition > WIN_POSITION;
    }

    private int currentPositionOf(String player) {
        return players.positionOf(player);
    }
}
