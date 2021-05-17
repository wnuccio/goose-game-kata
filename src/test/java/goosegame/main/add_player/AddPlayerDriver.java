package goosegame.main.add_player;

import goosegame.ApplicationDriver;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AddPlayerDriver  {
    private final ApplicationDriver driver;

    public AddPlayerDriver(ApplicationDriver driver) {
        this.driver = driver;
    }

    public String addPlayer(String player) {
        return driver.acceptInput("add player " + player);
    }

    public void verifyPlayersAdded(String output, String player1, String... players) {
        assertThat(output.startsWith("players: ")).isTrue();
        assertThat(output.contains(player1)).isTrue();
        Stream.of(players).forEach(player -> assertThat(output.contains(player)).isTrue());
    }

    public void verifyAlreadyExistingPlayer(String output, String player) {
        assertThat(output).isEqualTo(String.format("%s: already existing player", player));
    }

    public void addPlayers(String... players) {
        for (String p : players) { addPlayer(p); }
    }
}
