package usecase.move_player;

import java.util.function.Supplier;

public class GenericRepeatedMovement extends RepeatedMovement {
    private int intermediatePosition;
    private Supplier<Integer> finalPositionFunction;

    public GenericRepeatedMovement(
            SimpleMovement previousMovement,
            MovementType type,
            int intermediatePosition, Supplier<Integer> finalPositionFunction) {

        super(previousMovement, type);
        this.finalPositionFunction = finalPositionFunction;
        this.intermediatePosition = intermediatePosition;
    }

    public static GenericRepeatedMovementBuilder after(SimpleMovement previousMovement) {
        return new GenericRepeatedMovementBuilder(previousMovement);
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
