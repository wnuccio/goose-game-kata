package usecase.move_player;

import java.util.function.Supplier;

public class GenericRepeatedMovement extends RepeatedMovement {

    private Supplier<Integer> finalPositionFunction;
    private int intermediatePosition;

    public GenericRepeatedMovement(
            SimpleMovement previousMovement,
            MovementType type,
            Supplier<Integer> finalPositionFunction,
            int intermediatePosition) {

        super(previousMovement, type);
        this.finalPositionFunction = finalPositionFunction;
        this.intermediatePosition = intermediatePosition;
    }

    @Override
    public int finalPosition() {
        return finalPositionFunction.get();
    }

    @Override
    public int intermediatePosition() {
        return intermediatePosition;
    }
}
