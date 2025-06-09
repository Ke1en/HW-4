package Java412;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<T> implements MyArray<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MULTIPLIER = 2;
    private int pointer = 0;

    public MyArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {

        if (capacity <= 0)
            throw new IllegalArgumentException("Capacity must be greater than 0");

        this.array = (T[]) new Object[capacity];

    }

    @Override
    public void add(T element) {

        if (pointer >= array.length)
            resize();

        array[pointer] = element;
        pointer++;

    }

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

    @Override
    public T get(int index) {
        return array[index];
    }

    @Override
    public T remove(int index) {

        checkIndexBounds(index);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, pointer - index - 1);
        pointer--;
        array[pointer] = null;

        return element;

    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        pointer = 0;
    }

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

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>(array);
    }

}
