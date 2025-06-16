package Java412;

import java.util.Iterator;

/**
 * Array iterator for MyArrayList iteration.
 *
 * @param <T> the type of elements returned by the iterator
 */
public class ArrayIterator<T> implements Iterator<T> {
    private int index = 0;
    /**
     * The Values.
     */
    T[] values;

    /**
     * Instantiates a new Array iterator.
     *
     * @param values the values
     */
    ArrayIterator(T[] values) {
        this.values = values;
    }

    /**
     * Get element from Array.
     *
     * @return {@code true} returns if Array has more elements {@code false} returns when it was last element in Array
     */
    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    /**
     * Get current element and increment index by 1.
     *
     * @return returns current element
     */
    @Override
    public T next() {
        return values[index++];
    }
}
