package assignment5.sequences;

public class FixedDoublyLinkedSequenceTest extends FixedSequenceTest<FixedDoublyLinkedSequence<Integer>> {
    @Override
    protected FixedDoublyLinkedSequence<Integer> newInstance(int maxSize) {
        return new FixedDoublyLinkedSequence<>(maxSize);
    }
}
