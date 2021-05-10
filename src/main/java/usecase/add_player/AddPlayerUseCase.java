package usecase.add_player;

import usecase.Presenter;
import usecase.UseCase;

public class AddPlayerUseCase implements UseCase {
    private Players players;
    private Presenter presenter;

    public AddPlayerUseCase(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    @Override
    public void acceptCommand(String commandLine) {
        AddPlayerCommandLineParser command = new AddPlayerCommandLineParser(commandLine);
        String player = command.playerName();
        
        if (players.contains(player)) {
            presenter.presentExistingPlayerError(player);
            return;
        }

        players.addPlayer(player);
        presenter.presentPlayers(players.all());
    }

    public void doAdd(String player) {
        if (players.contains(player)) {
            presenter.presentExistingPlayerError(player);
            return;
        }

        players.addPlayer(player);
        presenter.presentPlayers(players.all());
    }
}

