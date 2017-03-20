package brainfuck.operators;

import brainfuck.State;

public abstract class AbstractOperatorNode {
    protected AbstractOperatorNode next;

    public AbstractOperatorNode() {
    }

    public AbstractOperatorNode(AbstractOperatorNode next) {
        this.next = next;
    }

    public AbstractOperatorNode getNext() {
        return next;
    }

    public void setNext(AbstractOperatorNode next) {
        this.next = next;
    }

    public abstract void run(State state);
}
