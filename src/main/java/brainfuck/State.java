package brainfuck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class State {
    private final List<Byte> state = new ArrayList<>();
    private int pointerIndex = 0;

    {
        state.add((byte) 0);
    }

    public byte getCurrentByte() {
        return state.get(pointerIndex);
    }

    public byte setCurrentByte(byte currentByteValue) {
        return state.set(pointerIndex, currentByteValue);
    }


    public void movePointerRight() {
        pointerIndex++;
        if (pointerIndex == state.size()) {
            state.add((byte) 0);
        }
    }

    public void movePointerLeft() {
        if (pointerIndex == 0) {
            throw new IllegalStateException();
        }
        pointerIndex--;
    }

    public void decrementCurrentByte() {
        setCurrentByte((byte) (getCurrentByte() - 1));
    }

    public void incrementCurrentByte() {
        setCurrentByte((byte) (getCurrentByte() + 1));
    }

    @Override
    public String toString() {
        return "State{" +
                "state=" + Arrays.toString(state.toArray()) +
                ", pointerIndex=" + pointerIndex +
                '}';
    }
}
