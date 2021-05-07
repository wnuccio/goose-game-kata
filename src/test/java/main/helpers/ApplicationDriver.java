package main.helpers;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationDriver {
    private ApplicationRunner applicationRunner;

    public ApplicationDriver(ApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    public void resetGame() { applicationRunner.acceptInput("reset game"); }

    public String addPlayer(String playerName) {
        return applicationRunner.acceptInput("add player " + playerName);
    }

    public void verifyAddPlayer(String output, String... players) {
        assertThat(output.startsWith("players: ")).isTrue();
        Stream.of(players).forEach(player -> assertThat(output.contains(player)).isTrue());
    }

    public String movePlayer(String player, int firstDice, int secondDice) {
        return applicationRunner.acceptInput(format("move %s %d, %d", player, firstDice, secondDice));
    }

    public void verifyExistingPlayer(String output, String player) {
        assertThat(output).isEqualTo(String.format("%s: already existing player", player));
    }
}
