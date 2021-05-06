package usecase;

import player.Players;

public class MovePlayerUseCase implements UseCase {
    private Players players;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, Presenter presenter) {
        this.players = players;
        this.presenter = presenter;
    }

    public void acceptCommand(String commandLine) {
        MoveCommand command = new MoveCommand(commandLine);

        String player = command.playerName();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        int firstDice = command.firstDice();
        int secondDice = command.secondDice();

        int prevPosition = players.positionOf(player);
        int newPosition = newPositionAfterRoll(player, firstDice, secondDice);

        players.setPositionOf(player, newPosition);

        Movement movement = Movement.of(player)
                .givenRoll(firstDice, secondDice)
                .from(prevPosition).to(newPosition);

        if (playerHasReachedWinningPosition(newPosition))
            movement.beVictory();

        presenter.presentMovement(movement);
    }

    private int newPositionAfterRoll(String player, int firstDice, int secondDice) {
        int totalRoll = firstDice + secondDice;
        return players.positionOf(player) + totalRoll;
    }

    private boolean playerHasReachedWinningPosition(int newPosition) {
        return newPosition == 63;
    }
}
