package assignment5.sequences;

public class DoublyLinkedSequenceTest extends SequenceTest<DoublyLinkedSequence<Integer>> {
    @Override
    protected DoublyLinkedSequence<Integer> newInstance() {
        return new DoublyLinkedSequence<>();
    }
}
