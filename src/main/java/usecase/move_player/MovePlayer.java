package usecase.move_player;

import boundary.output.MovementPresenter;
import domain.Players;

public class MovePlayer {
    private Players players;
    private ComputeMovement computeMovement;
    private MovementPresenter presenter;

    public MovePlayer(Players players, ComputeMovement computeMovement, MovementPresenter presenter) {
        this.players = players;
        this.computeMovement = computeMovement;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) {
            return;
        }

        Movement movement = computeMovement.fromCommand(command);
        presenter.presentMovement(buildMovementViewFrom(command, movement));
    }

    private MovementView buildMovementViewFrom(MoveCommand command, Movement movement) {
        return new MovementView(
                movement,
                command.player(),
                command.dice().first(),
                command.dice().second()
        );
    }
}
