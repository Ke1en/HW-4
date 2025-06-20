package Java412;

import java.util.Comparator;

public class Application {
    public static void main(String[] args) {

        MyArray<Integer> listInt = new MyArrayList<>(5);
        listInt.add(4);
        listInt.add(17);
        listInt.add(8);
        listInt.add(10);

        listInt.remove(3);

        listInt.insert(1, 25);

        listInt.sort(Comparator.naturalOrder());

        for (int i = 0; i < listInt.size(); i++) {
            System.out.println(listInt.get(i));
        }

        for (Integer i : listInt) {
            System.out.println("?" + i);
        }

        listInt.clear();

        for (int i = 0; i < listInt.size(); i++) {
            System.out.println(listInt.get(i));
        }

    }

}
