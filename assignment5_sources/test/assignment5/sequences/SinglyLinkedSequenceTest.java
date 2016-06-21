package assignment5.sequences;

public class SinglyLinkedSequenceTest extends SequenceTest<SinglyLinkedSequence<Integer>> {
    @Override
    protected SinglyLinkedSequence<Integer> newInstance() {
        return new SinglyLinkedSequence<>();
    }
}
