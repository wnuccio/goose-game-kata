package _2_domain.rules.first;

import _2_domain.movement.PlayerTurnView;
import _2_domain.player.Position;
import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class FirstMovementPresenter {
    public void present(FirstMovement movement, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        String player = playerTurnView.player();
        int firstDice = playerTurnView.firstDice();
        int secondDice = playerTurnView.secondDice();
        String playerRolls = format("%s rolls %s, %s" + ". ", player, firstDice, secondDice);
        Position start = movement.startPosition();
        Position end = movement.finalPosition();
        String playerMoves = format("%s moves from %s to %s", player, start.name(), end.name());
        String playerWins = end.isWin() ? format(". %s Wins!!", player) : "";

        presenter.append(playerRolls + playerMoves + playerWins);
    }
}
