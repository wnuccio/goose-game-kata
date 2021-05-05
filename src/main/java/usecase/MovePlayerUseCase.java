package usecase;

import player.Players;

import static java.lang.String.valueOf;

public class MovePlayerUseCase {
    private Players players;
    private OutputBoundary outputBoundary;

    public MovePlayerUseCase(Players players, OutputBoundary outputBoundary) {
        this.players = players;
        this.outputBoundary = outputBoundary;
    }

    public void acceptCommand(String commandLine) {
        MoveCommand command = new MoveCommand(commandLine);

        String playerName = command.playerName();
        int firstDice = command.firstDice();
        int secondDice = command.secondDice();

        int prevPosition = players.positionOf(playerName);
        int newPosition = players.moveOnRoll(playerName, firstDice, secondDice);

        String output = buildOutputFrom(playerName, firstDice, secondDice, prevPosition, newPosition);
        outputBoundary.writeOutputLine(output);
    }

    private String buildOutputFrom(String playerName, int firstDice, int secondDice, int prevPosition, int newPosition) {
        String prevPositionString = prevPosition == 0 ? "Start" : valueOf(prevPosition);
        String playerRolls = String.format("%s rolls %s, %s", playerName, firstDice, secondDice);
        String playerMoves = String.format("%s moves from %s to %s", playerName, prevPositionString, newPosition);
        return playerRolls + ". " + playerMoves;
    }
}
