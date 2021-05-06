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

        Movement movement = computeMovement(player, command.firstDice(), command.secondDice());

        players.setPositionOf(player, movement.toPosition());
        presenter.presentMovement(movement);
    }

    private Movement computeMovement(String player, int firstDice, int secondDice) {
        int prevPosition = players.positionOf(player);
        int newPosition = newPositionAfterRoll(player, firstDice, secondDice);

        return Movement.of(player)
                .givenRoll(firstDice, secondDice)
                .from(prevPosition).to(newPosition)
                .setVictory(isWinningPosition(newPosition));
    }

    private int newPositionAfterRoll(String player, int firstDice, int secondDice) {
        int totalRoll = firstDice + secondDice;
        return players.positionOf(player) + totalRoll;
    }

    private boolean isWinningPosition(int newPosition) {
        return newPosition == 63;
    }
}
