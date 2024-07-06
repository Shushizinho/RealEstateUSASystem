package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class BubbleSort  implements SortingAlgorithm{


    @Override
    public void sort(List<VisitRequest> visitRequests) {

        int n = visitRequests.size();
        int i, j;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (visitRequests.get(j).getPreferredDate().isBefore(visitRequests.get(j + 1).getPreferredDate())){


                    // Swap arr[j] and arr[j+1]
                    VisitRequest  temp = visitRequests.get(j);
                    visitRequests.set(j, visitRequests.get(j + 1));
                    visitRequests.set(j + 1, temp);
                    swapped = true;
                }
            }

            // If no two elements were
            // swapped by inner loop, then break
            if (swapped == false)
                break;
        }
    }

    }

