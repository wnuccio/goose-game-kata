package usecase.move_player;

import usecase.CommandLineParser;
import usecase.Presenter;
import usecase.UseCase;
import usecase.add_player.Players;

public class MovePlayerUseCase implements UseCase {
    private Players players;
    private Dice dice;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, Dice dice, Presenter presenter) {
        this.players = players;
        this.dice = dice;
        this.presenter = presenter;
    }

    public void acceptCommand(String commandLine) {
        MoveCommand command = parseMoveCommand(commandLine);
        String player = command.player();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        command.setDiceIfAbsent(dice.roll());

        Movement movement = Movement
                .of(player)
                .from(players.positionOf(player))
                .givenRoll(command.dice())
                .end();

        players.setPositionOf(player, movement.toPosition());
        presenter.presentMovement(movement);
    }

    private MoveCommand parseMoveCommand(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);
        String player = parser.token(1);
        boolean hasDiceValues = parser.tokenNumber() != 2;

        MoveCommand result = new MoveCommand(player);
        if (hasDiceValues) {
            int firstDice = parser.digitAtToken(2);
            int secondDice = parser.digitAtToken(3);
            result = new MoveCommand(player, dice.from(firstDice, secondDice));
        }
        return result;
    }

}
