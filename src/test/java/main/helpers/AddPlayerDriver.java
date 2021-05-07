package main.helpers;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddPlayerDriver  {
    private ApplicationDriver driver;

    public AddPlayerDriver(ApplicationDriver driver) {
        this.driver = driver;
    }

    public String addPlayer(String playerName) {
        return driver.acceptInput("add player " + playerName);
    }

    public void verifyAddPlayer(String output, String player1, String... players) {
        assertThat(output.startsWith("players: ")).isTrue();
        assertThat(output.contains(player1)).isTrue();
        Stream.of(players).forEach(player -> assertThat(output.contains(player)).isTrue());
    }

    public void verifyExistingPlayer(String output, String player) {
        assertThat(output).isEqualTo(String.format("%s: already existing player", player));
    }
}
