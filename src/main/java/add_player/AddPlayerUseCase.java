package add_player;

public class AddPlayerUseCase {
    private Players players;
    private OutputBoundary outputBoundary;

    public AddPlayerUseCase(Players players, OutputBoundary outputBoundary) {
        this.players = players;
        this.outputBoundary = outputBoundary;
    }

    public void acceptCommand(String commandLine) {
        String playerName = extractPlayerNameFrom(commandLine);

        if (players.contains(playerName)) {
            outputBoundary.writeOutputLine("" + playerName + ": already existing player");
            return;
        }

        players.addPlayer(playerName);
        outputBoundary.writeOutputLine("players: " + players.allNamesSeparatedByComma());
    }

    private String extractPlayerNameFrom(String line) {
        String[] tokens = line.split(" ");
        return tokens[2];
    }
}

