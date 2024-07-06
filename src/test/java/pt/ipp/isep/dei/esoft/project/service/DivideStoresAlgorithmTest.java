package pt.ipp.isep.dei.esoft.project.service;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Pair;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Divide stores algorithm test.
 */
class DivideStoresAlgorithmTest {

    /**
     * Gets total sum.
     */
    @Test
    void getTotalSum() {
        Integer[] listA = {9, 28, 32, 11, 5, 2, 69, 20, 12, 35,  3,  7, 11,  2,  5};
//        Integer[] listB = {1,  2,  3,  4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15};
        assertEquals(251,DivideStoresAlgorithm.getTotalSum(listA));
    }

    /**
     * Gets binary.
     */
    @Test
    void getBinary() {
        Integer num = 4;
        assertEquals("100",DivideStoresAlgorithm.getBinary(num));
    }

    /**
     * Get partial sum.
     */
    @Test
    void getPartialSum(){
        Integer[] listA = {9, 28, 32, 11, 5, 2, 69, 20, 12, 35,  3,  7, 11,  2,  5};
        String binary = "1011";
        assertEquals(48,DivideStoresAlgorithm.getPartialSum(binary, listA));

    }

    /**
     * Divide stores.
     */
    @Test
    void divideStores(){
        Integer[] listA = {9, 28, 32, 11, 5, 2, 69, 20, 12, 35,  3,  7, 11,  2,  5};
        Integer[] listB = {1,  2,  3,  4, 5, 6,  7,  8,  9, 10, 11, 12, 13, 14, 15};
        List<Pair<Integer, Integer>>expectedA = new ArrayList<>();
        expectedA.add(new Pair<Integer, Integer>(9, 1));
        expectedA.add(new Pair<Integer, Integer>(28, 2));
        expectedA.add(new Pair<Integer, Integer>(32, 3));
        expectedA.add(new Pair<Integer, Integer>(11, 4));
        expectedA.add(new Pair<Integer, Integer>(5, 5));
        expectedA.add(new Pair<Integer, Integer>(0, 0));
        expectedA.add(new Pair<Integer, Integer>(69, 7));
        expectedA.add(new Pair<Integer, Integer>(20, 8));
        expectedA.add(new Pair<Integer, Integer>(12, 9));
        expectedA.add(new Pair<Integer, Integer>(35, 10));
        expectedA.add(new Pair<Integer, Integer>(3, 11));
        expectedA.add(new Pair<Integer, Integer>(0, 0));
        expectedA.add(new Pair<Integer, Integer>(0, 0));
        expectedA.add(new Pair<Integer, Integer>(0, 0));
        expectedA.add(new Pair<Integer, Integer>(0, 0));

        List<Pair<Integer, Integer>> expectedB = new ArrayList<>();
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(2, 6));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(0, 0));
        expectedB.add(new Pair<Integer, Integer>(7, 12));
        expectedB.add(new Pair<Integer, Integer>(11, 13));
        expectedB.add(new Pair<Integer, Integer>(2, 14));
        expectedB.add(new Pair<Integer, Integer>(5, 15));
        Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> expected = new Pair<>(expectedA,expectedB);
        Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> sublists = DivideStoresAlgorithm.divideStoresConsole(listA, listB);
//        Boolean a = ("1" == DivideStoresAlgorithm.divideStores(listA, listB)[0]);
//        Boolean b = ("11101" == DivideStoresAlgorithm.divideStores(listA, listB)[1]);
//        Boolean c = (a && b);
        assertEquals(expected,sublists);

    }
}