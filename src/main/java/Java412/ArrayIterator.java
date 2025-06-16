package Java412;

import java.util.*;

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
    private final T[] values;

    /**
     * Instantiates a new Array iterator.
     *
     * @param values the values
     */
    ArrayIterator(T[] values) {

        ArrayList<T> nonNullValues = new ArrayList<>();
        for (T value : values) {
            if (value != null) {
                nonNullValues.add(value);
            }
        }

        this.values = (T[]) nonNullValues.toArray();

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
