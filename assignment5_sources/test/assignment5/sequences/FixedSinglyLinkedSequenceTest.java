package assignment5.sequences;

public class FixedSinglyLinkedSequenceTest extends FixedSequenceTest<FixedSinglyLinkedSequence<Integer>> {
    @Override
    protected FixedSinglyLinkedSequence<Integer> newInstance(int maxSize) {
        return new FixedSinglyLinkedSequence<>(maxSize);
    }
}
