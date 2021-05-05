package add_player;

public class AddPlayerUseCase {
    private OutputBoundary outputBoundary;

    public AddPlayerUseCase(OutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    public void acceptCommand(String line) {
        String[] tokens = line.split(" ");
        String playerName = tokens[2];
        outputBoundary.writeOutputLine("players: " + playerName);
    }
}

