package pt.ipp.isep.dei.esoft.project.domain;

/**
 * The type Insertion sort.
 */
public class InsertionSortAlgorithm {
    /**
     * This method sort the value of all property's area.
     * @param arr array of all property's area
     * @return array of property's area sorted
     */
    public static double[] sort(double[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            double key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }
}
