package app.domain.movement;

import app.domain.interpreter.CommandLine;
import app.domain.interpreter.Interpreter;
import app.domain.player.Dice;

public class InterpretMovePlayer implements Interpreter {
    private final FindPlayer findPlayer;

    public InterpretMovePlayer(FindPlayer findPlayer) {
        this.findPlayer = findPlayer;
    }

    @Override
    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("(move) (\\w*) ([1-6]), ([1-6])", tokens -> {
            String player = tokens.name(2);
            int dice1 = tokens.number(3);
            int dice2 = tokens.number(4);
            findPlayer.acceptCommand(player, new Dice(dice1, dice2));
        });
    }
}
