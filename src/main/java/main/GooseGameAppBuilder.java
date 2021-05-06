package main;

import console.ConsolePresenter;
import player.Players;
import usecase.*;

import java.util.HashMap;
import java.util.Scanner;

public class GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        Scanner inputScanner = new Scanner(System.in);
        return () -> inputScanner.nextLine();
    }

    protected OutputBoundary getOutputBoundary() {
        return string -> System.out.println(string);
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        ConsolePresenter presenter = new ConsolePresenter(outputBoundary);
        Players players = new Players();

        UseCaseDispatcher useCaseDispatcher = useCaseDispatcher(outputBoundary, presenter, players);

        return new GooseGameApp(inputBoundary, useCaseDispatcher);
    }

    private UseCaseDispatcher useCaseDispatcher(OutputBoundary outputBoundary, ConsolePresenter presenter, Players players) {
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(players, outputBoundary);
        MovePlayerUseCase movePlayerUseCase = new MovePlayerUseCase(players, presenter);
        ResetGameUseCase resetGameUseCase = new ResetGameUseCase(players);

        HashMap<String, UseCase> useCaseMap = new HashMap<>();
        useCaseMap.put("add", addPlayerUseCase);
        useCaseMap.put("move", movePlayerUseCase);
        useCaseMap.put("reset", resetGameUseCase);

        return new UseCaseDispatcher(useCaseMap);
    }
}
