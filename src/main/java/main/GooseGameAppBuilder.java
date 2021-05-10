package main;

import boundary.Interpreter;
import boundary.RandomDiceRoller;
import boundary.ResetInterpeter;
import boundary.UseCaseDispatcher;
import boundary.console.ConsolePresenter;
import boundary.console.OutputBoundary;
import boundary.console.SystemInputOutput;
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
        Interpreter interpreter = useCaseDispatcher(presenter, players, applicationSwitch);

        return new GooseGameApp(applicationSwitch, inputBoundary, interpreter);
    }

    private Interpreter useCaseDispatcher(ConsolePresenter presenter, Players players, ApplicationSwitch applicationSwitch) {
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(players, presenter);
        MovePlayerUseCase movePlayer = movePlayerUseCase(presenter, players);
        ResetService resetService = new ResetService(applicationSwitch, players);

        UseCaseDispatcher useCaseDispatcher = new UseCaseDispatcher(resetService, addPlayerUseCase, movePlayer);
        return new ResetInterpeter(resetService, useCaseDispatcher);
    }

    private MovePlayerUseCase movePlayerUseCase(ConsolePresenter presenter, Players players) {
        MovePlayer movePlayer = new MovePlayer(players, presenter);
        return new RollAndMove(diceRoller(), movePlayer);
    }
}
