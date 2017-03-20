package brainfuck;

import brainfuck.operators.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BrainFuckInterpreter {
    private Deque<AbstractOperatorNode> loopOperatorStack = new LinkedList<>();
    private static final String KEY_WORDS_PATTERN = "[<>+-.\\[\\]]";
    private Consumer<String> outputConsumer;

    public BrainFuckInterpreter(Consumer<String> outputConsumer) {
        this.outputConsumer = outputConsumer;
    }

    public void run(String brainfuckProgram) {
        if (brainfuckProgram.isEmpty()) {
            return;
        }

        Pattern pattern = Pattern.compile(KEY_WORDS_PATTERN);
        Matcher matcher = pattern.matcher(brainfuckProgram);

        AbstractOperatorNode root = new EmptyOperatorNode();
        AbstractOperatorNode current = root;

        while (matcher.find()) {
            String operator = matcher.group();
            AbstractOperatorNode newNode = createNode(operator);
            current.setNext(newNode);

            if (newNode instanceof LoopStartOperatorNode) {
                loopOperatorStack.push(newNode);
                AbstractOperatorNode loopStart = new EmptyOperatorNode();
                ((LoopStartOperatorNode) newNode).setLoop(loopStart);
                current = loopStart;
            } else if (newNode instanceof LoopEndOperatorNode) {
                current = loopOperatorStack.pop();
            } else {
                current = newNode;
            }
        }

        AbstractOperatorNode node = root;
        State state = new State();
        while (node != null) {
            node.run(state);
            node = node.getNext();
        }
    }

    private AbstractOperatorNode createNode(String operator) {
        AbstractOperatorNode node;
        switch (operator) {
            case "+":
                node = new ChangeValueOperatorNode(false);
                break;
            case "-":
                node = new ChangeValueOperatorNode(true);
                break;
            case ".":
                node = new OutputOperatorNode(outputConsumer);
                break;
            case ">":
                node = new ShiftOperatorNode(false);
                break;
            case "<":
                node = new ShiftOperatorNode(true);
                break;
            case "[":
                node = new LoopStartOperatorNode();
                break;
            case "]":
                node = new LoopEndOperatorNode();
                break;
            default:
                throw new IllegalStateException();
        }
        return node;
    }
}
