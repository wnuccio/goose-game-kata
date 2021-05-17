package goosegame.usecase.move_player;

import goosegame.domain.Players;

import java.util.List;

public class MovePlayer {
    private final Players players;
    private final ComputeMovement computeMovement;
    private final MovementPresenter presenter;

    public MovePlayer(Players players, ComputeMovement computeMovement, MovementPresenter presenter) {
        this.players = players;
        this.computeMovement = computeMovement;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) {
            return;
        }

        List<Movement> movements = computeMovement.fromCommand(command);
        presenter.presentMovement(buildMovementViewFrom(command, movements));
    }

    private MovementView buildMovementViewFrom(MoveCommand command, List<Movement> movements) {
        return new MovementView(
                movements,
                command.player(),
                command.dice().first(),
                command.dice().second()
        );
    }
}
