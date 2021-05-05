package add_player;

public class AddPlayerUseCase {
    private Players players;
    private OutputBoundary outputBoundary;

    public AddPlayerUseCase(Players players, OutputBoundary outputBoundary) {
        this.players = players;
        this.outputBoundary = outputBoundary;
    }

    public void acceptCommand(String line) {
        String[] tokens = line.split(" ");
        String playerName = tokens[2];
        players.addPlayer(playerName);
        outputBoundary.writeOutputLine("players: " + playerName);
    }
}

