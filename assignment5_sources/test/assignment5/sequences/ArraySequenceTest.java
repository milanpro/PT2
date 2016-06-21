package assignment5.sequences;

public class ArraySequenceTest extends SequenceTest<ArraySequence<Integer>> {
    @Override
    protected ArraySequence<Integer> newInstance() {
        return new ArraySequence<>();
    }
}
