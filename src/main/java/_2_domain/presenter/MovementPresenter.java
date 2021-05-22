package _2_domain.presenter;

import _1_actions.player.move.rules.bouncing.BouncingMovement;
import _1_actions.player.move.rules.bridge.JumpOnBridgeMovement;
import _1_actions.player.move.rules.first.FirstMovement;
import _1_actions.player.move.rules.goose.GooseMovement;
import _1_actions.player.move.rules.switchrule.SwitchMovement;
import _2_domain.player.Position;

import java.util.HashMap;

import static java.lang.String.format;

public class MovementPresenter {
    private final Output output;

    private PlayerTurnView playerTurnView;
    private StringBuilder outputBuilder;

    public MovementPresenter(Output output) {
        this.output = output;
    }

    public void init(PlayerTurnView playerTurnView) {
        this.playerTurnView = playerTurnView;
        this.outputBuilder = new StringBuilder();
    }

    public void writeOutput() {
        output.writeOutputLine(outputBuilder.toString());
    }

    public void presentFirstMovement(FirstMovement movement) {
        int firstDice = playerTurnView.firstDice();
        int secondDice = playerTurnView.secondDice();
        String playerRolls = format("%s rolls %s, %s" + ". ", player(), firstDice, secondDice);
        String playerMoves = format("%s moves from %s to %s", player(), positionName(movement.startPosition()), positionName(movement.finalPosition()));
        String playerWins = movement.isVictory() ? format(". %s Wins!!", player()) : "";

        outputBuilder.append(playerRolls + playerMoves + playerWins);
    }

    public void presentJumpOnBridge(JumpOnBridgeMovement movement) {
        String playerJumps = format(". %s jumps to 12", player());

        outputBuilder.append(playerJumps);
    }

    public void presentBouncing(BouncingMovement movement) {
        String playerBounces = format(". %s bounces! %s returns to %s",
                player(),
                player(),
                positionName(movement.finalPosition()));

        outputBuilder.append(playerBounces);
    }

    public void presentSwitchMovement(SwitchMovement movement) {
        String playerSwitch = format(". On %s there is %s, who returns to %s",
                positionName(movement.startPosition()),
                movement.switchedPlayer(),
                positionName(movement.finalPosition()));

        outputBuilder.append(playerSwitch);
    }

    public void presentGooseMovement(GooseMovement movement) {
        String playerMovesAgain = format(" %s moves again and goes to %s",
                player(),
                positionName(movement.finalPosition()));

        outputBuilder.append(playerMovesAgain);
    }

    private String positionName(Position position) {
        if (position.isOverTheVictory()) return Position.WIN.value();

        HashMap<Position, String> names = new HashMap<>();
        names.put(Position.START, "Start");
        names.put(Position.BRIDGE, "The Bridge");

        String name = names.getOrDefault(position, position.value());
        String gooseSuffix = position.hasTheGoose() ? ", The Goose." : "";
        return name + gooseSuffix;
    }

    private String player() {
        return playerTurnView.player();
    }
}
