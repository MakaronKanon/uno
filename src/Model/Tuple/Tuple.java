package Model.Tuple;

/**
 * Representation of a 2-tuple. A left and a right value.
 * Class is immutable.
 * @param <T1> type of left value.
 * @param <T2> type of right value.
 */
public class Tuple<T1,T2> {
    private final T1 left;
    private final T2 right;

    /**
     * Creates a 2-tuple.
     * @param left value.
     * @param right value.
     */
    public Tuple(T1 left, T2 right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return the left value.
     */
    public T1 getLeft() {
        return left;
    }

    /**
     * @return the right value.
     */
    public T2 getRight() {
        return right;
    }
}