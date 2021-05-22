package _1_actions.player.move.rules.first;

import _2_domain.presenter.PlayerTurnView;
import _2_domain.presenter.StringBuilderPresenter;

import static java.lang.String.format;

public class FirstMovementPresenter {
    public void present(FirstMovement movement, StringBuilderPresenter presenter, PlayerTurnView playerTurnView) {
        int firstDice = playerTurnView.firstDice();
        int secondDice = playerTurnView.secondDice();
        String playerRolls = format("%s rolls %s, %s" + ". ", playerTurnView.player(), firstDice, secondDice);
        String playerMoves = format("%s moves from %s to %s", playerTurnView.player(), presenter.positionName(movement.startPosition()), presenter.positionName(movement.finalPosition()));
        String playerWins = movement.isVictory() ? format(". %s Wins!!", playerTurnView.player()) : "";

        presenter.append(playerRolls + playerMoves + playerWins);
    }
}
