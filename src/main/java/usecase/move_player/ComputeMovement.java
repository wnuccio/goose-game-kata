package usecase.move_player;

import usecase.add_player.Players;

import static usecase.move_player.Movement.WIN_POSITION;

public class ComputeMovement {
    private Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }


    public Movement doComputationFor(String player, Dice dice) {
        int currentPosition = players.positionOf(player);
        int firstDice = dice.first();
        int secondDice = dice.second();
        int newPosition = currentPosition + dice.total();

        MovementBuilder movement = Movement
                .of(player)
                .givenRoll(firstDice, secondDice)
                .from(currentPosition)
                .to(newPosition);

        recomputeNewPositionConsideringBouncing(movement);

        return movement.end();
    }

    private void recomputeNewPositionConsideringBouncing(MovementBuilder movement) {
        Movement tempMovement = movement.end();
        if (tempMovement.toPosition() <= WIN_POSITION) return;

        int surplus = tempMovement.toPosition() - WIN_POSITION;
        int rightPosition = WIN_POSITION - surplus;

        movement
                .to(rightPosition)
                .isBouncing(true);
    }
}
