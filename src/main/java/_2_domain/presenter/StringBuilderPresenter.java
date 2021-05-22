package _2_domain.presenter;

import _2_domain.player.Position;

import java.util.HashMap;

public class StringBuilderPresenter {
    private final Output output;

    private StringBuilder outputBuilder;

    public StringBuilderPresenter(Output output) {
        this.output = output;
    }

    public void init() {
        this.outputBuilder = new StringBuilder();
    }

    public void append(String string) {
        outputBuilder.append(string);
    }

    public void writeOutput() {
        output.writeOutputLine(outputBuilder.toString());
    }

    public String positionName(Position position) {
        if (position.isOverTheVictory()) return Position.WIN.value();

        HashMap<Position, String> names = new HashMap<>();
        names.put(Position.START, "Start");
        names.put(Position.BRIDGE, "The Bridge");

        String name = names.getOrDefault(position, position.value());
        String gooseSuffix = position.hasTheGoose() ? ", The Goose." : "";
        return name + gooseSuffix;
    }

}
