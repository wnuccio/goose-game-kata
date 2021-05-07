package main.helpers;

import main.test.TestSystemInputOuput;

import java.util.stream.Stream;

import static java.lang.String.format;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApplicationDriver {
    private TestSystemInputOuput inputOuput = TestSystemInputOuput.instance();

    public String acceptInput(String inputString) {
        inputOuput.writeInputByTest(inputString);
        waitSomeSeconds();
        return inputOuput.readOutputByTest();
    }

    private void waitSomeSeconds() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resetGame() { acceptInput("reset game"); }

    public String addPlayer(String playerName) {
        return acceptInput("add player " + playerName);
    }

    public void verifyAddPlayer(String output, String... players) {
        assertThat(output.startsWith("players: ")).isTrue();
        Stream.of(players).forEach(player -> assertThat(output.contains(player)).isTrue());
    }

    public String movePlayer(String player, int firstDice, int secondDice) {
        return acceptInput(format("move %s %d, %d", player, firstDice, secondDice));
    }

    public void verifyExistingPlayer(String output, String player) {
        assertThat(output).isEqualTo(String.format("%s: already existing player", player));
    }
}
