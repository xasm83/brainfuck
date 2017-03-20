package brainfuck.operators;

import brainfuck.State;

public class LoopStartOperatorNode extends AbstractOperatorNode {
    private AbstractOperatorNode loop;

    public void setLoop(AbstractOperatorNode loop) {
        this.loop = loop;
    }

    public void run(State state) {
        while (state.getCurrentByte() != 0) {
            AbstractOperatorNode currentLoopNode = loop;
            while (currentLoopNode != null) {
                currentLoopNode.run(state);
                currentLoopNode = currentLoopNode.getNext();
            }
        }
    }
}
