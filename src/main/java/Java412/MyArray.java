package Java412;

import java.util.Comparator;

public interface MyArray<T> extends Iterable<T> {
    void add(T e);
    void insert(int index, T e);
    T get(int index);
    T remove(int index);
    void clear();
    int size();
    void sort(Comparator<? super T> comparator);
}
