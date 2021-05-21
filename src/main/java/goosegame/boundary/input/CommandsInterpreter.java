package goosegame.boundary.input;

import goosegame.domain.Dice;
import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.move_player.MoveCommand;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;
import goosegame.usecase.reset_game.ResetService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommandsInterpreter {
    private final AddPlayer addPlayer;

    private final MovePlayer movePlayer;
    private final RollAndMove rollAndMove;
    private final ResetService resetService;
    private CommandLine commandLine;

    public CommandsInterpreter(AddPlayer addPlayer, MovePlayer movePlayer, RollAndMove rollAndMove, ResetService resetService) {
        this.addPlayer = addPlayer;
        this.movePlayer = movePlayer;
        this.rollAndMove = rollAndMove;
        this.resetService = resetService;
    }

    public void acceptCommand(String commandLine) {
        this.commandLine = new CommandLine(commandLine);

        Interpreter interpretAddPlayer = new InterpretAddPlayer(addPlayer);
        List<Interpreter> intepreters = new ArrayList<>();
        intepreters.add(interpretAddPlayer);

        for(Interpreter i: intepreters) {
            if (i.interpret(this.commandLine)) return;
        }

        interpretCommands(
            this::interpretMovePlayer,
            this::interpretRollAndMove,
            this::interpretReset,
            this::interpretStop,
            this::unrecognizedCommand
        );
    }

    void interpretCommands(Function<CommandLine, Boolean>... interpretationsFunctions) {
        for (Function<CommandLine, Boolean> f: interpretationsFunctions) {
            Boolean applied = f.apply(commandLine);
            if (applied) break;
        }
    }

    private boolean interpretMovePlayer(CommandLine commandLine) {
        return commandLine.interpret("(move) (\\w*) ([1-6]), ([1-6])", tokens -> {
            String player = tokens.name(2);
            int dice1 = tokens.number(3);
            int dice2 = tokens.number(4);
            MoveCommand command = new MoveCommand(player, new Dice(dice1, dice2));
            movePlayer.acceptCommand(command);
        });
    }

    private boolean interpretRollAndMove(CommandLine commandLine) {
        return commandLine.interpret("(move) (\\w*)", tokens -> {
            String player = tokens.name(2);
            rollAndMove.acceptCommand(player);
        });
    }

    private boolean interpretReset(CommandLine commandLine) {
        return commandLine.interpret("reset game", resetService::doReset);
    }

    private boolean interpretStop(CommandLine commandLine) {
        return commandLine.interpret("stop game", resetService::doStop);
    }

    private boolean unrecognizedCommand(CommandLine commandLine) {
        return false;
    }
}
