package app.config;

import app.console.CommandLineProcessor;
import app.console.GameController;
import app.domain.game.Game;
import app.domain.game.InterpretResetGame;
import app.domain.game.InterpretStopGame;
import app.domain.interpreter.Interpreter;
import app.domain.movement.FindPlayer;
import app.domain.movement.InterpretMovePlayer;
import app.domain.movement.MovePlayer;
import app.domain.movement.MovementsRecorderFactory;
import app.domain.player.*;
import app.domain.rollmove.DiceRoller;
import app.domain.rollmove.InterpretRollAndMove;
import app.domain.rollmove.RollAndMove;
import app.domain.rules.switchrule.SwitchPlayersRule;

import java.util.List;

import static app.console.CommandLineProcessor.ignoreUnrecognizedCommands;
import static java.util.Arrays.asList;

public class GameConfiguration {

    private final GooseGameAppBuilder builder;
    private GameController gameController;
    private Game game;
    private Board board;
    private Players players;
    private InputOutput inputOutput;
    private DiceRoller diceRoller;

    public GameConfiguration(GooseGameAppBuilder builder) {
        this.builder = builder;
    }

    public GameController buildApplication() {
        return gooseGameApp();
    }

    private GameController gooseGameApp() {
        if (gameController == null) gameController = new GameController(game(), inputOutput(), interpreter());
        return gameController;
    }

    private Game game() {
        if (game == null) game = new Game(players());
        return game;
    }

    private Board board() {
        if (board == null) board = new BoardConfiguration().buildBoard();
        return board;
    }

    private Players players() {
        if (players == null) players = new Players();
        return players;
    }

    private InputOutput inputOutput() {
        if (inputOutput == null) inputOutput = builder.getInputOutput();
        return inputOutput;
    }

    private DiceRoller diceRoller() {
        if (diceRoller == null) diceRoller = builder.diceRoller();
        return diceRoller;
    }

    private PlayerPresenter presenter() {
        return new PlayerPresenter(inputOutput());
    }

    private MovementsRecorderFactory movementsFactory() {
        return new MovementsRecorderFactory(inputOutput());
    }

    private CommandLineProcessor interpreter() {
        List<Interpreter> intepreters = asList(
                new InterpretAddPlayer(addPlayer()),
                new InterpretMovePlayer(findPlayer(movePlayer())),
                new InterpretRollAndMove(rollAndMove(findPlayer(movePlayer()))),
                new InterpretResetGame(game()),
                new InterpretStopGame(game()) ,
                ignoreUnrecognizedCommands
        );

        return new CommandLineProcessor(intepreters);
    }

    private FindPlayer findPlayer(MovePlayer movePlayer) {
        return new FindPlayer(players(), movePlayer);
    }

    private AddPlayer addPlayer() {
        return new AddPlayer(board(), players(), presenter());
    }

    private MovePlayer movePlayer() {
        return new MovePlayer(movementsFactory(), switchPlayersRule());
    }

    private SwitchPlayersRule switchPlayersRule() {
        return new SwitchPlayersRule(players());
    }

    private RollAndMove rollAndMove(FindPlayer findPlayer) {
        return new RollAndMove(diceRoller(), findPlayer);
    }
}
