package app.domain.movement;

import app.domain.presenter.Output;
import app.domain.presenter.StringBuilderPresenter;

public class MovementsRecorderFactory {

    private final Output output;

    public MovementsRecorderFactory(Output output) {
        this.output = output;
    }

    public MovementsRecorder createMovementsRecorder() {
        StringBuilderPresenter presenter = new StringBuilderPresenter(output);
        return new MovementsRecorder(presenter);
    }
}
