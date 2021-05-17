package goosegame.boundary.application;


import goosegame.domain.Dice;
import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.move_player.MoveCommand;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;
import goosegame.usecase.reset_game.ResetService;

import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class CommandsInterpreter {

    private final AddPlayer addPlayer;
    private final MovePlayer movePlayer;
    private final RollAndMove rollAndMove;
    private final ResetService resetService;

    public CommandsInterpreter(AddPlayer addPlayer, MovePlayer movePlayer, RollAndMove rollAndMove, ResetService resetService) {
        this.addPlayer = addPlayer;
        this.movePlayer = movePlayer;
        this.rollAndMove = rollAndMove;
        this.resetService = resetService;
    }

    public void acceptCommand(String commandLine) {
        Matcher m;

        m = compile("(add player) (\\w*)").matcher(commandLine);
        if (m.find()) {
            String player = m.group(2);
            addPlayer.doAdd(player);
            return;
        }

        m = compile("(move) (\\w*) ([1-6]), ([1-6])").matcher(commandLine);
        if (m.find()) {
            String player = m.group(2);
            int dice1 = Integer.parseInt(m.group(3));
            int dice2 = Integer.parseInt(m.group(4));
            MoveCommand command = new MoveCommand(player, new Dice(dice1, dice2));
            movePlayer.acceptCommand(command);
            return;
        }

        m = compile("(move) (\\w*)").matcher(commandLine);
        if (m.find()) {
            String player = m.group(2);
            rollAndMove.acceptCommand(player);
            return;
        }

        m = compile("(reset) (\\w*)").matcher(commandLine);
        if (m.find()) {
            resetService.doReset();
            return;
        }

        m = compile("(stop) (\\w*)").matcher(commandLine);
        if (m.find()) {
            resetService.doStop();
            return;
        }
    }
}
