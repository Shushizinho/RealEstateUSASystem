package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class InsertionSort implements SortingAlgorithm{
    @Override
    public void sort(List<VisitRequest> visitRequestList) {
        int n = visitRequestList.size();
        for (int i = 1; i < n; ++i) {
            VisitRequest key = visitRequestList.get(i);
            int j = i - 1;

            while (j >= 0 && visitRequestList.get(j).getPreferredDate().isBefore(key.getPreferredDate())) {
                visitRequestList.set(j + 1, visitRequestList.get(j));
                j = j - 1;
            }
            visitRequestList.set(j + 1, key);
        }
    }













}
