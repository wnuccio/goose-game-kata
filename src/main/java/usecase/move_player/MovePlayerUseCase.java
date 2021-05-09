package usecase.move_player;

import usecase.Presenter;
import usecase.add_player.Players;

public class MovePlayerUseCase {
    private Players players;
    private Dice dice;
    private Presenter presenter;

    public MovePlayerUseCase(Players players, Dice dice, Presenter presenter) {
        this.players = players;
        this.dice = dice;
        this.presenter = presenter;
    }

    public void acceptCommand(MoveCommand command) {
        String player = command.player();

        if ( ! players.contains(player)) {
            presenter.presentNoSuchPlayerError(player);
            return;
        }

        SimpleMovement firstMovement = buildMovementFor(player, diceFrom(command));

        Movement movement = repeatMovementOnSpecialPositions(firstMovement);

        players.setPositionOf(player, movement.toPosition());

        presenter.presentMovement(movement);
    }

    private Movement repeatMovementOnSpecialPositions(SimpleMovement movement) {
        if (movement.endsOnBridge()) {
            return new JumpOnBridgeMovement(movement);
        }

        if (movement.endsOnGoose()) {
            return new RepeatOnGooseMovement(movement);
        }

        return movement;
    }

    private SimpleMovement buildMovementFor(String player, Dice dice) {
        return SimpleMovement
                .of(player)
                .from(players.positionOf(player))
                .givenRoll(dice)
                .end();
    }

    private Dice diceFrom(MoveCommand command) {
        if (command.dice().isPresent())
            return command.dice().get();
        return dice.roll();
    }

}
