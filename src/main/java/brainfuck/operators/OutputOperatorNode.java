package brainfuck.operators;

import brainfuck.State;

import java.util.function.Consumer;

public class OutputOperatorNode extends AbstractOperatorNode {

    private Consumer<String> outputConsumer;

    private OutputOperatorNode() {
    }

    public OutputOperatorNode(Consumer<String> outputConsumer) {
        this.outputConsumer = outputConsumer;
    }

    public void run(State state) {
        outputConsumer.accept(String.valueOf(state.getCurrentByte()));
    }
}

