package main;

import boundary.ConsolePresenter;
import boundary.MoveCommandParser;
import boundary.OutputBoundary;
import boundary.RandomDice;
import usecase.UseCase;
import usecase.UseCaseDispatcher;
import usecase.add_player.AddPlayerUseCase;
import usecase.add_player.Players;
import usecase.move_player.Dice;
import usecase.move_player.MovePlayer;
import usecase.move_player.MovePlayerUseCase;
import usecase.move_player.RollAndMove;
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

    protected Dice dice() {
        return new RandomDice(1, 1);
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
        MovePlayerUseCase movePlayer = movePlayerUseCase(presenter, players, dice);
        MoveCommandParser moveCommandParser = new MoveCommandParser(dice);
        ResetGameUseCase resetGameUseCase = new ResetGameUseCase(players);

        HashMap<String, UseCase> useCaseMap = new HashMap<>();
        useCaseMap.put("add", addPlayerUseCase);
        useCaseMap.put("reset", resetGameUseCase);


        return new UseCaseDispatcher(useCaseMap, movePlayer, moveCommandParser);
    }

    private MovePlayerUseCase movePlayerUseCase(ConsolePresenter presenter, Players players, Dice dice) {
        MovePlayer movePlayer = new MovePlayer(players, dice, presenter);
        return new RollAndMove(dice, movePlayer);
    }
}
