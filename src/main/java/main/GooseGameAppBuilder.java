package main;

import boundary.*;
import usecase.add_player.AddPlayerUseCase;
import usecase.add_player.Players;
import usecase.move_player.DiceRoller;
import usecase.move_player.MovePlayer;
import usecase.move_player.MovePlayerUseCase;
import usecase.move_player.RollAndMove;
import usecase.reset_game.ResetGameService;

public class GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        return new SystemInputOutput();
    }

    protected OutputBoundary getOutputBoundary() {
        return new SystemInputOutput();
    }

    protected DiceRoller diceRoller() {
        return () -> new Dice(1, 2);
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        ConsolePresenter presenter = new ConsolePresenter(outputBoundary);
        Players players = new Players();

        UseCaseDispatcher useCaseDispatcher = useCaseDispatcher(presenter, players);

        return new GooseGameApp(inputBoundary, useCaseDispatcher);
    }

    private UseCaseDispatcher useCaseDispatcher(ConsolePresenter presenter, Players players) {
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(players, presenter);
        MovePlayerUseCase movePlayer = movePlayerUseCase(presenter, players);
        ResetGameService resetGameService = new ResetGameService(players);

        return new UseCaseDispatcher(resetGameService, addPlayerUseCase, movePlayer);
    }

    private MovePlayerUseCase movePlayerUseCase(ConsolePresenter presenter, Players players) {
        MovePlayer movePlayer = new MovePlayer(players, presenter);
        return new RollAndMove(diceRoller(), movePlayer);
    }
}
