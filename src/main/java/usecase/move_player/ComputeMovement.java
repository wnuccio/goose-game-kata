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

        Movement movement = Movement
                .of(player)
                .givenRoll(firstDice, secondDice)
                .from(currentPosition)
                .to(newPosition);

        recomputeNewPositionConsideringBouncing(movement);

        movement.setVictory(isWinningPosition(newPosition));

        return movement;
    }

    private void recomputeNewPositionConsideringBouncing(Movement movement) {
        if (movement.toPosition() <= WIN_POSITION) return;

        int surplus = movement.toPosition() - WIN_POSITION;
        int rightPosition = WIN_POSITION - surplus;

        movement
                .to(rightPosition)
                .setBouncing(true);
    }

    private boolean isWinningPosition(int newPosition) {
        return newPosition == WIN_POSITION;
    }
}
