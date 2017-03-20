import brainfuck.BrainFuckInterpreter;
import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class BrainFuckInterpreterTest {

    @Test
    public void basicCase() {
        StringConsumer outputConsumer = new StringConsumer();
        String program = "+++>+++-.<.[-.]";
        BrainFuckInterpreter interpreter = new BrainFuckInterpreter(outputConsumer);
        interpreter.run(program);
        assertEquals("23210", outputConsumer.getResult());
    }

    @Test
    public void singleOp() {
        StringConsumer outputConsumer = new StringConsumer();
        String program = "+";
        BrainFuckInterpreter interpreter = new BrainFuckInterpreter(outputConsumer);
        interpreter.run(program);
        assertEquals(outputConsumer.getResult(), "");
    }

    @Test
    public void nestedLoop() {
        StringConsumer outputConsumer = new StringConsumer();
        String program = "+++++.[--[-.]]++.[-.]";
        BrainFuckInterpreter interpreter = new BrainFuckInterpreter(outputConsumer);
        interpreter.run(program);
        assertEquals(outputConsumer.getResult(), "5210210");
    }

    @Test
    public void singleOpOutput() {
        StringConsumer outputConsumer = new StringConsumer();
        String program = ".";
        BrainFuckInterpreter interpreter = new BrainFuckInterpreter(outputConsumer);
        interpreter.run(program);
        assertEquals(outputConsumer.getResult(), "0");
    }

    class StringConsumer implements Consumer<String> {
        String result = "";

        String getResult() {
            return result;
        }

        public void accept(String item) {
            result += item;
        }
    }
}
