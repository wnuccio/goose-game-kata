package main;

import boundary.console.ConsolePresenter;
import boundary.console.OutputBoundary;
import boundary.console.SystemInputOutput;
import boundary.interpreters.AddPlayerInterpeter;
import boundary.interpreters.Interpreter;
import boundary.interpreters.MovePlayerInterpreter;
import boundary.interpreters.ResetInterpeter;
import boundary.random.RandomDiceRoller;
import domain.DiceRoller;
import domain.Players;
import usecase.add_player.AddPlayerUseCase;
import usecase.move_player.MovePlayer;
import usecase.move_player.MovePlayerUseCase;
import usecase.move_player.RollAndMove;
import usecase.reset_game.ApplicationSwitch;
import usecase.reset_game.ResetService;

public class GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return new SystemInputOutput();
    }

    protected OutputBoundary getOutputBoundary() {
        return new SystemInputOutput();
    }

    protected DiceRoller diceRoller() {
        return new RandomDiceRoller();
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        ConsolePresenter presenter = new ConsolePresenter(outputBoundary);
        Players players = new Players();
        ApplicationSwitch applicationSwitch = new ApplicationSwitch();
        Interpreter interpreter = interpretersChain(presenter, players, applicationSwitch);

        return new GooseGameApp(applicationSwitch, inputBoundary, interpreter);
    }

    private Interpreter interpretersChain(ConsolePresenter presenter, Players players, ApplicationSwitch applicationSwitch) {
        return new ResetInterpeter(resetService(players, applicationSwitch),
                new AddPlayerInterpeter(addPlayer(presenter, players),
                        new MovePlayerInterpreter(
                                movePlayer(presenter, players), null)));
    }

    private AddPlayerUseCase addPlayer(ConsolePresenter presenter, Players players) {
        return new AddPlayerUseCase(players, presenter);
    }

    private ResetService resetService(Players players, ApplicationSwitch applicationSwitch) {
        return new ResetService(applicationSwitch, players);
    }

    private MovePlayerUseCase movePlayer(ConsolePresenter presenter, Players players) {
        MovePlayer movePlayer = new MovePlayer(players, presenter);
        return new RollAndMove(diceRoller(), movePlayer);
    }
}
