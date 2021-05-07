package usecase.move_player;

import player.Players;
import usecase.Presenter;
import usecase.UseCase;

public class MovePlayerUseCase implements UseCase {
    private Players players;
    private Dice dice;
    private ComputeMovement computeMovement;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, Dice dice, ComputeMovement computeMovement, Presenter presenter) {
        this.players = players;
        this.dice = dice;
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
        if (command.hasNoDice()) {
            return new ComputeMovementWithDice(computeMovement, dice).doComputationFor(command.playerName());
        }

        return computeMovement.doComputationFor(
                command.playerName(),
                command.firstDice(),
                command.secondDice());
    }

}
