package goosegame.boundary.input;

import goosegame.domain.Dice;
import goosegame.usecase.move_player.MoveCommand;
import goosegame.usecase.move_player.MovePlayer;

public class InterpretMovePlayer implements Interpreter {
    private final MovePlayer movePlayer;

    public InterpretMovePlayer(MovePlayer movePlayer) {
        this.movePlayer = movePlayer;
    }

    @Override
    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("(move) (\\w*) ([1-6]), ([1-6])", tokens -> {
            String player = tokens.name(2);
            int dice1 = tokens.number(3);
            int dice2 = tokens.number(4);
            MoveCommand command = new MoveCommand(player, new Dice(dice1, dice2));
            movePlayer.acceptCommand(command);
        });
    }
}
