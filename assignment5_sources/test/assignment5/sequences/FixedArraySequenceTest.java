package assignment5.sequences;

public class FixedArraySequenceTest extends FixedSequenceTest<FixedArraySequence<Integer>> {
    @Override
    protected FixedArraySequence<Integer> newInstance(int maxSize) {
        return new FixedArraySequence<>(maxSize);
    }
}
