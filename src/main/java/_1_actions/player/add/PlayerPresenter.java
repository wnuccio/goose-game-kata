package _1_actions.player.add;

import _1_actions.interpreter.Output;

public class PlayerPresenter {
    private final Output output;

    public PlayerPresenter(Output output) {
        this.output = output;
    }

    public void presentPlayers(String... players) {
        String playersSeparatedByComma = String.join(", ", players);
        output.writeOutputLine("players: " + playersSeparatedByComma);
    }

    public void presentExistingPlayerError(String player) {
        output.writeOutputLine(player + ": already existing player");
    }
}
