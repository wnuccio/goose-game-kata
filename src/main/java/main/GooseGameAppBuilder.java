package main;

import boundary.ConsolePresenter;
import boundary.MoveCommandParser;
import boundary.OutputBoundary;
import boundary.RealDice;
import usecase.UseCase;
import usecase.UseCaseDispatcher;
import usecase.add_player.AddPlayerUseCase;
import usecase.add_player.Players;
import usecase.move_player.*;
import usecase.reset_game.ResetGameUseCase;

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

    protected DiceRoller diceRoller() {
        throw new UnsupportedOperationException();
    }

    protected Dice dice() {
        return new RealDice(1, 1);
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
        Dice dice = dice();
        MovePlayerUseCase movePlayer = movePlayerUseCase(presenter, players);
        MoveCommandParser moveCommandParser = new MoveCommandParser(dice);
        ResetGameUseCase resetGameUseCase = new ResetGameUseCase(players);

        HashMap<String, UseCase> useCaseMap = new HashMap<>();
        useCaseMap.put("add", addPlayerUseCase);
        useCaseMap.put("reset", resetGameUseCase);


        return new UseCaseDispatcher(useCaseMap, movePlayer, moveCommandParser);
    }

    private MovePlayerUseCase movePlayerUseCase(ConsolePresenter presenter, Players players) {
        MovePlayer movePlayer = new MovePlayer(players, presenter);
        return new RollAndMove(diceRoller(), movePlayer);
    }
}
