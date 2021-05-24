package app.domain.rollmove;

import app.domain.interpreter.CommandLine;
import app.domain.interpreter.Interpreter;

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
