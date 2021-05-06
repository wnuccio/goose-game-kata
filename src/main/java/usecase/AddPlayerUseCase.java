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
        AddPlayerCommand command = new AddPlayerCommand(commandLine);
        String player = command.playerName();
        
        if (players.contains(player)) {
            presenter.presentExistingPlayerError(player);
            return;
        }

        players.addPlayer(player);
        presenter.presentPlayers(players.all());
    }
}

