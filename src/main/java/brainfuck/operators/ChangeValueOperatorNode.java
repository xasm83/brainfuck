package brainfuck.operators;

import brainfuck.State;

public class ChangeValueOperatorNode extends AbstractOperatorNode {

    private boolean decrement;

    public ChangeValueOperatorNode(boolean decrement) {
        this.decrement = decrement;
    }

    public void run(State state) {
        if (decrement) {
            state.decrementCurrentByte();
        } else {
            state.incrementCurrentByte();
        }
    }
}
