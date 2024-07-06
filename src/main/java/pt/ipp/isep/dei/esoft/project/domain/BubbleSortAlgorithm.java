package pt.ipp.isep.dei.esoft.project.domain;

public class BubbleSortAlgorithm {
    /**
     * This method sort the value of all property's area.
     * @param arr array of all property's area
     * @return array of property's area sorted
     */
    public static double[] sort(double[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    double aux = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = aux;
                }
            }
        }
        return arr;

    }
}