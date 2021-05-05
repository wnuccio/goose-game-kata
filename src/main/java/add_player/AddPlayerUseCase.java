package add_player;

import java.util.List;

public class AddPlayerUseCase {
    private Players players;
    private OutputBoundary outputBoundary;

    public AddPlayerUseCase(Players players, OutputBoundary outputBoundary) {
        this.players = players;
        this.outputBoundary = outputBoundary;
    }

    public void acceptCommand(String commandLine) {
        String playerName = extractPlayerNameFrom(commandLine);
        players.addPlayer(playerName);
        outputBoundary.writeOutputLine("players: " + allPlayerNames());
    }

    private String allPlayerNames() {
        List<String> allPlayers = players.all();
        return String.join(", ", allPlayers);
    }

    private String extractPlayerNameFrom(String line) {
        String[] tokens = line.split(" ");
        return tokens[2];
    }
}

