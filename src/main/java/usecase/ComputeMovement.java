package usecase;

import player.Players;

public class ComputeMovement {
    private Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public Movement doComputationFor(String player, int firstDice, int secondDice) {
        int prevPosition = players.positionOf(player);
        int newPosition = newPositionAfterRoll(player, firstDice, secondDice);

        return Movement.of(player)
                .givenRoll(firstDice, secondDice)
                .from(prevPosition).to(newPosition)
                .setVictory(isWinningPosition(newPosition));
    }

    private int newPositionAfterRoll(String player, int firstDice, int secondDice) {
        int totalRoll = firstDice + secondDice;
        return players.positionOf(player) + totalRoll;
    }

    private boolean isWinningPosition(int newPosition) {
        return newPosition == 63;
    }
}
