package move_player;

import add_player.OutputBoundary;
import add_player.Players;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class MovePlayerUseCase {
    private Players players;
    private OutputBoundary outputBoundary;

    public MovePlayerUseCase(Players players, OutputBoundary outputBoundary) {
        this.players = players;
        this.outputBoundary = outputBoundary;
    }

    public void acceptCommand(String commandLine) {
        String[] tokens = commandLine.split(" ");

        String playerName = tokens[1];
        int firstDice = parseInt(tokens[2].substring(0, 1));
        int secondDice = parseInt(tokens[3]);
        int prevPosition = players.positionOf(playerName);

        int newPosition = newPosition(firstDice, secondDice, prevPosition);

        players.move(playerName, newPosition);

        String output = buildOutputFrom(playerName, firstDice, secondDice, prevPosition, newPosition);

        outputBoundary.writeOutputLine(output);
    }

    private String buildOutputFrom(String playerName, int firstDice, int secondDice, int prevPosition, int newPosition) {
        String prevPositionString = prevPosition == 0 ? "Start" : valueOf(prevPosition);
        String playerRolls = String.format("%s rolls %s, %s", playerName, firstDice, secondDice);
        String playerMoves = String.format("%s moves from %s to %s", playerName, prevPositionString, newPosition);
        return playerRolls + ". " + playerMoves;
    }

    private int newPosition(int firstDice, int secondDice, int prevPosition) {
        int totalRoll = firstDice + secondDice;
        return prevPosition + totalRoll;
    }
}
