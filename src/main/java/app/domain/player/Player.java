package app.domain.player;

import app.domain.movement.Dice;
import app.domain.movement.Movement;
import app.domain.movement.PlayerOnTurn;
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

    private void moveByDice(Dice dice) {
        Position start = this.position;
        this.position = this.position.plus(dice);
        FirstMovement movement = new FirstMovement(start, this.position);
        notifyMovement(movement);
    }

    private void correctPositionWithBouncing() {
        if (this.position.isBeyondWin()) {
            this.position = this.position.bounced();
            BouncingMovement movement = new BouncingMovement(this.position.board(), this.position);
            notifyMovement(movement);
        }
    }

    private void notifyMovement(Movement movement) {
        observers.forEach(o -> o.playerPositionChanged(movement));
    }

    public void applyRuleOnCurrentPosition(PlayerOnTurn playerOnTurn) {
        position.applyAttachedRule(playerOnTurn);
    }
}
