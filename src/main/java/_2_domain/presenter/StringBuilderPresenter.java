package _2_domain.presenter;

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
}
