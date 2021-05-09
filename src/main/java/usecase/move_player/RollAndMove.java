package usecase.move_player;

public class RollAndMove extends MovePlayerUseCase {
    private Dice dice;
    private MovePlayerUseCase movePlayer;

    public RollAndMove(Dice dice, MovePlayerUseCase movePlayer) {
        super(null, null, null);
        this.dice = dice;
        this.movePlayer = movePlayer;
    }

    @Override
    public void acceptCommand(MoveCommand command) {
        if (command.dice().isPresent()) {
            movePlayer.acceptCommand(command);
            return;
        }

        MoveCommand commandWithDice = new MoveCommand(command.player(), dice.roll());
        movePlayer.acceptCommand(commandWithDice);
    }
}
