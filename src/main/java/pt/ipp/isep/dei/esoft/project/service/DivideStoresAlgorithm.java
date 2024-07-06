package pt.ipp.isep.dei.esoft.project.service;

import pt.ipp.isep.dei.esoft.project.domain.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Divide stores algorithm.
 */
public class DivideStoresAlgorithm {
    private static long startTime;
    /**
     * The constant minVal.
     */
    public static String[] minVal = new String[2];

    /**
     * Divide stores pair.
     *
     * @param storeIDList          the store id list
     * @param numberPropertiesList the number properties list
     * @return the pair
     */
    public static Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> divideStoresConsole(Integer[] storeIDList, Integer[] numberPropertiesList) {
        System.out.println("Calculating Pair of Sublists. Please Wait...");
        startTime = System.currentTimeMillis();
        int n = numberPropertiesList.length;
        int total = getTotalSum(numberPropertiesList);
        minVal[1] = "0";
        minVal[0] = Integer.toString(total);
        long k = (int) (Math.pow(2,n-1));
        for (int i = 1; i < k; i++) {
            String binary = getBinary(i);
            int p = getPartialSum(binary, numberPropertiesList);
            int diff = Math.abs(total-2*p);
            if (diff<Integer.parseInt(minVal[0])){
                minVal[0]=Integer.toString(diff);
                minVal[1] = binary;
            }

        }
        Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> sublists = getPairOfSublists(minVal,numberPropertiesList,storeIDList);
        System.out.printf("Input Size: %d; Difference: %d; Time: %.3fs%n",numberPropertiesList.length,Integer.parseInt(minVal[0]),(double) getElapsedTime()/1000);
        System.out.print("Sublist A:{");
        for (Pair<Integer,Integer> pair:sublists.getLeft()) {
            System.out.printf("(%d,%d);", pair.getLeft(), pair.getRight());
        }
        System.out.print("}\nSublist B:{");
        for (Pair<Integer,Integer> pair:sublists.getRight()) {
            System.out.printf("(%d,%d);", pair.getLeft(), pair.getRight());
        }
        System.out.print("}\n");
        return sublists;
    }

    /**
     * Divide stores string.
     *
     * @param storeIDList          the store id list
     * @param numberPropertiesList the number properties list
     * @return the string
     */
    public static String divideStores(Integer[] storeIDList, Integer[] numberPropertiesList) {
        startTime = System.currentTimeMillis();
        int n = numberPropertiesList.length;
        int total = getTotalSum(numberPropertiesList);
        minVal[1] = "0";
        minVal[0] = Integer.toString(total);
        long k = (int) (Math.pow(2,n-1));
        for (int i = 1; i < k; i++) {
            String binary = getBinary(i);
            int p = getPartialSum(binary, numberPropertiesList);
            int diff = Math.abs(total-2*p);
            if (diff<Integer.parseInt(minVal[0])){
                minVal[0]=Integer.toString(diff);
                minVal[1] = binary;
            }

        }
        Pair<List<Pair<Integer, Integer>>, List<Pair<Integer, Integer>>> sublists = getPairOfSublists(minVal,numberPropertiesList,storeIDList);
        StringBuilder info = new StringBuilder();
        info.append(String.format("Input Size: %d; Difference: %d; Time: %.3fs%n", numberPropertiesList.length,Integer.parseInt(minVal[0]),(double) getElapsedTime()/1000));
        info.append("Sublist A:{");
        for (Pair<Integer,Integer> pair:sublists.getLeft()) {
            info.append(String.format("(%d,%d);", pair.getLeft(), pair.getRight()));
        }
        info.append("}\nSublist B:{");
        for (Pair<Integer,Integer> pair:sublists.getRight()) {
            info.append(String.format("(%d,%d);", pair.getLeft(), pair.getRight()));
        }
        info.append("}\n");
        return info.toString();
    }
    private static Pair<List<Pair<Integer,Integer>>,List<Pair<Integer,Integer>>> getPairOfSublists(String[] minVal, Integer[] numberPropertiesList, Integer[] storeIDList) {
        List<Pair<Integer,Integer>> leftList = new ArrayList<>();
        List<Pair<Integer,Integer>> rightList = new ArrayList<>();
        String binary = new StringBuilder(minVal[1]).reverse().toString();
        char[] binaryArray = new char[numberPropertiesList.length];
        int temp = 0;
        for (char c:binary.toCharArray()) {
            binaryArray[temp] = c;
            temp++;
        }
        for (int j = 0; j < numberPropertiesList.length; j++) {
            if (binaryArray[j] == '1') {
                leftList.add(new Pair<>(storeIDList[j],numberPropertiesList[j]));
                rightList.add(new Pair<>(0,0));
            }
            else  {
                leftList.add(new Pair<>(0,0));
                rightList.add(new Pair<>(storeIDList[j],numberPropertiesList[j]));
            }
        }
        return new Pair<>(leftList,rightList);
    }

    /**
     * Gets binary.
     *
     * @param i the
     * @return the binary
     */
    static String getBinary(int i) {
        if (i == 0) {
            return "0";
        }

        StringBuilder binary = new StringBuilder();
        while (i != 0) {
            int remainder = i % 2;
            binary.insert(0, remainder);
            i /= 2;
        }

        return binary.toString();
    }

    /**
     * Gets total sum.
     *
     * @param numberPropertiesList the number properties list
     * @return the total sum
     */
    static int getTotalSum(Integer[] numberPropertiesList) {
        int tot=0;
        for (Integer i:numberPropertiesList) {
            tot+=i;
        }
        return tot;
    }

    /**
     * Gets partial sum.
     *
     * @param binary               the binary
     * @param numberPropertiesList the number properties list
     * @return the partial sum
     */
    static int getPartialSum(String binary, Integer[] numberPropertiesList) {
        int[] list = new int[binary.replace("0","").length()];
        binary = new StringBuilder(binary).reverse().toString();
        int j=0;
        int e =0;
        for (char c: binary.toCharArray()) {
            if (c == '1') {
                list[j] = numberPropertiesList[e];
                j++;
            }
            e++;
        }
        int tot=0;
        for (Integer i:list) {
            tot+=i;
        }
        return tot;
    }

    /**
     * Gets elapsed time.
     *
     * @return the elapsed time
     */
    public static long getElapsedTime() {
        long currentTime = System.currentTimeMillis();
        return currentTime - startTime;
    }

}
