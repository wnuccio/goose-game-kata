package _1_actions.player.rollmove;

import _1_actions.interpreter.CommandLine;
import _1_actions.interpreter.Interpreter;

public class InterpretRollAndMove implements Interpreter {
    private final RollAndMove rollAndMove;

    public InterpretRollAndMove(RollAndMove rollAndMove) {
        this.rollAndMove = rollAndMove;
    }


    @Override
    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("(move) (\\w*)", tokens -> {
            String player = tokens.name(2);
            rollAndMove.acceptCommand(player);
        });
    }
}
