package Java412;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Implementation of MyArrayList.
 *
 * @param <T> the type of elements returned by the iterator
 */
public class MyArrayList<T> implements MyArray<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MULTIPLIER = 2;
    private int pointer = 0;

    /**
     * Constructs an empty list with default size.
     */
    public MyArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list of specified size.
     *
     * @param capacity size of a new MyArrayList
     */
    public MyArrayList(int capacity) {

        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0");

        this.array = (T[]) new Object[capacity];

    }

    /**
     * Add element into list.
     *
     * @param element inserted element
     */
    @Override
    public void add(T element) {

        if (pointer >= array.length)
            resize();

        array[pointer] = element;
        pointer++;

    }

    /**
     * Insert an element into list at the specified position.
     *
     * @param index position to insert element
     * @param element inserted element
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void insert(int index, T element) {

        if (index == pointer) {
            add(element);

            return;
        }

        checkIndexBounds(index);
        if (pointer + 1 >= array.length)
            resize();

        System.arraycopy(array, index, array, index + 1, pointer - 1);
        array[index] = element;
        pointer++;

    }

    /**
     * Get element from list at the specified position.
     *
     * @param index position to get from list if it exists
     * @return returns element by index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T get(int index) {

        checkIndexBounds(index);

        return array[index];

    }

    /**
     * Delete an element from list.
     * Shifts any subsequent elements to the left.
     *
     * @param index position to remove element in list if it exists
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void remove(int index) {

        checkIndexBounds(index);
        System.arraycopy(array, index + 1, array, index, pointer - index - 1);
        pointer--;
        array[pointer] = null;

    }

    /**
     * Removes all elements from list.
     */
    @Override
    public void clear() {

        Arrays.fill(array, null);
        pointer = 0;

    }

    /**
     * Sorts the list according to the order of passed comparator.
     *
     * @param comparator used to compare list elements
     * @throws NullPointerException if the list contains {@code null} elements
     */
    @Override
    public void sort(Comparator<? super T> comparator) {
        quickSort(0, pointer - 1, comparator);
    }

    private void quickSort(int leftArrayBound, int rightArrayBound, Comparator comparator) {

        if (leftArrayBound >= rightArrayBound)
            return;

        int middle = leftArrayBound + ((rightArrayBound - leftArrayBound) / 2);
        T pivot = array[middle];

        int leftBound = leftArrayBound;
        int rightBound = rightArrayBound;

        while (leftBound <= rightBound) {

            while (comparator.compare(array[leftBound], pivot) < 0) {
                leftBound++;
            }

            while (comparator.compare(array[rightBound], pivot) > 0) {
                rightBound--;
            }

            if (leftBound <= rightBound) {
                T temp = array[leftBound];
                array[leftBound] = array[rightBound];
                array[rightBound] = temp;
                leftBound++;
                rightBound--;
            }

        }

        if (leftArrayBound < rightBound) {
            quickSort(leftArrayBound, rightBound, comparator);
        }

        if (rightArrayBound > leftBound) {
            quickSort(rightBound, leftArrayBound, comparator);
        }

    }

    /**
     * Returns the number of elements in list.
     *
     * @return the number of elements in list
     */
    @Override
    public int size() {
        return pointer;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= pointer)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + pointer);
    }

    private void resize() {

        T[] newArray = (T[]) new Object[array.length * MULTIPLIER];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;

    }

    /**
     * Creates an Iterator with current list values.
     *
     * @return returns new Iterator with list values
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(array);
    }

}
