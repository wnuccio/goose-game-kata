package goosegame.boundary.input;

import goosegame.domain.Dice;
import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.move_player.MoveCommand;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;
import goosegame.usecase.reset_game.ResetService;

import java.util.function.Consumer;

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

        if (interpretAddPlayer()) return;
        if (interpretMovePlayerWithDice()) return;
        if (interpretRollAndMove()) return;
        if (interpretReset()) return;
        if (interpretStop()) return;

        doNothing();
    }

    private boolean interpret(String regex, Consumer<String[]> execution) {
        String[] tokens = commandLine.parse(regex);
        if (tokens.length == 0) return false;
        execution.accept(tokens);
        return true;
    }

    private boolean interpretAddPlayer() {
        return interpret("(add player) (\\w*)", tokens -> {
            String player = tokens[2];
            addPlayer.doAdd(player);
        });
    }

    private boolean interpretMovePlayerWithDice() {
        return interpret("(move) (\\w*) ([1-6]), ([1-6])", tokens -> {
            String player = tokens[2];
            int dice1 = Integer.parseInt(tokens[3]);
            int dice2 = Integer.parseInt(tokens[4]);
            MoveCommand command = new MoveCommand(player, new Dice(dice1, dice2));
            movePlayer.acceptCommand(command);
        });
    }

    private boolean interpretRollAndMove() {
        return interpret("(move) (\\w*)", tokens -> {
            String player = tokens[2];
            rollAndMove.acceptCommand(player);
        });
    }

    private boolean interpretReset() {
        return interpret("(reset) (\\w*)", tokens -> resetService.doReset());
    }

    private boolean interpretStop() {
        return interpret("(stop) (\\w*)", tokens -> resetService.doStop());
    }

    private void doNothing() {
    }
}
