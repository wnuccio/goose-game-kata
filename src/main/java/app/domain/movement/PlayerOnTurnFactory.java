package app.domain.movement;

import app.domain.player.Dice;
import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.presenter.Output;
import app.domain.presenter.StringBuilderPresenter;

public class PlayerOnTurnFactory {

    private final Output output;

    public PlayerOnTurnFactory(Output output) {
        this.output = output;
    }

    public PlayerOnTurn createPlayerOnTurn(Player player, Dice dice) {
        StringBuilderPresenter presenter = new StringBuilderPresenter(output);
        Movements movements = new Movements(presenter);
        return new PlayerOnTurn(player, dice, movements);
    }
}
