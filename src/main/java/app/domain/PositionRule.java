package app.domain;

import app.domain.player.Player;

public interface PositionRule {
    void applyTo(Player player);
}
