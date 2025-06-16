package Java412;

import java.util.Comparator;

/**
 * The interface for MyArrayList.
 *
 * @param <T> the type of elements returned by the iterator
 */
public interface MyArray<T> extends Iterable<T> {
    /**
     * Add element into list.
     *
     * @param e inserted element
     */
    void add(T e);

    /**
     * Insert an element into list at the specified position.
     *
     * @param index position to insert element
     * @param e     inserted element
     */
    void insert(int index, T e);

    /**
     * Get element from list at the specified position.
     *
     * @param index position to get from list
     * @return returns element by index
     */
    T get(int index);

    /**
     * Delete an element from list at the specified position.
     *
     * @param index position to remove element in list
     */
    void remove(int index);

    /**
     * Removes all elements from list.
     */
    void clear();

    /**
     * Returns list size.
     *
     * @return amount of elements in list
     */
    int size();

    /**
     * QuickSort implementation.
     *
     * @param comparator pass comparator parameter to sort
     */
    void sort(Comparator<? super T> comparator);
}
