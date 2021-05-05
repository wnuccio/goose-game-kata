package usecase;

import player.Players;

import static java.lang.String.valueOf;

public class MovePlayerUseCase {
    private Players players;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    public void acceptCommand(String commandLine) {
        MoveCommand command = new MoveCommand(commandLine);

        String playerName = command.playerName();
        int firstDice = command.firstDice();
        int secondDice = command.secondDice();

        int prevPosition = players.positionOf(playerName);
        int newPosition = players.moveOnRoll(playerName, firstDice, secondDice);

        Movement movement = Movement.of(playerName)
                .givenRoll(firstDice, secondDice)
                .from(prevPosition).to(newPosition);

        if (newPosition == 63)
            movement.beVictory();

        presenter.presentMovement(movement);
    }
}