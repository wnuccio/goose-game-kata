package usecase.move_player;

import usecase.add_player.Players;

import static usecase.move_player.Movement.WIN_POSITION;

public class ComputeMovement {
    private Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public Movement doComputationFor(String player, int firstDice, int secondDice) {
        int prevPosition = players.positionOf(player);
        int newPosition = newPositionAfterRoll(player, firstDice, secondDice);

        Movement movement = Movement
                .of(player)
                .givenRoll(firstDice, secondDice)
                .from(prevPosition)
                .to(newPosition);

        recomputeNewPositionConsideringBouncing(movement);

        movement.setVictory(isWinningPosition(newPosition));

        return movement;
    }

    private int newPositionAfterRoll(String player, int firstDice, int secondDice) {
        int totalRoll = firstDice + secondDice;
        return players.positionOf(player) + totalRoll;
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
