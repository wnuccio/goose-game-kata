package usecase.move_player;

import usecase.CommandLineParser;
import usecase.Presenter;
import usecase.UseCase;
import usecase.add_player.Players;

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
        MoveCommand command = parseMoveCommand(commandLine);
        String player = command.playerName();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        Movement movement = computeMovement(command);

        players.setPositionOf(player, movement.toPosition());
        presenter.presentMovement(movement);
    }

    private MoveCommand parseMoveCommand(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);
        String player = parser.token(1);
        boolean hasDiceValues = parser.tokenNumber() != 2;

        MoveCommand result = new MoveCommand(player);
        if (hasDiceValues) {
            int firstDice = parser.numberAt(2);
            int secondDice = parser.numberAt(3);
            result.dice(firstDice, secondDice);
        }
        return result;
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
