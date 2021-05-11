package usecase.move_player;

import domain.Players;
import usecase.Presenter;

public class MovePlayer {
    private Players players;
    private ComputeMovement computeMovement;
    private Presenter presenter;

    public MovePlayer(Players players, ComputeMovement computeMovement, Presenter presenter) {
        this.players = players;
        this.computeMovement = computeMovement;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        if ( ! players.contains(command.player())) {
            presenter.presentNoSuchPlayerError(command.player());
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
