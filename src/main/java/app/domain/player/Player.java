package app.domain.player;

import app.domain.rules.bouncing.BouncingMovement;
import app.domain.rules.first.FirstMovement;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private final String name;
    private Position position;
    private final Collection<PlayerObserver> observers;

    public Player(String name, Position position) {
        this.name = name;
        this.position = position;
        this.observers = new ArrayList<>();
    }

    public String name() {
        return name;
    }

    public Player position(Position position) {
        this.position = position;
        return this;
    }

    public void applyMovement(Movement movement) {
        position = movement.finalPosition();
        notifyMovement(movement);
    }

    public Position position() {
        return position;
    }

    public void addObserver(PlayerObserver observer) {
        this.observers.add(observer);
    }

    public void moveByDiceConsideringBouncing(Dice dice) {
        moveByDice(dice);
        correctPositionWithBouncing();
    }

    public void applyRuleOnCurrentPosition(PlayerOnTurn playerOnTurn) {
        position.applyAttachedRule(playerOnTurn);
    }

    private void moveByDice(Dice dice) {
        FirstMovement movement = new FirstMovement(position, position.plus(dice));
        applyMovement(movement);
    }

    private void correctPositionWithBouncing() {
        if (!this.position.isBeyondWin()) return;

        BouncingMovement movement = new BouncingMovement(this.position.board(), this.position.bounced());
        applyMovement(movement);
    }

    private void notifyMovement(Movement movement) {
        observers.forEach(o -> o.playerPositionChanged(movement));
    }
}
