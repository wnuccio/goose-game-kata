package goosegame.boundary.interpreters;

import goosegame.domain.Dice;
import goosegame.usecase.move_player.MoveCommand;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;

public class MovePlayerInterpreter extends Interpreter {

    private final RollAndMove rollAndMove;
    private final MovePlayer movePlayer;
    private CommandLineParser parser;

    public MovePlayerInterpreter(RollAndMove rollAndMove, MovePlayer movePlayer, Interpreter nextInterpreter) {
        super(nextInterpreter);
        this.rollAndMove = rollAndMove;
        this.movePlayer = movePlayer;
    }

    public void acceptCommand(String commandLine) {
        parser = new CommandLineParser(commandLine);

        if (!parser.lineStartsWith("move")) {
            super.acceptCommand(commandLine);
            return;
        }

        if (diceValuesSpecified()) {
            movePlayer.acceptCommand(buildMoveCommand());
            return;
        }

        rollAndMove.acceptCommand(player());
    }

    private MoveCommand buildMoveCommand() {
        int firstDice = parser.digitAtToken(2);
        int secondDice = parser.digitAtToken(3);
        Dice dice = new Dice(firstDice, secondDice);
        return new MoveCommand(player(), dice);
    }

    private String player() {
        return parser.token(1);
    }

    private boolean diceValuesSpecified() {
        return parser.tokenNumber() != 2;
    }
}
