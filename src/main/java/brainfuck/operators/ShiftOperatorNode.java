package brainfuck.operators;

import brainfuck.State;

public class ShiftOperatorNode extends AbstractOperatorNode {
    private boolean shiftLeft;

    public ShiftOperatorNode(boolean shiftLeft) {
        this.shiftLeft = shiftLeft;
    }

    public void run(State state) {
        if (shiftLeft) {
            state.movePointerLeft();
        } else {
            state.movePointerRight();
        }
    }
}
