package usecase.move_player;

import player.Players;
import usecase.Presenter;
import usecase.UseCase;

public class MovePlayerUseCase implements UseCase {
    private Players players;
    private ComputeMovement computeMovement;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, ComputeMovement computeMovement, Presenter presenter) {
        this.players = players;
        this.computeMovement = computeMovement;
        this.presenter = presenter;
    }

    public void acceptCommand(String commandLine) {
        MoveCommand command = new MoveCommand(commandLine);
        String player = command.playerName();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        Movement movement = computeMovement(command);

        players.setPositionOf(player, movement.toPosition());
        presenter.presentMovement(movement);
    }

    private Movement computeMovement(MoveCommand command) {
        return computeMovement.doComputationFor(
                command.playerName(),
                command.firstDice(),
                command.secondDice());
    }

}
