package app.domain.movement;

import app.domain.presenter.Output;
import app.domain.presenter.StringBuilderPresenter;

public class MovementsFactory {

    private final Output output;

    public MovementsFactory(Output output) {
        this.output = output;
    }

    public Movements createMovements() {
        StringBuilderPresenter presenter = new StringBuilderPresenter(output);
        return new Movements(presenter);
    }
}
