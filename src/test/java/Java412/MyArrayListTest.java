package Java412;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {
    private MyArrayList<Integer> testDefaultList;
    private MyArrayList<Integer> testDefaultListWithCapacity;

    @BeforeEach
    void setUp() {

        testDefaultList = new MyArrayList<>();
        testDefaultListWithCapacity = new MyArrayList<>(32);

    }

    @AfterEach
    void tearDown() {

        testDefaultList.clear();
        testDefaultListWithCapacity.clear();

    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1,2,3,4,5'"
    })
    void add_whenElementsAdded_thenGetReturnsCorrectValue(String values) {

        for (String value : values.split(",")) {
            testDefaultList.add(Integer.parseInt(value));
            testDefaultListWithCapacity.add(Integer.parseInt(value));

            assertEquals(Integer.parseInt(value), testDefaultList.get(testDefaultList.size() - 1));
            assertEquals(Integer.parseInt(value), testDefaultListWithCapacity.get(testDefaultListWithCapacity.size() - 1));
        }

    }

    @Test
    void add_whenElementsAdded_thenDefaultCapacityIncreasedCorrectly() {

        int defaultCapacity = 16;

        for (int i = 0; i <= defaultCapacity; i++)
            testDefaultList.add(i);

        assertEquals(defaultCapacity + 1, testDefaultList.size());

    }

    @ParameterizedTest
    @CsvSource({
            "0,1",
            "1,2",
            "2,3",
            "3,4",
            "4,5",
            "5,6",
            "6,7",
            "7,8",
            "8,9",
            "9,10",
            "10,11",
            "11,12",
            "12,13",
            "13,14",
            "14,15",
            "15,16",
            "16,17"
    })
    void insert_whenElementsInserted_ThenGetCorrectValue(int initialCount, int insertValue) {

        for (int i=0; i<initialCount; i++)
            testDefaultList.add(i);

        testDefaultList.insert(0, insertValue);
        testDefaultList.insert(testDefaultList.size(), insertValue + 10);

        assertEquals(insertValue, testDefaultList.get(0));
        assertEquals(insertValue + 10, testDefaultList.get(testDefaultList.size() - 1));

    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "1",
            "2"
    })
    void remove_whenIndexIsValid_thenElementRemovedCorrectly(int removeIndex) {

        for (int i=0; i<16; i++)
            testDefaultList.add(i);

        if (removeIndex >= 0 && removeIndex < testDefaultList.size()) {
            int removedIndex = testDefaultList.get(removeIndex);
            testDefaultList.remove(removeIndex);

            assertNotEquals(removedIndex, testDefaultList.get(removeIndex));
        }

    }

    @ParameterizedTest
    @CsvSource(value = {
            "'4,5,3,8'"
    })
    void sort_whenCalled_thenListIsSorted(String values) {

        for (String value : values.split(","))
            testDefaultList.add(Integer.parseInt(value));

        testDefaultList.sort(Comparator.naturalOrder());
        assertEquals(3, testDefaultList.get(0));

        testDefaultList.sort(Comparator.reverseOrder());
        assertEquals(8, testDefaultList.get(0));

    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1,2,3,4,5'"
    })
    void clear_whenCalled_thenListIsEmpty(String values) {

        for (String value : values.split(","))
            testDefaultList.add(Integer.parseInt(value));

        testDefaultList.clear();

        assertEquals(0, testDefaultList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> testDefaultList.get(0));

    }

    @ParameterizedTest
    @CsvSource(value = {
            "'4,5,3,8'"
    })
    void add_whileIteratorHasNext_thenValuesAreCorrect(String valuesStr) {

        String[] valuesArr = valuesStr.split(",");

        for (String v : valuesArr)
            testDefaultList.add(Integer.parseInt(v));

        Iterator<Integer> iterator = testDefaultList.iterator();

        int index = 0;

        while (iterator.hasNext()) {
            assertEquals(Integer.parseInt(valuesArr[index]), iterator.next());
            index++;
        }

        assertEquals(valuesArr.length, index);

    }

    @Test
    void size_whenElementAddedOrRemoved_thenSizeIsCorrect() {

        assertEquals(0, testDefaultList.size());
        testDefaultList.add(1);
        assertEquals(1, testDefaultList.size());
        testDefaultList.add(2);
        assertEquals(2, testDefaultList.size());
        testDefaultList.remove(0);
        assertEquals(1, testDefaultList.size());
        testDefaultList.clear();
        assertEquals(0, testDefaultList.size());

    }

    @ParameterizedTest
    @CsvSource(value = {
            "'1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20'"
    })
    void add_whenElementAddedAfterListReachDefaultCapacity_thenCapacityResized(String values){

        for (String value : values.split(",")) {
            testDefaultList.add(Integer.parseInt(value));

            assertEquals(Integer.parseInt(value), testDefaultList.size());
        }

    }

    @Test
    void newMyArrayList_whenCreated_thenThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {MyArray<Integer> newList = new MyArrayList<>(0);});
    }

    @Test
    void get_whenInvalidIndex_thenThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testDefaultList.get(-1));
    }

    @Test
    void insert_WhenInvalidIndex_thenThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testDefaultList.insert(1, 1));
    }

    @Test
    void insert_whenInvalidIndexIsNegative_thenThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testDefaultList.insert(-1, 1));
    }

    @Test
    void remove_whenListIsEmptry_thenThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testDefaultList.remove(0));
    }

}
