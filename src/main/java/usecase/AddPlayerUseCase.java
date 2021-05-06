package usecase;

import player.Players;

public class AddPlayerUseCase implements UseCase {
    private Players players;
    private Presenter presenter;

    public AddPlayerUseCase(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    @Override
    public void acceptCommand(String commandLine) {
        String playerName = extractPlayerNameFrom(commandLine);

        if (players.contains(playerName)) {
            presenter.presentExistingPlayerError(playerName);
            return;
        }

        players.addPlayer(playerName);
        presenter.presentPlayers(players.all());
    }

    private String extractPlayerNameFrom(String line) {
        String[] tokens = line.split(" ");
        return tokens[2];
    }
}

